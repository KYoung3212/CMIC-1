package com.churchmutual.core.model;

import com.churchmutual.commons.enums.BusinessRole;
import com.churchmutual.commons.enums.BusinessUserStatus;
import com.churchmutual.core.service.CMICOrganizationLocalServiceUtil;
import com.churchmutual.rest.model.CMICUserDTO;
import com.churchmutual.rest.model.CMICUserRelationDTO;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;

import java.util.ArrayList;
import java.util.List;

public class CMICUserDisplay {

	public CMICUserDisplay(CMICUserDTO cmicUserDTO, User user, String portraitImageURL) throws PortalException {
		if (user == null) {
			throw new PortalException("User cannot be null");
		}

		_email = user.getEmailAddress();

		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		_firstName = firstName;
		_fullName = firstName + StringPool.SPACE + lastName;
		_lastName = lastName;
		_portraitImageUrl = portraitImageURL;
		_userId = user.getUserId();

		if (cmicUserDTO != null) {
			List<CMICUserRelationDTO> cmicUserRelationDTOS = cmicUserDTO.getOrganizationList();

			for (CMICUserRelationDTO cmicUserRelationDTO : cmicUserRelationDTOS) {
				_organizationList.add(new CMICUserRelationDisplay(cmicUserRelationDTO));
			}

			_role = cmicUserDTO.getUserRole();
			_uuid = cmicUserDTO.getUuid();
		}
		else {
			long[] organizationIds = user.getOrganizationIds();

			for (long organizationId : organizationIds) {
				CMICOrganization cmicOrganization =
					CMICOrganizationLocalServiceUtil.fetchCMICOrganizationByOrganizationId(organizationId);

				_organizationList.add(new CMICUserRelationDisplay(cmicOrganization));
			}

			//TODO CMIC-415 populate _role with Liferay User's roles

			_uuid = user.getExternalReferenceCode();
		}
	}

	public String getEmail() {
		return _email;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getFullName() {
		return _fullName;
	}

	public String getLastName() {
		return _lastName;
	}

	public List<CMICUserRelationDisplay> getOrganizationList() {
		return _organizationList;
	}

	public String getPortraitImageUrl() {
		return _portraitImageUrl;
	}

	public String getRole() {
		return _role;
	}

	public String getStatus() {
		return _status;
	}

	public String getStatusKey() {
		return _statusKey;
	}

	public long getUserId() {
		return _userId;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setBusinessRole(BusinessRole businessRole) {
		if (businessRole != null) {
			_role = businessRole.getShortenedNameKey();
		}
	}

	public void setBusinessUserStatus(BusinessUserStatus businessUserStatus) {
		if (businessUserStatus != null) {
			_status = businessUserStatus.getUserStatusName();
			_statusKey = businessUserStatus.getMessageKey();

			if (BusinessUserStatus.INVITED.equals(businessUserStatus)) {
				_firstName = StringPool.DOUBLE_DASH;
				_lastName = StringPool.DOUBLE_DASH;
			}
		}
	}

	private String _email;
	private String _firstName;
	private String _fullName;
	private String _lastName;
	private List<CMICUserRelationDisplay> _organizationList = new ArrayList<>();
	private String _portraitImageUrl;
	private String _role;
	private String _status;
	private String _statusKey;
	private long _userId;
	private String _uuid;

}