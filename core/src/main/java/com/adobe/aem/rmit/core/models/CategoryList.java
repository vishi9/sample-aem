package com.adobe.aem.rmit.core.models;
/**
 * This pojo Created by Amulya on 03/10/2018.
 */
public class CategoryList {

	private String categoryLinkLabel;
	private String categoryLinkUrl;
	CategoryList(String categoryLinkLabel, String categoryLinkUrl) {

		this.categoryLinkLabel = categoryLinkLabel;
		this.categoryLinkUrl = categoryLinkUrl;
	}
	public String getCategoryLinkLabel() {
		return categoryLinkLabel;
	}

	public String getCategoryLinkUrl() {
		return categoryLinkUrl;
	}
}