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

package com.liferay.hook.calendar.service.persistence;

import com.liferay.hook.calendar.model.CalEventAttendee;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cal event attendee service. This utility wraps {@link CalEventAttendeePersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Joaquin Cabal
 * @see CalEventAttendeePersistence
 * @see CalEventAttendeePersistenceImpl
 * @generated
 */
public class CalEventAttendeeUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(CalEventAttendee calEventAttendee) {
		getPersistence().clearCache(calEventAttendee);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<CalEventAttendee> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<CalEventAttendee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<CalEventAttendee> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static CalEventAttendee update(CalEventAttendee calEventAttendee,
		boolean merge) throws SystemException {
		return getPersistence().update(calEventAttendee, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static CalEventAttendee update(CalEventAttendee calEventAttendee,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(calEventAttendee, merge, serviceContext);
	}

	/**
	* Caches the cal event attendee in the entity cache if it is enabled.
	*
	* @param calEventAttendee the cal event attendee
	*/
	public static void cacheResult(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee) {
		getPersistence().cacheResult(calEventAttendee);
	}

	/**
	* Caches the cal event attendees in the entity cache if it is enabled.
	*
	* @param calEventAttendees the cal event attendees
	*/
	public static void cacheResult(
		java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> calEventAttendees) {
		getPersistence().cacheResult(calEventAttendees);
	}

	/**
	* Creates a new cal event attendee with the primary key. Does not add the cal event attendee to the database.
	*
	* @param calEventAttendeeId the primary key for the new cal event attendee
	* @return the new cal event attendee
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee create(
		long calEventAttendeeId) {
		return getPersistence().create(calEventAttendeeId);
	}

	/**
	* Removes the cal event attendee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee that was removed
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee remove(
		long calEventAttendeeId)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(calEventAttendeeId);
	}

	public static com.liferay.hook.calendar.model.CalEventAttendee updateImpl(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(calEventAttendee, merge);
	}

	/**
	* Returns the cal event attendee with the primary key or throws a {@link com.liferay.hook.calendar.NoSuchCalEventAttendeeException} if it could not be found.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByPrimaryKey(
		long calEventAttendeeId)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(calEventAttendeeId);
	}

	/**
	* Returns the cal event attendee with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee, or <code>null</code> if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByPrimaryKey(
		long calEventAttendeeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(calEventAttendeeId);
	}

	/**
	* Returns all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventUserAssist(userId, eventId, assist);
	}

	/**
	* Returns a range of all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventUserAssist(userId, eventId, assist, start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventUserAssist(userId, eventId, assist, start, end,
			orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEventUserAssist_First(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventUserAssist_First(userId, eventId, assist,
			orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEventUserAssist_First(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventUserAssist_First(userId, eventId, assist,
			orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEventUserAssist_Last(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventUserAssist_Last(userId, eventId, assist,
			orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEventUserAssist_Last(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventUserAssist_Last(userId, eventId, assist,
			orderByComparator);
	}

	/**
	* Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param calEventAttendeeId the primary key of the current cal event attendee
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee[] findByEventUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventUserAssist_PrevAndNext(calEventAttendeeId,
			userId, eventId, assist, orderByComparator);
	}

	/**
	* Returns all the cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventAssist(eventId, assist);
	}

	/**
	* Returns a range of all the cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEventAssist(eventId, assist, start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventAssist(eventId, assist, start, end,
			orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEventAssist_First(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventAssist_First(eventId, assist, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEventAssist_First(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventAssist_First(eventId, assist, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEventAssist_Last(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventAssist_Last(eventId, assist, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEventAssist_Last(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByEventAssist_Last(eventId, assist, orderByComparator);
	}

	/**
	* Returns the cal event attendees before and after the current cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param calEventAttendeeId the primary key of the current cal event attendee
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee[] findByEventAssist_PrevAndNext(
		long calEventAttendeeId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEventAssist_PrevAndNext(calEventAttendeeId, eventId,
			assist, orderByComparator);
	}

	/**
	* Returns all the cal event attendees where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserAssist(userId, assist);
	}

	/**
	* Returns a range of all the cal event attendees where userId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserAssist(userId, assist, start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees where userId = &#63; and assist = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param assist the assist
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserAssist(userId, assist, start, end,
			orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByUserAssist_First(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserAssist_First(userId, assist, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByUserAssist_First(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserAssist_First(userId, assist, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByUserAssist_Last(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserAssist_Last(userId, assist, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByUserAssist_Last(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByUserAssist_Last(userId, assist, orderByComparator);
	}

	/**
	* Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param calEventAttendeeId the primary key of the current cal event attendee
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee[] findByUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserAssist_PrevAndNext(calEventAttendeeId, userId,
			assist, orderByComparator);
	}

	/**
	* Returns all the cal event attendees where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUser(userId);
	}

	/**
	* Returns a range of all the cal event attendees where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUser(userId, start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUser(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByUser_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUser_First(userId, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByUser_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUser_First(userId, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByUser_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUser_Last(userId, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByUser_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByUser_Last(userId, orderByComparator);
	}

	/**
	* Returns the cal event attendees before and after the current cal event attendee in the ordered set where userId = &#63;.
	*
	* @param calEventAttendeeId the primary key of the current cal event attendee
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee[] findByUser_PrevAndNext(
		long calEventAttendeeId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUser_PrevAndNext(calEventAttendeeId, userId,
			orderByComparator);
	}

	/**
	* Returns all the cal event attendees where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEvent(eventId);
	}

	/**
	* Returns a range of all the cal event attendees where eventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventId the event ID
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @return the range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEvent(eventId, start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees where eventId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param eventId the event ID
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEvent(eventId, start, end, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEvent_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEvent_First(eventId, orderByComparator);
	}

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEvent_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEvent_First(eventId, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee findByEvent_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByEvent_Last(eventId, orderByComparator);
	}

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee fetchByEvent_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByEvent_Last(eventId, orderByComparator);
	}

	/**
	* Returns the cal event attendees before and after the current cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param calEventAttendeeId the primary key of the current cal event attendee
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.hook.calendar.model.CalEventAttendee[] findByEvent_PrevAndNext(
		long calEventAttendeeId, long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByEvent_PrevAndNext(calEventAttendeeId, eventId,
			orderByComparator);
	}

	/**
	* Returns all the cal event attendees.
	*
	* @return the cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
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
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the cal event attendees.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of cal event attendees
	* @param end the upper bound of the range of cal event attendees (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEventUserAssist(long userId, long eventId,
		boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEventUserAssist(userId, eventId, assist);
	}

	/**
	* Removes all the cal event attendees where eventId = &#63; and assist = &#63; from the database.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEventAssist(long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEventAssist(eventId, assist);
	}

	/**
	* Removes all the cal event attendees where userId = &#63; and assist = &#63; from the database.
	*
	* @param userId the user ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUserAssist(long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserAssist(userId, assist);
	}

	/**
	* Removes all the cal event attendees where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUser(userId);
	}

	/**
	* Removes all the cal event attendees where eventId = &#63; from the database.
	*
	* @param eventId the event ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeByEvent(long eventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByEvent(eventId);
	}

	/**
	* Removes all the cal event attendees from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEventUserAssist(long userId, long eventId,
		boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEventUserAssist(userId, eventId, assist);
	}

	/**
	* Returns the number of cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEventAssist(long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEventAssist(eventId, assist);
	}

	/**
	* Returns the number of cal event attendees where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUserAssist(long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserAssist(userId, assist);
	}

	/**
	* Returns the number of cal event attendees where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUser(userId);
	}

	/**
	* Returns the number of cal event attendees where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countByEvent(long eventId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByEvent(eventId);
	}

	/**
	* Returns the number of cal event attendees.
	*
	* @return the number of cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static CalEventAttendeePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (CalEventAttendeePersistence)PortletBeanLocatorUtil.locate(com.liferay.hook.calendar.service.ClpSerializer.getServletContextName(),
					CalEventAttendeePersistence.class.getName());

			ReferenceRegistry.registerReference(CalEventAttendeeUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(CalEventAttendeePersistence persistence) {
	}

	private static CalEventAttendeePersistence _persistence;
}