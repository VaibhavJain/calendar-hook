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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cal event attendee service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Joaquin Cabal
 * @see CalEventAttendeePersistenceImpl
 * @see CalEventAttendeeUtil
 * @generated
 */
public interface CalEventAttendeePersistence extends BasePersistence<CalEventAttendee> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link CalEventAttendeeUtil} to access the cal event attendee persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the cal event attendee in the entity cache if it is enabled.
	*
	* @param calEventAttendee the cal event attendee
	*/
	public void cacheResult(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee);

	/**
	* Caches the cal event attendees in the entity cache if it is enabled.
	*
	* @param calEventAttendees the cal event attendees
	*/
	public void cacheResult(
		java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> calEventAttendees);

	/**
	* Creates a new cal event attendee with the primary key. Does not add the cal event attendee to the database.
	*
	* @param calEventAttendeeId the primary key for the new cal event attendee
	* @return the new cal event attendee
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee create(
		long calEventAttendeeId);

	/**
	* Removes the cal event attendee with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee that was removed
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee remove(
		long calEventAttendeeId)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.hook.calendar.model.CalEventAttendee updateImpl(
		com.liferay.hook.calendar.model.CalEventAttendee calEventAttendee,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the cal event attendee with the primary key or throws a {@link com.liferay.hook.calendar.NoSuchCalEventAttendeeException} if it could not be found.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee findByPrimaryKey(
		long calEventAttendeeId)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the cal event attendee with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param calEventAttendeeId the primary key of the cal event attendee
	* @return the cal event attendee, or <code>null</code> if a cal event attendee with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByPrimaryKey(
		long calEventAttendeeId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventUserAssist(
		long userId, long eventId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByEventUserAssist_First(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEventUserAssist_First(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByEventUserAssist_Last(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEventUserAssist_Last(
		long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee[] findByEventUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEventAssist(
		long eventId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByEventAssist_First(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEventAssist_First(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByEventAssist_Last(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEventAssist_Last(
		long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee[] findByEventAssist_PrevAndNext(
		long calEventAttendeeId, long eventId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUserAssist(
		long userId, boolean assist, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByUserAssist_First(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByUserAssist_First(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee findByUserAssist_Last(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByUserAssist_Last(
		long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee[] findByUserAssist_PrevAndNext(
		long calEventAttendeeId, long userId, boolean assist,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId) throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByUser(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee findByUser_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByUser_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee findByUser_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByUser_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee[] findByUser_PrevAndNext(
		long calEventAttendeeId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findByEvent(
		long eventId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee findByEvent_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEvent_First(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee
	* @throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee findByEvent_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last cal event attendee in the ordered set where eventId = &#63;.
	*
	* @param eventId the event ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching cal event attendee, or <code>null</code> if a matching cal event attendee could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.hook.calendar.model.CalEventAttendee fetchByEvent_Last(
		long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public com.liferay.hook.calendar.model.CalEventAttendee[] findByEvent_PrevAndNext(
		long calEventAttendeeId, long eventId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.hook.calendar.NoSuchCalEventAttendeeException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the cal event attendees.
	*
	* @return the cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

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
	public java.util.List<com.liferay.hook.calendar.model.CalEventAttendee> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63; from the database.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEventUserAssist(long userId, long eventId,
		boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees where eventId = &#63; and assist = &#63; from the database.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEventAssist(long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees where userId = &#63; and assist = &#63; from the database.
	*
	* @param userId the user ID
	* @param assist the assist
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUserAssist(long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees where userId = &#63; from the database.
	*
	* @param userId the user ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees where eventId = &#63; from the database.
	*
	* @param eventId the event ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByEvent(long eventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the cal event attendees from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees where userId = &#63; and eventId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param eventId the event ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventUserAssist(long userId, long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees where eventId = &#63; and assist = &#63;.
	*
	* @param eventId the event ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countByEventAssist(long eventId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees where userId = &#63; and assist = &#63;.
	*
	* @param userId the user ID
	* @param assist the assist
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countByUserAssist(long userId, boolean assist)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countByUser(long userId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees where eventId = &#63;.
	*
	* @param eventId the event ID
	* @return the number of matching cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countByEvent(long eventId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of cal event attendees.
	*
	* @return the number of cal event attendees
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}