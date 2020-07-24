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

import com.churchmutual.commons.constants.ExpandoConstants;
import com.churchmutual.commons.enums.BusinessCompanyRole;
import com.churchmutual.commons.enums.BusinessPortalType;
import com.churchmutual.commons.enums.BusinessRole;
import com.churchmutual.commons.enums.BusinessUserStatus;
import com.churchmutual.commons.util.CollectionsUtil;
import com.churchmutual.core.constants.SelfProvisioningConstants;
import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.CMICAccountEntryDisplay;
import com.churchmutual.core.model.CMICOrganization;
import com.churchmutual.core.model.CMICUserDisplay;
import com.churchmutual.core.service.CMICAccountEntryLocalService;
import com.churchmutual.core.service.CMICOrganizationLocalService;
import com.churchmutual.core.service.base.CMICUserLocalServiceBaseImpl;
import com.churchmutual.rest.AccountWebService;
import com.churchmutual.rest.PortalUserWebService;
import com.churchmutual.rest.model.CMICAccountDTO;
import com.churchmutual.rest.model.CMICUserDTO;
import com.churchmutual.rest.model.CMICUserRelationDTO;

import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.expando.kernel.model.ExpandoBridge;
import com.liferay.petra.string.StringPool;
import com.liferay.petra.string.StringUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.model.UserGroup;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.Validator;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Kayleen Lim
 */
@Component(property = "model.class.name=com.churchmutual.core.model.CMICUser", service = AopService.class)
public class CMICUserLocalServiceImpl extends CMICUserLocalServiceBaseImpl {

	@Override
	public void addRecentlyViewedCMICAccountEntryId(long userId, String cmicAccountEntryId) throws PortalException {
		User user = userLocalService.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String recentlyViewedCmicAccountEntryIds = (String)expandoBridge.getAttribute(
			ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS);

		if (recentlyViewedCmicAccountEntryIds != null) {
			List<String> recentAccountEntryIds = StringUtil.split(recentlyViewedCmicAccountEntryIds);

			recentAccountEntryIds.remove(cmicAccountEntryId);

			recentAccountEntryIds.add(0, cmicAccountEntryId);

			if (recentAccountEntryIds.size() > _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT) {
				recentAccountEntryIds = recentAccountEntryIds.subList(0, _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT);
			}

			expandoBridge.setAttribute(
				ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS,
				StringUtil.merge(recentAccountEntryIds, StringPool.COMMA), false);
		}
		else {
			expandoBridge.setAttribute(
				ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS, cmicAccountEntryId, false);
		}
	}

	@Override
	public User fetchUserByCmicUUID(long companyId, String cmicUUID) throws PortalException {
		return userLocalService.fetchUserByReferenceCode(companyId, cmicUUID);
	}

	@Override
	public List<Group> getBusinesses(long userId) throws PortalException {
		updateUserBusinesses(userId);

		List<Organization> organizations = organizationLocalService.getUserOrganizations(userId);

		List<Group> groups = new ArrayList<>();

		for (Organization organization : organizations) {
			Group group = groupLocalService.getGroup(organization.getGroupId());

			groups.add(group);
		}

		return groups;
	}

	@Override
	public BusinessPortalType getBusinessPortalType(String registrationCode) throws PortalException {
		CMICUserDTO cmicUserDTO = portalUserWebService.validateUserRegistration(registrationCode);

		return _getBusinessPortalType(cmicUserDTO);
	}

	@Override
	public BusinessPortalType getBusinessPortalTypeByGroupId(long groupId) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		String className = group.getClassName();

		if (Organization.class.getName().equals(className)) {
			return BusinessPortalType.BROKER;
		}
		else if (AccountEntry.class.getName().equals(className)) {
			return BusinessPortalType.INSURED;
		}

		throw new PortalException("Error: portal type was undefined for group " + groupId);
	}

	@Override
	public JSONArray getBusinessRoles(long groupId) throws PortalException {
		JSONArray response = jsonFactory.createJSONArray();

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		BusinessRole[] businessRoles = BusinessRole.getBusinessRoles(businessPortalType);

		for (BusinessRole businessRole : businessRoles) {
			JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

			jsonObject.put("label", businessRole.getShortenedNameKey());
			jsonObject.put("value", businessRole.getMessageKey());

			response.put(jsonObject);
		}

		return response;
	}

	@Override
	public List<User> getCMICOrganizationUsers(long cmicOrganizationId) throws PortalException {
		CMICOrganization cmicOrganization = cmicOrganizationPersistence.findByPrimaryKey(cmicOrganizationId);

		return userLocalService.getOrganizationUsers(cmicOrganization.getOrganizationId());
	}

	@Override
	public List<CMICUserDisplay> getGroupOtherUsers(long userId, long groupId) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		long classPK = group.getClassPK();

		List<User> groupUsers = new ArrayList<>();

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		if (BusinessPortalType.BROKER.equals(businessPortalType)) {
			CMICOrganization cmicOrganization = cmicOrganizationPersistence.findByOrganizationId(classPK);

			List<CMICUserDTO> cmicUserDTOs = portalUserWebService.getProducerEntityUsers(
				cmicOrganization.getProducerId());

			userGroupRoleLocalService.deleteUserGroupRolesByGroupId(groupId);

			List<Long> newUserGroupRoleUserIds = new ArrayList<>();

			for (CMICUserDTO cmicUserDTO : cmicUserDTOs) {
				try {
					User user = fetchUserByCmicUUID(group.getCompanyId(), cmicUserDTO.getUuid());

					if (user != null) {
						String shortenedNameKey = cmicUserDTO.getUserRole();

						BusinessRole businessRole = BusinessRole.fromShortenedNameKey(
							shortenedNameKey, businessPortalType);

						Role role = roleLocalService.getRole(user.getCompanyId(), businessRole.getRoleName());

						long curUserId = user.getUserId();

						userGroupRoleLocalService.addUserGroupRoles(curUserId, groupId, new long[] {role.getRoleId()});

						newUserGroupRoleUserIds.add(curUserId);

						groupUsers.add(user);
					}
				}
				catch (PortalException pe) {
					_log.error(pe);
				}
			}

			userLocalService.setOrganizationUsers(classPK, ArrayUtil.toLongArray(newUserGroupRoleUserIds));
		}
		else {

			// TODO CMIC-104 For insured self-provisioning, call CMIC endpoint to retrieve users of an insured organization and update memberships

			List<AccountEntryUserRel> userRels =
				accountEntryUserRelLocalService.getAccountEntryUserRelsByAccountEntryId(classPK);

			groupUsers = userRels.stream(
			).map(
				a -> userLocalService.fetchUser(a.getAccountUserId())
			).collect(
				Collectors.toList()
			);
		}

		List<CMICUserDisplay> cmicUserDisplays = new ArrayList<>();

		for (User groupUser : groupUsers) {
			if (userId != groupUser.getUserId()) {
				CMICUserDisplay cmicUserDisplay = getUserDetailsWithRoleAndStatus(groupUser.getUserId(), groupId);

				cmicUserDisplays.add(cmicUserDisplay);
			}
		}

		return cmicUserDisplays;
	}

	@Override
	public String getPortraitImageURL(long userId) throws PortalException {
		User user = userLocalService.getUser(userId);

		String portraitURL = UserConstants.getPortraitURL(
			portal.getPathImage(), user.isMale(), user.getPortraitId(), user.getUserUuid());

		return portraitURL + "&timestamp=" + new Date().getTime();
	}

	@Override
	public List<CMICAccountEntryDisplay> getRecentlyViewedCMICAccountEntryDisplays(long userId) throws PortalException {
		User user = userLocalService.getUser(userId);

		ExpandoBridge expandoBridge = user.getExpandoBridge();

		String recentlyViewedAccountEntryIds = (String)expandoBridge.getAttribute(
			ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS);

		List<String> recentAccountEntryIds = new ArrayList<>();

		if (Validator.isNotNull(recentlyViewedAccountEntryIds)) {
			recentAccountEntryIds = StringUtil.split(recentlyViewedAccountEntryIds);
		}

		if (recentAccountEntryIds.size() == _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT) {
			return cmicAccountEntryLocalService.getCMICAccountEntryDisplays(recentAccountEntryIds);
		}

		updateUserBusinesses(userId);

		int start = 0;
		int index = 0;

		List<CMICAccountEntry> cmicAccountEntries =
			cmicAccountEntryLocalService.getCMICAccountEntriesByUserIdOrderedByName(
				userId, start, _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT);

		while ((index < cmicAccountEntries.size()) &&
			   (recentAccountEntryIds.size() < _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT)) {

			CMICAccountEntry cmicAccountEntry = cmicAccountEntries.get(index++);

			String cmicAccountEntryId = String.valueOf(cmicAccountEntry.getCmicAccountEntryId());

			if (!recentAccountEntryIds.contains(cmicAccountEntryId)) {
				recentAccountEntryIds.add(cmicAccountEntryId);
			}
		}

		expandoBridge.setAttribute(
			ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS,
			StringUtil.merge(recentAccountEntryIds, StringPool.COMMA), false);

		return cmicAccountEntryLocalService.getCMICAccountEntryDisplays(recentAccountEntryIds);
	}

	@Override
	public CMICUserDisplay getUserDetails(long userId) throws PortalException {
		User user = userLocalService.getUser(userId);

		updateUserBusinesses(userId);

		String cmicUUID = user.getExternalReferenceCode();

		CMICUserDTO cmicUserDTO = portalUserWebService.getUserDetails(cmicUUID);

		return new CMICUserDisplay(cmicUserDTO, user, getPortraitImageURL(userId));
	}

	@Override
	public CMICUserDisplay getUserDetailsWithRoleAndStatus(long userId, long groupId) throws PortalException {
		CMICUserDisplay cmicUserDisplay = getUserDetails(userId);

		BusinessRole businessRole = _getBusinessRole(userId, groupId);

		cmicUserDisplay.setBusinessRole(businessRole);

		BusinessUserStatus businessUserStatus = _getBusinessUserStatus(userId, groupId);

		cmicUserDisplay.setBusinessUserStatus(businessUserStatus);

		return cmicUserDisplay;
	}

	@Override
	public void inviteBusinessMembers(long userId, long groupId, String emailAddresses) throws PortalException {
		String[] emailStrings = emailAddresses.split(StringPool.COMMA);

		long entityId = _getEntityId(groupId);

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		for (String emailAddress : emailStrings) {
			String email = emailAddress.trim();

			if (BusinessPortalType.BROKER.equals(businessPortalType)) {
				CMICOrganization cmicOrganization = cmicOrganizationPersistence.fetchByOrganizationId(entityId);

				if (cmicOrganization != null) {
					portalUserWebService.inviteUserToCMICOrganization(email, cmicOrganization.getProducerId());
				}
			}

			_inviteBusinessMember(userId, groupId, email);
		}
	}

	@Override
	public boolean isUserRegistered(String cmicUUID) {
		return portalUserWebService.isUserRegistered(cmicUUID);
	}

	@Override
	public boolean isUserValid(
		String businessZipCode, String divisionAgentNumber, String registrationCode, String cmicUUID) {

		return portalUserWebService.isUserValid(businessZipCode, divisionAgentNumber, registrationCode, cmicUUID);
	}

	@Override
	public void removeUserFromCMICOrganization(long userId, long cmicOrganizationId) throws PortalException {
		User user = userLocalService.getUser(userId);

		String cmicUUID = user.getExternalReferenceCode();

		CMICOrganization cmicOrganization = cmicOrganizationPersistence.findByOrganizationId(cmicOrganizationId);

		portalUserWebService.removeUserFromCMICOrganization(cmicUUID, cmicOrganization.getProducerId());
	}

	@Override
	public void updateBusinessMembers(
			long userId, long groupId, String updateUserRolesJSONString, String removeUsersJSONString)
		throws PortalException {

		Map<String, String> userRolesToUpdate = _deserializeUpdateRoles(updateUserRolesJSONString);

		List<String> userEmailsToRemove = _deserializeRemoveUsers(removeUsersJSONString);

		_updateBusinessUserRoles(userId, groupId, userRolesToUpdate);

		_removeUsersFromBusiness(groupId, userEmailsToRemove);

		// validate only one owner exists

		_getOwnerUser(groupId);
	}

	@Override
	public String updatePortraitImage(long userId, String imageFileString) throws PortalException {

		// remove file type information "data:image/png;base64," from the string

		String imageString = imageFileString.substring(imageFileString.indexOf(StringPool.COMMA) + 1);

		byte[] decodedImageFile = Base64.getDecoder(
		).decode(
			imageString
		);

		userLocalService.updatePortrait(userId, decodedImageFile);

		return getPortraitImageURL(userId);
	}

	@Override
	public void validateUserRegistration(String registrationCode) throws PortalException {
		if (BusinessPortalType.INSURED.equals(getBusinessPortalType(registrationCode))) {
			throw new PortalException("User must have the Broker portal type");
		}
	}

	/**
	 * Update the user's organizations (Producer Organizations) and/or accounts (Businesses)
	 */
	protected void updateUserBusinesses(long userId) throws PortalException {
		User user = userLocalService.getUser(userId);

		String cmicUUID = user.getExternalReferenceCode();

		CMICUserDTO cmicUserDTO = portalUserWebService.getUserDetails(cmicUUID);

		List<CMICUserRelationDTO> userRelations = cmicUserDTO.getOrganizationList();

		List<CMICOrganization> newUserOganizations = new ArrayList<>();

		List<CMICAccountEntry> newUserAccountEntries = new ArrayList<>();

		for (CMICUserRelationDTO userRelation : userRelations) {
			if (userRelation.isProducer()) {
				String agentNumber = userRelation.getAgentNumber();
				String divisionNumber = userRelation.getDivisionNumber();

				String producerCode = divisionNumber + agentNumber;

				long producerId = userRelation.getProducerId();

				CMICOrganization cmicOrganization = cmicOrganizationLocalService.fetchCMICOrganizationByProducerId(
					producerId);

				if (cmicOrganization == null) {
					cmicOrganization = cmicOrganizationLocalService.addCMICOrganization(userId, producerId);
				}
				else {
					cmicOrganization = cmicOrganizationLocalService.updateCMICOrganizationContactInfo(
						userId, producerId);
				}

				newUserOganizations.add(cmicOrganization);

				List<CMICAccountDTO> cmicAccountDTOs = accountWebService.getAccountsSearchByProducer(
					new String[] {producerCode});

				for (CMICAccountDTO cmicAccountDTO : cmicAccountDTOs) {
					CMICAccountEntry cmicAccountEntry = cmicAccountEntryLocalService.addOrUpdateCMICAccountEntry(
						userId, cmicAccountDTO.getAccountNumber(), cmicAccountDTO.getCompanyNumber(),
						cmicAccountDTO.getAccountName(), producerId, producerCode);

					newUserAccountEntries.add(cmicAccountEntry);
				}
			}
		}

		cmicAccountEntryLocalService.updateCMICAccountEntryDetails(newUserAccountEntries);

		// Compare the user's memberships for organizations and/or accounts, and if it's different, update

		List<CMICOrganization> existingUserOrganizations = cmicOrganizationLocalService.getCMICOrganizationsByUserId(
			userId);

		if (!existingUserOrganizations.containsAll(newUserOganizations) ||
			!newUserOganizations.containsAll(existingUserOrganizations)) {

			long[] organizationIds = newUserOganizations.stream(
			).mapToLong(
				cmicOrganization -> cmicOrganization.getOrganizationId()
			).toArray();

			organizationLocalService.setUserOrganizations(userId, organizationIds);
		}

		List<CMICAccountEntry> existingUserAccountEntries = cmicAccountEntryLocalService.getCMICAccountEntriesByUserId(
			userId);

		if (!existingUserAccountEntries.containsAll(newUserAccountEntries) ||
			!newUserAccountEntries.containsAll(existingUserAccountEntries)) {

			long[] newAccountEntryIds = newUserAccountEntries.stream(
			).mapToLong(
				cmicAccountEntry -> cmicAccountEntry.getAccountEntryId()
			).toArray();

			long[] deleteAccountEntryIds = existingUserAccountEntries.stream(
			).mapToLong(
				cmicAccountEntry -> cmicAccountEntry.getAccountEntryId()
			).filter(
				accountEntryId -> !ArrayUtil.contains(newAccountEntryIds, accountEntryId)
			).toArray();

			accountEntryUserRelLocalService.updateAccountEntryUserRels(
				newAccountEntryIds, deleteAccountEntryIds, userId);
		}
	}

	@Reference
	protected AccountEntryLocalService accountEntryLocalService;

	@Reference
	protected AccountEntryUserRelLocalService accountEntryUserRelLocalService;

	@Reference
	protected AccountWebService accountWebService;

	@Reference
	protected CMICAccountEntryLocalService cmicAccountEntryLocalService;

	@Reference
	protected CMICOrganizationLocalService cmicOrganizationLocalService;

	@Reference
	protected JSONFactory jsonFactory;

	@Reference
	protected Portal portal;

	@Reference
	protected PortalUserWebService portalUserWebService;

	private User _createOrFetchUser(long creatorUserId, String email) throws PortalException {
		User creatorUser = userLocalService.fetchUser(creatorUserId);

		User invitedUser = userLocalService.fetchUserByEmailAddress(creatorUser.getCompanyId(), email);

		if (invitedUser == null) {
			invitedUser = userLocalService.addUser(
				creatorUserId, creatorUser.getCompanyId(), SelfProvisioningConstants.AUTO_PASSWORD, StringPool.BLANK,
				StringPool.BLANK, SelfProvisioningConstants.AUTO_SCREEN_NAME, StringPool.BLANK, email, 0,
				StringPool.BLANK, LocaleUtil.getDefault(), SelfProvisioningConstants.NAME, StringPool.BLANK,
				SelfProvisioningConstants.NAME, 0, 0, SelfProvisioningConstants.IS_MALE,
				SelfProvisioningConstants.BIRTHDAY_MONTH, SelfProvisioningConstants.BIRTHDAY_DAY,
				SelfProvisioningConstants.BIRTHDAY_YEAR, StringPool.BLANK, null, null, null, null,
				SelfProvisioningConstants.SEND_EMAIL_NOTIFICATIONS, null);
		}

		return invitedUser;
	}

	private List<String> _deserializeRemoveUsers(String removeUsersJSONString) throws PortalException {
		JSONArray removeUsersJSONArray = jsonFactory.createJSONArray(removeUsersJSONString);

		List<String> userEmailsToRemove = new ArrayList<>();

		for (int i = 0; i < removeUsersJSONArray.length(); i++) {
			JSONObject removeUserJSONObject = removeUsersJSONArray.getJSONObject(i);

			String email = removeUserJSONObject.getString("email");

			userEmailsToRemove.add(email);
		}

		return userEmailsToRemove;
	}

	private Map<String, String> _deserializeUpdateRoles(String updateUserRolesJSONString) throws PortalException {
		JSONArray updateRolesJSONArray = jsonFactory.createJSONArray(updateUserRolesJSONString);

		Map<String, String> userRolesToUpdate = new HashMap<>();

		for (int i = 0; i < updateRolesJSONArray.length(); i++) {
			JSONObject updateUserRoleJSONObject = updateRolesJSONArray.getJSONObject(i);

			String email = updateUserRoleJSONObject.getString("email");

			String roleName = updateUserRoleJSONObject.getString(
				"role"
			).toLowerCase();

			userRolesToUpdate.put(email, roleName);
		}

		return userRolesToUpdate;
	}

	private BusinessPortalType _getBusinessPortalType(CMICUserDTO cmicUserDTO) throws PortalException {
		_validateCMICUserDTO(cmicUserDTO);

		String userRole = cmicUserDTO.getUserRole();

		String[] splitStrings = userRole.split(StringPool.SPACE);

		if ((splitStrings != null) && (splitStrings.length > 1)) {
			switch (splitStrings[0]) {
				case "producer":
					return BusinessPortalType.BROKER;
				case "insured":
					return BusinessPortalType.INSURED;
				default:
					throw new PortalException("Error: the portal type was undefined for user with role " + userRole);
			}
		}

		throw new PortalException("Error: portal type was undefined for user with role " + userRole);
	}

	private BusinessRole _getBusinessRole(long userId, long groupId) throws PortalException {
		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		BusinessRole[] businessRoles = BusinessRole.getBusinessRoles(businessPortalType);

		User user = userLocalService.getUser(userId);

		for (BusinessRole businessRole : businessRoles) {
			Role role = roleLocalService.getRole(user.getCompanyId(), businessRole.getRoleName());

			if (userGroupRoleLocalService.hasUserGroupRole(userId, groupId, role.getRoleId())) {
				return businessRole;
			}
		}

		return null;
	}

	private BusinessUserStatus _getBusinessUserStatus(long userId, long groupId) throws PortalException {
		User user = userLocalService.getUser(userId);

		Role invitedRole = roleLocalService.getRole(user.getCompanyId(), RoleConstants.SITE_MEMBER);

		if (userGroupRoleLocalService.hasUserGroupRole(userId, groupId, invitedRole.getRoleId())) {
			return BusinessUserStatus.INVITED;
		}

		return BusinessUserStatus.ACTIVE;
	}

	private long _getEntityId(long groupId) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		return group.getClassPK();
	}

	private User _getOwnerUser(long groupId) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		BusinessRole businessRole = BusinessRole.getBusinessOwnerRole(businessPortalType);

		Role ownerRole = roleLocalService.getRole(group.getCompanyId(), businessRole.getRoleName());

		long entityId = _getEntityId(groupId);

		List<User> groupUsers;

		if (BusinessPortalType.BROKER.equals(businessPortalType)) {
			groupUsers = userLocalService.getOrganizationUsers(entityId);
		}
		else {
			List<AccountEntryUserRel> userRels =
				accountEntryUserRelLocalService.getAccountEntryUserRelsByAccountEntryId(entityId);

			groupUsers = userRels.stream(
			).map(
				a -> userLocalService.fetchUser(a.getAccountUserId())
			).collect(
				Collectors.toList()
			);
		}

		List<User> owners = groupUsers.stream(
		).filter(
			groupUser -> userGroupRoleLocalService.hasUserGroupRole(
				groupUser.getUserId(), groupId, ownerRole.getRoleId())
		).collect(
			Collectors.toList()
		);

		if (owners.size() != 1) {
			throw new PortalException("Error updating roles: Business must have EXACTLY ONE user as Owner");
		}

		return CollectionsUtil.getFirst(owners);
	}

	private void _inviteBusinessMember(long userId, long groupId, String email) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		long companyId = group.getCompanyId();
		long entityId = group.getClassPK();

		User invitedUser = _createOrFetchUser(userId, email);

		long invitedUserId = invitedUser.getUserId();

		// Default role applied to invited users should be "Admin"

		Role adminRole;

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		if (BusinessPortalType.BROKER.equals(businessPortalType)) {
			adminRole = roleLocalService.getRole(companyId, BusinessRole.ORGANIZATION_ADMINISTRATOR.getRoleName());

			organizationLocalService.addUserOrganization(invitedUserId, entityId);

			Group brokerSiteGroup = groupLocalService.getFriendlyURLGroup(
				companyId, BusinessPortalType.BROKER.getFriendlyURL());

			userLocalService.addGroupUser(brokerSiteGroup.getGroupId(), invitedUser);

			Role role = roleLocalService.getRole(companyId, BusinessCompanyRole.BROKER.getRoleName());

			userGroupRoleLocalService.addUserGroupRoles(invitedUserId, groupId, new long[] {role.getRoleId()});
		}
		else {
			adminRole = roleLocalService.getRole(companyId, BusinessRole.ACCOUNT_ADMINISTRATOR.getRoleName());

			if (!accountEntryUserRelLocalService.hasAccountEntryUserRel(entityId, invitedUserId)) {
				accountEntryUserRelLocalService.addAccountEntryUserRel(entityId, invitedUserId);
			}

			UserGroup userGroup = userGroupLocalService.fetchUserGroup(
				companyId, BusinessCompanyRole.INSURED.getUserGroupName());

			userGroupLocalService.addUserUserGroup(invitedUserId, userGroup);
		}

		BusinessRole businessRole = _getBusinessRole(invitedUserId, groupId);

		BusinessUserStatus businessUserStatus = _getBusinessUserStatus(invitedUserId, groupId);

		if (businessRole == null) {
			_updateBusinessUserRole(invitedUser.getUserId(), groupId, adminRole.getRoleId());

			_updateBusinessUserStatus(invitedUser.getUserId(), groupId, BusinessUserStatus.INVITED);
		}

		if (BusinessUserStatus.INVITED.equals(businessUserStatus)) {

			// TODO Send CMIC invitation email

		}
	}

	private void _promoteFirstActiveUser(long userId, long groupId) throws PortalException {
		User user = userLocalService.getUser(userId);

		User owner = _getOwnerUser(groupId);

		BusinessUserStatus ownerStatus = _getBusinessUserStatus(owner.getUserId(), groupId);

		if (!BusinessUserStatus.ACTIVE.equals(ownerStatus)) {
			BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

			BusinessRole ownerBusinessRole = BusinessRole.getBusinessOwnerRole(businessPortalType);
			BusinessRole adminBusinessRole = BusinessRole.getBusinessAdminRole(businessPortalType);

			Role ownerRole = roleLocalService.getRole(user.getCompanyId(), ownerBusinessRole.getRoleName());

			Role adminRole = roleLocalService.getRole(user.getCompanyId(), adminBusinessRole.getRoleName());

			_updateBusinessUserRole(owner.getUserId(), groupId, adminRole.getRoleId());

			_updateBusinessUserRole(userId, groupId, ownerRole.getRoleId());

			_updateBusinessUserStatus(userId, groupId, BusinessUserStatus.ACTIVE);
		}
	}

	private void _removeUsersFromBusiness(long groupId, List<String> userEmailsToRemove) throws PortalException {
		Group group = groupLocalService.getGroup(groupId);

		long companyId = group.getCompanyId();
		long entityId = group.getClassPK();

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		for (String userEmail : userEmailsToRemove) {
			User memberUser = userLocalService.getUserByEmailAddress(companyId, userEmail);

			long memberUserId = memberUser.getUserId();

			userGroupRoleLocalService.deleteUserGroupRolesByUserId(memberUserId);

			if (BusinessPortalType.BROKER.equals(businessPortalType)) {
				organizationLocalService.deleteUserOrganization(memberUserId, entityId);
			}
			else {
				accountEntryUserRelLocalService.deleteAccountEntryUserRels(entityId, new long[] {memberUserId});
			}
		}
	}

	private void _updateBusinessUserRole(long userId, long groupId, long roleId) throws PortalException {
		User user = userLocalService.getUser(userId);

		BusinessRole businessRole = _getBusinessRole(userId, groupId);

		if (businessRole != null) {
			Role prevRole = roleLocalService.getRole(user.getCompanyId(), businessRole.getRoleName());

			userGroupRoleLocalService.deleteUserGroupRoles(userId, groupId, new long[] {prevRole.getRoleId()});
		}

		userGroupRoleLocalService.addUserGroupRoles(userId, groupId, new long[] {roleId});
	}

	private void _updateBusinessUserRoles(long userId, long groupId, Map<String, String> userRolesToUpdate)
		throws PortalException {

		User currentUser = userLocalService.getUser(userId);

		long companyId = currentUser.getCompanyId();

		BusinessPortalType businessPortalType = getBusinessPortalTypeByGroupId(groupId);

		BusinessRole ownerBusinessRole = BusinessRole.getBusinessOwnerRole(businessPortalType);

		Role ownerRole = roleLocalService.getRole(currentUser.getCompanyId(), ownerBusinessRole.getRoleName());

		boolean currentUserIsOwner = userGroupRoleLocalService.hasUserGroupRole(
			currentUser.getUserId(), groupId, ownerRole.getRoleId());

		for (Map.Entry<String, String> userRoleToUpdate : userRolesToUpdate.entrySet()) {
			String userEmail = userRoleToUpdate.getKey();
			String roleName = userRoleToUpdate.getValue();

			User user = userLocalService.getUserByEmailAddress(companyId, userEmail);

			BusinessRole businessRole = BusinessRole.fromShortenedNameKey(roleName, businessPortalType);

			Role newRole = roleLocalService.getRole(user.getCompanyId(), businessRole.getRoleName());

			BusinessUserStatus businessUserStatus = _getBusinessUserStatus(user.getUserId(), groupId);

			if (ownerRole.equals(businessRole) && !currentUserIsOwner) {
				throw new PortalException(
					"Error while updating members: User " + currentUser.getUserId() +
						" does not have the business OWNER role");
			}

			if (BusinessUserStatus.INVITED.equals(businessUserStatus) && ownerRole.equals(newRole)) {
				throw new PortalException("Error while updating members: Cannot set user with Invited status to OWNER");
			}

			_updateBusinessUserRole(user.getUserId(), groupId, newRole.getRoleId());
		}
	}

	private void _updateBusinessUserStatus(long userId, long groupId, BusinessUserStatus businessUserStatus)
		throws PortalException {

		User user = userLocalService.getUser(userId);

		Role invitedRole = roleLocalService.getRole(user.getCompanyId(), RoleConstants.SITE_MEMBER);

		if (BusinessUserStatus.INVITED.equals(businessUserStatus)) {
			userGroupRoleLocalService.addUserGroupRoles(userId, groupId, new long[] {invitedRole.getRoleId()});
		}
		else if (BusinessUserStatus.ACTIVE.equals(businessUserStatus)) {
			userGroupRoleLocalService.deleteUserGroupRoles(userId, groupId, new long[] {invitedRole.getRoleId()});
		}
	}

	private void _validateCMICUserDTO(CMICUserDTO cmicUserDTO) throws PortalException {
		if (cmicUserDTO == null) {
			throw new PortalException("Error: received invalid cmicUser information");
		}
	}

	private static final int _RECENT_ACCOUNT_ENTRIES_DISPLAY_COUNT = 5;

	private static final Log _log = LogFactoryUtil.getLog(CMICUserLocalServiceImpl.class);

}