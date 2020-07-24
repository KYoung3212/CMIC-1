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
 * Provides a wrapper for {@link CMICPolicyLocalService}.
 *
 * @author Kayleen Lim
 * @see CMICPolicyLocalService
 * @generated
 */
public class CMICPolicyLocalServiceWrapper
	implements CMICPolicyLocalService, ServiceWrapper<CMICPolicyLocalService> {

	public CMICPolicyLocalServiceWrapper(
		CMICPolicyLocalService cmicPolicyLocalService) {

		_cmicPolicyLocalService = cmicPolicyLocalService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicPolicyLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICPolicyDisplay>
			getPolicyDisplays(long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicPolicyLocalService.getPolicyDisplays(cmicAccountEntryId);
	}

	@Override
	public CMICPolicyLocalService getWrappedService() {
		return _cmicPolicyLocalService;
	}

	@Override
	public void setWrappedService(
		CMICPolicyLocalService cmicPolicyLocalService) {

		_cmicPolicyLocalService = cmicPolicyLocalService;
	}

	private CMICPolicyLocalService _cmicPolicyLocalService;

}