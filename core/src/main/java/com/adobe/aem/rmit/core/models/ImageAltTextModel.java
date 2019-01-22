package com.adobe.aem.rmit.core.models;

import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;

import com.adobe.aem.rmit.core.DamUtils;


@Model(adaptables = SlingHttpServletRequest.class)
public class ImageAltTextModel {

	@Inject
	private ResourceResolver resourceResolver;
	
	@Inject
	@Optional
	private String imagePath;

	public String getAltText() {
		
		return DamUtils.getAltText(imagePath, resourceResolver);
	}
}
