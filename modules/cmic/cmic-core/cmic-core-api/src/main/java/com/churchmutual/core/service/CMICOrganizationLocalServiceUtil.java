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
 * Provides the local service utility for CMICOrganization. This utility wraps
 * <code>com.churchmutual.core.service.impl.CMICOrganizationLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Kayleen Lim
 * @see CMICOrganizationLocalService
 * @generated
 */
public class CMICOrganizationLocalServiceUtil {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.churchmutual.core.service.impl.CMICOrganizationLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * Adds the cmic organization to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was added
	 */
	public static com.churchmutual.core.model.CMICOrganization
		addCMICOrganization(
			com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return getService().addCMICOrganization(cmicOrganization);
	}

	public static com.churchmutual.core.model.CMICOrganization
			addCMICOrganization(long userId, long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().addCMICOrganization(userId, producerId);
	}

	/**
	 * Creates a new cmic organization with the primary key. Does not add the cmic organization to the database.
	 *
	 * @param cmicOrganizationId the primary key for the new cmic organization
	 * @return the new cmic organization
	 */
	public static com.churchmutual.core.model.CMICOrganization
		createCMICOrganization(long cmicOrganizationId) {

		return getService().createCMICOrganization(cmicOrganizationId);
	}

	/**
	 * Deletes the cmic organization from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was removed
	 */
	public static com.churchmutual.core.model.CMICOrganization
		deleteCMICOrganization(
			com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return getService().deleteCMICOrganization(cmicOrganization);
	}

	/**
	 * Deletes the cmic organization with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganizationId the primary key of the cmic organization
	 * @return the cmic organization that was removed
	 * @throws PortalException if a cmic organization with the primary key could not be found
	 */
	public static com.churchmutual.core.model.CMICOrganization
			deleteCMICOrganization(long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().deleteCMICOrganization(cmicOrganizationId);
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICOrganizationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.churchmutual.core.model.impl.CMICOrganizationModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

	public static com.churchmutual.core.model.CMICOrganization
		fetchCMICOrganization(long cmicOrganizationId) {

		return getService().fetchCMICOrganization(cmicOrganizationId);
	}

	public static com.churchmutual.core.model.CMICOrganization
		fetchCMICOrganizationByOrganizationId(long organizationId) {

		return getService().fetchCMICOrganizationByOrganizationId(
			organizationId);
	}

	public static com.churchmutual.core.model.CMICOrganization
			fetchCMICOrganizationByProducerId(long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().fetchCMICOrganizationByProducerId(producerId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery
		getActionableDynamicQuery() {

		return getService().getActionableDynamicQuery();
	}

	/**
	 * Returns the cmic organization with the primary key.
	 *
	 * @param cmicOrganizationId the primary key of the cmic organization
	 * @return the cmic organization
	 * @throws PortalException if a cmic organization with the primary key could not be found
	 */
	public static com.churchmutual.core.model.CMICOrganization
			getCMICOrganization(long cmicOrganizationId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCMICOrganization(cmicOrganizationId);
	}

	public static java.util.List
		<com.churchmutual.core.model.CMICOrganizationDisplay>
				getCMICOrganizationDisplays(long userId)
			throws com.liferay.portal.kernel.exception.PortalException {

		return getService().getCMICOrganizationDisplays(userId);
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
	public static java.util.List<com.churchmutual.core.model.CMICOrganization>
		getCMICOrganizations(int start, int end) {

		return getService().getCMICOrganizations(start, end);
	}

	public static java.util.List<com.churchmutual.core.model.CMICOrganization>
		getCMICOrganizationsByUserId(long userId) {

		return getService().getCMICOrganizationsByUserId(userId);
	}

	/**
	 * Returns the number of cmic organizations.
	 *
	 * @return the number of cmic organizations
	 */
	public static int getCMICOrganizationsCount() {
		return getService().getCMICOrganizationsCount();
	}

	public static
		com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery
			getIndexableActionableDynamicQuery() {

		return getService().getIndexableActionableDynamicQuery();
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

	/**
	 * Updates the cmic organization in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cmicOrganization the cmic organization
	 * @return the cmic organization that was updated
	 */
	public static com.churchmutual.core.model.CMICOrganization
		updateCMICOrganization(
			com.churchmutual.core.model.CMICOrganization cmicOrganization) {

		return getService().updateCMICOrganization(cmicOrganization);
	}

	public static com.churchmutual.core.model.CMICOrganization
			updateCMICOrganizationContactInfo(long userId, long producerId)
		throws com.liferay.portal.kernel.exception.PortalException {

		return getService().updateCMICOrganizationContactInfo(
			userId, producerId);
	}

	public static CMICOrganizationLocalService getService() {
		return _serviceTracker.getService();
	}

	private static ServiceTracker
		<CMICOrganizationLocalService, CMICOrganizationLocalService>
			_serviceTracker;

	static {
		Bundle bundle = FrameworkUtil.getBundle(
			CMICOrganizationLocalService.class);

		ServiceTracker
			<CMICOrganizationLocalService, CMICOrganizationLocalService>
				serviceTracker =
					new ServiceTracker
						<CMICOrganizationLocalService,
						 CMICOrganizationLocalService>(
							 bundle.getBundleContext(),
							 CMICOrganizationLocalService.class, null);

		serviceTracker.open();

		_serviceTracker = serviceTracker;
	}

}