package com.liferay.hook.calendar.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.liferay.portal.kernel.struts.BaseStrutsAction;



public class EditEventHookAction extends BaseStrutsAction {
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		return "/calendar/hook/edit_event_hook.jsp";
	}
		
}
