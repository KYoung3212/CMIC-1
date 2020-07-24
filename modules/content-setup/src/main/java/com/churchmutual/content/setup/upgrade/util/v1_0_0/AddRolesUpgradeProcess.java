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

package com.churchmutual.content.setup.upgrade.util.v1_0_0;

import com.churchmutual.commons.enums.BusinessCompanyRole;
import com.churchmutual.commons.enums.BusinessRole;
import com.churchmutual.commons.util.RoleHelper;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.Role;
import com.liferay.portal.kernel.model.role.RoleConstants;
import com.liferay.portal.kernel.security.permission.ActionKeys;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

/**
 * @author Matthew Chan
 */
public class AddRolesUpgradeProcess extends UpgradeProcess {

	@Override
	protected void doUpgrade() throws Exception {
		String[] groupResourceActionIds = {ActionKeys.VIEW};

		Role brokerRole = RoleHelper.addRole(
			BusinessCompanyRole.BROKER.getRoleName(), StringPool.BLANK, RoleConstants.TYPE_ORGANIZATION);

		Role underwriterRole = RoleHelper.addRole(
			BusinessCompanyRole.UNDERWRITER.getRoleName(), StringPool.BLANK, RoleConstants.TYPE_ORGANIZATION);

		Role insuredRole = RoleHelper.addRole(
			BusinessCompanyRole.INSURED.getRoleName(), StringPool.BLANK, RoleConstants.TYPE_SITE);

		Role organizationMemberRole = RoleHelper.addRole(
			BusinessRole.ORGANIZATION_MEMBER.getRoleName(), StringPool.BLANK, RoleConstants.TYPE_ORGANIZATION);

		Role[] roles = {brokerRole, underwriterRole, insuredRole, organizationMemberRole};

		RoleHelper.addResourcePermissionWithGroupTemplateScope(roles, groupResourceActionIds, Group.class.getName());
	}

}