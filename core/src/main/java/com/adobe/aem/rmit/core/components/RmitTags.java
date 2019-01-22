
package com.adobe.aem.rmit.core.components;

/**
 * This class for RMIT Tags and custome properties
 * 
 * @author Shardendu
 *
 */
public class RmitTags {

	private String tags;
	private String tagsUrl;

	public RmitTags(String tags, String tagsUrl) {

		this.tags = tags;
		this.tagsUrl = tagsUrl;
	}

	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}

	/**
	 * @return the tagsUrl
	 */
	public String getTagsUrl() {
		return tagsUrl;
	}

	/**
	 * @param tags
	 *            the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}

	/**
	 * @param tagsUrl
	 *            the tagsUrl to set
	 */
	public void setTagsUrl(String tagsUrl) {
		this.tagsUrl = tagsUrl;
	}
}
