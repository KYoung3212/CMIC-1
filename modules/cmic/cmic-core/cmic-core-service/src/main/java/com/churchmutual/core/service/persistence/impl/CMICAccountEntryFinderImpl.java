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

package com.churchmutual.core.service.persistence.impl;

import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.impl.CMICAccountEntryImpl;
import com.churchmutual.core.service.persistence.CMICAccountEntryFinder;

import com.liferay.portal.dao.orm.custom.sql.CustomSQL;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringUtil;

import java.util.List;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Matthew Chan
 */
@Component
public class CMICAccountEntryFinderImpl extends CMICAccountEntryFinderBaseImpl implements CMICAccountEntryFinder {

	@Override
	public List<CMICAccountEntry> findByUserIdOrderedByName(long userId, int start, int end) {
		List<CMICAccountEntry> list = null;

		Session session = null;

		try {
			session = openSession();

			String sql = customSQL.get(getClass(), _GET_CMIC_ACCOUNT_ENTRIES_BY_USER_ID_ORDERED_BY_NAME);

			sql = StringUtil.replace(sql, "[$USER_ID$]", String.valueOf(userId));

			SQLQuery sqlQuery = session.createSynchronizedSQLQuery(sql);

			sqlQuery.addEntity("cmic_CMICAccountEntry", CMICAccountEntryImpl.class);

			list = (List<CMICAccountEntry>)QueryUtil.list(sqlQuery, getDialect(), start, end);
		}
		catch (Exception e) {
			_log.error(e.getLocalizedMessage(), e);

			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return list;
	}

	@Reference
	protected CustomSQL customSQL;

	private static final String _GET_CMIC_ACCOUNT_ENTRIES_BY_USER_ID_ORDERED_BY_NAME =
		CMICAccountEntryFinder.class.getName() + ".getCMICAccountEntriesByUserIdOrderedByName";

	private static Log _log = LogFactoryUtil.getLog(CMICAccountEntryFinderImpl.class);

}