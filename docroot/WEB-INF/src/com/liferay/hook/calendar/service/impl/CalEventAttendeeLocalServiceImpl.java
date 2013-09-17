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

package com.liferay.hook.calendar.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.hook.calendar.model.CalEventAttendee;
import com.liferay.hook.calendar.service.base.CalEventAttendeeLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.UserLocalServiceUtil;

/**
 * The implementation of the cal event attendee local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.hook.calendar.service.CalEventAttendeeLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Joaquin Cabal
 * @see com.liferay.hook.calendar.service.base.CalEventAttendeeLocalServiceBaseImpl
 * @see com.liferay.hook.calendar.service.CalEventAttendeeLocalServiceUtil
 */
public class CalEventAttendeeLocalServiceImpl
	extends CalEventAttendeeLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.liferay.hook.calendar.service.CalEventAttendeeLocalServiceUtil} to access the cal event attendee local service.
	 */
	
	public void deleteCalEventAttendeesByEventId(long eventId) throws PortalException, SystemException{
		getCalEventAttendeePersistence().removeByEvent(eventId);
	}
	
	public List<CalEventAttendee> findCalEventAttendeesByEventId(long eventId) throws PortalException,SystemException {
		return getCalEventAttendeePersistence().findByEvent(eventId);
	}
	
	
}