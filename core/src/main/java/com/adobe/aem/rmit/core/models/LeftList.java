package com.adobe.aem.rmit.core.models;

public class LeftList {
	private String leftListLinkLabel;
	private String leftListLinkUrl;
	LeftList(String leftListLinkLabel, String leftListLinkUrl) {
	
		this.leftListLinkLabel = leftListLinkLabel;
		this.leftListLinkUrl = leftListLinkUrl;
	}
	public String getLeftListLinkLabel() {
		return leftListLinkLabel;
	}
	public void setLeftListLinkLabel(String leftListLinkLabel) {
		this.leftListLinkLabel = leftListLinkLabel;
	}
	public String getLeftListLinkUrl() {
		return leftListLinkUrl;
	}
	public void setLeftListLinkUrl(String leftListLinkUrl) {
		this.leftListLinkUrl = leftListLinkUrl;
	}
	
}
