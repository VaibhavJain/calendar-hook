/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.hook.calendar.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link CalEventAttendeeLocalService}.
 * </p>
 *
 * @author    Joaquin Cabal
 * @see       CalEventAttendeeLocalService
 * @generated
 */
public class CalEventAttendeeLocalServiceWrapper
	implements CalEventAttendeeLocalService,
		ServiceWrapper<CalEventAttendeeLocalService> {
	public CalEventAttendeeLocalServiceWrapper(
		CalEventAttendeeLocalService calEventAttendeeLocalService) {
		_calEventAttendeeLocalService = calEventAttendeeLocalService;
	}

	/**
	* Adds the cal event attendee to the database. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendee the cal event attendee
	* @return the cal event attendee that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee addCalEventAttendee(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.addCalEventAttendee(calEventAttendee);
	}

	/**
	* Creates a new cal event attendee with the primary key. Does not add the cal event attendee to the database.
	*
	* @param calEventAttendeeId the primary key for the new cal event attendee
	* @return the new cal event attendee
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee createCalEventAttendee(
		long calEventAttendeeId) {
		return _calEventAttendeeLocalService.createCalEventAttendee(calEventAttendeeId);
	}

	/**
	* Deletes the cal event attendee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee that was removed
	* @throws PortalException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee deleteCalEventAttendee(
		long calEventAttendeeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.deleteCalEventAttendee(calEventAttendeeId);
	}

	/**
	* Deletes the cal event attendee from the database. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendee the cal event attendee
	* @return the cal event attendee that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee deleteCalEventAttendee(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.deleteCalEventAttendee(calEventAttendee);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _calEventAttendeeLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.dynamicQuery(dynamicQuery, start,
			end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.dynamicQuery(dynamicQuery, start,
			end, orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.liferay.hook.calendar.model.CalEventAttendee fetchCalEventAttendee(
		long calEventAttendeeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.fetchCalEventAttendee(calEventAttendeeId);
	}

	/**
	* Returns the cal event attendee with the primary key.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee
	* @throws PortalException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee getCalEventAttendee(
		long calEventAttendeeId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.getCalEventAttendee(calEventAttendeeId);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns a range of all the cal event attendees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> getCalEventAttendees(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.getCalEventAttendees(start, end);
	}

	/**
	* Returns the number of cal event attendees.
	*
	* @return the number of cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int getCalEventAttendeesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.getCalEventAttendeesCount();
	}

	/**
	* Updates the cal event attendee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendee the cal event attendee
	* @return the cal event attendee that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee updateCalEventAttendee(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.updateCalEventAttendee(calEventAttendee);
	}

	/**
	* Updates the cal event attendee in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendee the cal event attendee
	* @param merge whether to merge the cal event attendee with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the cal event attendee that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee updateCalEventAttendee(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.updateCalEventAttendee(calEventAttendee,
			merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _calEventAttendeeLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_calEventAttendeeLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _calEventAttendeeLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public void deleteCalEventAttendeesByEventId(long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_calEventAttendeeLocalService.deleteCalEventAttendeesByEventId(eventId);
	}

	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findCalEventAttendeesByEventId(
		long eventId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendeeLocalService.findCalEventAttendeesByEventId(eventId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public CalEventAttendeeLocalService getWrappedCalEventAttendeeLocalService() {
		return _calEventAttendeeLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedCalEventAttendeeLocalService(
		CalEventAttendeeLocalService calEventAttendeeLocalService) {
		_calEventAttendeeLocalService = calEventAttendeeLocalService;
	}

	public CalEventAttendeeLocalService getWrappedService() {
		return _calEventAttendeeLocalService;
	}

	public void setWrappedService(
		CalEventAttendeeLocalService calEventAttendeeLocalService) {
		_calEventAttendeeLocalService = calEventAttendeeLocalService;
	}

	private CalEventAttendeeLocalService _calEventAttendeeLocalService;
}