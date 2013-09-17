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

import com.liferay.hook.calendar.service.CalEventAttendeeLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.lang.reflect.Proxy;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Joaquin Cabal
 */
public class CalEventAttendeeClp extends BaseModelImpl<CalEventAttendee>
	implements CalEventAttendee {
	public CalEventAttendeeClp() {
	}

	public Class<?> getModelClass() {
		return CalEventAttendee.class;
	}

	public String getModelClassName() {
		return CalEventAttendee.class.getName();
	}

	public long getPrimaryKey() {
		return _calEventAttendeeId;
	}

	public void setPrimaryKey(long primaryKey) {
		setCalEventAttendeeId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_calEventAttendeeId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("calEventAttendeeId", getCalEventAttendeeId());
		attributes.put("eventId", getEventId());
		attributes.put("userId", getUserId());
		attributes.put("assist", getAssist());

		return attributes;
	}

	@Override
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

	public long getCalEventAttendeeId() {
		return _calEventAttendeeId;
	}

	public void setCalEventAttendeeId(long calEventAttendeeId) {
		_calEventAttendeeId = calEventAttendeeId;
	}

	public long getEventId() {
		return _eventId;
	}

	public void setEventId(long eventId) {
		_eventId = eventId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public boolean getAssist() {
		return _assist;
	}

	public boolean isAssist() {
		return _assist;
	}

	public void setAssist(boolean assist) {
		_assist = assist;
	}

	public BaseModel<?> getCalEventAttendeeRemoteModel() {
		return _calEventAttendeeRemoteModel;
	}

	public void setCalEventAttendeeRemoteModel(
		BaseModel<?> calEventAttendeeRemoteModel) {
		_calEventAttendeeRemoteModel = calEventAttendeeRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			CalEventAttendeeLocalServiceUtil.addCalEventAttendee(this);
		}
		else {
			CalEventAttendeeLocalServiceUtil.updateCalEventAttendee(this);
		}
	}

	@Override
	public CalEventAttendee toEscapedModel() {
		return (CalEventAttendee)Proxy.newProxyInstance(CalEventAttendee.class.getClassLoader(),
			new Class[] { CalEventAttendee.class },
			new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		CalEventAttendeeClp clone = new CalEventAttendeeClp();

		clone.setCalEventAttendeeId(getCalEventAttendeeId());
		clone.setEventId(getEventId());
		clone.setUserId(getUserId());
		clone.setAssist(getAssist());

		return clone;
	}

	public int compareTo(CalEventAttendee calEventAttendee) {
		long primaryKey = calEventAttendee.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		CalEventAttendeeClp calEventAttendee = null;

		try {
			calEventAttendee = (CalEventAttendeeClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = calEventAttendee.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{calEventAttendeeId=");
		sb.append(getCalEventAttendeeId());
		sb.append(", eventId=");
		sb.append(getEventId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", assist=");
		sb.append(getAssist());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.hook.calendar.model.CalEventAttendee");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>calEventAttendeeId</column-name><column-value><![CDATA[");
		sb.append(getCalEventAttendeeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>eventId</column-name><column-value><![CDATA[");
		sb.append(getEventId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>assist</column-name><column-value><![CDATA[");
		sb.append(getAssist());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _calEventAttendeeId;
	private long _eventId;
	private long _userId;
	private String _userUuid;
	private boolean _assist;
	private BaseModel<?> _calEventAttendeeRemoteModel;
}