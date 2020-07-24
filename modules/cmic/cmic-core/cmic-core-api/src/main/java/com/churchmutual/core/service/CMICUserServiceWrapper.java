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
 * Provides a wrapper for {@link CMICUserService}.
 *
 * @author Kayleen Lim
 * @see CMICUserService
 * @generated
 */
public class CMICUserServiceWrapper
	implements CMICUserService, ServiceWrapper<CMICUserService> {

	public CMICUserServiceWrapper(CMICUserService cmicUserService) {
		_cmicUserService = cmicUserService;
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CMICUserServiceUtil} to access the cmic user remote service. Add custom service methods to <code>com.churchmutual.core.service.impl.CMICUserServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	@Override
	public void addRecentlyViewedCMICAccountEntryId(String cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserService.addRecentlyViewedCMICAccountEntryId(
			cmicAccountEntryId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.Group> getBusinesses()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getBusinesses();
	}

	@Override
	public com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalType(String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getBusinessPortalType(registrationCode);
	}

	@Override
	public com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalTypeByGroupId(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getBusinessPortalTypeByGroupId(groupId);
	}

	@Override
	public com.liferay.portal.kernel.json.JSONArray getBusinessRoles(
			long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getBusinessRoles(groupId);
	}

	@Override
	public java.util.List<com.liferay.portal.kernel.model.User>
			getCMICOrganizationUsers(long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getCMICOrganizationUsers(cmicOrganizationId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICUserDisplay>
			getGroupOtherUsers(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getGroupOtherUsers(groupId);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicUserService.getOSGiServiceIdentifier();
	}

	@Override
	public String getPortraitImageURL()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getPortraitImageURL();
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntryDisplay>
			getRecentlyViewedCMICAccountEntryDisplays()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getRecentlyViewedCMICAccountEntryDisplays();
	}

	@Override
	public com.churchmutual.core.model.CMICUserDisplay getUserDetails()
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getUserDetails();
	}

	@Override
	public com.churchmutual.core.model.CMICUserDisplay
			getUserDetailsWithRoleAndStatus(long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.getUserDetailsWithRoleAndStatus(groupId);
	}

	@Override
	public void inviteBusinessMembers(long groupId, String emailAddresses)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserService.inviteBusinessMembers(groupId, emailAddresses);
	}

	@Override
	public boolean isUserRegistered(String cmicUUID) {
		return _cmicUserService.isUserRegistered(cmicUUID);
	}

	@Override
	public boolean isUserValid(
		String businessZipCode, String divisionAgentNumber,
		String registrationCode, String cmicUUID) {

		return _cmicUserService.isUserValid(
			businessZipCode, divisionAgentNumber, registrationCode, cmicUUID);
	}

	@Override
	public void removeUserFromCMICOrganization(
			long userId, long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserService.removeUserFromCMICOrganization(
			userId, cmicOrganizationId);
	}

	@Override
	public void updateBusinessMembers(
			long groupId, String updateUserRolesJSONString,
			String removeUsersJSONString)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserService.updateBusinessMembers(
			groupId, updateUserRolesJSONString, removeUsersJSONString);
	}

	@Override
	public String updatePortraitImage(String imageFileString)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicUserService.updatePortraitImage(imageFileString);
	}

	@Override
	public void validateUserRegistration(String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicUserService.validateUserRegistration(registrationCode);
	}

	@Override
	public CMICUserService getWrappedService() {
		return _cmicUserService;
	}

	@Override
	public void setWrappedService(CMICUserService cmicUserService) {
		_cmicUserService = cmicUserService;
	}

	private CMICUserService _cmicUserService;

}