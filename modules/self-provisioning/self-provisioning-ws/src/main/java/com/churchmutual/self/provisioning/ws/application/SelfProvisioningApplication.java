package com.churchmutual.self.provisioning.ws.application;

import com.churchmutual.account.permissions.AccountEntryModelPermission;
import com.churchmutual.account.permissions.OrganizationModelPermission;
import com.churchmutual.commons.constants.CommonConstants;
import com.churchmutual.commons.enums.BusinessRole;
import com.churchmutual.self.provisioning.api.BusinessUserService;
import com.churchmutual.self.provisioning.api.SelfProvisioningBusinessService;
import com.churchmutual.self.provisioning.api.dto.UpdateBusinessMembersRequest;
import com.churchmutual.self.provisioning.ws.application.serializer.AccountUserSerializer;
import com.churchmutual.self.provisioning.ws.application.serializer.UpdateBusinessMembersDeserializer;
import com.liferay.account.constants.AccountActionKeys;
import com.liferay.account.model.AccountEntry;
import com.liferay.account.model.AccountEntryUserRel;
import com.liferay.account.service.AccountEntryLocalService;
import com.liferay.account.service.AccountEntryUserRelLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactory;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactoryUtil;
import com.liferay.portal.kernel.service.OrganizationLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.jaxrs.whiteboard.JaxrsWhiteboardConstants;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Matthew Chan
 * TODO Enable Access Control and define authentication method
 */
@Component(
	property = {
		JaxrsWhiteboardConstants.JAX_RS_APPLICATION_BASE + "=/self-provisioning",
		JaxrsWhiteboardConstants.JAX_RS_NAME + "=SelfProvisioning.Rest",
		"auth.verifier.guest.allowed=true",
		"liferay.access.control.disable=true"
	},
	service = Application.class
)
@Produces(MediaType.APPLICATION_JSON)
public class SelfProvisioningApplication extends Application {

	@GET
	@Path("/primary/{userId}/group/{groupId}")
	public Response getPrimaryUser(@PathParam("userId") long userId, @PathParam("groupId") long portalGroupId) {
		try {
			User user = _userLocalService.getUserById(userId);

			long groupId = _getRelatedGroupId(userId);

			JSONObject response = _accountUserSerializer.serialize(user, groupId);

			return _success(response);
		}
		catch (Exception pe) {
			return _handleError(pe);
		}
	}

	// TODO update implementation when backport is available DCTRL-2292

	@GET
	@Path("/{userId}/group/{groupId}")
	public Response getRelatedUsersList(@PathParam("userId") long userId, @PathParam("groupId") long portalGroupId) {
		try {
			List<User> usersList;

			if (_businessUserService.isProducerBusinessUser(userId)) {
				usersList = _getUsersFromOrganization(userId, true);
			}
			else {
				usersList = _getUsersFromAccountEntry(userId, true);
			}

			long groupId = _getRelatedGroupId(userId);

			JSONArray response = _accountUserSerializer.serialize(usersList, groupId);

			return _success(response);
		}
		catch (Exception pe) {
			return _handleError(pe);
		}
	}

	@GET
	@Path("/roleTypes/{userId}/group/{groupId}")
	public Response getRoleTypes(
		@PathParam("userId") long userId,
		@PathParam("groupId") long portalGroupId) {
		try {
			String businessType = _businessUserService.isProducerBusinessUser(userId)
				? CommonConstants.BUSINESS_TYPE_ORGANIZATION : CommonConstants.BUSINESS_TYPE_ACCOUNT;

			JSONArray response = JSONFactoryUtil.createJSONArray();

			for (BusinessRole businessRole : BusinessRole.getBusinessRoles(businessType)) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();

				jsonObject.put("label", businessRole.getShortenedNameKey());
				jsonObject.put("value", businessRole.getMessageKey());

				response.put(jsonObject);
			}

			return _success(response);
		}
		catch (Exception pe) {
			return _handleError(pe);
		}
	}

	public Set<Object> getSingletons() {
		return Collections.singleton(this);
	}

	@POST
	@Path("/invite-members/")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inviteMember(String jsonSelfProvisioningInfo) {
		try {
			JSONObject selfProvisioningInfo = _jsonFactory.createJSONObject(jsonSelfProvisioningInfo);

			String emails = selfProvisioningInfo.getString("emails");
			long portalGroupId = selfProvisioningInfo.getLong("groupId");
			long userId = selfProvisioningInfo.getLong("userId");

			String[] invitationEmails = emails.split(",");

			_addAccountUser(portalGroupId, invitationEmails, userId);

			JSONObject response = _jsonFactory.createJSONObject();

			return _success(response);
		}
		catch (Exception pe) {
			return _handleError(pe);
		}
	}

	@Path("/update-account-members/{company-id}/{user-id}/{group-id}")
	@POST
	public Response updateAccountMembers(
		@PathParam("company-id") long companyId,
	   	@PathParam("user-id") long userId,
	   	@PathParam("group-id") long passedGroupId,
		String body) {

		try {
			UpdateBusinessMembersRequest updateBusinessMembersRequest =
				_updateBusinessMembersDeserializer.deserialize(body);

			updateBusinessMembersRequest.setCompanyId(companyId);
			updateBusinessMembersRequest.setUserId(userId);
			updateBusinessMembersRequest.setGroupId(passedGroupId);

			selfProvisioningBusinessService.updateBusinessMembers(updateBusinessMembersRequest);

			return _success(_jsonFactory.createJSONObject());
		} catch (PortalException pe) {
			return _handleError(pe);
		}
	}

	private void _addAccountUser(long portalGroupId, String[] emails, long creatorUserId) throws PortalException {
		PermissionChecker permissionChecker = _getPermissionChecker(creatorUserId);

		if (_businessUserService.isProducerBusinessUser(creatorUserId)) {
			long organizationId = _businessUserService.getProducerOrganizationId(creatorUserId);

			long groupId = _organizationLocalService.getOrganization(organizationId).getGroupId();

			OrganizationModelPermission.check(
				permissionChecker, groupId, organizationId, AccountActionKeys.CREATE_ORGANIZATION_USER);

			selfProvisioningBusinessService.inviteBusinessUsersByEmail(emails, groupId, portalGroupId, creatorUserId, true);
		}
		else {
			long accountEntryId = _businessUserService.getUserAccountEntryId(creatorUserId);

			long groupId = _accountEntryLocalService.getAccountEntry(accountEntryId).getAccountEntryGroupId();

			AccountEntryModelPermission.check(
				permissionChecker, groupId, accountEntryId, AccountActionKeys.CREATE_ACCOUNT_ENTRY_USER);

			selfProvisioningBusinessService.inviteBusinessUsersByEmail(emails, groupId, portalGroupId, creatorUserId, false);
		}
	}

	private PermissionChecker _getPermissionChecker(long userId) throws PortalException {
		User user = _userLocalService.getUser(userId);

		PermissionCheckerFactory permissionCheckerFactory = PermissionCheckerFactoryUtil.getPermissionCheckerFactory();

		return permissionCheckerFactory.create(user);
	}

	private long _getRelatedGroupId(long userId) throws PortalException {
		if (_businessUserService.isProducerBusinessUser(userId)) {
			long organizationId = _businessUserService.getProducerOrganizationId(userId);

			Organization organization = _organizationLocalService.getOrganization(organizationId);

			return organization.getGroupId();
		}

		long accountEntryId = _businessUserService.getUserAccountEntryId(userId);

		AccountEntry accountEntry = _accountEntryLocalService.getAccountEntry(accountEntryId);

		return accountEntry.getAccountEntryGroupId();
	}

	private List<User> _getUsersFromAccountEntry(long insuredUserId, boolean onlyIncludeOtherUsers)
		throws PortalException {

		long accountEntryId = _businessUserService.getUserAccountEntryId(insuredUserId);

		List<AccountEntryUserRel> accountEntryUserRelList =
			_accountEntryUserRelLocalService.getAccountEntryUserRelsByAccountEntryId(accountEntryId);

		List<User> userList = accountEntryUserRelList.stream(
		).flatMap(
			a -> Stream.of(_userLocalService.fetchUser(a.getAccountUserId()))
		).collect(
			Collectors.toList()
		);

		if (onlyIncludeOtherUsers) {
			User insuredUser = _userLocalService.getUser(insuredUserId);

			userList.remove(insuredUser);
		}

		return userList;
	}

	private List<User> _getUsersFromOrganization(long producerUserId, boolean onlyIncludeOtherUsers)
		throws PortalException {

		long organizationId = _businessUserService.getProducerOrganizationId(producerUserId);

		List<User> userList = _userLocalService.getOrganizationUsers(organizationId);

		if (onlyIncludeOtherUsers) {
			User producerUser = _userLocalService.getUser(producerUserId);

			userList.remove(producerUser);
		}

		return userList;
	}

	private Response _handleError(Exception e) {
		_log.error(e);

		return Response.status(
			Response.Status.INTERNAL_SERVER_ERROR
		).entity(
			e.getMessage()
		).build();
	}

	private Response _success(JSONArray array) {
		return Response.status(
			Response.Status.OK
		).entity(
			array.toJSONString()
		).build();
	}

	private Response _success(JSONObject entity) {
		return Response.status(
			Response.Status.OK
		).entity(
			entity.toJSONString()
		).build();
	}

	private static final Log _log = LogFactoryUtil.getLog(SelfProvisioningApplication.class);

	@Reference
	private AccountEntryLocalService _accountEntryLocalService;

	@Reference
	private AccountEntryUserRelLocalService _accountEntryUserRelLocalService;

	@Reference
	private SelfProvisioningBusinessService selfProvisioningBusinessService;

	@Reference
	private AccountUserSerializer _accountUserSerializer;

	@Reference
	private BusinessUserService _businessUserService;

	@Reference
	private JSONFactory _jsonFactory;

	@Reference
	private OrganizationLocalService _organizationLocalService;

	@Reference
	private UserLocalService _userLocalService;

	@Reference
	private UpdateBusinessMembersDeserializer _updateBusinessMembersDeserializer;

}