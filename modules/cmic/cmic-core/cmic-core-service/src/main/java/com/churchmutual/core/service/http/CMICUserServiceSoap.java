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

package com.churchmutual.core.service.http;

import com.churchmutual.core.service.CMICUserServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * Provides the SOAP utility for the
 * <code>CMICUserServiceUtil</code> service
 * utility. The static methods of this class call the same methods of the
 * service utility. However, the signatures are different because it is
 * difficult for SOAP to support certain types.
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at http://localhost:8080/api/axis. Set the
 * property <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICUserServiceHttp
 * @generated
 */
public class CMICUserServiceSoap {

	public static void addRecentlyViewedCMICAccountEntryId(
			String cmicAccountEntryId)
		throws RemoteException {

		try {
			CMICUserServiceUtil.addRecentlyViewedCMICAccountEntryId(
				cmicAccountEntryId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.Group[] getBusinesses()
		throws RemoteException {

		try {
			java.util.List<com.liferay.portal.kernel.model.Group> returnValue =
				CMICUserServiceUtil.getBusinesses();

			return returnValue.toArray(
				new com.liferay.portal.kernel.model.Group[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalType(String registrationCode)
		throws RemoteException {

		try {
			com.churchmutual.commons.enums.BusinessPortalType returnValue =
				CMICUserServiceUtil.getBusinessPortalType(registrationCode);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalTypeByGroupId(long groupId)
		throws RemoteException {

		try {
			com.churchmutual.commons.enums.BusinessPortalType returnValue =
				CMICUserServiceUtil.getBusinessPortalTypeByGroupId(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getBusinessRoles(long groupId) throws RemoteException {
		try {
			com.liferay.portal.kernel.json.JSONArray returnValue =
				CMICUserServiceUtil.getBusinessRoles(groupId);

			return returnValue.toString();
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.portal.kernel.model.User[]
			getCMICOrganizationUsers(long cmicOrganizationId)
		throws RemoteException {

		try {
			java.util.List<com.liferay.portal.kernel.model.User> returnValue =
				CMICUserServiceUtil.getCMICOrganizationUsers(
					cmicOrganizationId);

			return returnValue.toArray(
				new com.liferay.portal.kernel.model.User[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.core.model.CMICUserDisplay[]
			getGroupOtherUsers(long groupId)
		throws RemoteException {

		try {
			java.util.List<com.churchmutual.core.model.CMICUserDisplay>
				returnValue = CMICUserServiceUtil.getGroupOtherUsers(groupId);

			return returnValue.toArray(
				new com.churchmutual.core.model.CMICUserDisplay
					[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String getPortraitImageURL() throws RemoteException {
		try {
			String returnValue = CMICUserServiceUtil.getPortraitImageURL();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.core.model.CMICAccountEntryDisplay[]
			getRecentlyViewedCMICAccountEntryDisplays()
		throws RemoteException {

		try {
			java.util.List<com.churchmutual.core.model.CMICAccountEntryDisplay>
				returnValue =
					CMICUserServiceUtil.
						getRecentlyViewedCMICAccountEntryDisplays();

			return returnValue.toArray(
				new com.churchmutual.core.model.CMICAccountEntryDisplay
					[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.core.model.CMICUserDisplay getUserDetails()
		throws RemoteException {

		try {
			com.churchmutual.core.model.CMICUserDisplay returnValue =
				CMICUserServiceUtil.getUserDetails();

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.churchmutual.core.model.CMICUserDisplay
			getUserDetailsWithRoleAndStatus(long groupId)
		throws RemoteException {

		try {
			com.churchmutual.core.model.CMICUserDisplay returnValue =
				CMICUserServiceUtil.getUserDetailsWithRoleAndStatus(groupId);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void inviteBusinessMembers(
			long groupId, String emailAddresses)
		throws RemoteException {

		try {
			CMICUserServiceUtil.inviteBusinessMembers(groupId, emailAddresses);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean isUserRegistered(String cmicUUID)
		throws RemoteException {

		try {
			boolean returnValue = CMICUserServiceUtil.isUserRegistered(
				cmicUUID);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static boolean isUserValid(
			String businessZipCode, String divisionAgentNumber,
			String registrationCode, String cmicUUID)
		throws RemoteException {

		try {
			boolean returnValue = CMICUserServiceUtil.isUserValid(
				businessZipCode, divisionAgentNumber, registrationCode,
				cmicUUID);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void removeUserFromCMICOrganization(
			long userId, long cmicOrganizationId)
		throws RemoteException {

		try {
			CMICUserServiceUtil.removeUserFromCMICOrganization(
				userId, cmicOrganizationId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void updateBusinessMembers(
			long groupId, String updateUserRolesJSONString,
			String removeUsersJSONString)
		throws RemoteException {

		try {
			CMICUserServiceUtil.updateBusinessMembers(
				groupId, updateUserRolesJSONString, removeUsersJSONString);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static String updatePortraitImage(String imageFileString)
		throws RemoteException {

		try {
			String returnValue = CMICUserServiceUtil.updatePortraitImage(
				imageFileString);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static void validateUserRegistration(String registrationCode)
		throws RemoteException {

		try {
			CMICUserServiceUtil.validateUserRegistration(registrationCode);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CMICUserServiceSoap.class);

}