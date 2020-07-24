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

import com.churchmutual.commons.constants.ExpandoConstants;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerAccountDetailsPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerAccountsPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerContactPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerDashboardPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerPolicyDetailsPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerProfileLinks;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerProfilePage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerResourcesPage;
import com.churchmutual.content.setup.upgrade.util.broker.BrokerUserRegistrationPage;
import com.churchmutual.content.setup.upgrade.util.common.BaseSiteUpgradeProcess;
import com.churchmutual.content.setup.upgrade.util.common.BrokerUpgradeConstants;

import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.expando.kernel.model.ExpandoColumnConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.UnicodeProperties;

/**
 * @author Matthew Chan
 */
public class AddBrokerSiteUpgradeProcess extends BaseSiteUpgradeProcess {

	public AddBrokerSiteUpgradeProcess(
		CompanyLocalService companyLocalService, DDMStructureLocalService ddmStructureLocalService,
		DDMTemplateLocalService ddmTemplateLocalService, ExpandoColumnLocalService expandoColumnLocalService,
		ExpandoTableLocalService expandoTableLocalService, GroupLocalService groupLocalService,
		JournalArticleLocalService journalArticleLocalService, LayoutSetLocalService layoutSetLocalService,
		PermissionCheckerFactory permissionCheckerFactory, Portal portal, RoleLocalService roleLocalService,
		UserLocalService userLocalService, VirtualHostLocalService virtualHostLocalService) {

		super(
			companyLocalService, ddmStructureLocalService, ddmTemplateLocalService, expandoColumnLocalService,
			expandoTableLocalService, groupLocalService, journalArticleLocalService, layoutSetLocalService,
			permissionCheckerFactory, portal, roleLocalService, userLocalService, virtualHostLocalService);

		this.portal = portal;
		this.userLocalService = userLocalService;
	}

	@Override
	protected void doUpgradeAsAdmin() throws Exception {
		long companyId = portal.getDefaultCompanyId();

		long brokerPortalGroupId = addPortalSite(companyId, BrokerUpgradeConstants.BROKER_SITE_NAME, "/broker");

		long userId = userLocalService.getDefaultUserId(companyId);

		addJournalArticle(brokerPortalGroupId, BrokerUpgradeConstants.PRODUCER_CONTACT_US_WEB_CONTENT_TITLE);

		UnicodeProperties properties = new UnicodeProperties();

		properties.put(ExpandoColumnConstants.PROPERTY_HIDDEN, "true");

		addExpandoColumn(
			companyId, User.class.getName(), ExpandoConstants.RECENTLY_VIEWED_CMIC_ACCOUNT_ENTRY_IDS,
			ExpandoColumnConstants.STRING, properties);

		_addProducerUsers(companyId, userId, brokerPortalGroupId);

		_addPrivatePages(companyId, userId, brokerPortalGroupId);

		_addPublicPages(companyId, userId, brokerPortalGroupId);
	}

	protected CompanyLocalService companyLocalService;
	protected DDMStructureLocalService ddmStructureLocalService;
	protected DDMTemplateLocalService ddmTemplateLocalService;
	protected JournalArticleLocalService journalArticleLocalService;
	protected Portal portal;
	protected UserLocalService userLocalService;

	private void _addPrivatePages(long companyId, long userId, long groupId) throws Exception {
		BrokerDashboardPage.addPage(companyId, userId, groupId);
		BrokerAccountsPage.addPage(companyId, userId, groupId);
		BrokerAccountDetailsPage.addPage(companyId, userId, groupId);
		BrokerPolicyDetailsPage.addPage(companyId, userId, groupId);
		BrokerResourcesPage.addPage(companyId, userId, groupId);
		BrokerContactPage.addPage(userId, groupId);
		BrokerProfilePage.addPage(companyId, userId, groupId);
	}

	private void _addProducerUsers(long companyId, long userId, long groupId) throws PortalException {
		addUser(companyId, userId, groupId, null, _DEFAULT_PASSWORD, _PRODUCER, _OWNER, "e7575932-9235-4829-8399-88d08d4c7542");
		addUser(companyId, userId, groupId, null, _DEFAULT_PASSWORD, _PRODUCER, _ADMIN, "8b6899dd-4f4d-4536-bf5f-780ebdb7701d");
		addUser(companyId, userId, groupId, null, _DEFAULT_PASSWORD, _PRODUCER, _MEMBER, "77985eaa-6dd4-4a5c-8004-17bde0a5bd73");
	}

	private void _addPublicPages(long companyId, long userId, long groupId) throws Exception {
		BrokerUserRegistrationPage.addPage(companyId, userId, groupId);
		BrokerProfileLinks.addLinks(companyId, userId, groupId);
	}

	private static final String _ADMIN = "Admin";

	private static final String _DEFAULT_PASSWORD = "cmicpassword";

	private static final String _MEMBER = "Member";

	private static final String _OWNER = "Owner";

	private static final String _PRODUCER = "Producer";

}