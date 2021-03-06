package com.adobe.aem.rmit.core;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

import com.adobe.granite.asset.api.Asset;

public class DamUtils2 {
	private DamUtils2() {
		
	}

	public static String getAltText(String imagePath, ResourceResolver resourceResolver) {
		String imageAltText = null;
		if (StringUtils.isNotBlank(imagePath)) {
			
			Resource imageResource = resourceResolver.getResource(imagePath + "/jcr:content/metadata");
			if (imageResource != null) {
				ValueMap imageMap = imageResource.adaptTo(ValueMap.class);
				if (imageMap != null) {
					imageAltText = imageMap.get("dc:alttag", String.class) != null
							? imageMap.get("dc:alttag", String.class)
							: "";
					if (StringUtils.isBlank(imageAltText)) {
						getImageTitle(imagePath, resourceResolver);
					}
				}
			}
		}
		return imageAltText;
	}
	
	public static String getImageTitle(String imagePath, ResourceResolver resourceResolver) {
		String imageName = null;
		Resource jcrResource = resourceResolver.getResource(imagePath);
		if (jcrResource != null) {
			Asset asset = jcrResource.adaptTo(Asset.class);
			if (asset != null)
				imageName = asset.getName();
		}
		return imageName;
	}
}
