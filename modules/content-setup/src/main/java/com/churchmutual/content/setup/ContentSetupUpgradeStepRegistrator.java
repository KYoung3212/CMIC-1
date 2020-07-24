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

package com.churchmutual.content.setup;

import com.churchmutual.content.setup.upgrade.util.v1_0_0.AddBrokerSiteUpgradeProcess;
import com.churchmutual.content.setup.upgrade.util.v1_0_0.AddRolesUpgradeProcess;
import com.churchmutual.user.registration.constants.UserRegistrationPortletKeys;

import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.kernel.model.Portlet;
import com.liferay.portal.kernel.model.Release;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.upgrade.registry.UpgradeStepRegistrator;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Chan
 */
@Component(immediate = true, service = UpgradeStepRegistrator.class)
public class ContentSetupUpgradeStepRegistrator implements UpgradeStepRegistrator {

	@Override
	public void register(Registry registry) {
		registry.register(
			"0.0.0", "1.0.0",
			new AddBrokerSiteUpgradeProcess(
				companyLocalService, ddmStructureLocalService, ddmTemplateLocalService, expandoColumnLocalService,
				expandoTableLocalService, groupLocalService, journalArticleLocalService, layoutSetLocalService,
				permissionCheckerFactory, portal, roleLocalService, userLocalService, virtualHostLocalService),
			new AddRolesUpgradeProcess());
	}

	@Reference(target = "(javax.portlet.name=com_churchmutual_account_list_web_portlet_AccountListWebPortlet)")
	protected Portlet accountListWebPortlet;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_account_producer_dashboard_web_portlet_AccountProducerDashboardWebPortlet)"
	)
	protected Portlet accountProducerDashboardWebPortlet;

	@Reference(target = "(javax.portlet.name=com_churchmutual_commission_web_portlet_CommissionWebPortlet)")
	protected Portlet commissionWebPortlet;

	@Reference
	protected CompanyLocalService companyLocalService;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_contact_producer_dashboard_web_portlet_ContactProducerDashboardWebPortlet)"
	)
	protected Portlet contactProducerDashboardWebPortlet;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_contact_producer_list_web_portlet_ContactProducerListWebPortlet)"
	)
	protected Portlet contactProducerListWebPortlet;

	@Reference
	protected DDMStructureLocalService ddmStructureLocalService;

	@Reference
	protected DDMTemplateLocalService ddmTemplateLocalService;

	@Reference
	protected ExpandoColumnLocalService expandoColumnLocalService;

	@Reference
	protected ExpandoTableLocalService expandoTableLocalService;

	@Reference
	protected GroupLocalService groupLocalService;

	@Reference
	protected JournalArticleLocalService journalArticleLocalService;

	@Reference(target = "(javax.portlet.name=com_liferay_journal_content_web_portlet_JournalContentPortlet)")
	protected Portlet journalContentPortlet;

	@Reference
	protected LayoutSetLocalService layoutSetLocalService;

	@Reference(target = "(javax.portlet.name=com_churchmutual_loss_run_report_web_portlet_LossRunReportWebPortlet)")
	protected Portlet lossRunReportWebPortlet;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_organization_profile_web_portlet_OrganizationProfileWebPortlet)"
	)
	protected Portlet organizationProfileWebPortlet;

	@Reference
	protected PermissionCheckerFactory permissionCheckerFactory;

	@Reference(target = "(javax.portlet.name=com_churchmutual_policy_list_web_portlet_PolicyListWebPortlet)")
	protected Portlet policyListWebPortlet;

	@Reference
	protected Portal portal;

	@Reference(target = "(javax.portlet.name=com_churchmutual_profile_web_portlet_ProfileWebPortlet)")
	protected Portlet profileWebPortlet;

	@Reference(
		target = "(&(release.bundle.symbolic.name=com.liferay.adaptive.media.document.library.thumbnails)(release.schema.version>=1.0.1))",
		unbind = "-"
	)
	protected Release release;

	@Reference
	protected RoleLocalService roleLocalService;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_self_provisioning_invite_insured_web_portlet_SelfProvisioningInviteInsuredWebPortlet)"
	)
	protected Portlet selfProvisioningInviteInsuredWebPortlet;

	@Reference(
		target = "(javax.portlet.name=com_churchmutual_self_provisioning_web_portlet_SelfProvisioningWebPortlet)"
	)
	protected Portlet selfProvisioningWebPortlet;

	@Reference(target = "(javax.portlet.name=com_churchmutual_transaction_list_web_portlet_TransactionListWebPortlet)")
	protected Portlet transactionListWebPortlet;

	@Reference
	protected UserLocalService userLocalService;

	@Reference(target = "(javax.portlet.name=" + UserRegistrationPortletKeys.USER_REGISTRATION_WEB + ")")
	protected Portlet userRegistrationWebPortlet;

	@Reference
	protected VirtualHostLocalService virtualHostLocalService;

}