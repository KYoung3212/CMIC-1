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
 * Provides a wrapper for {@link CMICPolicyService}.
 *
 * @author Kayleen Lim
 * @see CMICPolicyService
 * @generated
 */
public class CMICPolicyServiceWrapper
	implements CMICPolicyService, ServiceWrapper<CMICPolicyService> {

	public CMICPolicyServiceWrapper(CMICPolicyService cmicPolicyService) {
		_cmicPolicyService = cmicPolicyService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicPolicyService.getOSGiServiceIdentifier();
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICPolicyDisplay>
			getPolicyDisplays(long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicPolicyService.getPolicyDisplays(cmicAccountEntryId);
	}

	@Override
	public CMICPolicyService getWrappedService() {
		return _cmicPolicyService;
	}

	@Override
	public void setWrappedService(CMICPolicyService cmicPolicyService) {
		_cmicPolicyService = cmicPolicyService;
	}

	private CMICPolicyService _cmicPolicyService;

}