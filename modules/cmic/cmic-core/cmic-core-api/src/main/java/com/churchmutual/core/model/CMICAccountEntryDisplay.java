package com.churchmutual.core.model;

import com.churchmutual.core.service.CMICAccountEntryLocalServiceUtil;

import com.churchmutual.core.service.CMICPolicyLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.util.List;
import java.util.stream.Collectors;

public class CMICAccountEntryDisplay {

	public CMICAccountEntryDisplay(CMICAccountEntry cmicAccountEntry) {
		_accountEntryId = cmicAccountEntry.getAccountEntryId();
		_accountNumber = cmicAccountEntry.getAccountNumber();
		_cmicAccountEntryId = cmicAccountEntry.getCmicAccountEntryId();
		_companyNumber = cmicAccountEntry.getCompanyNumber();
		_numExpiredPolicies = cmicAccountEntry.getNumExpiredPolicies();
		_numFuturePolicies = cmicAccountEntry.getNumFuturePolicies();
		_numInForcePolicies = cmicAccountEntry.getNumInForcePolicies();
		_totalBilledPremium = cmicAccountEntry.getTotalBilledPremium();

		try {
			_accountName = CMICAccountEntryLocalServiceUtil.getAccountEntryName(cmicAccountEntry);

			List<CMICPolicyDisplay> cmicPolicyDisplays = CMICPolicyLocalServiceUtil.getPolicyDisplays(cmicAccountEntry.getCmicAccountEntryId());

			List<String> policyNumbers = cmicPolicyDisplays.stream().map(cmicPolicyDisplay -> cmicPolicyDisplay.getPolicyNumber()).collect(Collectors.toList());

			_policyNumbers = StringUtil.merge(policyNumbers, StringPool.COMMA);

			_producerCode = CMICAccountEntryLocalServiceUtil.getProducerCode(cmicAccountEntry);
			_producerName = CMICAccountEntryLocalServiceUtil.getOrganizationName(cmicAccountEntry);
		}
		catch (PortalException pe) {
			_log.error(pe);
		}
	}

	public long getAccountEntryId() {
		return _accountEntryId;
	}

	public String getAccountName() {
		return _accountName;
	}

	public String getAccountNumber() {
		return _accountNumber;
	}

	public long getCmicAccountEntryId() {
		return _cmicAccountEntryId;
	}

	public String getCompanyNumber() {
		return _companyNumber;
	}

	public int getNumExpiredPolicies() {
		return _numExpiredPolicies;
	}

	public int getNumFuturePolicies() {
		return _numFuturePolicies;
	}

	public int getNumInForcePolicies() {
		return _numInForcePolicies;
	}

	public String getPolicyNumbers() {
		return _policyNumbers;
	}

	public String getProducerCode() {
		return _producerCode;
	}

	public String getProducerName() {
		return _producerName;
	}

	public String getTotalBilledPremium() {
		return _totalBilledPremium;
	}

	private static Log _log = LogFactoryUtil.getLog(CMICAccountEntryDisplay.class);

	private long _accountEntryId;
	private String _accountName;
	private String _accountNumber;
	private long _cmicAccountEntryId;
	private String _companyNumber;
	private int _numExpiredPolicies;
	private int _numFuturePolicies;
	private int _numInForcePolicies;
	private String _policyNumbers;
	private String _producerCode;
	private String _producerName;
	private String _totalBilledPremium;

}