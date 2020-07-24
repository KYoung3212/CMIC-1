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

package com.churchmutual.content.setup.upgrade.util.common;

import com.churchmutual.commons.constants.LayoutURLKeyConstants;
import com.churchmutual.content.setup.upgrade.util.v1_0_0.AddBrokerSiteUpgradeProcess;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.model.DDMTemplate;
import com.liferay.dynamic.data.mapping.model.DDMTemplateConstants;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalService;
import com.liferay.dynamic.data.mapping.service.DDMTemplateLocalService;
import com.liferay.expando.kernel.model.ExpandoColumn;
import com.liferay.expando.kernel.model.ExpandoTable;
import com.liferay.expando.kernel.model.ExpandoTableConstants;
import com.liferay.expando.kernel.service.ExpandoColumnLocalService;
import com.liferay.expando.kernel.service.ExpandoTableLocalService;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.model.JournalFolderConstants;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Company;
import com.liferay.portal.kernel.model.Group;
import com.liferay.portal.kernel.model.GroupConstants;
import com.liferay.portal.kernel.model.LayoutSet;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.permission.PermissionCheckerFactory;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.LayoutSetLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.service.VirtualHostLocalService;
import com.liferay.portal.kernel.template.TemplateConstants;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.PropsKeys;
import com.liferay.portal.kernel.util.PropsUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TreeMapBuilder;
import com.liferay.portal.kernel.util.UnicodeProperties;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author Luiz Marins
 */
public abstract class BaseSiteUpgradeProcess extends BaseAdminUpgradeProcess {

	public BaseSiteUpgradeProcess(
		CompanyLocalService companyLocalService, DDMStructureLocalService ddmStructureLocalService,
		DDMTemplateLocalService ddmTemplateLocalService, ExpandoColumnLocalService expandoColumnLocalService,
		ExpandoTableLocalService expandoTableLocalService, GroupLocalService groupLocalService,
		JournalArticleLocalService journalArticleLocalService, LayoutSetLocalService layoutSetLocalService,
		PermissionCheckerFactory permissionCheckerFactory, Portal portal, RoleLocalService roleLocalService,
		UserLocalService userLocalService, VirtualHostLocalService virtualHostLocalService) {

		super(permissionCheckerFactory, portal, roleLocalService, userLocalService);

		this.companyLocalService = companyLocalService;
		this.ddmStructureLocalService = ddmStructureLocalService;
		this.ddmTemplateLocalService = ddmTemplateLocalService;
		this.expandoColumnLocalService = expandoColumnLocalService;
		this.expandoTableLocalService = expandoTableLocalService;
		this.groupLocalService = groupLocalService;
		this.journalArticleLocalService = journalArticleLocalService;
		this.layoutSetLocalService = layoutSetLocalService;
		this.userLocalService = userLocalService;
		this.virtualHostLocalService = virtualHostLocalService;
	}

	protected void addExpandoColumn(
			long companyId, String className, String columnName, int dataType, UnicodeProperties properties)
		throws PortalException {

		ExpandoTable expandoTable = expandoTableLocalService.fetchTable(
			companyId, portal.getClassNameId(className), ExpandoTableConstants.DEFAULT_TABLE_NAME);

		if (expandoTable == null) {
			expandoTable = expandoTableLocalService.addTable(
				companyId, className, ExpandoTableConstants.DEFAULT_TABLE_NAME);
		}

		ExpandoColumn expandoColumn = expandoColumnLocalService.addColumn(
			expandoTable.getTableId(), columnName, dataType);

		expandoColumn.setTypeSettingsProperties(properties);

		expandoColumnLocalService.updateExpandoColumn(expandoColumn);

		if (_log.isInfoEnabled()) {
			_log.info(String.format("Added an expando column for className, %s, with name %s", className, columnName));
		}
	}

	protected Group addGroup(long companyId, String name, String description, int type, String friendlyURL)
		throws PortalException {

		ServiceContext serviceContext = getDefaultServiceContext(companyId);

		Map<Locale, String> nameMap = new HashMap<>();

		nameMap.put(LocaleUtil.getDefault(), name);

		Map<Locale, String> descriptionMap = new HashMap<>();

		descriptionMap.put(LocaleUtil.getDefault(), description);

		return groupLocalService.addGroup(
			userLocalService.getDefaultUserId(companyId), GroupConstants.DEFAULT_PARENT_GROUP_ID, null, 0, 0, nameMap,
			descriptionMap, type, true, GroupConstants.DEFAULT_MEMBERSHIP_RESTRICTION, friendlyURL, true, true,
			serviceContext);
	}

	protected void addJournalArticle(long groupId, String title) throws Exception {
		long companyId = portal.getDefaultCompanyId();

		long userId = userLocalService.getDefaultUserId(companyId);

		// Add template for basic web content structure

		String ddmStructureKey = "BASIC-WEB-CONTENT";

		long journalArticleClassNameId = portal.getClassNameId(JournalArticle.class);

		Company company = companyLocalService.getCompanyByMx(PropsUtil.get(PropsKeys.COMPANY_DEFAULT_WEB_ID));

		Group globalGroup = company.getGroup();

		long globalGroupId = globalGroup.getGroupId();

		DDMStructure ddmStructure = ddmStructureLocalService.getStructure(
			globalGroupId, journalArticleClassNameId, ddmStructureKey);

		long classPK = ddmStructure.getStructureId();

		Map<Locale, String> titleMap = _getTitleMap(groupId, title);
		Map<Locale, String> descriptionMap = new HashMap<>();
		String language = TemplateConstants.LANG_TYPE_FTL;

		String fileName = String.format("%s/%s.%s", _JOURNAL_CONTENT_DIR, title, TemplateConstants.LANG_TYPE_FTL);

		InputStream stream = AddBrokerSiteUpgradeProcess.class.getResourceAsStream(fileName);

		String script = StringUtil.read(stream);

		ServiceContext serviceContext = getDefaultServiceContext(companyId);

		serviceContext.setScopeGroupId(groupId);
		serviceContext.setAddGroupPermissions(true);

		DDMTemplate ddmTemplate = ddmTemplateLocalService.addTemplate(
			userId, groupId, portal.getClassNameId(DDMStructure.class), classPK,
			portal.getClassNameId(JournalArticle.class), titleMap, descriptionMap,
			DDMTemplateConstants.TEMPLATE_TYPE_DISPLAY, StringPool.BLANK, language, script, serviceContext);

		// Add empty web content article with template

		long folderId = JournalFolderConstants.DEFAULT_PARENT_FOLDER_ID;

		String content = _getDefaultContent(groupId);

		journalArticleLocalService.addArticle(
			userId, groupId, folderId, titleMap, descriptionMap, content, ddmStructureKey, ddmTemplate.getTemplateKey(),
			serviceContext);

		if (_log.isInfoEnabled()) {
			_log.info(String.format("Added a journal article with title %s for group with groupId %s", title, groupId));
		}
	}

	protected long addPortalSite(long companyId, String name, String friendlyURL) throws PortalException {
		return addPortalSite(companyId, name, friendlyURL, GroupConstants.TYPE_SITE_PRIVATE);
	}

	protected long addPortalSite(long companyId, String name, String friendlyURL, int siteType) throws PortalException {
		Group group = addGroup(companyId, name, StringPool.BLANK, siteType, friendlyURL);

		long groupId = group.getGroupId();

		String producerThemeId = LayoutURLKeyConstants.THEME_ID_CMIC_PRODUCER;

		String insuredThemeId = LayoutURLKeyConstants.THEME_ID_CMIC_INSURED;

		// Update site theme

		layoutSetLocalService.updateLookAndFeel(groupId, true, producerThemeId, StringPool.BLANK, StringPool.BLANK);

		layoutSetLocalService.updateLookAndFeel(groupId, false, insuredThemeId, StringPool.BLANK, StringPool.BLANK);

		return groupId;
	}

	protected User addUser(
			long companyId, long defaultUserId, long groupId, String emailAddress, String password, String firstName, String lastName, String externalReferenceCode)
		throws PortalException {

		if (Validator.isNull(emailAddress)) {
			emailAddress = String.format("%s.%s@liferay.com", firstName, lastName);
		}

		long[] groupIds = new long[]{groupId};

		User user = userLocalService.addUser(
			defaultUserId, companyId, false, password, password, true, null, emailAddress, 0, null, LocaleUtil.getDefault(),
			firstName, null, lastName, -1, -1, true, 1, 1, 1977, null, groupIds, null, null, null, false, null);

		if (Validator.isNotNull(externalReferenceCode)) {
			user.setExternalReferenceCode(externalReferenceCode);

			userLocalService.updateUser(user);
		}

		return user;
	}

	protected ServiceContext getDefaultServiceContext(long companyId) throws PortalException {
		ServiceContext serviceContext = new ServiceContext();

		serviceContext.setCompanyId(companyId);
		serviceContext.setUserId(userLocalService.getDefaultUserId(companyId));

		return serviceContext;
	}

	protected void updateVirtualHost(long companyId, long groupId, String hostname, boolean privateLayout)
		throws PortalException {

		if (Validator.isNotNull(virtualHostLocalService.fetchVirtualHost(hostname))) {
			return;
		}

		LayoutSet layoutSet = layoutSetLocalService.getLayoutSet(groupId, privateLayout);

		virtualHostLocalService.updateVirtualHosts(
			companyId, layoutSet.getLayoutSetId(),
			TreeMapBuilder.put(
				hostname, StringPool.BLANK
			).build());
	}

	protected CompanyLocalService companyLocalService;
	protected DDMStructureLocalService ddmStructureLocalService;
	protected DDMTemplateLocalService ddmTemplateLocalService;
	protected ExpandoColumnLocalService expandoColumnLocalService;
	protected ExpandoTableLocalService expandoTableLocalService;
	protected GroupLocalService groupLocalService;
	protected JournalArticleLocalService journalArticleLocalService;
	protected LayoutSetLocalService layoutSetLocalService;
	protected UserLocalService userLocalService;
	protected VirtualHostLocalService virtualHostLocalService;

	private String _getDefaultContent(long groupId) throws PortalException {
		Locale locale = portal.getSiteDefaultLocale(groupId);

		Document document = SAXReaderUtil.createDocument();

		Element rootElement = document.addElement("root");

		rootElement.addAttribute("available-locales", locale.toString());
		rootElement.addAttribute("default-locale", locale.toString());

		Element dynamicElementElement = rootElement.addElement("dynamic-element");

		dynamicElementElement.addAttribute("index-type", "text");
		dynamicElementElement.addAttribute("name", "content");
		dynamicElementElement.addAttribute("type", "text_area");
		dynamicElementElement.addAttribute("instance-id", StringUtil.randomId());

		Element element = dynamicElementElement.addElement("dynamic-content");

		element.addAttribute("language-id", LocaleUtil.toLanguageId(locale));
		element.addCDATA(StringPool.BLANK);

		return document.asXML();
	}

	private Map<Locale, String> _getTitleMap(long groupId, String title) throws PortalException {
		Locale defaultLocale = portal.getSiteDefaultLocale(groupId);

		Map<Locale, String> titleMap = new HashMap<>();

		titleMap.put(defaultLocale, title);

		return titleMap;
	}

	private static final String _JOURNAL_CONTENT_DIR = "/journal-content";

	private static final Log _log = LogFactoryUtil.getLog(BaseSiteUpgradeProcess.class);

}