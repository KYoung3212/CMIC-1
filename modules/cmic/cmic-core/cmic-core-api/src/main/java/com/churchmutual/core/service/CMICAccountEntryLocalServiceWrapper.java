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
 * Provides a wrapper for {@link CMICAccountEntryLocalService}.
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryLocalService
 * @generated
 */
public class CMICAccountEntryLocalServiceWrapper
	implements CMICAccountEntryLocalService,
			   ServiceWrapper<CMICAccountEntryLocalService> {

	public CMICAccountEntryLocalServiceWrapper(
		CMICAccountEntryLocalService cmicAccountEntryLocalService) {

		_cmicAccountEntryLocalService = cmicAccountEntryLocalService;
	}

	/**
	 * Adds the cmic account entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was added
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry addCMICAccountEntry(
		com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return _cmicAccountEntryLocalService.addCMICAccountEntry(
			cmicAccountEntry);
	}

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>CMICAccountEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CMICAccountEntryLocalServiceUtil</code>.
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry
			addOrUpdateCMICAccountEntry(
				long userId, String accountNumber, String companyNumber,
				String accountName, long producerId, String producerCode)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.addOrUpdateCMICAccountEntry(
			userId, accountNumber, companyNumber, accountName, producerId,
			producerCode);
	}

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry createCMICAccountEntry(
		long cmicAccountEntryId) {

		return _cmicAccountEntryLocalService.createCMICAccountEntry(
			cmicAccountEntryId);
	}

	/**
	 * Deletes the cmic account entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was removed
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry deleteCMICAccountEntry(
		com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return _cmicAccountEntryLocalService.deleteCMICAccountEntry(
			cmicAccountEntry);
	}

	/**
	 * Deletes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry deleteCMICAccountEntry(
			long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.deleteCMICAccountEntry(
			cmicAccountEntryId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cmicAccountEntryLocalService.dynamicQuery();
	}

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _cmicAccountEntryLocalService.dynamicQuery(dynamicQuery);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {

		return _cmicAccountEntryLocalService.dynamicQuery(
			dynamicQuery, start, end);
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
	@Override
	public <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {

		return _cmicAccountEntryLocalService.dynamicQuery(
			dynamicQuery, start, end, orderByComparator);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {

		return _cmicAccountEntryLocalService.dynamicQueryCount(dynamicQuery);
	}

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Override
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {

		return _cmicAccountEntryLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.churchmutual.core.model.CMICAccountEntry fetchCMICAccountEntry(
		long cmicAccountEntryId) {

		return _cmicAccountEntryLocalService.fetchCMICAccountEntry(
			cmicAccountEntryId);
	}

	@Override
	public com.churchmutual.core.model.CMICAccountEntry fetchCMICAccountEntry(
		String accountNumber, String companyNumber) {

		return _cmicAccountEntryLocalService.fetchCMICAccountEntry(
			accountNumber, companyNumber);
	}

	@Override
	public String getAccountEntryName(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getAccountEntryName(
			cmicAccountEntry);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cmicAccountEntryLocalService.getActionableDynamicQuery();
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
	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntries(int start, int end) {

		return _cmicAccountEntryLocalService.getCMICAccountEntries(start, end);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntriesByUserId(long userId) {

		return _cmicAccountEntryLocalService.getCMICAccountEntriesByUserId(
			userId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntry>
		getCMICAccountEntriesByUserIdOrderedByName(
			long userId, int start, int end) {

		return _cmicAccountEntryLocalService.
			getCMICAccountEntriesByUserIdOrderedByName(userId, start, end);
	}

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	@Override
	public int getCMICAccountEntriesCount() {
		return _cmicAccountEntryLocalService.getCMICAccountEntriesCount();
	}

	/**
	 * Returns the cmic account entry with the primary key.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry getCMICAccountEntry(
			long cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getCMICAccountEntry(
			cmicAccountEntryId);
	}

	@Override
	public com.churchmutual.core.model.CMICAccountEntry getCMICAccountEntry(
			String accountNumber, String companyNumber)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getCMICAccountEntry(
			accountNumber, companyNumber);
	}

	@Override
	public com.churchmutual.core.model.CMICAccountEntryDisplay
			getCMICAccountEntryDisplay(String cmicAccountEntryId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getCMICAccountEntryDisplay(
			cmicAccountEntryId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntryDisplay>
		getCMICAccountEntryDisplays(
			java.util.List<String> cmicAccountEntryIds) {

		return _cmicAccountEntryLocalService.getCMICAccountEntryDisplays(
			cmicAccountEntryIds);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICAccountEntryDisplay>
		getCMICAccountEntryDisplays(long userId) {

		return _cmicAccountEntryLocalService.getCMICAccountEntryDisplays(
			userId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cmicAccountEntryLocalService.
			getIndexableActionableDynamicQuery();
	}

	@Override
	public String getOrganizationName(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getOrganizationName(
			cmicAccountEntry);
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicAccountEntryLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getPersistedModel(primaryKeyObj);
	}

	@Override
	public String getProducerCode(
			com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicAccountEntryLocalService.getProducerCode(cmicAccountEntry);
	}

	/**
	 * Updates the cmic account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was updated
	 */
	@Override
	public com.churchmutual.core.model.CMICAccountEntry updateCMICAccountEntry(
		com.churchmutual.core.model.CMICAccountEntry cmicAccountEntry) {

		return _cmicAccountEntryLocalService.updateCMICAccountEntry(
			cmicAccountEntry);
	}

	@Override
	public void updateCMICAccountEntryDetails(
			java.util.List<com.churchmutual.core.model.CMICAccountEntry>
				cmicAccountEntries)
		throws com.liferay.portal.kernel.exception.PortalException {

		_cmicAccountEntryLocalService.updateCMICAccountEntryDetails(
			cmicAccountEntries);
	}

	@Override
	public CMICAccountEntryLocalService getWrappedService() {
		return _cmicAccountEntryLocalService;
	}

	@Override
	public void setWrappedService(
		CMICAccountEntryLocalService cmicAccountEntryLocalService) {

		_cmicAccountEntryLocalService = cmicAccountEntryLocalService;
	}

	private CMICAccountEntryLocalService _cmicAccountEntryLocalService;

}