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

import com.churchmutual.core.model.CMICAccountEntry;
import com.churchmutual.core.model.CMICAccountEntryDisplay;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.Projection;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.service.BaseLocalService;
import com.liferay.portal.kernel.service.PersistedModelLocalService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.kernel.util.OrderByComparator;

import java.io.Serializable;

import java.util.List;

import org.osgi.annotation.versioning.ProviderType;

/**
 * Provides the local service interface for CMICAccountEntry. Methods of this
 * service will not have security checks based on the propagated JAAS
 * credentials because this service can only be accessed from within the same
 * VM.
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryLocalServiceUtil
 * @generated
 */
@ProviderType
@Transactional(
	isolation = Isolation.PORTAL,
	rollbackFor = {PortalException.class, SystemException.class}
)
public interface CMICAccountEntryLocalService
	extends BaseLocalService, PersistedModelLocalService {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CMICAccountEntryLocalServiceUtil} to access the cmic account entry local service. Add custom service methods to <code>com.churchmutual.core.service.impl.CMICAccountEntryLocalServiceImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */

	/**
	 * Adds the cmic account entry to the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was added
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CMICAccountEntry addCMICAccountEntry(
		CMICAccountEntry cmicAccountEntry);

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Use <code>CMICAccountEntryLocalService</code> via injection or a <code>org.osgi.util.tracker.ServiceTracker</code> or use <code>CMICAccountEntryLocalServiceUtil</code>.
	 */
	public CMICAccountEntry addOrUpdateCMICAccountEntry(
			long userId, String accountNumber, String companyNumber,
			String accountName, long producerId, String producerCode)
		throws PortalException;

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	@Transactional(enabled = false)
	public CMICAccountEntry createCMICAccountEntry(long cmicAccountEntryId);

	/**
	 * Deletes the cmic account entry from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was removed
	 */
	@Indexable(type = IndexableType.DELETE)
	public CMICAccountEntry deleteCMICAccountEntry(
		CMICAccountEntry cmicAccountEntry);

	/**
	 * Deletes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	@Indexable(type = IndexableType.DELETE)
	public CMICAccountEntry deleteCMICAccountEntry(long cmicAccountEntryId)
		throws PortalException;

	/**
	 * @throws PortalException
	 */
	@Override
	public PersistedModel deletePersistedModel(PersistedModel persistedModel)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public DynamicQuery dynamicQuery();

	/**
	 * Performs a dynamic query on the database and returns the matching rows.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the matching rows
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(DynamicQuery dynamicQuery);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end);

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public <T> List<T> dynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<T> orderByComparator);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(DynamicQuery dynamicQuery);

	/**
	 * Returns the number of rows matching the dynamic query.
	 *
	 * @param dynamicQuery the dynamic query
	 * @param projection the projection to apply to the query
	 * @return the number of rows matching the dynamic query
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public long dynamicQueryCount(
		DynamicQuery dynamicQuery, Projection projection);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CMICAccountEntry fetchCMICAccountEntry(long cmicAccountEntryId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CMICAccountEntry fetchCMICAccountEntry(
		String accountNumber, String companyNumber);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getAccountEntryName(CMICAccountEntry cmicAccountEntry)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public ActionableDynamicQuery getActionableDynamicQuery();

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
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CMICAccountEntry> getCMICAccountEntries(int start, int end);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CMICAccountEntry> getCMICAccountEntriesByUserId(long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CMICAccountEntry> getCMICAccountEntriesByUserIdOrderedByName(
		long userId, int start, int end);

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public int getCMICAccountEntriesCount();

	/**
	 * Returns the cmic account entry with the primary key.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws PortalException if a cmic account entry with the primary key could not be found
	 */
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CMICAccountEntry getCMICAccountEntry(long cmicAccountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CMICAccountEntry getCMICAccountEntry(
			String accountNumber, String companyNumber)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public CMICAccountEntryDisplay getCMICAccountEntryDisplay(
			String cmicAccountEntryId)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CMICAccountEntryDisplay> getCMICAccountEntryDisplays(
		List<String> cmicAccountEntryIds);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<CMICAccountEntryDisplay> getCMICAccountEntryDisplays(
		long userId);

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public IndexableActionableDynamicQuery getIndexableActionableDynamicQuery();

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getOrganizationName(CMICAccountEntry cmicAccountEntry)
		throws PortalException;

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	public String getOSGiServiceIdentifier();

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public PersistedModel getPersistedModel(Serializable primaryKeyObj)
		throws PortalException;

	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public String getProducerCode(CMICAccountEntry cmicAccountEntry)
		throws PortalException;

	/**
	 * Updates the cmic account entry in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 * @return the cmic account entry that was updated
	 */
	@Indexable(type = IndexableType.REINDEX)
	public CMICAccountEntry updateCMICAccountEntry(
		CMICAccountEntry cmicAccountEntry);

	public void updateCMICAccountEntryDetails(
			List<CMICAccountEntry> cmicAccountEntries)
		throws PortalException;

}