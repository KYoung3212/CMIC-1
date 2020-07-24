package com.churchmutual.rest.model;

import java.util.List;

/**
 * @author Kayleen Lim
 */
public class CMICUserDTO extends CMICObjectDTO {

	public int getId() {
		return _id;
	}

	public List<CMICUserRelationDTO> getOrganizationList() {
		return _organizationList;
	}

	public String getRegistrationCode() {
		return _registrationCode;
	}

	public String getRegistrationExpirationDate() {
		return _registrationExpirationDate;
	}

	public String getUserRole() {
		return _userRole;
	}

	public String getUserStatusText() {
		return _userStatusText;
	}

	public String getUuid() {
		return _uuid;
	}

	public void setId(int id) {
		_id = id;
	}

	public void setOrganizationList(List<CMICUserRelationDTO> organizationList) {
		_organizationList = organizationList;
	}

	public void setRegistrationCode(String registrationCode) {
		_registrationCode = registrationCode;
	}

	public void setRegistrationExpirationDate(String registrationExpirationDate) {
		_registrationExpirationDate = registrationExpirationDate;
	}

	public void setUserRole(String userRole) {
		_userRole = userRole;
	}

	public void setUserStatusText(String userStatusText) {
		_userStatusText = userStatusText;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	private int _id;
	private List<CMICUserRelationDTO> _organizationList;
	private String _registrationCode;
	private String _registrationExpirationDate;
	private String _userRole;
	private String _userStatusText;
	private String _uuid;

}