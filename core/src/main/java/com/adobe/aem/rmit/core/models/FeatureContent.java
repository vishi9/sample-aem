package com.adobe.aem.rmit.core.models;
/**
 * This pojo Created by Amulya on 02/11/2018.
 */
public class FeatureContent {
	private String link;
	private String heading;
	private String shortDescription;
	private String ctaLabel;
	private String image;
	private String imageAltText;
	
	FeatureContent(String link, String heading, String shortDescription, String ctaLabel, String image,
			String imageAltText) {
		this.link = link;
		this.heading = heading;
		this.shortDescription = shortDescription;
		this.ctaLabel = ctaLabel;
		this.image = image;
		this.imageAltText = imageAltText;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getHeading() {
		return heading;
	}
	public void setHeading(String heading) {
		this.heading = heading;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getCtaLabel() {
		return ctaLabel;
	}
	public void setCtaLabel(String ctaLabel) {
		this.ctaLabel = ctaLabel;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImageAltText() {
		return imageAltText;
	}
	public void setImageAltText(String imageAltText) {
		this.imageAltText = imageAltText;
	}
	
}
