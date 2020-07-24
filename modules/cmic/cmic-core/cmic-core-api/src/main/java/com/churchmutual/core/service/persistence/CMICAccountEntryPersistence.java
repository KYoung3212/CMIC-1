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

import com.churchmutual.core.exception.NoSuchCMICAccountEntryException;
import com.churchmutual.core.model.CMICAccountEntry;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The persistence interface for the cmic account entry service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Kayleen Lim
 * @see CMICAccountEntryUtil
 * @generated
 */
@ProviderType
public interface CMICAccountEntryPersistence
	extends BasePersistence<CMICAccountEntry> {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CMICAccountEntryUtil} to access the cmic account entry persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry findByAccountEntryId(long accountEntryId)
		throws NoSuchCMICAccountEntryException;

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry fetchByAccountEntryId(long accountEntryId);

	/**
	 * Returns the cmic account entry where accountEntryId = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountEntryId the account entry ID
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry fetchByAccountEntryId(
		long accountEntryId, boolean useFinderCache);

	/**
	 * Removes the cmic account entry where accountEntryId = &#63; from the database.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the cmic account entry that was removed
	 */
	public CMICAccountEntry removeByAccountEntryId(long accountEntryId)
		throws NoSuchCMICAccountEntryException;

	/**
	 * Returns the number of cmic account entries where accountEntryId = &#63;.
	 *
	 * @param accountEntryId the account entry ID
	 * @return the number of matching cmic account entries
	 */
	public int countByAccountEntryId(long accountEntryId);

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry findByAN_CN(
			String accountNumber, String companyNumber)
		throws NoSuchCMICAccountEntryException;

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber);

	/**
	 * Returns the cmic account entry where accountNumber = &#63; and companyNumber = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @param useFinderCache whether to use the finder cache
	 * @return the matching cmic account entry, or <code>null</code> if a matching cmic account entry could not be found
	 */
	public CMICAccountEntry fetchByAN_CN(
		String accountNumber, String companyNumber, boolean useFinderCache);

	/**
	 * Removes the cmic account entry where accountNumber = &#63; and companyNumber = &#63; from the database.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the cmic account entry that was removed
	 */
	public CMICAccountEntry removeByAN_CN(
			String accountNumber, String companyNumber)
		throws NoSuchCMICAccountEntryException;

	/**
	 * Returns the number of cmic account entries where accountNumber = &#63; and companyNumber = &#63;.
	 *
	 * @param accountNumber the account number
	 * @param companyNumber the company number
	 * @return the number of matching cmic account entries
	 */
	public int countByAN_CN(String accountNumber, String companyNumber);

	/**
	 * Caches the cmic account entry in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntry the cmic account entry
	 */
	public void cacheResult(CMICAccountEntry cmicAccountEntry);

	/**
	 * Caches the cmic account entries in the entity cache if it is enabled.
	 *
	 * @param cmicAccountEntries the cmic account entries
	 */
	public void cacheResult(
		java.util.List<CMICAccountEntry> cmicAccountEntries);

	/**
	 * Creates a new cmic account entry with the primary key. Does not add the cmic account entry to the database.
	 *
	 * @param cmicAccountEntryId the primary key for the new cmic account entry
	 * @return the new cmic account entry
	 */
	public CMICAccountEntry create(long cmicAccountEntryId);

	/**
	 * Removes the cmic account entry with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry that was removed
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	public CMICAccountEntry remove(long cmicAccountEntryId)
		throws NoSuchCMICAccountEntryException;

	public CMICAccountEntry updateImpl(CMICAccountEntry cmicAccountEntry);

	/**
	 * Returns the cmic account entry with the primary key or throws a <code>NoSuchCMICAccountEntryException</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry
	 * @throws NoSuchCMICAccountEntryException if a cmic account entry with the primary key could not be found
	 */
	public CMICAccountEntry findByPrimaryKey(long cmicAccountEntryId)
		throws NoSuchCMICAccountEntryException;

	/**
	 * Returns the cmic account entry with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param cmicAccountEntryId the primary key of the cmic account entry
	 * @return the cmic account entry, or <code>null</code> if a cmic account entry with the primary key could not be found
	 */
	public CMICAccountEntry fetchByPrimaryKey(long cmicAccountEntryId);

	/**
	 * Returns all the cmic account entries.
	 *
	 * @return the cmic account entries
	 */
	public java.util.List<CMICAccountEntry> findAll();

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
	public java.util.List<CMICAccountEntry> findAll(int start, int end);

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
	public java.util.List<CMICAccountEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CMICAccountEntry>
			orderByComparator);

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
	public java.util.List<CMICAccountEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<CMICAccountEntry>
			orderByComparator,
		boolean useFinderCache);

	/**
	 * Removes all the cmic account entries from the database.
	 */
	public void removeAll();

	/**
	 * Returns the number of cmic account entries.
	 *
	 * @return the number of cmic account entries
	 */
	public int countAll();

}