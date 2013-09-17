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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.hook.calendar.service.http.CalEventAttendeeServiceSoap}.
 *
 * @author    Joaquin Cabal
 * @see       com.liferay.hook.calendar.service.http.CalEventAttendeeServiceSoap
 * @generated
 */
public class CalEventAttendeeSoap implements Serializable {
	public static CalEventAttendeeSoap toSoapModel(CalEventAttendee model) {
		CalEventAttendeeSoap soapModel = new CalEventAttendeeSoap();

		soapModel.setCalEventAttendeeId(model.getCalEventAttendeeId());
		soapModel.setEventId(model.getEventId());
		soapModel.setUserId(model.getUserId());
		soapModel.setAssist(model.getAssist());

		return soapModel;
	}

	public static CalEventAttendeeSoap[] toSoapModels(CalEventAttendee[] models) {
		CalEventAttendeeSoap[] soapModels = new CalEventAttendeeSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static CalEventAttendeeSoap[][] toSoapModels(
		CalEventAttendee[][] models) {
		CalEventAttendeeSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new CalEventAttendeeSoap[models.length][models[0].length];
		}
		else {
			soapModels = new CalEventAttendeeSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static CalEventAttendeeSoap[] toSoapModels(
		List<CalEventAttendee> models) {
		List<CalEventAttendeeSoap> soapModels = new ArrayList<CalEventAttendeeSoap>(models.size());

		for (CalEventAttendee model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new CalEventAttendeeSoap[soapModels.size()]);
	}

	public CalEventAttendeeSoap() {
	}

	public long getPrimaryKey() {
		return _calEventAttendeeId;
	}

	public void setPrimaryKey(long pk) {
		setCalEventAttendeeId(pk);
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

	public boolean getAssist() {
		return _assist;
	}

	public boolean isAssist() {
		return _assist;
	}

	public void setAssist(boolean assist) {
		_assist = assist;
	}

	private long _calEventAttendeeId;
	private long _eventId;
	private long _userId;
	private boolean _assist;
}