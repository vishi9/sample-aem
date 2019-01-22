package com.adobe.aem.rmit.core.models;
/**
 * This pojo Created by Amulya on 11/10/2018.
 */
public class LegalList {
	private String fieldLabel;
	private String fieldUrl;
	LegalList(String fieldLabel, String fieldUrl) {
		this.fieldLabel = fieldLabel;
		this.fieldUrl = fieldUrl;
	}
	public String getFieldLabel() {
		return fieldLabel;
	}
	public void setFieldLabel(String fieldLabel) {
		this.fieldLabel = fieldLabel;
	}
	public String getFieldUrl() {
		return fieldUrl;
	}
	public void setFieldUrl(String fieldUrl) {
		this.fieldUrl = fieldUrl;
	}
}
