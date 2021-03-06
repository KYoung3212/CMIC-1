package com.churchmutual.rest;

import com.churchmutual.rest.model.CMICUserDTO;

import com.liferay.portal.kernel.exception.PortalException;

import java.util.List;

/**
 * @author Kayleen Lim
 */
public interface PortalUserWebService {

	public List<CMICUserDTO> getCMICOrganizationUsers(long producerId);

	public List<CMICUserDTO> getProducerEntityUsers(long producerId) throws PortalException;

	public CMICUserDTO getUserDetails(String uuid) throws PortalException;

	public void inviteUserToCMICOrganization(String emailAddress, long producerId);

	public boolean isUserRegistered(String uuid);

	public boolean isUserValid(String agentNumber, String divisionNumber, String registrationCode, String uuid);

	public void removeUserFromCMICOrganization(String cmicUUID, long producerId);

	public CMICUserDTO validateUser(String registrationCode);

	public CMICUserDTO validateUserRegistration(String registrationCode);

}