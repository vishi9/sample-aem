package com.adobe.aem.rmit.core.models;

/**
 * This pojo Created by Soorya on 24/09/2018.
 */
public class IconList {

	private String icon;
	private String linkLabel;
	private String linkUrl;

	IconList(String icon, String linkLabel, String linkUrl) {

		this.icon = icon;
		this.linkLabel = linkLabel;
		this.linkUrl = linkUrl;
	}

	public String getIcon() {
		return icon;
	}

	public String getLinkLabel() {
		return linkLabel;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

}