package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model(adaptables = Resource.class)
/**
 * slingModel for returning the multifield property values and other property
 * values from Primary Navigation Component.
 * 
 * @author cvalluru
 * @since 24-10-2018
 */
public class PrimaryNavModel {

	private static final Logger LOG = LoggerFactory.getLogger(PrimaryNavModel.class);
	private List<PrimaryNavMenu> primaryNavMenu = new ArrayList<>();
	private List<String> primaryNavList;

	@Inject
	@Optional
	private String[] primaryNavLinks;

	@Inject
	@Optional
	private String promoReference;

	@PostConstruct
	protected void init() {
		LOG.info("init method of PrimaryNavModel class");
		if (ArrayUtils.isNotEmpty(primaryNavLinks)) {
			primaryNavList = Arrays.asList(primaryNavLinks);
			primaryNavList.forEach(
					primaryHeading -> primaryNavMenu.add(new Gson().fromJson(primaryHeading, PrimaryNavMenu.class)));
		}
	}

	/**
	 * return the promoReference object.
	 **/
	public String getPromoReference() {
		return promoReference;

	}

	/**
	 * @return the primaryNavList
	 */
	public List<String> getPrimaryNavList() {
		return primaryNavList;
	}

	/**
	 * @return the primaryNavMenu
	 */
	public List<PrimaryNavMenu> getPrimaryNavMenu() {
		return primaryNavMenu;
	}
}
