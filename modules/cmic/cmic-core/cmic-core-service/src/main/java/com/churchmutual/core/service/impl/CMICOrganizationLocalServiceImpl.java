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

import com.churchmutual.core.constants.ProducerType;
import com.churchmutual.core.model.CMICOrganization;
import com.churchmutual.core.model.CMICOrganizationDisplay;
import com.churchmutual.core.service.base.CMICOrganizationLocalServiceBaseImpl;
import com.churchmutual.rest.ProducerWebService;
import com.churchmutual.rest.model.CMICContactDTO;
import com.churchmutual.rest.model.CMICProducerDTO;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.Address;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.ListType;
import com.liferay.portal.kernel.model.ListTypeConstants;
import com.liferay.portal.kernel.model.Organization;
import com.liferay.portal.kernel.model.OrganizationConstants;
import com.liferay.portal.kernel.model.Phone;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.service.CountryService;
import com.liferay.portal.kernel.service.RegionService;
import com.liferay.portal.kernel.service.ServiceContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the cmic organization local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.churchmutual.core.service.CMICOrganizationLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICOrganizationLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.churchmutual.core.model.CMICOrganization", service = AopService.class)
public class CMICOrganizationLocalServiceImpl extends CMICOrganizationLocalServiceBaseImpl {

	@Override
	public CMICOrganization addCMICOrganization(long userId, long producerId) throws PortalException {
		CMICProducerDTO cmicProducerDTO = producerWebService.getProducerById(producerId);

		CMICOrganization cmicOrganization = fetchCMICOrganizationByProducerId(producerId);

		if (cmicOrganization != null) {
			return cmicOrganization;
		}

		Organization organization = organizationLocalService.addOrganization(
			userId, OrganizationConstants.DEFAULT_PARENT_ORGANIZATION_ID, cmicProducerDTO.getName(), false);

		long cmicOrganizationId = counterLocalService.increment(CMICOrganization.class.getName());

		cmicOrganization = createCMICOrganization(cmicOrganizationId);

		cmicOrganization.setOrganizationId(organization.getOrganizationId());
		cmicOrganization.setProducerId(producerId);
		cmicOrganization.setAgentNumber(cmicProducerDTO.getAgent());
		cmicOrganization.setDivisionNumber(cmicProducerDTO.getDivision());

		int producerType = ProducerType.getTypeFromName(cmicProducerDTO.getProducerType());

		cmicOrganization.setProducerType(producerType);

		cmicOrganization.setActive(true);

		cmicOrganization = cmicOrganizationPersistence.update(cmicOrganization);

		updateCMICOrganizationContactInfo(userId, producerId);

		return cmicOrganization;
	}

	@Override
	public CMICOrganization fetchCMICOrganizationByOrganizationId(long organizationId) {
		return cmicOrganizationPersistence.fetchByOrganizationId(organizationId);
	}

	@Override
	public CMICOrganization fetchCMICOrganizationByProducerId(long producerId) throws PortalException {
		return cmicOrganizationPersistence.fetchByProducerId(producerId);
	}

	@Override
	public List<CMICOrganizationDisplay> getCMICOrganizationDisplays(long userId) throws PortalException {
		List<CMICOrganization> cmicOrganizations = getCMICOrganizationsByUserId(userId);

		List<CMICOrganizationDisplay> cmicOrganizationDisplayList = new ArrayList<>();

		for (CMICOrganization cmicOrganization : cmicOrganizations) {
			Organization organization = organizationLocalService.getOrganization(cmicOrganization.getOrganizationId());

			Phone phone = _fetchPrimaryPhone(organization);

			CMICOrganizationDisplay cmicOrganizationDisplay = new CMICOrganizationDisplay(
				cmicOrganization, organization, organization.getAddress(), phone);

			cmicOrganizationDisplayList.add(cmicOrganizationDisplay);
		}

		return cmicOrganizationDisplayList;
	}

	@Override
	public List<CMICOrganization> getCMICOrganizationsByUserId(long userId) {
		List<Organization> organizations = organizationLocalService.getUserOrganizations(userId);

		return organizations.stream(
		).map(
			org -> fetchCMICOrganizationByOrganizationId(org.getOrganizationId())
		).filter(
			Objects::nonNull
		).collect(
			Collectors.toList()
		);
	}

	public CMICOrganization updateCMICOrganizationContactInfo(long userId, long producerId) throws PortalException {
		CMICOrganization cmicOrganization = fetchCMICOrganizationByProducerId(producerId);

		Organization organization = organizationLocalService.getOrganization(cmicOrganization.getOrganizationId());

		CMICContactDTO cmicContactDTO = producerWebService.getPrimaryContact(cmicOrganization.getProducerId());

		Address primaryAddress = _fetchPrimaryAddress(organization);

		if (primaryAddress == null) {
			_addAddress(userId, cmicContactDTO, organization);
		}
		else {
			_updateAddress(primaryAddress, cmicContactDTO);
		}

		Phone primaryPhone = _fetchPrimaryPhone(organization);

		if (primaryPhone == null) {
			_addPhone(userId, cmicContactDTO, organization);
		}
		else {
			_updatePhone(primaryPhone, cmicContactDTO);
		}

		return cmicOrganization;
	}

	@Reference
	protected CountryService countryService;

	@Reference
	protected ProducerWebService producerWebService;

	@Reference
	protected RegionService regionService;

	private void _addAddress(long userId, CMICContactDTO cmicContactDTO, Organization organization)
		throws PortalException {

		long countryId = 0L;
		long regionId = 0L;

		Country country = countryService.getCountryByA3(cmicContactDTO.getCountry());

		if (country != null) {
			countryId = country.getCountryId();

			regionService.getRegion(countryId, cmicContactDTO.getState());
		}

		long typeId = _toListTypeId("other", ListTypeConstants.ORGANIZATION_ADDRESS);

		ServiceContext serviceContext = new ServiceContext();

		addressLocalService.addAddress(
			userId, Organization.class.getName(), organization.getOrganizationId(), cmicContactDTO.getAddressLine1(),
			cmicContactDTO.getAddressLine2(), StringPool.BLANK, cmicContactDTO.getCity(),
			cmicContactDTO.getPostalCode(), regionId, countryId, typeId, true, true, serviceContext);
	}

	private void _addPhone(long userId, CMICContactDTO cmicContactDTO, Organization organization)
		throws PortalException {

		long typeId = _toListTypeId("other", ListTypeConstants.ORGANIZATION_PHONE);

		ServiceContext serviceContext = new ServiceContext();

		phoneLocalService.addPhone(
			userId, Organization.class.getName(), organization.getOrganizationId(), cmicContactDTO.getPhoneNumber(),
			StringPool.BLANK, typeId, true, serviceContext);
	}

	private Address _fetchPrimaryAddress(Organization organization) {
		List<Address> addresses = organization.getAddresses();

		for (Address address : addresses) {
			if (address.isPrimary()) {
				return address;
			}
		}

		return null;
	}

	private Phone _fetchPrimaryPhone(Organization organization) {
		List<Phone> phones = phoneLocalService.getPhones(
			organization.getCompanyId(), Organization.class.getName(), organization.getOrganizationId());

		for (Phone phone : phones) {
			if (phone.isPrimary()) {
				return phone;
			}
		}

		return null;
	}

	private long _toListTypeId(String name, String type) {
		ListType listType = listTypeLocalService.getListType(name, type);

		if (listType != null) {
			return listType.getListTypeId();
		}

		return 0;
	}

	private void _updateAddress(Address address, CMICContactDTO cmicContactDTO) throws PortalException {
		long countryId = 0L;
		long regionId = 0L;

		Country country = countryService.fetchCountryByA3(cmicContactDTO.getCountry());

		if (country != null) {
			countryId = country.getCountryId();

			Region region = regionService.fetchRegion(countryId, cmicContactDTO.getState());

			if (region != null) {
				regionId = region.getRegionId();
			}
		}

		addressLocalService.updateAddress(
			address.getAddressId(), cmicContactDTO.getAddressLine1(), cmicContactDTO.getAddressLine2(),
			StringPool.BLANK, cmicContactDTO.getCity(), cmicContactDTO.getPostalCode(), regionId, countryId,
			address.getTypeId(), address.getMailing(), true);
	}

	private void _updatePhone(Phone phone, CMICContactDTO cmicContactDTO) throws PortalException {
		phoneLocalService.updatePhone(
			phone.getPhoneId(), cmicContactDTO.getPhoneNumber(), StringPool.BLANK, phone.getTypeId(), true);
	}

}