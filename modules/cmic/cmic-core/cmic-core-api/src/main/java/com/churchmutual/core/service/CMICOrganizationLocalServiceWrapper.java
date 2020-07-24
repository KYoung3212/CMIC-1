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
 * Provides a wrapper for {@link CMICOrganizationLocalService}.
 *
 * @author Kayleen Lim
 * @see CMICOrganizationLocalService
 * @generated
 */
public class CMICOrganizationLocalServiceWrapper
	implements CMICOrganizationLocalService,
			   ServiceWrapper<CMICOrganizationLocalService> {

	public CMICOrganizationLocalServiceWrapper(
		CMICOrganizationLocalService cmicOrganizationLocalService) {

		_cmicOrganizationLocalService = cmicOrganizationLocalService;
	}

	/**
	 * Adds the cmic organization to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was added
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization addCMICOrganization(
		com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return _cmicOrganizationLocalService.addCMICOrganization(
			cmicOrganization);
	}

	@Override
	public com.churchmutual.core.model.CMICOrganization addCMICOrganization(
			long userId, long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.addCMICOrganization(
			userId, producerId);
	}

	/**
	 * Creates a new cmic organization with the primary key. Does not add the cmic organization to the database.
	 *
	 * @param cmicOrganizationId the primary key for the new cmic organization
	 * @return the new cmic organization
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization createCMICOrganization(
		long cmicOrganizationId) {

		return _cmicOrganizationLocalService.createCMICOrganization(
			cmicOrganizationId);
	}

	/**
	 * Deletes the cmic organization from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was removed
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization deleteCMICOrganization(
		com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return _cmicOrganizationLocalService.deleteCMICOrganization(
			cmicOrganization);
	}

	/**
	 * Deletes the cmic organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganizationId the primary key of the cmic organization
	 * @return the cmic organization that was removed
	 * @throws PortalException if a cmic organization with the primary key could not be found
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization deleteCMICOrganization(
			long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.deleteCMICOrganization(
			cmicOrganizationId);
	}

	/**
	 * @throws PortalException
	 */
	@Override
	public com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
			com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.deletePersistedModel(
			persistedModel);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _cmicOrganizationLocalService.dynamicQuery();
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

		return _cmicOrganizationLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	 * Performs a dynamic query on the database and returns a range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICOrganizationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cmicOrganizationLocalService.dynamicQuery(
			dynamicQuery, start, end);
	}

	/**
	 * Performs a dynamic query on the database and returns an ordered range of the matching rows.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICOrganizationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

		return _cmicOrganizationLocalService.dynamicQuery(
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

		return _cmicOrganizationLocalService.dynamicQueryCount(dynamicQuery);
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

		return _cmicOrganizationLocalService.dynamicQueryCount(
			dynamicQuery, projection);
	}

	@Override
	public com.churchmutual.core.model.CMICOrganization fetchCMICOrganization(
		long cmicOrganizationId) {

		return _cmicOrganizationLocalService.fetchCMICOrganization(
			cmicOrganizationId);
	}

	@Override
	public com.churchmutual.core.model.CMICOrganization
		fetchCMICOrganizationByOrganizationId(long organizationId) {

		return _cmicOrganizationLocalService.
			fetchCMICOrganizationByOrganizationId(organizationId);
	}

	@Override
	public com.churchmutual.core.model.CMICOrganization
			fetchCMICOrganizationByProducerId(long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.fetchCMICOrganizationByProducerId(
			producerId);
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return _cmicOrganizationLocalService.getActionableDynamicQuery();
	}

	/**
	 * Returns the cmic organization with the primary key.
	 *
	 * @param cmicOrganizationId the primary key of the cmic organization
	 * @return the cmic organization
	 * @throws PortalException if a cmic organization with the primary key could not be found
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization getCMICOrganization(
			long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.getCMICOrganization(
			cmicOrganizationId);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICOrganizationDisplay>
			getCMICOrganizationDisplays(long userId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.getCMICOrganizationDisplays(
			userId);
	}

	/**
	 * Returns a range of all the cmic organizations.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICOrganizationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of cmic organizations
	 * @param end the upper bound of the range of cmic organizations (not inclusive)
	 * @return the range of cmic organizations
	 */
	@Override
	public java.util.List<com.churchmutual.core.model.CMICOrganization>
		getCMICOrganizations(int start, int end) {

		return _cmicOrganizationLocalService.getCMICOrganizations(start, end);
	}

	@Override
	public java.util.List<com.churchmutual.core.model.CMICOrganization>
		getCMICOrganizationsByUserId(long userId) {

		return _cmicOrganizationLocalService.getCMICOrganizationsByUserId(
			userId);
	}

	/**
	 * Returns the number of cmic organizations.
	 *
	 * @return the number of cmic organizations
	 */
	@Override
	public int getCMICOrganizationsCount() {
		return _cmicOrganizationLocalService.getCMICOrganizationsCount();
	}

	@Override
	public com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
		getIndexableActionableDynamicQuery() {

		return _cmicOrganizationLocalService.
			getIndexableActionableDynamicQuery();
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _cmicOrganizationLocalService.getOSGiServiceIdentifier();
	}

	@Override
	public com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
			java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	 * Updates the cmic organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was updated
	 */
	@Override
	public com.churchmutual.core.model.CMICOrganization updateCMICOrganization(
		com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return _cmicOrganizationLocalService.updateCMICOrganization(
			cmicOrganization);
	}

	@Override
	public com.churchmutual.core.model.CMICOrganization
			updateCMICOrganizationContactInfo(long userId, long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return _cmicOrganizationLocalService.updateCMICOrganizationContactInfo(
			userId, producerId);
	}

	@Override
	public CMICOrganizationLocalService getWrappedService() {
		return _cmicOrganizationLocalService;
	}

	@Override
	public void setWrappedService(
		CMICOrganizationLocalService cmicOrganizationLocalService) {

		_cmicOrganizationLocalService = cmicOrganizationLocalService;
	}

	private CMICOrganizationLocalService _cmicOrganizationLocalService;

}