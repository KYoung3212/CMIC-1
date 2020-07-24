package com.churchmutual.rest.model;

/**
 * @author Kayleen Lim
 */
public class CMICAccountDTO extends CMICObjectDTO {

	public String getAccountName() {
		return _accountName;
	}

	public String getAccountNumber() {
		return _accountNumber;
	}

	public String getCompanyNumber() {
		return _companyNumber;
	}

	public String getProducerCode() {
		return _producerCode;
	}

	public String getServicingProducerCode() {
		return _servicingProducerCode;
	}

	public void setAccountName(String accountName) {
		_accountName = accountName;
	}

	public void setAccountNumber(String accountNumber) {
		_accountNumber = accountNumber;
	}

	public void setCompanyNumber(String companyNumber) {
		_companyNumber = companyNumber;
	}

	public void setProducerCode(String producerCode) {
		_producerCode = producerCode;
	}

	public void setServicingProducerCode(String servicingProducerCode) {
		_servicingProducerCode = servicingProducerCode;
	}

	private String _accountName;
	private String _accountNumber;
	private String _companyNumber;
	private String _producerCode;
	private String _servicingProducerCode;

}