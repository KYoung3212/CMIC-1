package com.churchmutual.rest.service;

import com.churchmutual.portal.ws.commons.client.executor.WebServiceExecutor;
import com.churchmutual.rest.PolicyWebService;
import com.churchmutual.rest.configuration.MockPolicyWebServiceConfiguration;
import com.churchmutual.rest.model.CMICPolicyAccountSummaryDTO;
import com.churchmutual.rest.model.CMICPolicyDTO;
import com.churchmutual.rest.model.CMICTransactionDTO;
import com.churchmutual.rest.service.mock.MockPolicyWebServiceClient;

import com.liferay.petra.lang.HashUtil;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.cache.PortalCache;
import com.liferay.portal.kernel.cache.SingleVMPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONDeserializer;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.util.ListUtil;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kayleen Lim
 */
@Component(
	configurationPid = "com.churchmutual.rest.configuration.MockPolicyWebServiceConfiguration", immediate = true,
	service = PolicyWebService.class
)
public class PolicyWebServiceImpl implements PolicyWebService {

	@Deactivate
	public void deactivate() {
		_singleVMPool.removePortalCache(_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_CACHE_NAME);
		_singleVMPool.removePortalCache(_GET_TRANSACTION_CACHE_NAME);
		_singleVMPool.removePortalCache(_GET_TRANSACTIONS_ON_POLICY_CACHE_NAME);
		_singleVMPool.removePortalCache(_GET_POLICIES_BY_POLICY_NUMBERS_CACHE_NAME);
		_singleVMPool.removePortalCache(_GET_POLICIES_ON_ACCOUNT_CACHE_NAME);
	}

	@Override
	public List<CMICPolicyDTO> getPoliciesByPolicyNumbers(String[] combinedPolicyNumber) throws PortalException {
		if (_mockPolicyWebServiceConfiguration.enableMockGetPoliciesByPolicyNumbers()) {
			return _mockPolicyWebServiceClient.getPoliciesByPolicyNumbers(combinedPolicyNumber);
		}

		CombinedPolicyNumberKey key = new CombinedPolicyNumberKey(combinedPolicyNumber);

		List<CMICPolicyDTO> cache = _getPoliciesByPolicyNumbersPortalCache.get(key);

		if (cache != null) {
			return cache;
		}

		Map<String, String[]> repeatedQueryParameters = new HashMap<>();

		repeatedQueryParameters.put("combinedPolicyNumber", combinedPolicyNumber);

		String response = _webServiceExecutor.executeGetWithRepeatedQueryParameters(
			_GET_POLICIES_BY_POLICY_NUMBERS_URL, repeatedQueryParameters);

		JSONDeserializer<CMICPolicyDTO[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		List<CMICPolicyDTO> list = new ArrayList();

		try {
			CMICPolicyDTO[] results = jsonDeserializer.deserialize(response, CMICPolicyDTO[].class);

			Collections.addAll(list, results);
		}
		catch (Exception e) {
		}

		_getPoliciesByPolicyNumbersPortalCache.put(key, list);

		return list;
	}

	@Override
	public List<CMICPolicyDTO> getPoliciesOnAccount(String accountNumber) throws PortalException {
		if (_mockPolicyWebServiceConfiguration.enableMockGetPoliciesOnAccount()) {
			return _mockPolicyWebServiceClient.getPoliciesOnAccount(accountNumber);
		}

		AccountNumbersKey key = new AccountNumbersKey(accountNumber);

		List<CMICPolicyDTO> cache = _getPoliciesOnAccountPortalCache.get(key);

		if (cache != null) {
			return cache;
		}

		Map<String, String> queryParameters = new HashMap<>();

		queryParameters.put("accountNumber", accountNumber);

		String response = _webServiceExecutor.executeGet(_GET_POLICIES_ON_ACCOUNT_URL, queryParameters);

		JSONDeserializer<CMICPolicyDTO[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		List<CMICPolicyDTO> list = new ArrayList();

		try {
			CMICPolicyDTO[] results = jsonDeserializer.deserialize(response, CMICPolicyDTO[].class);

			Collections.addAll(list, results);
		}
		catch (Exception e) {
		}

		_getPoliciesOnAccountPortalCache.put(key, list);

		return list;
	}

	@Override
	public List<CMICPolicyAccountSummaryDTO> getPolicyAccountSummariesByAccounts(String[] accountNumber)
		throws PortalException {

		if (_mockPolicyWebServiceConfiguration.enableMockGetPolicyAccountSummariesByAccounts()) {
			return _mockPolicyWebServiceClient.getPolicyAccountSummariesByAccounts(accountNumber);
		}

		AccountNumbersKey key = new AccountNumbersKey(accountNumber);

		List<CMICPolicyAccountSummaryDTO> cache = _getPolicyAccountSummariesByAccountsPortalCache.get(key);

		if (cache != null) {
			return cache;
		}

		Map<String, String[]> repeatedQueryParameters = new HashMap<>();

		repeatedQueryParameters.put("accountNumber", accountNumber);

		String response = _webServiceExecutor.executeGetWithRepeatedQueryParameters(
			_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_URL, repeatedQueryParameters);

		JSONDeserializer<CMICPolicyAccountSummaryDTO[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		List<CMICPolicyAccountSummaryDTO> list = new ArrayList();

		try {
			CMICPolicyAccountSummaryDTO[] results = jsonDeserializer.deserialize(
				response, CMICPolicyAccountSummaryDTO[].class);

			Collections.addAll(list, results);
		}
		catch (Exception e) {
		}

		_getPolicyAccountSummariesByAccountsPortalCache.put(key, list);

		return list;
	}

	@Override
	public CMICTransactionDTO getTransaction(String combinedPolicyNumber, int sequenceNumber) throws PortalException {
		if (_mockPolicyWebServiceConfiguration.enableMockGetTransaction()) {
			return _mockPolicyWebServiceClient.getTransaction(combinedPolicyNumber, sequenceNumber);
		}

		CombinedPolicyNumberAndSequenceNumberKey key = new CombinedPolicyNumberAndSequenceNumberKey(
			combinedPolicyNumber, sequenceNumber);

		CMICTransactionDTO cache = _getTransactionPortalCache.get(key);

		if (cache != null) {
			return cache;
		}

		Map<String, String> queryParameters = new HashMap<>();

		queryParameters.put("combinedPolicyNumber", combinedPolicyNumber);
		queryParameters.put("sequenceNumber", String.valueOf(sequenceNumber));

		String response = _webServiceExecutor.executeGet(_GET_TRANSACTION_URL, queryParameters);

		JSONDeserializer<CMICTransactionDTO> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		CMICTransactionDTO transaction = null;

		try {
			transaction = jsonDeserializer.deserialize(response, CMICTransactionDTO.class);
		}
		catch (Exception e) {
			throw new PortalException(
				String.format(
					"Transaction with combinedPolicyNumber %s and sequenceNumber %s could not be found",
					combinedPolicyNumber, sequenceNumber),
				e);
		}

		_getTransactionPortalCache.put(key, transaction);

		return transaction;
	}

	@Override
	public List<CMICTransactionDTO> getTransactionsOnPolicy(String combinedPolicyNumber) throws PortalException {
		if (_mockPolicyWebServiceConfiguration.enableMockGetTransactionsOnPolicy()) {
			return _mockPolicyWebServiceClient.getTransactionsOnPolicy(combinedPolicyNumber);
		}

		CombinedPolicyNumberKey key = new CombinedPolicyNumberKey(combinedPolicyNumber);

		List<CMICTransactionDTO> cache = _getTransactionsOnPolicyPortalCache.get(key);

		if (cache != null) {
			return cache;
		}

		Map<String, String> queryParameters = new HashMap<>();

		queryParameters.put("combinedPolicyNumber", combinedPolicyNumber);

		String response = _webServiceExecutor.executeGet(_GET_TRANSACTIONS_ON_POLICY_URL, queryParameters);

		JSONDeserializer<CMICTransactionDTO[]> jsonDeserializer = _jsonFactory.createJSONDeserializer();

		List<CMICTransactionDTO> list = new ArrayList();

		try {
			CMICTransactionDTO[] results = jsonDeserializer.deserialize(response, CMICTransactionDTO[].class);

			Collections.addAll(list, results);
		}
		catch (Exception e) {
		}

		_getTransactionsOnPolicyPortalCache.put(key, list);

		return list;
	}

	@Activate
	@Modified
	protected void activate(Map<String, Object> properties) {
		_mockPolicyWebServiceConfiguration = ConfigurableUtil.createConfigurable(
			MockPolicyWebServiceConfiguration.class, properties);

		_getPolicyAccountSummariesByAccountsPortalCache =
			(PortalCache<AccountNumbersKey, List<CMICPolicyAccountSummaryDTO>>)_singleVMPool.getPortalCache(
				_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_CACHE_NAME);
		_getTransactionsOnPolicyPortalCache =
			(PortalCache<CombinedPolicyNumberKey, List<CMICTransactionDTO>>)_singleVMPool.getPortalCache(
				_GET_TRANSACTIONS_ON_POLICY_CACHE_NAME);
		_getPoliciesByPolicyNumbersPortalCache =
			(PortalCache<CombinedPolicyNumberKey, List<CMICPolicyDTO>>)_singleVMPool.getPortalCache(
				_GET_POLICIES_BY_POLICY_NUMBERS_CACHE_NAME);
		_getPoliciesOnAccountPortalCache =
			(PortalCache<AccountNumbersKey, List<CMICPolicyDTO>>)_singleVMPool.getPortalCache(
				_GET_POLICIES_ON_ACCOUNT_CACHE_NAME);
		_getTransactionPortalCache =
			(PortalCache<CombinedPolicyNumberAndSequenceNumberKey, CMICTransactionDTO>)_singleVMPool.getPortalCache(
				_GET_TRANSACTION_CACHE_NAME);
	}

	private static final String _GET_POLICIES_BY_POLICY_NUMBERS_CACHE_NAME =
		PolicyWebServiceImpl.class.getName() + "_GET_POLICIES_BY_POLICY_NUMBERS";

	private static final String _GET_POLICIES_BY_POLICY_NUMBERS_URL = "/policy-service/v1/policies";

	private static final String _GET_POLICIES_ON_ACCOUNT_CACHE_NAME =
		PolicyWebServiceImpl.class.getName() + "_GET_POLICIES_ON_ACCOUNT";

	private static final String _GET_POLICIES_ON_ACCOUNT_URL = "/policy-service/v1/policies/on-account";

	private static final String _GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_CACHE_NAME =
		PolicyWebServiceImpl.class.getName() + "_GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS";

	private static final String _GET_POLICY_ACCOUNT_SUMMARIES_BY_ACCOUNTS_URL =
		"/policy-service/v1/policy-summaries/on-account";

	private static final String _GET_TRANSACTION_CACHE_NAME = PolicyWebServiceImpl.class.getName() + "_GET_TRANSACTION";

	private static final String _GET_TRANSACTION_URL = "/policy-service/v1/transactions";

	private static final String _GET_TRANSACTIONS_ON_POLICY_CACHE_NAME =
		PolicyWebServiceImpl.class.getName() + "_GET_TRANSACTIONS_ON_POLICY";

	private static final String _GET_TRANSACTIONS_ON_POLICY_URL = "/policy-service/v1/transactions/on-policy";

	private PortalCache<CombinedPolicyNumberKey, List<CMICPolicyDTO>> _getPoliciesByPolicyNumbersPortalCache;
	private PortalCache<AccountNumbersKey, List<CMICPolicyDTO>> _getPoliciesOnAccountPortalCache;
	private PortalCache<AccountNumbersKey, List<CMICPolicyAccountSummaryDTO>>
		_getPolicyAccountSummariesByAccountsPortalCache;
	private PortalCache<CombinedPolicyNumberAndSequenceNumberKey, CMICTransactionDTO> _getTransactionPortalCache;
	private PortalCache<CombinedPolicyNumberKey, List<CMICTransactionDTO>> _getTransactionsOnPolicyPortalCache;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private MockPolicyWebServiceClient _mockPolicyWebServiceClient;

	private MockPolicyWebServiceConfiguration _mockPolicyWebServiceConfiguration;

	@Reference
	private SingleVMPool _singleVMPool;

	@Reference
	private WebServiceExecutor _webServiceExecutor;

	private static class AccountNumbersKey implements Serializable {

		@Override
		public boolean equals(Object obj) {
			AccountNumbersKey key = (AccountNumbersKey)obj;

			if (Objects.equals(key._accountNumbers, _accountNumbers)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return HashUtil.hash(0, _accountNumbers);
		}

		private AccountNumbersKey(String accountNumber) {
			_accountNumbers = ListUtil.toList(accountNumber);
		}

		private AccountNumbersKey(String[] accountNumbers) {
			_accountNumbers = ListUtil.fromArray(accountNumbers);
		}

		private static final long serialVersionUID = 1L;

		private final List<String> _accountNumbers;

	}

	private static class CombinedPolicyNumberAndSequenceNumberKey extends CombinedPolicyNumberKey {

		@Override
		public boolean equals(Object obj) {
			CombinedPolicyNumberAndSequenceNumberKey key = (CombinedPolicyNumberAndSequenceNumberKey)obj;

			if (Objects.equals(key._combinedPolicyNumbers, super._combinedPolicyNumbers) &&
				(key._sequenceNumber == _sequenceNumber)) {

				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			int hashCode = HashUtil.hash(0, super._combinedPolicyNumbers);

			return HashUtil.hash(hashCode, _sequenceNumber);
		}

		private CombinedPolicyNumberAndSequenceNumberKey(String combinedPolicyNumber, int sequenceNumber) {
			super(combinedPolicyNumber);

			_sequenceNumber = sequenceNumber;
		}

		private static final long serialVersionUID = 1L;

		private final int _sequenceNumber;

	}

	private static class CombinedPolicyNumberKey implements Serializable {

		@Override
		public boolean equals(Object obj) {
			CombinedPolicyNumberKey key = (CombinedPolicyNumberKey)obj;

			if (Objects.equals(key._combinedPolicyNumbers, _combinedPolicyNumbers)) {
				return true;
			}

			return false;
		}

		@Override
		public int hashCode() {
			return HashUtil.hash(0, _combinedPolicyNumbers);
		}

		protected final List<String> _combinedPolicyNumbers;

		private CombinedPolicyNumberKey(String combinedPolicyNumber) {
			_combinedPolicyNumbers = ListUtil.toList(combinedPolicyNumber);
		}

		private CombinedPolicyNumberKey(String[] combinedPolicyNumbers) {
			_combinedPolicyNumbers = ListUtil.fromArray(combinedPolicyNumbers);
		}

		private static final long serialVersionUID = 1L;

	}

}