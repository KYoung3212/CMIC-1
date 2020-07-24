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

package com.churchmutual.core.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link CMICUserLocalService}.
 *
 * @author Kayleen Lim
 * @see CMICUserLocalService
 * @generated
 */
public class CMICUserLocalServiceWrapper
	implements CMICUserLocalService, ServiceWrapper<CMICUserLocalService> {

	public CMICUserLocalServiceWrapper(
		CMICUserLocalService cmicUserLocalService) {

		_cmicUserLocalService = cmicUserLocalService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CMICUserLocalServiceUtil} to access the cmic user local service. Add custom service methods to <code>com.churchmutual.core.service.impl.CMICUserLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public void addRecentlyViewedCMICAccountEntryId(
			long userId, String cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserLocalService.addRecentlyViewedCMICAccountEntryId(
			userId, cmicAccountEntryId);
	}

	@Override
	public com.liferay.portal.kernel.model.User fetchUserByCmicUUID(
			long companyId, String cmicUUID)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.fetchUserByCmicUUID(companyId, cmicUUID);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getBusinesses(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getBusinesses(userId);
	}

	@Override
	public com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalType(String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getBusinessPortalType(registrationCode);
	}

	@Override
	public com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalTypeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getBusinessPortalTypeByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getBusinessRoles(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getBusinessRoles(groupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
			getCMICOrganizationUsers(long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getCMICOrganizationUsers(
			cmicOrganizationId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICUserDisplay>
			getGroupOtherUsers(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getGroupOtherUsers(userId, groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicUserLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public String getPortraitImageURL(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getPortraitImageURL(userId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntryDisplay>
			getRecentlyViewedCMICAccountEntryDisplays(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getRecentlyViewedCMICAccountEntryDisplays(
			userId);
	}

	@Override
	public com.churchmutual.core.model.CMICUserDisplay getUserDetails(
			long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getUserDetails(userId);
	}

	@Override
	public com.churchmutual.core.model.CMICUserDisplay
			getUserDetailsWithRoleAndStatus(long userId, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.getUserDetailsWithRoleAndStatus(
			userId, groupId);
	}

	@Override
	public void inviteBusinessMembers(
			long userId, long groupId, String emailAddresses)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserLocalService.inviteBusinessMembers(
			userId, groupId, emailAddresses);
	}

	@Override
	public boolean isUserRegistered(String cmicUUID) {
		return _cmicUserLocalService.isUserRegistered(cmicUUID);
	}

	@Override
	public boolean isUserValid(
		String businessZipCode, String divisionAgentNumber,
		String registrationCode, String cmicUUID) {

		return _cmicUserLocalService.isUserValid(
			businessZipCode, divisionAgentNumber, registrationCode, cmicUUID);
	}

	@Override
	public void removeUserFromCMICOrganization(
			long userId, long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserLocalService.removeUserFromCMICOrganization(
			userId, cmicOrganizationId);
	}

	@Override
	public void updateBusinessMembers(
			long userId, long groupId, String updateUserRolesJSONString,
			String removeUsersJSONString)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserLocalService.updateBusinessMembers(
			userId, groupId, updateUserRolesJSONString, removeUsersJSONString);
	}

	@Override
	public String updatePortraitImage(long userId, String imageFileString)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserLocalService.updatePortraitImage(
			userId, imageFileString);
	}

	@Override
	public void validateUserRegistration(String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserLocalService.validateUserRegistration(registrationCode);
	}

	@Override
	public CMICUserLocalService getWrappedService() {
		return _cmicUserLocalService;
	}

	@Override
	public void setWrappedService(CMICUserLocalService cmicUserLocalService) {
		_cmicUserLocalService = cmicUserLocalService;
	}

	private CMICUserLocalService _cmicUserLocalService;

}