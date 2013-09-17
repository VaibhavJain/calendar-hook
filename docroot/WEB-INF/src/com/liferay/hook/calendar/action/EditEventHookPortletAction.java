package com.liferay.hook.calendar.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;
import javax.portlet.WindowState;
import javax.servlet.http.HttpServletRequest;

import com.liferay.counter.service.CounterLocalServiceUtil;
import com.liferay.hook.calendar.model.CalEventAttendee;
import com.liferay.hook.calendar.service.CalEventAttendeeLocalServiceUtil;
import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.cal.DayAndPosition;
import com.liferay.portal.kernel.cal.Duration;
import com.liferay.portal.kernel.cal.Recurrence;
import com.liferay.portal.kernel.cal.TZSRecurrence;
import com.liferay.portal.kernel.dao.search.DisplayTerms;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.json.JSONArray;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.struts.BaseStrutsPortletAction;
import com.liferay.portal.kernel.struts.StrutsPortletAction;
import com.liferay.portal.kernel.util.CalendarFactoryUtil;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.kernel.workflow.WorkflowConstants;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.comparator.UserLastNameComparator;
import com.liferay.portlet.asset.AssetCategoryException;
import com.liferay.portlet.asset.AssetTagException;
import com.liferay.hook.calendar.util.AssetPublisherUtil;
import com.liferay.hook.calendar.util.MailUtil;
import com.liferay.portlet.calendar.EventDurationException;
import com.liferay.portlet.calendar.EventEndDateException;
import com.liferay.portlet.calendar.EventStartDateException;
import com.liferay.portlet.calendar.EventTitleException;
import com.liferay.portlet.calendar.NoSuchEventException;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.portlet.calendar.service.CalEventLocalServiceUtil;
import com.liferay.portlet.calendar.service.CalEventServiceUtil;
import com.liferay.util.ContentUtil;

public class EditEventHookPortletAction extends BaseStrutsPortletAction {
	
	public static Log LOGGER = LogFactoryUtil.getLog(EditEventHookPortletAction.class);
	
	
	@Override
	public void processAction(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ActionRequest actionRequest,
			ActionResponse actionResponse) throws Exception {
		
		String cmd = ParamUtil.getString(actionRequest, Constants.CMD);
		CalEvent event = null;
		try {
			if (cmd.equals(Constants.ADD) || cmd.equals(Constants.UPDATE)) {
				event = updateEvent(actionRequest);
				
				//Add CalEventAttendess
				saveCalEventAttendees(actionRequest,event.getEventId());
			}
			else if (cmd.equals(Constants.DELETE)) {
				deleteEvent(actionRequest);
			}

			WindowState windowState = actionRequest.getWindowState();

			if (!windowState.equals(LiferayWindowState.POP_UP)) {
				PortletHookActionUtil.sendRedirect(actionRequest, actionResponse);
			}
			else {
				String redirect = PortalUtil.escapeRedirect(
					ParamUtil.getString(actionRequest, "redirect"));

				if (Validator.isNotNull(redirect)) {
					actionResponse.sendRedirect(redirect);
				}
			}
		}
		catch (Exception e) {
			if (e instanceof NoSuchEventException ||
				e instanceof PrincipalException) {

				SessionErrors.add(actionRequest, e.getClass());

				PortletHookActionUtil.setForward(actionRequest, "portlet.calendar.error");
			}
			else if (e instanceof EventDurationException ||
					 e instanceof EventEndDateException ||
					 e instanceof EventStartDateException ||
					 e instanceof EventTitleException) {

				SessionErrors.add(actionRequest, e.getClass());
			}
			else if (e instanceof AssetCategoryException ||
					 e instanceof AssetTagException) {

				SessionErrors.add(actionRequest, e.getClass(), e);
			}
			else {
				throw e;
			}
		}
		//originalStrutsPortletAction.processAction(originalStrutsPortletAction,
		//	portletConfig, actionRequest, actionResponse);
	}
	
	
	
	
	@Override
	public String render(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, RenderRequest renderRequest,
			RenderResponse renderResponse) throws Exception {
		
		
		long eventId = ParamUtil.getLong(renderRequest, "eventId");
		if(eventId > 0) {
			List<CalEventAttendee> attendees = CalEventAttendeeLocalServiceUtil.findCalEventAttendeesByEventId(eventId);
			
			if(attendees != null && !attendees.isEmpty()) {
				List<User> users = new ArrayList<User>(attendees.size());
				for(CalEventAttendee calEventAttendee : attendees) {
					users.add(UserLocalServiceUtil.getUser(calEventAttendee.getUserId()));
				}
				renderRequest.setAttribute("attendees",users);
			}
			
			
		}
		
		return originalStrutsPortletAction.render(null, portletConfig,
				renderRequest, renderResponse);
	}
	
	
	@Override
	public void serveResource(StrutsPortletAction originalStrutsPortletAction,
			PortletConfig portletConfig, ResourceRequest resourceRequest,
			ResourceResponse resourceResponse) throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay) resourceRequest.getAttribute(WebKeys.THEME_DISPLAY);
		String keywords = ParamUtil.getString(resourceRequest, DisplayTerms.KEYWORDS);
		JSONArray attendees = getUsersByFullName(keywords, themeDisplay.getCompanyId());
		
		resourceResponse.getWriter().write(attendees.toString());
		
	}
	
	
	protected void deleteEvent(ActionRequest actionRequest) throws Exception {
		long eventId = ParamUtil.getLong(actionRequest, "eventId");

		CalEventServiceUtil.deleteEvent(eventId);
		CalEventAttendeeLocalServiceUtil.deleteCalEventAttendeesByEventId(eventId);
	}
	
	
	
	/***
	 * Save attendees involved with an event
	 * @param actionRequest
	 * @param eventId
	 * @throws SystemException
	 * @throws PortalException 
	 * @throws UnsupportedEncodingException 
	 **/
	private void saveCalEventAttendees(ActionRequest actionRequest,long eventId) throws SystemException, PortalException, UnsupportedEncodingException{
		
		CalEventAttendeeLocalServiceUtil.deleteCalEventAttendeesByEventId(eventId);
		
		String [] attendeeIds = ParamUtil.getParameterValues(actionRequest, "attendeesUserId");
		ThemeDisplay td = (ThemeDisplay) actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		if(Validator.isNotNull(attendeeIds) && attendeeIds.length > 0) {
			for(int i=0;i<attendeeIds.length;i++) {
				CalEventAttendee calEventAttendee = CalEventAttendeeLocalServiceUtil.createCalEventAttendee(
						CounterLocalServiceUtil.increment());
				calEventAttendee.setEventId(eventId);
				
				User user = UserLocalServiceUtil.getUser(Long.valueOf(attendeeIds[i]));
				calEventAttendee.setUserId(user.getUserId());
				calEventAttendee.setAssist(false);
				
				CalEventAttendeeLocalServiceUtil.addCalEventAttendee(calEventAttendee);
				
				CalEvent event = CalEventLocalServiceUtil.getCalEvent(eventId);
				
				MailUtil.notifyAttendee(user,event,td);
			}
			
		}
		
	}
	
	
	
	
	/***
	 * create event
	 * @param actionRequest
	 * @return
	 * @throws Exception
	 **/
	private CalEvent updateEvent(ActionRequest actionRequest) throws Exception {
		long eventId = ParamUtil.getLong(actionRequest, "eventId");
		
		String title = ParamUtil.getString(actionRequest, "title");
		String description = ParamUtil.getString(actionRequest, "description");
		String location = ParamUtil.getString(actionRequest, "location");

		int startDateMonth = ParamUtil.getInteger(
			actionRequest, "startDateMonth");
		int startDateDay = ParamUtil.getInteger(actionRequest, "startDateDay");
		int startDateYear = ParamUtil.getInteger(
			actionRequest, "startDateYear");
		int startDateHour = ParamUtil.getInteger(
			actionRequest, "startDateHour");
		int startDateMinute = ParamUtil.getInteger(
			actionRequest, "startDateMinute");
		int startDateAmPm = ParamUtil.getInteger(
			actionRequest, "startDateAmPm");

		if (startDateAmPm == Calendar.PM) {
			startDateHour += 12;
		}

		int durationHour = ParamUtil.getInteger(actionRequest, "durationHour");
		int durationMinute = ParamUtil.getInteger(
			actionRequest, "durationMinute");
		boolean allDay = ParamUtil.getBoolean(actionRequest, "allDay");
		boolean timeZoneSensitive = ParamUtil.getBoolean(
			actionRequest, "timeZoneSensitive");
		String type = ParamUtil.getString(actionRequest, "type");

		int endDateMonth = ParamUtil.getInteger(actionRequest, "endDateMonth");
		int endDateDay = ParamUtil.getInteger(actionRequest, "endDateDay");
		int endDateYear = ParamUtil.getInteger(actionRequest, "endDateYear");

		boolean repeating = false;

		int recurrenceType = ParamUtil.getInteger(
			actionRequest, "recurrenceType");

		if (recurrenceType != Recurrence.NO_RECURRENCE) {
			repeating = true;
		}

		Locale locale = null;
		TimeZone timeZone = null;

		if (timeZoneSensitive) {
			User user = PortalUtil.getUser(actionRequest);

			locale = user.getLocale();
			timeZone = user.getTimeZone();
		}
		else {
			locale = LocaleUtil.getDefault();
			timeZone = TimeZoneUtil.getDefault();
		}

		Calendar startDate = CalendarFactoryUtil.getCalendar(timeZone, locale);

		startDate.set(Calendar.MONTH, startDateMonth);
		startDate.set(Calendar.DATE, startDateDay);
		startDate.set(Calendar.YEAR, startDateYear);
		startDate.set(Calendar.HOUR_OF_DAY, startDateHour);
		startDate.set(Calendar.MINUTE, startDateMinute);
		startDate.set(Calendar.SECOND, 0);
		startDate.set(Calendar.MILLISECOND, 0);

		if (allDay) {
			startDate.set(Calendar.HOUR_OF_DAY, 0);
			startDate.set(Calendar.MINUTE, 0);
			startDate.set(Calendar.SECOND, 0);
			startDate.set(Calendar.MILLISECOND, 0);

			durationHour = 24;
			durationMinute = 0;
		}

		TZSRecurrence recurrence = null;

		if (repeating) {
			Calendar recStartCal = null;

			if (timeZoneSensitive) {
				recStartCal = CalendarFactoryUtil.getCalendar(
					TimeZoneUtil.getTimeZone(StringPool.UTC));

				recStartCal.setTime(startDate.getTime());
			}
			else {
				recStartCal = (Calendar)startDate.clone();
			}

			recurrence = new TZSRecurrence(
				recStartCal, new Duration(1, 0, 0, 0), recurrenceType);

			recurrence.setTimeZone(timeZone);

			recurrence.setWeekStart(Calendar.SUNDAY);

			if (recurrenceType == Recurrence.DAILY) {
				int dailyType = ParamUtil.getInteger(
					actionRequest, "dailyType");

				if (dailyType == 0) {
					int dailyInterval = ParamUtil.getInteger(
						actionRequest, "dailyInterval", 1);

					recurrence.setInterval(dailyInterval);
				}
				else {
					DayAndPosition[] dayPos = {
						new DayAndPosition(Calendar.MONDAY, 0),
						new DayAndPosition(Calendar.TUESDAY, 0),
						new DayAndPosition(Calendar.WEDNESDAY, 0),
						new DayAndPosition(Calendar.THURSDAY, 0),
						new DayAndPosition(Calendar.FRIDAY, 0)};

					recurrence.setByDay(dayPos);
				}
			}
			else if (recurrenceType == Recurrence.WEEKLY) {
				int weeklyInterval = ParamUtil.getInteger(
					actionRequest, "weeklyInterval", 1);

				recurrence.setInterval(weeklyInterval);

				List<DayAndPosition> dayPos = new ArrayList<DayAndPosition>();

				addWeeklyDayPos(actionRequest, dayPos, Calendar.SUNDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.MONDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.TUESDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.WEDNESDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.THURSDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.FRIDAY);
				addWeeklyDayPos(actionRequest, dayPos, Calendar.SATURDAY);

				if (dayPos.size() == 0) {
					dayPos.add(new DayAndPosition(Calendar.MONDAY, 0));
				}

				recurrence.setByDay(
					dayPos.toArray(new DayAndPosition[dayPos.size()]));
			}
			else if (recurrenceType == Recurrence.MONTHLY) {
				int monthlyType = ParamUtil.getInteger(
					actionRequest, "monthlyType");

				if (monthlyType == 0) {
					int monthlyDay = ParamUtil.getInteger(
						actionRequest, "monthlyDay0");

					recurrence.setByMonthDay(new int[] {monthlyDay});

					int monthlyInterval = ParamUtil.getInteger(
						actionRequest, "monthlyInterval0", 1);

					recurrence.setInterval(monthlyInterval);
				}
				else {
					int monthlyPos = ParamUtil.getInteger(
						actionRequest, "monthlyPos");
					int monthlyDay = ParamUtil.getInteger(
						actionRequest, "monthlyDay1");

					DayAndPosition[] dayPos = {
						new DayAndPosition(monthlyDay, monthlyPos)};

					recurrence.setByDay(dayPos);

					int monthlyInterval = ParamUtil.getInteger(
						actionRequest, "monthlyInterval1", 1);

					recurrence.setInterval(monthlyInterval);
				}
			}
			else if (recurrenceType == Recurrence.YEARLY) {
				int yearlyType = ParamUtil.getInteger(
					actionRequest, "yearlyType");

				if (yearlyType == 0) {
					int yearlyMonth = ParamUtil.getInteger(
						actionRequest, "yearlyMonth0");
					int yearlyDay = ParamUtil.getInteger(
						actionRequest, "yearlyDay0");

					recurrence.setByMonth(new int[] {yearlyMonth});
					recurrence.setByMonthDay(new int[] {yearlyDay});

					int yearlyInterval = ParamUtil.getInteger(
						actionRequest, "yearlyInterval0", 1);

					recurrence.setInterval(yearlyInterval);
				}
				else {
					int yearlyPos = ParamUtil.getInteger(
						actionRequest, "yearlyPos");
					int yearlyDay = ParamUtil.getInteger(
						actionRequest, "yearlyDay1");
					int yearlyMonth = ParamUtil.getInteger(
						actionRequest, "yearlyMonth1");

					DayAndPosition[] dayPos = {
						new DayAndPosition(yearlyDay, yearlyPos)};

					recurrence.setByDay(dayPos);

					recurrence.setByMonth(new int[] {yearlyMonth});

					int yearlyInterval = ParamUtil.getInteger(
						actionRequest, "yearlyInterval1", 1);

					recurrence.setInterval(yearlyInterval);
				}
			}

			int endDateType = ParamUtil.getInteger(
				actionRequest, "endDateType");

			if (endDateType == 1) {
				int endDateOccurrence = ParamUtil.getInteger(
					actionRequest, "endDateOccurrence");

				recurrence.setOccurrence(endDateOccurrence);
			}
			else if (endDateType == 2) {
				Calendar endDate = CalendarFactoryUtil.getCalendar(timeZone);

				endDate.set(Calendar.MONTH, endDateMonth);
				endDate.set(Calendar.DATE, endDateDay);
				endDate.set(Calendar.YEAR, endDateYear);
				endDate.set(Calendar.HOUR_OF_DAY, startDateHour);
				endDate.set(Calendar.MINUTE, startDateMinute);
				endDate.set(Calendar.SECOND, 0);
				endDate.set(Calendar.MILLISECOND, 0);

				Calendar recEndCal = null;

				if (timeZoneSensitive) {
					recEndCal = CalendarFactoryUtil.getCalendar(
						TimeZoneUtil.getTimeZone(StringPool.UTC));

					recEndCal.setTime(endDate.getTime());
				}
				else {
					recEndCal = (Calendar)endDate.clone();
				}

				recurrence.setUntil(recEndCal);
			}
		}

		int remindBy = ParamUtil.getInteger(actionRequest, "remindBy");
		int firstReminder = ParamUtil.getInteger(
			actionRequest, "firstReminder");
		int secondReminder = ParamUtil.getInteger(
			actionRequest, "secondReminder");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			CalEvent.class.getName(), actionRequest);
		
		
		CalEvent event = null;
		if (eventId <= 0) {

			// Add event

			event = CalEventServiceUtil.addEvent(
				title, description, location, startDateMonth, startDateDay,
				startDateYear, startDateHour, startDateMinute, endDateMonth,
				endDateDay, endDateYear, durationHour, durationMinute, allDay,
				timeZoneSensitive, type, repeating, recurrence, remindBy,
				firstReminder, secondReminder, serviceContext);

			AssetPublisherUtil.addAndStoreSelection(
				actionRequest, CalEvent.class.getName(), event.getEventId(),
				-1);
			
			return event;
		}
		else {

			// Update event

			return CalEventServiceUtil.updateEvent(
				eventId, title, description, location, startDateMonth,
				startDateDay, startDateYear, startDateHour, startDateMinute,
				endDateMonth, endDateDay, endDateYear, durationHour,
				durationMinute, allDay, timeZoneSensitive, type, repeating,
				recurrence, remindBy, firstReminder, secondReminder,
				serviceContext);
		}
		
		
	}
	
	
	
	
	protected void addWeeklyDayPos(
			ActionRequest actionRequest, List<DayAndPosition> list, int day) {

			if (ParamUtil.getBoolean(actionRequest, "weeklyDayPos" + day)) {
				list.add(new DayAndPosition(day, 0));
			}
		}
	
	
	
	
	
	/**
	 * find attendees by keywords
	 * @param keywords
	 * @param companyId
	 * @return
	 * @throws SystemException
	 */
	public JSONArray getUsersByFullName(String keywords,long companyId) throws SystemException {
		JSONArray jsonArray = JSONFactoryUtil.createJSONArray();
		
		UserLastNameComparator orderComp = new UserLastNameComparator(true);
		
		List<User> users = UserLocalServiceUtil.search(companyId, keywords, WorkflowConstants.STATUS_ANY, null,0, 50, 
				orderComp);
		
		if(Validator.isNotNull(users) && !users.isEmpty()) {
			for(User u : users) {
				JSONObject jsonObject = JSONFactoryUtil.createJSONObject();
				jsonObject.put("userId", u.getUserId());
				jsonObject.put("firstName", u.getFirstName());
				jsonObject.put("lastName", u.getLastName());
				jsonObject.put("fullName", u.getLastName()+" "+u.getFirstName());
				jsonObject.put("emailAddress",u.getEmailAddress());
				
				jsonArray.put(jsonObject);
			}
		}
		
		
		return jsonArray;
	}
	
	
	protected void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse,
			String redirect)
		throws IOException, SystemException {

		if (SessionErrors.isEmpty(actionRequest)) {
			ThemeDisplay themeDisplay =
				(ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

			LayoutTypePortlet layoutTypePortlet =
				themeDisplay.getLayoutTypePortlet();

			boolean hasPortletId = false;

			String portletId = (String)actionRequest.getAttribute(
				WebKeys.PORTLET_ID);

			try {
				hasPortletId = layoutTypePortlet.hasPortletId(portletId);
			}
			catch (Exception e) {
			}

			Portlet portlet = PortletLocalServiceUtil.getPortletById(
				themeDisplay.getCompanyId(), portletId);

			if (hasPortletId || portlet.isAddDefaultResource()) {
				PortletHookActionUtil.addSuccessMessage(actionRequest, actionResponse);
			}
		}

		if (Validator.isNull(redirect)) {
			redirect = (String)actionRequest.getAttribute(WebKeys.REDIRECT);
		}

		if (Validator.isNull(redirect)) {
			redirect = ParamUtil.getString(actionRequest, "redirect");
		}

		if (Validator.isNotNull(redirect)) {

			// LPS-1928

			HttpServletRequest request = PortalUtil.getHttpServletRequest(
				actionRequest);

			if (BrowserSnifferUtil.isIe(request) &&
				(BrowserSnifferUtil.getMajorVersion(request) == 6.0) &&
				redirect.contains(StringPool.POUND)) {

				String redirectToken = "&#";

				if (!redirect.contains(StringPool.QUESTION)) {
					redirectToken = StringPool.QUESTION + redirectToken;
				}

				redirect = StringUtil.replace(
					redirect, StringPool.POUND, redirectToken);
			}

			redirect = PortalUtil.escapeRedirect(redirect);

			if (Validator.isNotNull(redirect)) {
				actionResponse.sendRedirect(redirect);
			}
		}
	}
	
}
