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

import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;
import org.osgi.util.tracker.ServiceTracker;

/**
 * Provides the local service utility for CMICAccountEntry. This utility wraps
 * <code>com.churchmutual.core.service.impl.CMICAccountEntryLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryLocalService
 * @generated
 */
public class CMICAccountEntryLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.churchmutual.core.service.impl.CMICAccountEntryLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cmic account entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was added
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
		addCMICAccountEntry(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return getService().addCMICAccountEntry(cmicAccountEntry);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>CMICAccountEntryLocalService</code> via injection or a <code>ServiceTracker</code> or use <code>CMICAccountEntryLocalServiceUtil</code>.
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
			addOrUpdateCMICAccountEntry(
				long userId, String accountNumber, String companyNumber,
				String accountName, long producerId, String producerCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addOrUpdateCMICAccountEntry(
			userId, accountNumber, companyNumber, accountName, producerId,
			producerCode);
	}

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
		createCMICAccountEntry(long cmicAccountEntryId) {

		return getService().createCMICAccountEntry(cmicAccountEntryId);
	}

	/**
	 * Deletes the cmic account entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was removed
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
		deleteCMICAccountEntry(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return getService().deleteCMICAccountEntry(cmicAccountEntry);
	}

	/**
	 * Deletes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
			deleteCMICAccountEntry(long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCMICAccountEntry(cmicAccountEntryId);
	}

	/**
	 * @throws PortalException
	 */
	public static com.liferay.portal.kernel.model.PersistedModel
			deletePersistedModel(
				com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery
		dynamicQuery() {

		return getService().dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @return the range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param dynamicQuery the dynamic query
	 * @param start the lower bound of the range of model instances
	 * @param end the upper bound of the range of model instances (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of matching rows
	 */
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return getService().dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.churchmutual.core.model.CMICAccountEntry
		fetchCMICAccountEntry(long cmicAccountEntryId) {

		return getService().fetchCMICAccountEntry(cmicAccountEntryId);
	}

	public static com.churchmutual.core.model.CMICAccountEntry
		fetchCMICAccountEntry(String accountNumber, String companyNumber) {

		return getService().fetchCMICAccountEntry(accountNumber, companyNumber);
	}

	public static String getAccountEntryName(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getAccountEntryName(cmicAccountEntry);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns a range of all the cmic account entries.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICAccountEntryModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cmic account entries
	 * @param end the upper bound of the range of cmic account entries (not inclusive)
	 * @return the range of cmic account entries
	 */
	public static java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntries(int start, int end) {

		return getService().getCMICAccountEntries(start, end);
	}

	public static java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntriesByUserId(long userId) {

		return getService().getCMICAccountEntriesByUserId(userId);
	}

	public static java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntriesByUserIdOrderedByName(
			long userId, int start, int end) {

		return getService().getCMICAccountEntriesByUserIdOrderedByName(
			userId, start, end);
	}

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	public static int getCMICAccountEntriesCount() {
		return getService().getCMICAccountEntriesCount();
	}

	/**
	 * Returns the cmic account entry with the primary key.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
			getCMICAccountEntry(long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCMICAccountEntry(cmicAccountEntryId);
	}

	public static com.churchmutual.core.model.CMICAccountEntry
			getCMICAccountEntry(String accountNumber, String companyNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCMICAccountEntry(accountNumber, companyNumber);
	}

	public static com.churchmutual.core.model.CMICAccountEntryDisplay
			getCMICAccountEntryDisplay(String cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCMICAccountEntryDisplay(cmicAccountEntryId);
	}

	public static java.util.List
		<com.churchmutual.core.model.CMICAccountEntryDisplay>
			getCMICAccountEntryDisplays(
				java.util.List<String> cmicAccountEntryIds) {

		return getService().getCMICAccountEntryDisplays(cmicAccountEntryIds);
	}

	public static java.util.List
		<com.churchmutual.core.model.CMICAccountEntryDisplay>
			getCMICAccountEntryDisplays(long userId) {

		return getService().getCMICAccountEntryDisplays(userId);
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
	}

	public static String getOrganizationName(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getOrganizationName(cmicAccountEntry);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel
			getPersistedModel(java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getPersistedModel(primaryKeyObj);
	}

	public static String getProducerCode(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getProducerCode(cmicAccountEntry);
	}

	/**
	 * Updates the cmic account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was updated
	 */
	public static com.churchmutual.core.model.CMICAccountEntry
		updateCMICAccountEntry(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return getService().updateCMICAccountEntry(cmicAccountEntry);
	}

	public static void updateCMICAccountEntryDetails(
			java.util.List<com.churchmutual.core.model.CMICAccountEntry>
				cmicAccountEntries)
		throws com.liferay.portal.kernel.exception.PortalException {

		getService().updateCMICAccountEntryDetails(cmicAccountEntries);
	}

	public static CMICAccountEntryLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CMICAccountEntryLocalService, CMICAccountEntryLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CMICAccountEntryLocalService.class);

		ServiceTracker
			<CMICAccountEntryLocalService, CMICAccountEntryLocalService>
				serviceTracker =
					new ServiceTracker
						<CMICAccountEntryLocalService,
						 CMICAccountEntryLocalService>(
							 bundle.getBundleContext(),
							 CMICAccountEntryLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}