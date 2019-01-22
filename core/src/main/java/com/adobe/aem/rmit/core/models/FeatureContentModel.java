package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.rmit.core.DamUtils;
import com.google.gson.Gson;

@Model(adaptables = Resource.class)
/**
 * slingModel for returning the multifield property values from Feature Content
 * Component
 * 
 * @author Amulya Bhavana Ommi
 * @since 02-11-2018
 */
public class FeatureContentModel {

	private static final Logger LOG = LoggerFactory.getLogger(FeatureContentModel.class);

	private List<FeatureContent> cardList = new ArrayList<>();

	private List<String> multifieldList;

	@Inject
	@Optional
	private String[] cardListValue;

	@Inject
	private ResourceResolver resourceResolver;

	/**
	 * Inside init() ,adding the logic to add the multifield property values in
	 * to list object .
	 * 
	 **/

	@PostConstruct
	protected void init() {
		if (ArrayUtils.isNotEmpty(cardListValue)) {
			LOG.info("Inside FeatureContentModel.java");
			multifieldList = Arrays.asList(cardListValue);
			multifieldList.forEach(fcontent -> cardList.add(new Gson().fromJson(fcontent, FeatureContent.class)));
			cardList.forEach(featureContent -> {
				String imagePath = featureContent.getImage();
				if (StringUtils.isBlank(featureContent.getImageAltText())) {
					featureContent.setImageAltText(DamUtils.getAltText(imagePath, resourceResolver));
				}

			});
		}
	}

	/**
	 * return the cardList object.
	 **/
	public List<FeatureContent> getCardList() {
		return cardList;
	}

	/**
	 * return the multifieldList object.
	 **/
	public List<String> getMultifieldList() {
		return multifieldList;
	}


}
