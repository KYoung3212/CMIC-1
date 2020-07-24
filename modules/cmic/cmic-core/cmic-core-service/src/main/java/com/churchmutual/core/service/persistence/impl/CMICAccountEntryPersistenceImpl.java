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

import com.churchmutual.core.exception.NoSuchCMICAccountEntryException;
import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.impl.CMICAccountEntryImpl;
import com.churchmutual.core.model.impl.CMICAccountEntryModelImpl;
import com.churchmutual.core.service.persistence.CMICAccountEntryPersistence;
import com.churchmutual.core.service.persistence.impl.constants.cmicPersistenceConstants;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.configuration.Configuration;
import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ProxyUtil;

import java.io.Serializable;

import java.lang.reflect.InvocationHandler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.sql.DataSource;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;

/**
 * The persistence implementation for the cmic account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Kayleen Lim
 * @generated
 */
@Component(service = CMICAccountEntryPersistence.class)
public class CMICAccountEntryPersistenceImpl
	extends BasePersistenceImpl<CMICAccountEntry>
	implements CMICAccountEntryPersistence {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use <code>CMICAccountEntryUtil</code> to access the cmic account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY =
		CMICAccountEntryImpl.class.getName();

	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List1";

	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION =
		FINDER_CLASS_NAME_ENTITY + ".List2";

	private FinderPath _finderPathWithPaginationFindAll;
	private FinderPath _finderPathWithoutPaginationFindAll;
	private FinderPath _finderPathCountAll;
	private FinderPath _finderPathFetchByAccountEntryId;
	private FinderPath _finderPathCountByAccountEntryId;

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry findByAccountEntryId(long accountEntryId)
		throws NoSuchCMICAccountEntryException {

		CMICAccountEntry cmicAccountEntry = fetchByAccountEntryId(
			accountEntryId);

		if (cmicAccountEntry == null) {
			StringBundler msg = new StringBundler(4);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountEntryId=");
			msg.append(accountEntryId);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCMICAccountEntryException(msg.toString());
		}

		return cmicAccountEntry;
	}

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry fetchByAccountEntryId(long accountEntryId) {
		return fetchByAccountEntryId(accountEntryId, true);
	}

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry fetchByAccountEntryId(
		long accountEntryId, boolean useFinderCache) {

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {accountEntryId};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAccountEntryId, finderArgs, this);
		}

		if (result instanceof CMICAccountEntry) {
			CMICAccountEntry cmicAccountEntry = (CMICAccountEntry)result;

			if ((accountEntryId != cmicAccountEntry.getAccountEntryId())) {
				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_SELECT_CMICACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				List<CMICAccountEntry> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAccountEntryId, finderArgs, list);
					}
				}
				else {
					CMICAccountEntry cmicAccountEntry = list.get(0);

					result = cmicAccountEntry;

					cacheResult(cmicAccountEntry);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByAccountEntryId, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CMICAccountEntry)result;
		}
	}

	/**
	 * Removes the cmic account entry where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the cmic account entry that was removed
	 */
	@Override
	public CMICAccountEntry removeByAccountEntryId(long accountEntryId)
		throws NoSuchCMICAccountEntryException {

		CMICAccountEntry cmicAccountEntry = findByAccountEntryId(
			accountEntryId);

		return remove(cmicAccountEntry);
	}

	/**
	 * Returns the number of cmic account entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching cmic account entries
	 */
	@Override
	public int countByAccountEntryId(long accountEntryId) {
		FinderPath finderPath = _finderPathCountByAccountEntryId;

		Object[] finderArgs = new Object[] {accountEntryId};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(2);

			query.append(_SQL_COUNT_CMICACCOUNTENTRY_WHERE);

			query.append(_FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2);

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				qPos.add(accountEntryId);

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_ACCOUNTENTRYID_ACCOUNTENTRYID_2 =
		"cmicAccountEntry.accountEntryId = ?";

	private FinderPath _finderPathFetchByAN_CN;
	private FinderPath _finderPathCountByAN_CN;

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry findByAN_CN(
			String accountNumber, String companyNumber)
		throws NoSuchCMICAccountEntryException {

		CMICAccountEntry cmicAccountEntry = fetchByAN_CN(
			accountNumber, companyNumber);

		if (cmicAccountEntry == null) {
			StringBundler msg = new StringBundler(6);

			msg.append(_NO_SUCH_ENTITY_WITH_KEY);

			msg.append("accountNumber=");
			msg.append(accountNumber);

			msg.append(", companyNumber=");
			msg.append(companyNumber);

			msg.append("}");

			if (_log.isDebugEnabled()) {
				_log.debug(msg.toString());
			}

			throw new NoSuchCMICAccountEntryException(msg.toString());
		}

		return cmicAccountEntry;
	}

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber) {

		return fetchByAN_CN(accountNumber, companyNumber, true);
	}

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	@Override
	public CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber, boolean useFinderCache) {

		accountNumber = Objects.toString(accountNumber, "");
		companyNumber = Objects.toString(companyNumber, "");

		Object[] finderArgs = null;

		if (useFinderCache) {
			finderArgs = new Object[] {accountNumber, companyNumber};
		}

		Object result = null;

		if (useFinderCache) {
			result = finderCache.getResult(
				_finderPathFetchByAN_CN, finderArgs, this);
		}

		if (result instanceof CMICAccountEntry) {
			CMICAccountEntry cmicAccountEntry = (CMICAccountEntry)result;

			if (!Objects.equals(
					accountNumber, cmicAccountEntry.getAccountNumber()) ||
				!Objects.equals(
					companyNumber, cmicAccountEntry.getCompanyNumber())) {

				result = null;
			}
		}

		if (result == null) {
			StringBundler query = new StringBundler(4);

			query.append(_SQL_SELECT_CMICACCOUNTENTRY_WHERE);

			boolean bindAccountNumber = false;

			if (accountNumber.isEmpty()) {
				query.append(_FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_3);
			}
			else {
				bindAccountNumber = true;

				query.append(_FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_2);
			}

			boolean bindCompanyNumber = false;

			if (companyNumber.isEmpty()) {
				query.append(_FINDER_COLUMN_AN_CN_COMPANYNUMBER_3);
			}
			else {
				bindCompanyNumber = true;

				query.append(_FINDER_COLUMN_AN_CN_COMPANYNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountNumber) {
					qPos.add(accountNumber);
				}

				if (bindCompanyNumber) {
					qPos.add(companyNumber);
				}

				List<CMICAccountEntry> list = q.list();

				if (list.isEmpty()) {
					if (useFinderCache) {
						finderCache.putResult(
							_finderPathFetchByAN_CN, finderArgs, list);
					}
				}
				else {
					CMICAccountEntry cmicAccountEntry = list.get(0);

					result = cmicAccountEntry;

					cacheResult(cmicAccountEntry);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(
						_finderPathFetchByAN_CN, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		if (result instanceof List<?>) {
			return null;
		}
		else {
			return (CMICAccountEntry)result;
		}
	}

	/**
	 * Removes the cmic account entry where accountNumber = &#63; and companyNumber = &#63; from the database.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the cmic account entry that was removed
	 */
	@Override
	public CMICAccountEntry removeByAN_CN(
			String accountNumber, String companyNumber)
		throws NoSuchCMICAccountEntryException {

		CMICAccountEntry cmicAccountEntry = findByAN_CN(
			accountNumber, companyNumber);

		return remove(cmicAccountEntry);
	}

	/**
	 * Returns the number of cmic account entries where accountNumber = &#63; and companyNumber = &#63;.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the number of matching cmic account entries
	 */
	@Override
	public int countByAN_CN(String accountNumber, String companyNumber) {
		accountNumber = Objects.toString(accountNumber, "");
		companyNumber = Objects.toString(companyNumber, "");

		FinderPath finderPath = _finderPathCountByAN_CN;

		Object[] finderArgs = new Object[] {accountNumber, companyNumber};

		Long count = (Long)finderCache.getResult(finderPath, finderArgs, this);

		if (count == null) {
			StringBundler query = new StringBundler(3);

			query.append(_SQL_COUNT_CMICACCOUNTENTRY_WHERE);

			boolean bindAccountNumber = false;

			if (accountNumber.isEmpty()) {
				query.append(_FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_3);
			}
			else {
				bindAccountNumber = true;

				query.append(_FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_2);
			}

			boolean bindCompanyNumber = false;

			if (companyNumber.isEmpty()) {
				query.append(_FINDER_COLUMN_AN_CN_COMPANYNUMBER_3);
			}
			else {
				bindCompanyNumber = true;

				query.append(_FINDER_COLUMN_AN_CN_COMPANYNUMBER_2);
			}

			String sql = query.toString();

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				QueryPos qPos = QueryPos.getInstance(q);

				if (bindAccountNumber) {
					qPos.add(accountNumber);
				}

				if (bindCompanyNumber) {
					qPos.add(companyNumber);
				}

				count = (Long)q.uniqueResult();

				finderCache.putResult(finderPath, finderArgs, count);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	private static final String _FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_2 =
		"cmicAccountEntry.accountNumber = ? AND ";

	private static final String _FINDER_COLUMN_AN_CN_ACCOUNTNUMBER_3 =
		"(cmicAccountEntry.accountNumber IS NULL OR cmicAccountEntry.accountNumber = '') AND ";

	private static final String _FINDER_COLUMN_AN_CN_COMPANYNUMBER_2 =
		"cmicAccountEntry.companyNumber = ?";

	private static final String _FINDER_COLUMN_AN_CN_COMPANYNUMBER_3 =
		"(cmicAccountEntry.companyNumber IS NULL OR cmicAccountEntry.companyNumber = '')";

	public CMICAccountEntryPersistenceImpl() {
		setModelClass(CMICAccountEntry.class);

		setModelImplClass(CMICAccountEntryImpl.class);
		setModelPKClass(long.class);
	}

	/**
	 * Caches the cmic account entry in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 */
	@Override
	public void cacheResult(CMICAccountEntry cmicAccountEntry) {
		entityCache.putResult(
			entityCacheEnabled, CMICAccountEntryImpl.class,
			cmicAccountEntry.getPrimaryKey(), cmicAccountEntry);

		finderCache.putResult(
			_finderPathFetchByAccountEntryId,
			new Object[] {cmicAccountEntry.getAccountEntryId()},
			cmicAccountEntry);

		finderCache.putResult(
			_finderPathFetchByAN_CN,
			new Object[] {
				cmicAccountEntry.getAccountNumber(),
				cmicAccountEntry.getCompanyNumber()
			},
			cmicAccountEntry);

		cmicAccountEntry.resetOriginalValues();
	}

	/**
	 * Caches the cmic account entries in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntries the cmic account entries
	 */
	@Override
	public void cacheResult(List<CMICAccountEntry> cmicAccountEntries) {
		for (CMICAccountEntry cmicAccountEntry : cmicAccountEntries) {
			if (entityCache.getResult(
					entityCacheEnabled, CMICAccountEntryImpl.class,
					cmicAccountEntry.getPrimaryKey()) == null) {

				cacheResult(cmicAccountEntry);
			}
			else {
				cmicAccountEntry.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all cmic account entries.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(CMICAccountEntryImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the cmic account entry.
	 *
	 * <p>
	 * The <code>EntityCache</code> and <code>FinderCache</code> are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(CMICAccountEntry cmicAccountEntry) {
		entityCache.removeResult(
			entityCacheEnabled, CMICAccountEntryImpl.class,
			cmicAccountEntry.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		clearUniqueFindersCache(
			(CMICAccountEntryModelImpl)cmicAccountEntry, true);
	}

	@Override
	public void clearCache(List<CMICAccountEntry> cmicAccountEntries) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (CMICAccountEntry cmicAccountEntry : cmicAccountEntries) {
			entityCache.removeResult(
				entityCacheEnabled, CMICAccountEntryImpl.class,
				cmicAccountEntry.getPrimaryKey());

			clearUniqueFindersCache(
				(CMICAccountEntryModelImpl)cmicAccountEntry, true);
		}
	}

	protected void cacheUniqueFindersCache(
		CMICAccountEntryModelImpl cmicAccountEntryModelImpl) {

		Object[] args = new Object[] {
			cmicAccountEntryModelImpl.getAccountEntryId()
		};

		finderCache.putResult(
			_finderPathCountByAccountEntryId, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByAccountEntryId, args, cmicAccountEntryModelImpl,
			false);

		args = new Object[] {
			cmicAccountEntryModelImpl.getAccountNumber(),
			cmicAccountEntryModelImpl.getCompanyNumber()
		};

		finderCache.putResult(
			_finderPathCountByAN_CN, args, Long.valueOf(1), false);
		finderCache.putResult(
			_finderPathFetchByAN_CN, args, cmicAccountEntryModelImpl, false);
	}

	protected void clearUniqueFindersCache(
		CMICAccountEntryModelImpl cmicAccountEntryModelImpl,
		boolean clearCurrent) {

		if (clearCurrent) {
			Object[] args = new Object[] {
				cmicAccountEntryModelImpl.getAccountEntryId()
			};

			finderCache.removeResult(_finderPathCountByAccountEntryId, args);
			finderCache.removeResult(_finderPathFetchByAccountEntryId, args);
		}

		if ((cmicAccountEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByAccountEntryId.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cmicAccountEntryModelImpl.getOriginalAccountEntryId()
			};

			finderCache.removeResult(_finderPathCountByAccountEntryId, args);
			finderCache.removeResult(_finderPathFetchByAccountEntryId, args);
		}

		if (clearCurrent) {
			Object[] args = new Object[] {
				cmicAccountEntryModelImpl.getAccountNumber(),
				cmicAccountEntryModelImpl.getCompanyNumber()
			};

			finderCache.removeResult(_finderPathCountByAN_CN, args);
			finderCache.removeResult(_finderPathFetchByAN_CN, args);
		}

		if ((cmicAccountEntryModelImpl.getColumnBitmask() &
			 _finderPathFetchByAN_CN.getColumnBitmask()) != 0) {

			Object[] args = new Object[] {
				cmicAccountEntryModelImpl.getOriginalAccountNumber(),
				cmicAccountEntryModelImpl.getOriginalCompanyNumber()
			};

			finderCache.removeResult(_finderPathCountByAN_CN, args);
			finderCache.removeResult(_finderPathFetchByAN_CN, args);
		}
	}

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	@Override
	public CMICAccountEntry create(long cmicAccountEntryId) {
		CMICAccountEntry cmicAccountEntry = new CMICAccountEntryImpl();

		cmicAccountEntry.setNew(true);
		cmicAccountEntry.setPrimaryKey(cmicAccountEntryId);

		return cmicAccountEntry;
	}

	/**
	 * Removes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public CMICAccountEntry remove(long cmicAccountEntryId)
		throws NoSuchCMICAccountEntryException {

		return remove((Serializable)cmicAccountEntryId);
	}

	/**
	 * Removes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public CMICAccountEntry remove(Serializable primaryKey)
		throws NoSuchCMICAccountEntryException {

		Session session = null;

		try {
			session = openSession();

			CMICAccountEntry cmicAccountEntry = (CMICAccountEntry)session.get(
				CMICAccountEntryImpl.class, primaryKey);

			if (cmicAccountEntry == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchCMICAccountEntryException(
					_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			return remove(cmicAccountEntry);
		}
		catch (NoSuchCMICAccountEntryException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected CMICAccountEntry removeImpl(CMICAccountEntry cmicAccountEntry) {
		Session session = null;

		try {
			session = openSession();

			if (!session.contains(cmicAccountEntry)) {
				cmicAccountEntry = (CMICAccountEntry)session.get(
					CMICAccountEntryImpl.class,
					cmicAccountEntry.getPrimaryKeyObj());
			}

			if (cmicAccountEntry != null) {
				session.delete(cmicAccountEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (cmicAccountEntry != null) {
			clearCache(cmicAccountEntry);
		}

		return cmicAccountEntry;
	}

	@Override
	public CMICAccountEntry updateImpl(CMICAccountEntry cmicAccountEntry) {
		boolean isNew = cmicAccountEntry.isNew();

		if (!(cmicAccountEntry instanceof CMICAccountEntryModelImpl)) {
			InvocationHandler invocationHandler = null;

			if (ProxyUtil.isProxyClass(cmicAccountEntry.getClass())) {
				invocationHandler = ProxyUtil.getInvocationHandler(
					cmicAccountEntry);

				throw new IllegalArgumentException(
					"Implement ModelWrapper in cmicAccountEntry proxy " +
						invocationHandler.getClass());
			}

			throw new IllegalArgumentException(
				"Implement ModelWrapper in custom CMICAccountEntry implementation " +
					cmicAccountEntry.getClass());
		}

		CMICAccountEntryModelImpl cmicAccountEntryModelImpl =
			(CMICAccountEntryModelImpl)cmicAccountEntry;

		Session session = null;

		try {
			session = openSession();

			if (cmicAccountEntry.isNew()) {
				session.save(cmicAccountEntry);

				cmicAccountEntry.setNew(false);
			}
			else {
				cmicAccountEntry = (CMICAccountEntry)session.merge(
					cmicAccountEntry);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (!_columnBitmaskEnabled) {
			finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
		}
		else if (isNew) {
			finderCache.removeResult(_finderPathCountAll, FINDER_ARGS_EMPTY);
			finderCache.removeResult(
				_finderPathWithoutPaginationFindAll, FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(
			entityCacheEnabled, CMICAccountEntryImpl.class,
			cmicAccountEntry.getPrimaryKey(), cmicAccountEntry, false);

		clearUniqueFindersCache(cmicAccountEntryModelImpl, false);
		cacheUniqueFindersCache(cmicAccountEntryModelImpl);

		cmicAccountEntry.resetOriginalValues();

		return cmicAccountEntry;
	}

	/**
	 * Returns the cmic account entry with the primary key or throws a <code>com.liferay.portal.kernel.exception.NoSuchModelException</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public CMICAccountEntry findByPrimaryKey(Serializable primaryKey)
		throws NoSuchCMICAccountEntryException {

		CMICAccountEntry cmicAccountEntry = fetchByPrimaryKey(primaryKey);

		if (cmicAccountEntry == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchCMICAccountEntryException(
				_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
		}

		return cmicAccountEntry;
	}

	/**
	 * Returns the cmic account entry with the primary key or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public CMICAccountEntry findByPrimaryKey(long cmicAccountEntryId)
		throws NoSuchCMICAccountEntryException {

		return findByPrimaryKey((Serializable)cmicAccountEntryId);
	}

	/**
	 * Returns the cmic account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry, or <code>null</code> if a cmic account entry with the primary key could not be found
	 */
	@Override
	public CMICAccountEntry fetchByPrimaryKey(long cmicAccountEntryId) {
		return fetchByPrimaryKey((Serializable)cmicAccountEntryId);
	}

	/**
	 * Returns all the cmic account entries.
	 *
	 * @return the cmic account entries
	 */
	@Override
	public List<CMICAccountEntry> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the cmic account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cmic account entries
	 * @param end the upper bound of the range of cmic account entries (not inclusive)
	 * @return the range of cmic account entries
	 */
	@Override
	public List<CMICAccountEntry> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the cmic account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cmic account entries
	 * @param end the upper bound of the range of cmic account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of cmic account entries
	 */
	@Override
	public List<CMICAccountEntry> findAll(
		int start, int end,
		OrderByComparator<CMICAccountEntry> orderByComparator) {

		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the cmic account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cmic account entries
	 * @param end the upper bound of the range of cmic account entries (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param useFinderCache whether to use the finder cache
	 * @return the ordered range of cmic account entries
	 */
	@Override
	public List<CMICAccountEntry> findAll(
		int start, int end,
		OrderByComparator<CMICAccountEntry> orderByComparator,
		boolean useFinderCache) {

		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
			(orderByComparator == null)) {

			pagination = false;

			if (useFinderCache) {
				finderPath = _finderPathWithoutPaginationFindAll;
				finderArgs = FINDER_ARGS_EMPTY;
			}
		}
		else if (useFinderCache) {
			finderPath = _finderPathWithPaginationFindAll;
			finderArgs = new Object[] {start, end, orderByComparator};
		}

		List<CMICAccountEntry> list = null;

		if (useFinderCache) {
			list = (List<CMICAccountEntry>)finderCache.getResult(
				finderPath, finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(
					2 + (orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_CMICACCOUNTENTRY);

				appendOrderByComparator(
					query, _ORDER_BY_ENTITY_ALIAS, orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_CMICACCOUNTENTRY;

				if (pagination) {
					sql = sql.concat(CMICAccountEntryModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<CMICAccountEntry>)QueryUtil.list(
						q, getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<CMICAccountEntry>)QueryUtil.list(
						q, getDialect(), start, end);
				}

				cacheResult(list);

				if (useFinderCache) {
					finderCache.putResult(finderPath, finderArgs, list);
				}
			}
			catch (Exception e) {
				if (useFinderCache) {
					finderCache.removeResult(finderPath, finderArgs);
				}

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the cmic account entries from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (CMICAccountEntry cmicAccountEntry : findAll()) {
			remove(cmicAccountEntry);
		}
	}

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(
			_finderPathCountAll, FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_CMICACCOUNTENTRY);

				count = (Long)q.uniqueResult();

				finderCache.putResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY, count);
			}
			catch (Exception e) {
				finderCache.removeResult(
					_finderPathCountAll, FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	protected EntityCache getEntityCache() {
		return entityCache;
	}

	@Override
	protected String getPKDBName() {
		return "cmicAccountEntryId";
	}

	@Override
	protected String getSelectSQL() {
		return _SQL_SELECT_CMICACCOUNTENTRY;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return CMICAccountEntryModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the cmic account entry persistence.
	 */
	@Activate
	public void activate() {
		CMICAccountEntryModelImpl.setEntityCacheEnabled(entityCacheEnabled);
		CMICAccountEntryModelImpl.setFinderCacheEnabled(finderCacheEnabled);

		_finderPathWithPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CMICAccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);

		_finderPathWithoutPaginationFindAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CMICAccountEntryImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll",
			new String[0]);

		_finderPathCountAll = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll",
			new String[0]);

		_finderPathFetchByAccountEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CMICAccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAccountEntryId",
			new String[] {Long.class.getName()},
			CMICAccountEntryModelImpl.ACCOUNTENTRYID_COLUMN_BITMASK);

		_finderPathCountByAccountEntryId = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAccountEntryId",
			new String[] {Long.class.getName()});

		_finderPathFetchByAN_CN = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, CMICAccountEntryImpl.class,
			FINDER_CLASS_NAME_ENTITY, "fetchByAN_CN",
			new String[] {String.class.getName(), String.class.getName()},
			CMICAccountEntryModelImpl.ACCOUNTNUMBER_COLUMN_BITMASK |
			CMICAccountEntryModelImpl.COMPANYNUMBER_COLUMN_BITMASK);

		_finderPathCountByAN_CN = new FinderPath(
			entityCacheEnabled, finderCacheEnabled, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countByAN_CN",
			new String[] {String.class.getName(), String.class.getName()});
	}

	@Deactivate
	public void deactivate() {
		entityCache.removeCache(CMICAccountEntryImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	@Reference(
		target = cmicPersistenceConstants.SERVICE_CONFIGURATION_FILTER,
		unbind = "-"
	)
	public void setConfiguration(Configuration configuration) {
		super.setConfiguration(configuration);

		_columnBitmaskEnabled = GetterUtil.getBoolean(
			configuration.get(
				"value.object.column.bitmask.enabled.com.churchmutual.core.model.CMICAccountEntry"),
			true);
	}

	@Override
	@Reference(
		target = cmicPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	@Reference(
		target = cmicPersistenceConstants.ORIGIN_BUNDLE_SYMBOLIC_NAME_FILTER,
		unbind = "-"
	)
	public void setSessionFactory(SessionFactory sessionFactory) {
		super.setSessionFactory(sessionFactory);
	}

	private boolean _columnBitmaskEnabled;

	@Reference
	protected EntityCache entityCache;

	@Reference
	protected FinderCache finderCache;

	private static final String _SQL_SELECT_CMICACCOUNTENTRY =
		"SELECT cmicAccountEntry FROM CMICAccountEntry cmicAccountEntry";

	private static final String _SQL_SELECT_CMICACCOUNTENTRY_WHERE =
		"SELECT cmicAccountEntry FROM CMICAccountEntry cmicAccountEntry WHERE ";

	private static final String _SQL_COUNT_CMICACCOUNTENTRY =
		"SELECT COUNT(cmicAccountEntry) FROM CMICAccountEntry cmicAccountEntry";

	private static final String _SQL_COUNT_CMICACCOUNTENTRY_WHERE =
		"SELECT COUNT(cmicAccountEntry) FROM CMICAccountEntry cmicAccountEntry WHERE ";

	private static final String _ORDER_BY_ENTITY_ALIAS = "cmicAccountEntry.";

	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY =
		"No CMICAccountEntry exists with the primary key ";

	private static final String _NO_SUCH_ENTITY_WITH_KEY =
		"No CMICAccountEntry exists with the key {";

	private static final Log _log = LogFactoryUtil.getLog(
		CMICAccountEntryPersistenceImpl.class);

	static {
		try {
			Class.forName(cmicPersistenceConstants.class.getName());
		}
		catch (ClassNotFoundException cnfe) {
			throw new ExceptionInInitializerError(cnfe);
		}
	}

}