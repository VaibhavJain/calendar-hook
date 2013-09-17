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

package com.liferay.hook.calendar.model.impl;

import com.liferay.hook.calendar.model.CalEventAttendee;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

/**
 * The cache model class for representing CalEventAttendee in entity cache.
 *
 * @author Joaquin Cabal
 * @see CalEventAttendee
 * @generated
 */
public class CalEventAttendeeCacheModel implements CacheModel<CalEventAttendee>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{calEventAttendeeId=");
		sb.append(calEventAttendeeId);
		sb.append(", eventId=");
		sb.append(eventId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", assist=");
		sb.append(assist);
		sb.append("}");

		return sb.toString();
	}

	public CalEventAttendee toEntityModel() {
		CalEventAttendeeImpl calEventAttendeeImpl = new CalEventAttendeeImpl();

		calEventAttendeeImpl.setCalEventAttendeeId(calEventAttendeeId);
		calEventAttendeeImpl.setEventId(eventId);
		calEventAttendeeImpl.setUserId(userId);
		calEventAttendeeImpl.setAssist(assist);

		calEventAttendeeImpl.resetOriginalValues();

		return calEventAttendeeImpl;
	}

	public long calEventAttendeeId;
	public long eventId;
	public long userId;
	public boolean assist;
}