package com.adobe.aem.rmit.core.models;

public class SocialList {
	private String listTitle;
	private String logoLink;
	private String logoLabel;
	SocialList(String listTitle, String logoLink, String logoLabel) {
		this.listTitle = listTitle;
		this.logoLink = logoLink;
		this.logoLabel = logoLabel;
	}
	public String getListTitle() {
		return listTitle;
	}
	public void setListTitle(String listTitle) {
		this.listTitle = listTitle;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public void setLogoLink(String logoLink) {
		this.logoLink = logoLink;
	}
	public String getLogoLabel() {
		return logoLabel;
	}
	public void setLogoLabel(String logoLabel) {
		this.logoLabel = logoLabel;
	}
}
