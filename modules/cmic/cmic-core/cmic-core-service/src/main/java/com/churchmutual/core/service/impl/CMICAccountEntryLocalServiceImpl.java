/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.churchmutual.core.service.impl;

import com.churchmutual.commons.util.CollectionsUtil;
import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.CMICAccountEntryDisplay;
import com.churchmutual.core.model.CMICOrganization;
import com.churchmutual.core.service.CMICOrganizationLocalService;
import com.churchmutual.core.service.base.CMICAccountEntryLocalServiceBaseImpl;
import com.churchmutual.rest.AccountWebService;
import com.churchmutual.rest.PolicyWebService;
import com.churchmutual.rest.ProducerWebService;
import com.churchmutual.rest.model.CMICPolicyAccountSummaryDTO;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryOrganizationRel;
import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryOrganizationRelLocalService;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.account.service.business.AccountEntryBusinessService;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the cmic account entry local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.churchmutual.core.service.CMICAccountEntryLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.churchmutual.core.model.CMICAccountEntry", service = AopService.class)
public class CMICAccountEntryLocalServiceImpl extends CMICAccountEntryLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>com.churchmutual.core.service.CMICAccountEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>com.churchmutual.core.service.CMICAccountEntryLocalServiceUtil</code>.
	 */
	@Override
	public CMICAccountEntry addOrUpdateCMICAccountEntry(
			long userId, String accountNumber, String companyNumber, String accountName, long producerId,
			String producerCode)
		throws PortalException {

		CMICAccountEntry cmicAccountEntry = cmicAccountEntryPersistence.fetchByAN_CN(accountNumber, companyNumber);

		CMICOrganization cmicOrganization = cmicOrganizationPersistence.findByProducerId(producerId);

		AccountEntry accountEntry = null;

		if (cmicAccountEntry == null) {
			long cmicAccountEntryId = counterLocalService.increment(CMICAccountEntry.class.getName());

			cmicAccountEntry = createCMICAccountEntry(cmicAccountEntryId);

			accountEntry = accountEntryBusinessService.createAccountEntry(
				userId, accountName, userId, cmicOrganization.getOrganizationId());

			cmicAccountEntry.setAccountEntryId(accountEntry.getAccountEntryId());
			cmicAccountEntry.setAccountNumber(accountNumber);
			cmicAccountEntry.setCompanyNumber(companyNumber);
		}
		else {
			accountEntry = accountEntryLocalService.getAccountEntry(cmicAccountEntry.getAccountEntryId());

			accountEntry.setName(accountName);

			accountEntryLocalService.updateAccountEntry(accountEntry);

			List<AccountEntryOrganizationRel> accountEntryOrganizationRels =
				accountEntryOrganizationRelLocalService.getAccountEntryOrganizationRels(
					accountEntry.getAccountEntryId());

			for (AccountEntryOrganizationRel accountEntryOrganizationRel : accountEntryOrganizationRels) {
				accountEntryOrganizationRelLocalService.deleteAccountEntryOrganizationRel(accountEntryOrganizationRel);
			}

			accountEntryOrganizationRelLocalService.addAccountEntryOrganizationRel(
				accountEntry.getAccountEntryId(), cmicOrganization.getOrganizationId());
		}

		return cmicAccountEntryPersistence.update(cmicAccountEntry);
	}

	@Override
	public CMICAccountEntry fetchCMICAccountEntry(String accountNumber, String companyNumber) {
		return cmicAccountEntryPersistence.fetchByAN_CN(accountNumber, companyNumber);
	}

	@Override
	public String getAccountEntryName(CMICAccountEntry cmicAccountEntry) throws PortalException {
		AccountEntry accountEntry = accountEntryLocalService.getAccountEntry(cmicAccountEntry.getAccountEntryId());

		return accountEntry.getName();
	}

	@Override
	public List<CMICAccountEntry> getCMICAccountEntriesByUserId(long userId) {
		List<AccountEntryUserRel> accountEntryUserRels =
			accountEntryUserRelLocalService.getAccountEntryUserRelsByAccountUserId(userId);

		return accountEntryUserRels.stream(
		).map(
			accountEntryUserRel -> cmicAccountEntryPersistence.fetchByAccountEntryId(
				accountEntryUserRel.getAccountEntryId())
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);
	}

	@Override
	public List<CMICAccountEntry> getCMICAccountEntriesByUserIdOrderedByName(long userId, int start, int end) {
		return cmicAccountEntryFinder.findByUserIdOrderedByName(userId, start, end);
	}

	@Override
	public CMICAccountEntry getCMICAccountEntry(String accountNumber, String companyNumber) throws PortalException {
		return cmicAccountEntryPersistence.findByAN_CN(accountNumber, companyNumber);
	}

	@Override
	public CMICAccountEntryDisplay getCMICAccountEntryDisplay(String cmicAccountEntryId) throws PortalException {
		CMICAccountEntry cmicAccountEntry = cmicAccountEntryPersistence.findByPrimaryKey(
			GetterUtil.getLong(cmicAccountEntryId));

		return new CMICAccountEntryDisplay(cmicAccountEntry);
	}

	@Override
	public List<CMICAccountEntryDisplay> getCMICAccountEntryDisplays(List<String> cmicAccountEntryIds) {
		List<CMICAccountEntryDisplay> cmicAccountEntryDisplays = new ArrayList<>();

		for (String cmicAccountEntryId : cmicAccountEntryIds) {
			CMICAccountEntry cmicAccountEntry = cmicAccountEntryPersistence.fetchByPrimaryKey(
				GetterUtil.getLong(cmicAccountEntryId));

			if (cmicAccountEntry != null) {
				cmicAccountEntryDisplays.add(new CMICAccountEntryDisplay(cmicAccountEntry));
			}
		}

		return cmicAccountEntryDisplays;
	}

	@Override
	public List<CMICAccountEntryDisplay> getCMICAccountEntryDisplays(long userId) {
		List<CMICAccountEntry> cmicAccountEntries = getCMICAccountEntriesByUserId(userId);

		List<CMICAccountEntryDisplay> cmicAccountEntryDisplays = new ArrayList<>();

		for (CMICAccountEntry cmicAccountEntry : cmicAccountEntries) {
			cmicAccountEntryDisplays.add(new CMICAccountEntryDisplay(cmicAccountEntry));
		}

		return cmicAccountEntryDisplays;
	}

	@Override
	public String getOrganizationName(CMICAccountEntry cmicAccountEntry) throws PortalException {
		List<AccountEntryOrganizationRel> accountEntryOrganizationRels =
			accountEntryOrganizationRelLocalService.getAccountEntryOrganizationRels(
				cmicAccountEntry.getAccountEntryId());

		AccountEntryOrganizationRel accountEntryOrganizationRel = CollectionsUtil.getFirst(
			accountEntryOrganizationRels);

		if (accountEntryOrganizationRel == null) {
			throw new PortalException(
				String.format(
					"Account Entry %d is not related to an organization", cmicAccountEntry.getAccountEntryId()));
		}

		Organization organization = organizationLocalService.getOrganization(
			accountEntryOrganizationRel.getOrganizationId());

		return organization.getName();
	}

	@Override
	public String getProducerCode(CMICAccountEntry cmicAccountEntry) throws PortalException {
		long companyId = PortalUtil.getDefaultCompanyId();

		Organization organization = organizationLocalService.getOrganization(
			companyId, getOrganizationName(cmicAccountEntry));

		CMICOrganization cmicOrganization = cmicOrganizationPersistence.findByOrganizationId(
			organization.getOrganizationId());

		String divisionNumber = cmicOrganization.getDivisionNumber();
		String agentNumber = cmicOrganization.getAgentNumber();

		return divisionNumber + agentNumber;
	}

	@Override
	public void updateCMICAccountEntryDetails(List<CMICAccountEntry> cmicAccountEntries) throws PortalException {
		int newUserAccountEntriesSize = cmicAccountEntries.size();

		int startIndex = 0;

		do {
			int endIndex = startIndex + _UPDATE_ACCOUNT_ENTRY_BATCH_SIZE;

			if (newUserAccountEntriesSize < endIndex) {
				endIndex = newUserAccountEntriesSize;
			}

			List<CMICAccountEntry> cmicAccountEntrySublist = cmicAccountEntries.subList(startIndex, endIndex);

			_updateCMICAccountEntryDetails(cmicAccountEntrySublist);

			startIndex = startIndex + _UPDATE_ACCOUNT_ENTRY_BATCH_SIZE;
		}
		while (startIndex < newUserAccountEntriesSize);
	}

	@Reference
	protected AccountEntryBusinessService accountEntryBusinessService;

	@Reference
	protected AccountEntryLocalService accountEntryLocalService;

	@Reference
	protected AccountEntryOrganizationRelLocalService accountEntryOrganizationRelLocalService;

	@Reference
	protected AccountEntryUserRelLocalService accountEntryUserRelLocalService;

	@Reference
	protected AccountWebService accountWebService;

	@Reference
	protected CMICOrganizationLocalService cmicOrganizationLocalService;

	@Reference
	protected OrganizationLocalService organizationLocalService;

	@Reference
	protected PolicyWebService policyWebService;

	@Reference
	protected ProducerWebService producerWebService;

	private void _updateCMICAccountEntryDetails(List<CMICAccountEntry> cmicAccountEntries) throws PortalException {
		String[] accountNumbers = cmicAccountEntries.stream(
		).map(
			cmicAccountEntry -> cmicAccountEntry.getAccountNumber()
		).toArray(
			String[]::new
		);

		List<CMICPolicyAccountSummaryDTO> cmicPolicyAccountSummaryDTOs =
			policyWebService.getPolicyAccountSummariesByAccounts(accountNumbers);

		for (CMICPolicyAccountSummaryDTO cmicPolicyAccountSummaryDTO : cmicPolicyAccountSummaryDTOs) {
			CMICAccountEntry cmicAccountEntry = getCMICAccountEntry(
				cmicPolicyAccountSummaryDTO.getAccountNumber(), cmicPolicyAccountSummaryDTO.getCompanyNumber());

			cmicAccountEntry.setNumExpiredPolicies(cmicPolicyAccountSummaryDTO.getNumExpiredPolicies());
			cmicAccountEntry.setNumFuturePolicies(cmicPolicyAccountSummaryDTO.getNumFuturePolicies());
			cmicAccountEntry.setNumInForcePolicies(cmicPolicyAccountSummaryDTO.getNumInForcePolicies());
			cmicAccountEntry.setTotalBilledPremium(
				cmicPolicyAccountSummaryDTO.getTotalBilledPremium(
				).toString());

			cmicAccountEntryPersistence.update(cmicAccountEntry);
		}
	}

	private static final int _UPDATE_ACCOUNT_ENTRY_BATCH_SIZE = 50;

}