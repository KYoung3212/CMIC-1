package com.churchmutual.rest.model;

/**
 * @author Kayleen Lim
 */
public class CMICUserRelationDTO extends CMICObjectDTO {

	public String getAccountNumber() {
		return _accountNumber;
	}

	public String getAgentNumber() {
		return _agentNumber;
	}

	public String getCompanyNumber() {
		return _companyNumber;
	}

	public String getDivisionNumber() {
		return _divisionNumber;
	}

	public long getProducerId() {
		return _producerId;
	}

	public boolean isProducer() {
		if (_producerId > 0) {
			return true;
		}

		return false;
	}

	public void setAccountNumber(String accountNumber) {
		_accountNumber = accountNumber;
	}

	public void setAgentNumber(String agentNumber) {
		_agentNumber = agentNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		_companyNumber = companyNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		_divisionNumber = divisionNumber;
	}

	public void setProducerId(long producerId) {
		_producerId = producerId;
	}

	private String _accountNumber;
	private String _agentNumber;
	private String _companyNumber;
	private String _divisionNumber;
	private long _producerId;

}