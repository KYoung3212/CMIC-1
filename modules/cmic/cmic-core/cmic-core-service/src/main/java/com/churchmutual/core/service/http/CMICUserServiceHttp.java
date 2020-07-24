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
import com.liferay.portal.kernel.security.auth.HttpPrincipal;
import com.liferay.portal.kernel.service.http.TunnelUtil;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * Provides the HTTP utility for the
 * <code>CMICUserServiceUtil</code> service
 * utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it requires an additional
 * <code>HttpPrincipal</code> parameter.
 *
 * <p>
 * The benefits of using the HTTP utility is that it is fast and allows for
 * tunneling without the cost of serializing to text. The drawback is that it
 * only works with Java.
 * </p>
 *
 * <p>
 * Set the property <b>tunnel.servlet.hosts.allowed</b> in portal.properties to
 * configure security.
 * </p>
 *
 * <p>
 * The HTTP utility is only generated for remote services.
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICUserServiceSoap
 * @generated
 */
public class CMICUserServiceHttp {

	public static void addRecentlyViewedCMICAccountEntryId(
			HttpPrincipal httpPrincipal, String cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class,
				"addRecentlyViewedCMICAccountEntryId",
				_addRecentlyViewedCMICAccountEntryIdParameterTypes0);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cmicAccountEntryId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.Group>
			getBusinesses(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getBusinesses",
				_getBusinessesParameterTypes1);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.Group>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalType(
				HttpPrincipal httpPrincipal, String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getBusinessPortalType",
				_getBusinessPortalTypeParameterTypes2);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, registrationCode);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.churchmutual.commons.enums.BusinessPortalType)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.churchmutual.commons.enums.BusinessPortalType
			getBusinessPortalTypeByGroupId(
				HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getBusinessPortalTypeByGroupId",
				_getBusinessPortalTypeByGroupIdParameterTypes3);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.churchmutual.commons.enums.BusinessPortalType)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.liferay.portal.kernel.json.JSONArray getBusinessRoles(
			HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getBusinessRoles",
				_getBusinessRolesParameterTypes4);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.liferay.portal.kernel.json.JSONArray)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.liferay.portal.kernel.model.User>
			getCMICOrganizationUsers(
				HttpPrincipal httpPrincipal, long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getCMICOrganizationUsers",
				_getCMICOrganizationUsersParameterTypes5);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cmicOrganizationId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.liferay.portal.kernel.model.User>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List<com.churchmutual.core.model.CMICUserDisplay>
			getGroupOtherUsers(HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getGroupOtherUsers",
				_getGroupOtherUsersParameterTypes6);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List<com.churchmutual.core.model.CMICUserDisplay>)
				returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String getPortraitImageURL(HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getPortraitImageURL",
				_getPortraitImageURLParameterTypes7);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static java.util.List
		<com.churchmutual.core.model.CMICAccountEntryDisplay>
				getRecentlyViewedCMICAccountEntryDisplays(
					HttpPrincipal httpPrincipal)
			throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class,
				"getRecentlyViewedCMICAccountEntryDisplays",
				_getRecentlyViewedCMICAccountEntryDisplaysParameterTypes8);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (java.util.List
				<com.churchmutual.core.model.CMICAccountEntryDisplay>)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.churchmutual.core.model.CMICUserDisplay getUserDetails(
			HttpPrincipal httpPrincipal)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getUserDetails",
				_getUserDetailsParameterTypes9);

			MethodHandler methodHandler = new MethodHandler(methodKey);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.churchmutual.core.model.CMICUserDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static com.churchmutual.core.model.CMICUserDisplay
			getUserDetailsWithRoleAndStatus(
				HttpPrincipal httpPrincipal, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "getUserDetailsWithRoleAndStatus",
				_getUserDetailsWithRoleAndStatusParameterTypes10);

			MethodHandler methodHandler = new MethodHandler(methodKey, groupId);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (com.churchmutual.core.model.CMICUserDisplay)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void inviteBusinessMembers(
			HttpPrincipal httpPrincipal, long groupId, String emailAddresses)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "inviteBusinessMembers",
				_inviteBusinessMembersParameterTypes11);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, emailAddresses);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isUserRegistered(
		HttpPrincipal httpPrincipal, String cmicUUID) {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "isUserRegistered",
				_isUserRegisteredParameterTypes12);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, cmicUUID);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static boolean isUserValid(
		HttpPrincipal httpPrincipal, String businessZipCode,
		String divisionAgentNumber, String registrationCode, String cmicUUID) {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "isUserValid",
				_isUserValidParameterTypes13);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, businessZipCode, divisionAgentNumber,
				registrationCode, cmicUUID);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return ((Boolean)returnObj).booleanValue();
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void removeUserFromCMICOrganization(
			HttpPrincipal httpPrincipal, long userId, long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "removeUserFromCMICOrganization",
				_removeUserFromCMICOrganizationParameterTypes14);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, userId, cmicOrganizationId);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void updateBusinessMembers(
			HttpPrincipal httpPrincipal, long groupId,
			String updateUserRolesJSONString, String removeUsersJSONString)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "updateBusinessMembers",
				_updateBusinessMembersParameterTypes15);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, groupId, updateUserRolesJSONString,
				removeUsersJSONString);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static String updatePortraitImage(
			HttpPrincipal httpPrincipal, String imageFileString)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "updatePortraitImage",
				_updatePortraitImageParameterTypes16);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, imageFileString);

			Object returnObj = null;

			try {
				returnObj = TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}

			return (String)returnObj;
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	public static void validateUserRegistration(
			HttpPrincipal httpPrincipal, String registrationCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		try {
			MethodKey methodKey = new MethodKey(
				CMICUserServiceUtil.class, "validateUserRegistration",
				_validateUserRegistrationParameterTypes17);

			MethodHandler methodHandler = new MethodHandler(
				methodKey, registrationCode);

			try {
				TunnelUtil.invoke(httpPrincipal, methodHandler);
			}
			catch (Exception e) {
				if (e instanceof
						com.liferay.portal.kernel.exception.PortalException) {

					throw (com.liferay.portal.kernel.exception.PortalException)
						e;
				}

				throw new com.liferay.portal.kernel.exception.SystemException(
					e);
			}
		}
		catch (com.liferay.portal.kernel.exception.SystemException se) {
			_log.error(se, se);

			throw se;
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CMICUserServiceHttp.class);

	private static final Class<?>[]
		_addRecentlyViewedCMICAccountEntryIdParameterTypes0 = new Class[] {
			String.class
		};
	private static final Class<?>[] _getBusinessesParameterTypes1 =
		new Class[] {};
	private static final Class<?>[] _getBusinessPortalTypeParameterTypes2 =
		new Class[] {String.class};
	private static final Class<?>[]
		_getBusinessPortalTypeByGroupIdParameterTypes3 = new Class[] {
			long.class
		};
	private static final Class<?>[] _getBusinessRolesParameterTypes4 =
		new Class[] {long.class};
	private static final Class<?>[] _getCMICOrganizationUsersParameterTypes5 =
		new Class[] {long.class};
	private static final Class<?>[] _getGroupOtherUsersParameterTypes6 =
		new Class[] {long.class};
	private static final Class<?>[] _getPortraitImageURLParameterTypes7 =
		new Class[] {};
	private static final Class<?>[]
		_getRecentlyViewedCMICAccountEntryDisplaysParameterTypes8 =
			new Class[] {};
	private static final Class<?>[] _getUserDetailsParameterTypes9 =
		new Class[] {};
	private static final Class<?>[]
		_getUserDetailsWithRoleAndStatusParameterTypes10 = new Class[] {
			long.class
		};
	private static final Class<?>[] _inviteBusinessMembersParameterTypes11 =
		new Class[] {long.class, String.class};
	private static final Class<?>[] _isUserRegisteredParameterTypes12 =
		new Class[] {String.class};
	private static final Class<?>[] _isUserValidParameterTypes13 = new Class[] {
		String.class, String.class, String.class, String.class
	};
	private static final Class<?>[]
		_removeUserFromCMICOrganizationParameterTypes14 = new Class[] {
			long.class, long.class
		};
	private static final Class<?>[] _updateBusinessMembersParameterTypes15 =
		new Class[] {long.class, String.class, String.class};
	private static final Class<?>[] _updatePortraitImageParameterTypes16 =
		new Class[] {String.class};
	private static final Class<?>[] _validateUserRegistrationParameterTypes17 =
		new Class[] {String.class};

}