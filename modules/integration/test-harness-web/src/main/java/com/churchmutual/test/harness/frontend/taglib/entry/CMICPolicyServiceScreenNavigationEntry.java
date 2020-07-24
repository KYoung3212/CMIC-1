package com.churchmutual.test.harness.frontend.taglib.entry;

import com.churchmutual.rest.PolicyWebService;
import com.churchmutual.rest.model.CMICPolicyAccountSummaryDTO;
import com.churchmutual.rest.model.CMICTransactionDTO;
import com.churchmutual.rest.model.CMICPolicyDTO;
import com.churchmutual.test.harness.constants.TestHarnessConstants;
import com.churchmutual.test.harness.model.HarnessDescriptor;

import com.liferay.frontend.taglib.servlet.taglib.ScreenNavigationEntry;
import com.liferay.frontend.taglib.servlet.taglib.util.JSPRenderer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Http;
import com.liferay.portal.kernel.util.ListUtil;
import com.liferay.portal.kernel.util.ParamUtil;

import java.util.List;
import java.util.Locale;

import javax.portlet.PortletRequest;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kayleen Lim
 */
@Component(
	immediate = true, property = "screen.navigation.entry.order:Integer=40", service = ScreenNavigationEntry.class
)
public class CMICPolicyServiceScreenNavigationEntry extends BaseTestHarnessScreenNavigationEntry {

	@Override
	public String getCategoryKey() {
		return TestHarnessConstants.CATEGORY_KEY_CHURCH_MUTUAL;
	}

	@Override
	public String getEntryKey() {
		return TestHarnessConstants.ENTRY_KEY_POLICY_SERVICE;
	}

	@Override
	public List<HarnessDescriptor> getHarnessDescriptors() {

		HarnessDescriptor getPoliciesByPolicyNumbersDescriptor = new HarnessDescriptor(
				"Get each policy specified",
				_GET_POLICIES_BY_POLICY_NUMBERS_ENDPOINT, Http.Method.GET);

		HarnessDescriptor.Parameter combinedPolicyArrayNumber = new HarnessDescriptor.Parameter(
				"combinedPolicyNumber", "combinedPolicyNumber", true,
				new String[] {"0000015 06-008765", "0000015 07-000054", "0000015 07-000055"}, "String[]");

		getPoliciesByPolicyNumbersDescriptor.addQueryParameters(combinedPolicyArrayNumber);

		HarnessDescriptor getPoliciesOnAccountDescriptor = new HarnessDescriptor(
				"Get all policies on an account",
				_GET_POLICIES_ON_ACCOUNT_ENDPOINT, Http.Method.GET);

		HarnessDescriptor.Parameter accountNumber = new HarnessDescriptor.Parameter(
				"accountNumber", "accountNumber", true, "0000015", String.class.getName());

		getPoliciesOnAccountDescriptor.addQueryParameters(accountNumber);

		HarnessDescriptor getPolicyAccountSummariesByAccountsDescriptor = new HarnessDescriptor(
				"Get a summary of all policies on an account",
				_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_ENDPOINT, Http.Method.GET);

		HarnessDescriptor.Parameter accountNumberArray = new HarnessDescriptor.Parameter(
				"accountNumber", "accountNumber", true, new String[] {"0000015", "0017522"}, "String[]");

		getPolicyAccountSummariesByAccountsDescriptor.addQueryParameters(accountNumberArray);

		HarnessDescriptor getTransactionDescriptor = new HarnessDescriptor(
			"Get a specific transaction on a policy", _GET_TRANSACTION_ENDPOINT, Http.Method.GET);

		HarnessDescriptor.Parameter combinedPolicyNumber = new HarnessDescriptor.Parameter(
			"combinedPolicyNumber", "combinedPolicyNumber", true, "0000015 06-008765", String.class.getName());

		HarnessDescriptor.Parameter sequenceNumber = new HarnessDescriptor.Parameter(
			"sequenceNumber", "sequenceNumber", true, 1, Integer.class.getName());

		getTransactionDescriptor.addQueryParameters(combinedPolicyNumber, sequenceNumber);

		HarnessDescriptor getTransactionsOnPolicyDescriptor = new HarnessDescriptor(
			"Get all transactions on a policy", _GET_TRANSACTIONS_ON_POLICY_ENDPOINT, Http.Method.GET);

		getTransactionsOnPolicyDescriptor.addQueryParameters(combinedPolicyNumber);

		return ListUtil.fromArray(
			getPoliciesByPolicyNumbersDescriptor, getPoliciesOnAccountDescriptor,
			getPolicyAccountSummariesByAccountsDescriptor, getTransactionDescriptor, getTransactionsOnPolicyDescriptor);
	}

	@Override
	public JSPRenderer getJSPRenderer() {
		return _jspRenderer;
	}

	@Override
	public String getLabel(Locale locale) {
		return LanguageUtil.get(getResourceBundle(locale), "policy-service");
	}

	@Override
	public String getScreenNavigationKey() {
		return TestHarnessConstants.SCREEN_NAVIGATION_KEY;
	}

	@Override
	public String invoke(PortletRequest portletRequest) {
		String endpoint = ParamUtil.getString(portletRequest, "endpoint");

		JSONArray response = JSONFactoryUtil.createJSONArray();

		try {
			if (_GET_TRANSACTION_ENDPOINT.equals(endpoint)) {
				String combinedPolicyNumber = ParamUtil.getString(portletRequest, "combinedPolicyNumber");
				int sequenceNumber = ParamUtil.getInteger(portletRequest, "sequenceNumber");

				CMICTransactionDTO transaction = _policyWebService.getTransaction(
					combinedPolicyNumber, sequenceNumber);

				response.put(transaction.toJSONObject());
			}
			else if (_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_ENDPOINT.equals(endpoint)) {
				String[] accountNumber = ParamUtil.getStringValues(portletRequest, "accountNumber");

				List<CMICPolicyAccountSummaryDTO> policyAccountSummaries =
					_policyWebService.getPolicyAccountSummariesByAccounts(accountNumber);

				policyAccountSummaries.forEach(
					policyAccountSummary -> response.put(policyAccountSummary.toJSONObject()));
			}
			else if (_GET_TRANSACTIONS_ON_POLICY_ENDPOINT.equals(endpoint)) {
				String combinedPolicyNumber = ParamUtil.getString(portletRequest, "combinedPolicyNumber");

				List<CMICTransactionDTO> transactions = _policyWebService.getTransactionsOnPolicy(
					combinedPolicyNumber);

				transactions.forEach(transaction -> response.put(transaction.toJSONObject()));
			}
			else if (_GET_POLICIES_BY_POLICY_NUMBERS_ENDPOINT.equals(endpoint)) {
				String[] combinedPolicyNumber = ParamUtil.getStringValues(portletRequest, "combinedPolicyNumber");

				List<CMICPolicyDTO> policies =
					_policyWebService.getPoliciesByPolicyNumbers(combinedPolicyNumber);

				policies.forEach(
						cmicPolicyDTO -> response.put(cmicPolicyDTO.toJSONObject()));
			}
			else if (_GET_POLICIES_ON_ACCOUNT_ENDPOINT.equals(endpoint)) {
				String accountNumber = ParamUtil.getString(portletRequest, "accountNumber");

				List<CMICPolicyDTO> policies =
					_policyWebService.getPoliciesOnAccount(accountNumber);

				policies.forEach(
						cmicPolicyDTO -> response.put(cmicPolicyDTO.toJSONObject()));
			}
		}
		catch (PortalException pe) {
			response.put(pe.getMessage());

			if (_log.isErrorEnabled()) {
				_log.error("Could not get response for " + endpoint, pe);
			}
		}

		return response.toString();
	}

	private static final String _GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_ENDPOINT =
		"/v1/policy-summaries/on-account";

	private static final String _GET_TRANSACTION_ENDPOINT = "/v1/transactions";

	private static final String _GET_TRANSACTIONS_ON_POLICY_ENDPOINT = "/v1/transactions/on-policy";

	private static final String _GET_POLICIES_BY_POLICY_NUMBERS_ENDPOINT =
		"/v1/policies";

	private static final String _GET_POLICIES_ON_ACCOUNT_ENDPOINT =
		"/v1/policies/on-account";

	private static final Log _log = LogFactoryUtil.getLog(CMICProducerServiceScreenNavigationEntry.class);

	@Reference
	private JSPRenderer _jspRenderer;

	@Reference
	private PolicyWebService _policyWebService;

}