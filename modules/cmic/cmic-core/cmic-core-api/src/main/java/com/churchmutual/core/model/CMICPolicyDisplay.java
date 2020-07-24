package com.churchmutual.core.model;

import com.churchmutual.rest.model.CMICPolicyDTO;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CMICPolicyDisplay {

	public CMICPolicyDisplay(CMICPolicyDTO cmicPolicyDTO) {
		_effectiveDate = _getFormattedPolicyDate(cmicPolicyDTO.getPolicyEffectiveDate());
		_expirationDate = _getFormattedPolicyDate(cmicPolicyDTO.getPolicyExpirationDate());
		_policyNumber = cmicPolicyDTO.getPolicyNumber();
		_policyType = cmicPolicyDTO.getPolicyType();

		BigDecimal totalBilledPremium = cmicPolicyDTO.getTotalBilledPremium();

		_totalBilledPremium = totalBilledPremium != null ? totalBilledPremium.toString() : "0";

		_numTransactions = 0;
	}

	public String getEffectiveDate() {
		return _effectiveDate;
	}

	public String getExpirationDate() {
		return _expirationDate;
	}

	public long getNumTransactions() {
		return _numTransactions;
	}

	public String getPolicyNumber() {
		return _policyNumber;
	}

	public String getPolicyType() {
		return _policyType;
	}

	public String getTotalBilledPremium() {
		return _totalBilledPremium;
	}

	private String _getFormattedPolicyDate(String date) {
		SimpleDateFormat format1 = new SimpleDateFormat(_FORMAT_YYYY_MM_DD);
		SimpleDateFormat format2 = new SimpleDateFormat(_FORMAT_MM_DD_YYYY);

		try {
			Date statementDate = format1.parse(date);

			return format2.format(statementDate);
		}
		catch (Exception e) {
			_log.warn(e);

			return date;
		}
	}

	private static final String _FORMAT_MM_DD_YYYY = "MM-dd-yyyy";

	private static final String _FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

	private static final Log _log = LogFactoryUtil.getLog(CMICPolicyDisplay.class);

	private String _effectiveDate;
	private String _expirationDate;
	private long _numTransactions;
	private String _policyNumber;
	private String _policyType;
	private String _totalBilledPremium;

}