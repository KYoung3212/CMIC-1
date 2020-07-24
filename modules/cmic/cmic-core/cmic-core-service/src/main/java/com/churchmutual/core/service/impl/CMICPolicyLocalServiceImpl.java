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

import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.CMICPolicyDisplay;
import com.churchmutual.core.service.CMICAccountEntryLocalService;
import com.churchmutual.core.service.base.CMICPolicyLocalServiceBaseImpl;
import com.churchmutual.rest.PolicyWebService;
import com.churchmutual.rest.model.CMICPolicyDTO;

import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;

import java.util.ArrayList;
import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The implementation of the cmic policy local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the <code>com.churchmutual.core.service.CMICPolicyLocalService</code> interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICPolicyLocalServiceBaseImpl
 */
@Component(property = "model.class.name=com.churchmutual.core.model.CMICPolicy", service = AopService.class)
public class CMICPolicyLocalServiceImpl extends CMICPolicyLocalServiceBaseImpl {

	@Override
	public List<CMICPolicyDisplay> getPolicyDisplays(long cmicAccountEntryId) throws PortalException {
		CMICAccountEntry cmicAccountEntry = cmicAccountEntryLocalService.getCMICAccountEntry(cmicAccountEntryId);

		List<CMICPolicyDTO> cmicPolicyDTOs = policyWebService.getPoliciesOnAccount(cmicAccountEntry.getAccountNumber());

		List<CMICPolicyDisplay> cmicPolicyDisplays = new ArrayList<>();

		for (CMICPolicyDTO cmicPolicyDTO : cmicPolicyDTOs) {
			cmicPolicyDisplays.add(new CMICPolicyDisplay(cmicPolicyDTO));
		}

		return cmicPolicyDisplays;
	}

	@Reference
	protected CMICAccountEntryLocalService cmicAccountEntryLocalService;

	@Reference
	protected PolicyWebService policyWebService;

}