package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.apache.sling.api.resource.Resource;
import javax.annotation.PostConstruct;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;

@Model(adaptables = Resource.class)
/**
 * slingModel for returning the multifield property values and other property
 * values from IconList Component.
 * 
 * @author Soorya Sreedharan
 * @since 24-09-2018
 */
public class IconListSlingModel {
	private static final Logger LOG = LoggerFactory.getLogger(IconListSlingModel.class);
	private List<IconList> iconList = new ArrayList<>();
	private List<String> multifieldList;

	@Inject
	@Named("iconListValue")
	@Optional
	private String[] iconListValue;

	@Inject
	@Optional
	private String layout;

	@Inject
	@Optional
	private String heading;

	@Inject
	@Optional
	@Default(values = "false")
	@Named("fullwidth")
	private Boolean isFullwidth;

	/**
	 * Inside init() ,adding the logic to add the multifield property values in
	 * to list object .
	 * 
	 **/
	@PostConstruct
	protected void init() {
		if (ArrayUtils.isNotEmpty(iconListValue)) {
			multifieldList = Arrays.asList(iconListValue);
			multifieldList.forEach(e -> iconList.add(new Gson().fromJson(e, IconList.class)));
		}
	}

	/**
	 * return the iconList object.
	 **/
	public List<IconList> getIconList() {
		return iconList;
	}

	/**
	 * return the multifieldList object.
	 **/
	public List<String> getMultifieldList() {
		return multifieldList;
	}

	/**
	 * return the layout object.
	 **/
	public String getLayout() {
		return layout;
	}

	/**
	 * return the heading object.
	 **/
	public String getHeading() {
		return heading;

	}

	/**
	 * return the isFullwidth object.
	 **/
	public Boolean getIsFullwidth() {
		return isFullwidth;
	}

}