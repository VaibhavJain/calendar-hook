package com.liferay.hook.calendar.util;

import java.io.IOException;

import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Element;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.WebKeys;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.portlet.asset.model.AssetEntry;
import com.liferay.portlet.asset.service.AssetEntryLocalServiceUtil;

public class AssetPublisherUtil {
	
	
	private static Log _log = LogFactoryUtil.getLog(AssetPublisherUtil.class);
	
	public static void addAndStoreSelection(
			PortletRequest portletRequest, String className, long classPK,
			int assetEntryOrder)
		throws Exception {

		String referringPortletResource = ParamUtil.getString(
			portletRequest, "referringPortletResource");

		if (Validator.isNull(referringPortletResource)) {
			return;
		}

		ThemeDisplay themeDisplay = (ThemeDisplay)portletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		Layout layout = LayoutLocalServiceUtil.getLayout(
			themeDisplay.getRefererPlid());

		PortletPreferences portletPreferences =
			PortletPreferencesFactoryUtil.getPortletSetup(
				themeDisplay.getScopeGroupId(), layout,
				referringPortletResource, null);

		String selectionStyle = portletPreferences.getValue(
			"selectionStyle", "dynamic");

		if (selectionStyle.equals("dynamic")) {
			return;
		}

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			className, classPK);

		addSelection(
			className, assetEntry.getEntryId(), assetEntryOrder,
			portletPreferences);

		portletPreferences.store();
	}
	
	
	public static void addSelection(
			String assetEntryType, long assetEntryId, int assetEntryOrder,
			PortletPreferences portletPreferences)
		throws Exception {

		AssetEntry assetEntry = AssetEntryLocalServiceUtil.getEntry(
			assetEntryId);

		String[] assetEntryXmls = portletPreferences.getValues(
			"assetEntryXml", new String[0]);

		String assetEntryXml = _getAssetEntryXml(
			assetEntryType, assetEntry.getClassUuid());

		if (assetEntryOrder > -1) {
			assetEntryXmls[assetEntryOrder] = assetEntryXml;
		}
		else {
			assetEntryXmls = ArrayUtil.append(assetEntryXmls, assetEntryXml);
		}

		portletPreferences.setValues("assetEntryXml", assetEntryXmls);
	}
	
	private static String _getAssetEntryXml(
			String assetEntryType, String assetEntryUuid) {

			String xml = null;

			try {
				Document document = SAXReaderUtil.createDocument(StringPool.UTF8);

				Element assetEntryElement = document.addElement("asset-entry");

				Element assetEntryTypeElement = assetEntryElement.addElement(
					"asset-entry-type");

				assetEntryTypeElement.addText(assetEntryType);

				Element assetEntryUuidElement = assetEntryElement.addElement(
					"asset-entry-uuid");

				assetEntryUuidElement.addText(assetEntryUuid);

				xml = document.formattedString(StringPool.BLANK);
			}
			catch (IOException ioe) {
				if (_log.isWarnEnabled()) {
					_log.warn(ioe);
				}
			}

			return xml;
		}
}
