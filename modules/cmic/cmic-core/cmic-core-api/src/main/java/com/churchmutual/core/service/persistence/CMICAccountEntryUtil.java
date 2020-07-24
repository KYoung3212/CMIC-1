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

package com.churchmutual.core.service.persistence;

import com.churchmutual.core.model.CMICAccountEntry;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The persistence utility for the cmic account entry service. This utility wraps <code>com.churchmutual.core.service.persistence.impl.CMICAccountEntryPersistenceImpl</code> and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryPersistence
 * @generated
 */
public class CMICAccountEntryUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(CMICAccountEntry cmicAccountEntry) {
		getPersistence().clearCache(cmicAccountEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#fetchByPrimaryKeys(Set)
	 */
	public static Map<Serializable, CMICAccountEntry> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {

		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CMICAccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {

		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CMICAccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {

		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CMICAccountEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<CMICAccountEntry> orderByComparator) {

		return getPersistence().findWithDynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static CMICAccountEntry update(CMICAccountEntry cmicAccountEntry) {
		return getPersistence().update(cmicAccountEntry);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static CMICAccountEntry update(
		CMICAccountEntry cmicAccountEntry, ServiceContext serviceContext) {

		return getPersistence().update(cmicAccountEntry, serviceContext);
	}

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry findByAccountEntryId(long accountEntryId)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().findByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry fetchByAccountEntryId(long accountEntryId) {
		return getPersistence().fetchByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry fetchByAccountEntryId(
		long accountEntryId, boolean useFinderCache) {

		return getPersistence().fetchByAccountEntryId(
			accountEntryId, useFinderCache);
	}

	/**
	 * Removes the cmic account entry where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the cmic account entry that was removed
	 */
	public static CMICAccountEntry removeByAccountEntryId(long accountEntryId)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().removeByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the number of cmic account entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching cmic account entries
	 */
	public static int countByAccountEntryId(long accountEntryId) {
		return getPersistence().countByAccountEntryId(accountEntryId);
	}

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry findByAN_CN(
			String accountNumber, String companyNumber)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().findByAN_CN(accountNumber, companyNumber);
	}

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber) {

		return getPersistence().fetchByAN_CN(accountNumber, companyNumber);
	}

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public static CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber, boolean useFinderCache) {

		return getPersistence().fetchByAN_CN(
			accountNumber, companyNumber, useFinderCache);
	}

	/**
	 * Removes the cmic account entry where accountNumber = &#63; and companyNumber = &#63; from the database.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the cmic account entry that was removed
	 */
	public static CMICAccountEntry removeByAN_CN(
			String accountNumber, String companyNumber)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().removeByAN_CN(accountNumber, companyNumber);
	}

	/**
	 * Returns the number of cmic account entries where accountNumber = &#63; and companyNumber = &#63;.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the number of matching cmic account entries
	 */
	public static int countByAN_CN(String accountNumber, String companyNumber) {
		return getPersistence().countByAN_CN(accountNumber, companyNumber);
	}

	/**
	 * Caches the cmic account entry in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 */
	public static void cacheResult(CMICAccountEntry cmicAccountEntry) {
		getPersistence().cacheResult(cmicAccountEntry);
	}

	/**
	 * Caches the cmic account entries in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntries the cmic account entries
	 */
	public static void cacheResult(List<CMICAccountEntry> cmicAccountEntries) {
		getPersistence().cacheResult(cmicAccountEntries);
	}

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	public static CMICAccountEntry create(long cmicAccountEntryId) {
		return getPersistence().create(cmicAccountEntryId);
	}

	/**
	 * Removes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	public static CMICAccountEntry remove(long cmicAccountEntryId)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().remove(cmicAccountEntryId);
	}

	public static CMICAccountEntry updateImpl(
		CMICAccountEntry cmicAccountEntry) {

		return getPersistence().updateImpl(cmicAccountEntry);
	}

	/**
	 * Returns the cmic account entry with the primary key or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	public static CMICAccountEntry findByPrimaryKey(long cmicAccountEntryId)
		throws com.churchmutual.core.exception.NoSuchCMICAccountEntryException {

		return getPersistence().findByPrimaryKey(cmicAccountEntryId);
	}

	/**
	 * Returns the cmic account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry, or <code>null</code> if a cmic account entry with the primary key could not be found
	 */
	public static CMICAccountEntry fetchByPrimaryKey(long cmicAccountEntryId) {
		return getPersistence().fetchByPrimaryKey(cmicAccountEntryId);
	}

	/**
	 * Returns all the cmic account entries.
	 *
	 * @return the cmic account entries
	 */
	public static List<CMICAccountEntry> findAll() {
		return getPersistence().findAll();
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
	public static List<CMICAccountEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
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
	public static List<CMICAccountEntry> findAll(
		int start, int end,
		OrderByComparator<CMICAccountEntry> orderByComparator) {

		return getPersistence().findAll(start, end, orderByComparator);
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
	public static List<CMICAccountEntry> findAll(
		int start, int end,
		OrderByComparator<CMICAccountEntry> orderByComparator,
		boolean useFinderCache) {

		return getPersistence().findAll(
			start, end, orderByComparator, useFinderCache);
	}

	/**
	 * Removes all the cmic account entries from the database.
	 */
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static CMICAccountEntryPersistence getPersistence() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CMICAccountEntryPersistence, CMICAccountEntryPersistence>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CMICAccountEntryPersistence.class);

		ServiceTracker<CMICAccountEntryPersistence, CMICAccountEntryPersistence>
			serviceTracker =
				new ServiceTracker
					<CMICAccountEntryPersistence, CMICAccountEntryPersistence>(
						bundle.getBundleContext(),
						CMICAccountEntryPersistence.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}