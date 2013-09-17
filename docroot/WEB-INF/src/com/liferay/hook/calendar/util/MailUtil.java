package com.liferay.hook.calendar.util;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

import com.liferay.mail.service.MailServiceUtil;
import com.liferay.portal.kernel.mail.MailMessage;
import com.liferay.portal.kernel.util.DateUtil;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HtmlUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.calendar.model.CalEvent;
import com.liferay.util.ContentUtil;


public class MailUtil {
	/***
	 * Notify attendees of an created event
	 * @param emailAddress
	 * @throws UnsupportedEncodingException 
	 **/
	public static void notifyAttendee(User user,CalEvent event,ThemeDisplay td) throws UnsupportedEncodingException {
		try {
			String body = ContentUtil.get("/com/liferay/hook/calendar/dependencies/email_event_notify_attendee.tmpl", true);
			InternetAddress from = new InternetAddress();
			from.setAddress("test@liferay.com");
			from.setPersonal("Administrador");
			
			InternetAddress to = new InternetAddress(user.getEmailAddress());
			to.setAddress(user.getEmailAddress());
			to.setPersonal(user.getFullName());
			
			
			String bodyComplete = StringUtil.replace(
					body,
					new String[] {
						"[$FROM_ADDRESS$]", "[$FROM_NAME$]", "[$TO_ADDRESS$]",
						"[$TO_NAME$]","[$EVENT_TITLE$]","[$EVENT_START_DATE$]"
					},
					new String[] {
						from.getAddress(),
						GetterUtil.getString(from.getPersonal(), from.getAddress()),
						HtmlUtil.escape(to.getAddress()),HtmlUtil.escape(GetterUtil.getString(to.getPersonal(), to.getAddress())),
						event.getTitle(),DateUtil.getDate(event.getStartDate(), "dd-MM-yyyy HH-mm", Locale.getDefault())
						
					});
			MailMessage mailMessage = new MailMessage(from,to,"Nuevo Evento",bodyComplete,true);
			
			MailServiceUtil.sendEmail(mailMessage);
			
		} catch (AddressException e) {
			
		}
		
	}
	
}
