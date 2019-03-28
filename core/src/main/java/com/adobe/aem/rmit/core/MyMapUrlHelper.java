package com.adobe.aem.rmit.core;

import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.settings.SlingSettingsService;

import com.adobe.cq.sightly.WCMUsePojo;

public class MyMapUrlHelper extends WCMUsePojo {

	private String mappedUrl = null;
	private static final String HTML_EXTENSION = ".html";
	private static final String FORWARD_SLASH = "/";
	private static final String AUTHOR = "author";
	String runMode = "publish";
	RMITUtil rmitUtil = new RMITUtil();

	@Override
	public void activate() throws Exception {

		String url = get("url", String.class);
		SlingSettingsService slingSettingsService = getSlingScriptHelper().getService(SlingSettingsService.class);
		if (slingSettingsService != null) {
			Set<String> runModes = slingSettingsService.getRunModes();
			if (runModes.contains(AUTHOR))
				runMode = AUTHOR;
			String targetUrl = rmitUtil.getUrl(url, runMode);
			mappedUrl = getMyMapURL(getRequest(), targetUrl);
			if (StringUtils.equalsAnyIgnoreCase(mappedUrl, FORWARD_SLASH)) {
				mappedUrl = StringUtils.EMPTY;
			}

		}

	}

	public static String getMyMapURL(SlingHttpServletRequest slingRequest, String url) {

		String mappedURL = url;
		if (slingRequest != null && url != null) {
			org.apache.sling.api.resource.ResourceResolver resolver = slingRequest.getResourceResolver();
			mappedURL = resolver.map(slingRequest, url);
		}
		return mappedURL;
	}

	public static String appendMyHtml(String mappedUrl) {
		if (mappedUrl == null)
			return null;
		if (mappedUrl.endsWith(HTML_EXTENSION))
			return mappedUrl;
		else if (mappedUrl.endsWith(FORWARD_SLASH))
			return mappedUrl.substring(0, mappedUrl.length() - 1) + HTML_EXTENSION;
		else
			return mappedUrl + HTML_EXTENSION;
	}

	/**
	 * @return the mappedUrl
	 */
	public String getMyMappedUrl() {
		return mappedUrl;
	}

}