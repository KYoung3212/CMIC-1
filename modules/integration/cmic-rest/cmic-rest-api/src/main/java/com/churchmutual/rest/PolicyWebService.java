package com.churchmutual.rest;

import com.churchmutual.rest.model.CMICPolicyAccountSummaryDTO;
import com.churchmutual.rest.model.CMICPolicyDTO;
import com.churchmutual.rest.model.CMICTransactionDTO;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Kayleen Lim
 */
public interface PolicyWebService {

	public List<CMICPolicyDTO> getPoliciesByPolicyNumbers(String[] combinedPolicyNumber) throws PortalException;

	public List<CMICPolicyDTO> getPoliciesOnAccount(String accountNumber) throws PortalException;

	public List<CMICPolicyAccountSummaryDTO> getPolicyAccountSummariesByAccounts(String[] accountNumber)
		throws PortalException;

	public CMICTransactionDTO getTransaction(String combinedPolicyNumber, int sequenceNumber) throws PortalException;

	public List<CMICTransactionDTO> getTransactionsOnPolicy(String combinedPolicyNumber) throws PortalException;

}