package com.liferay.hook.calendar.action;

import java.io.IOException;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.PortletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.Globals;
import org.apache.struts.config.ModuleConfig;

import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.BrowserSnifferUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.JavaConstants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.LayoutTypePortlet;
import com.liferay.portal.model.Portlet;
import com.liferay.portal.service.PortletLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.WebKeys;


public class PortletHookActionUtil {
	public static String getForwardKey(HttpServletRequest request) {
		String portletId = (String)request.getAttribute(WebKeys.PORTLET_ID);

		String portletNamespace = PortalUtil.getPortletNamespace(portletId);

		return portletNamespace.concat(WebKeys.PORTLET_STRUTS_FORWARD);
	}

	public static String getForwardKey(PortletRequest portletRequest) {
		String portletId = (String)portletRequest.getAttribute(
			WebKeys.PORTLET_ID);

		String portletNamespace = PortalUtil.getPortletNamespace(portletId);

		return portletNamespace.concat(WebKeys.PORTLET_STRUTS_FORWARD);
	}

	protected static void addSuccessMessage(
		ActionRequest actionRequest, ActionResponse actionResponse) {

		PortletConfig portletConfig = (PortletConfig)actionRequest.getAttribute(
			JavaConstants.JAVAX_PORTLET_CONFIG);

		boolean addProcessActionSuccessMessage = GetterUtil.getBoolean(
			portletConfig.getInitParameter("add-process-action-success-action"),
			true);

		if (!addProcessActionSuccessMessage) {
			return;
		}

		String successMessage = ParamUtil.getString(
			actionRequest, "successMessage");

		SessionMessages.add(actionRequest, "request_processed", successMessage);
	}

	protected static String getForward(PortletRequest portletRequest) {
		return getForward(portletRequest, null);
	}

	protected static String getForward(
		PortletRequest portletRequest, String defaultValue) {

		String forward = (String)portletRequest.getAttribute(
			getForwardKey(portletRequest));

		if (forward == null) {
			return defaultValue;
		}
		else {
			return forward;
		}
	}
	
	protected static void sendRedirect(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws IOException, SystemException {

		sendRedirect(actionRequest, actionResponse, null);
	}

	protected static void sendRedirect(
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
				addSuccessMessage(actionRequest, actionResponse);
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

	protected static void setForward(PortletRequest portletRequest, String forward) {
		portletRequest.setAttribute(getForwardKey(portletRequest), forward);
	}
}	
