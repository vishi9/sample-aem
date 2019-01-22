/**
 * 
 */
package com.adobe.aem.rmit.core.components;

/**
 * This class is Grid List Model
 * 
 * @author cvalluru
 *
 */
public class GridList {

	private String title;
	private String image;
	private String shortDescription;
	private String categoryTag;
	private String categoryTagUrl;
	private String articleDate;
	private String path;
	private String imageAltText;

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image
	 *            the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the shortDescription
	 */
	public String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription
	 *            the shortDescription to set
	 */
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the categoryTag
	 */
	public String getCategoryTag() {
		return categoryTag;
	}

	/**
	 * @param categoryTag
	 *            the categoryTag to set
	 */
	public void setCategoryTag(String categoryTag) {
		this.categoryTag = categoryTag;
	}

	/**
	 * @return the categoryTagUrl
	 */
	public String getCategoryTagUrl() {
		return categoryTagUrl;
	}

	/**
	 * @param categoryTag
	 *            the categoryTag to set
	 */
	public void setCategoryTagUrl(String categoryTagUrl) {
		this.categoryTagUrl = categoryTagUrl;
	}

	/**
	 * @return the articleDate
	 */
	public String getArticleDate() {
		return articleDate;
	}

	/**
	 * @param articleDate
	 *            the articleDate to set
	 */
	public void setArticleDate(String articleDate) {
		this.articleDate = articleDate;
	}

	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * @return the imageAltText
	 */
	public String getImageAltText() {
		return imageAltText;
	}

	/**
	 * @param imageAltText the imageAltText to set
	 */
	public void setImageAltText(String imageAltText) {
		this.imageAltText = imageAltText;
	}
}
