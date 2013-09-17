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

package com.liferay.hook.calendar.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link CalEventAttendee}.
 * </p>
 *
 * @author    Joaquin Cabal
 * @see       CalEventAttendee
 * @generated
 */
public class CalEventAttendeeWrapper implements CalEventAttendee,
	ModelWrapper<CalEventAttendee> {
	public CalEventAttendeeWrapper(CalEventAttendee calEventAttendee) {
		_calEventAttendee = calEventAttendee;
	}

	public Class<?> getModelClass() {
		return CalEventAttendee.class;
	}

	public String getModelClassName() {
		return CalEventAttendee.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("calEventAttendeeId", getCalEventAttendeeId());
		attributes.put("eventId", getEventId());
		attributes.put("userId", getUserId());
		attributes.put("assist", getAssist());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long calEventAttendeeId = (Long)attributes.get("calEventAttendeeId");

		if (calEventAttendeeId != null) {
			setCalEventAttendeeId(calEventAttendeeId);
		}

		Long eventId = (Long)attributes.get("eventId");

		if (eventId != null) {
			setEventId(eventId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Boolean assist = (Boolean)attributes.get("assist");

		if (assist != null) {
			setAssist(assist);
		}
	}

	/**
	* Returns the primary key of this cal event attendee.
	*
	* @return the primary key of this cal event attendee
	*/
	public long getPrimaryKey() {
		return _calEventAttendee.getPrimaryKey();
	}

	/**
	* Sets the primary key of this cal event attendee.
	*
	* @param primaryKey the primary key of this cal event attendee
	*/
	public void setPrimaryKey(long primaryKey) {
		_calEventAttendee.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the cal event attendee ID of this cal event attendee.
	*
	* @return the cal event attendee ID of this cal event attendee
	*/
	public long getCalEventAttendeeId() {
		return _calEventAttendee.getCalEventAttendeeId();
	}

	/**
	* Sets the cal event attendee ID of this cal event attendee.
	*
	* @param calEventAttendeeId the cal event attendee ID of this cal event attendee
	*/
	public void setCalEventAttendeeId(long calEventAttendeeId) {
		_calEventAttendee.setCalEventAttendeeId(calEventAttendeeId);
	}

	/**
	* Returns the event ID of this cal event attendee.
	*
	* @return the event ID of this cal event attendee
	*/
	public long getEventId() {
		return _calEventAttendee.getEventId();
	}

	/**
	* Sets the event ID of this cal event attendee.
	*
	* @param eventId the event ID of this cal event attendee
	*/
	public void setEventId(long eventId) {
		_calEventAttendee.setEventId(eventId);
	}

	/**
	* Returns the user ID of this cal event attendee.
	*
	* @return the user ID of this cal event attendee
	*/
	public long getUserId() {
		return _calEventAttendee.getUserId();
	}

	/**
	* Sets the user ID of this cal event attendee.
	*
	* @param userId the user ID of this cal event attendee
	*/
	public void setUserId(long userId) {
		_calEventAttendee.setUserId(userId);
	}

	/**
	* Returns the user uuid of this cal event attendee.
	*
	* @return the user uuid of this cal event attendee
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _calEventAttendee.getUserUuid();
	}

	/**
	* Sets the user uuid of this cal event attendee.
	*
	* @param userUuid the user uuid of this cal event attendee
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_calEventAttendee.setUserUuid(userUuid);
	}

	/**
	* Returns the assist of this cal event attendee.
	*
	* @return the assist of this cal event attendee
	*/
	public boolean getAssist() {
		return _calEventAttendee.getAssist();
	}

	/**
	* Returns <code>true</code> if this cal event attendee is assist.
	*
	* @return <code>true</code> if this cal event attendee is assist; <code>false</code> otherwise
	*/
	public boolean isAssist() {
		return _calEventAttendee.isAssist();
	}

	/**
	* Sets whether this cal event attendee is assist.
	*
	* @param assist the assist of this cal event attendee
	*/
	public void setAssist(boolean assist) {
		_calEventAttendee.setAssist(assist);
	}

	public boolean isNew() {
		return _calEventAttendee.isNew();
	}

	public void setNew(boolean n) {
		_calEventAttendee.setNew(n);
	}

	public boolean isCachedModel() {
		return _calEventAttendee.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_calEventAttendee.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _calEventAttendee.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _calEventAttendee.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_calEventAttendee.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _calEventAttendee.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_calEventAttendee.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new CalEventAttendeeWrapper((CalEventAttendee)_calEventAttendee.clone());
	}

	public int compareTo(CalEventAttendee calEventAttendee) {
		return _calEventAttendee.compareTo(calEventAttendee);
	}

	@Override
	public int hashCode() {
		return _calEventAttendee.hashCode();
	}

	public com.liferay.portal.model.CacheModel<CalEventAttendee> toCacheModel() {
		return _calEventAttendee.toCacheModel();
	}

	public CalEventAttendee toEscapedModel() {
		return new CalEventAttendeeWrapper(_calEventAttendee.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _calEventAttendee.toString();
	}

	public java.lang.String toXmlString() {
		return _calEventAttendee.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_calEventAttendee.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public CalEventAttendee getWrappedCalEventAttendee() {
		return _calEventAttendee;
	}

	public CalEventAttendee getWrappedModel() {
		return _calEventAttendee;
	}

	public void resetOriginalValues() {
		_calEventAttendee.resetOriginalValues();
	}

	private CalEventAttendee _calEventAttendee;
}