package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model(adaptables = Resource.class)
/**
 * slingModel for returning the multifield property values and other property
 * values from Top Nav Component.
 * 
 * @author Soorya Sreedharan
 * @since 15-10-2018
 */
public class TopNavModel {
	private static final Logger LOG = LoggerFactory.getLogger(TopNavModel.class);
	private List<TabValueList> topNavTabList = new ArrayList<>();
	private List<String> multifieldTabList = new ArrayList<>();

	@Inject
	@Optional
	String[] tabList;

	@Inject
	@Optional
	String assetPicker;

	@Inject
	@Optional
	String assetLink;

	@Inject
	@Optional
	String tabHighlight;

	@Inject
	@Optional
	String searchHeading;

	@Inject
	@Optional
	String searchLink;

	@Inject
	@Optional
	@Default(values = "false")
	@Named("fullwidth")

	/**
	 * Inside init() ,adding the logic to add the multifield property values in
	 * to list object .
	 **/
	@PostConstruct
	protected void init() {
		if (ArrayUtils.isNotEmpty(tabList)) {
			multifieldTabList = Arrays.asList(tabList);
			LOG.info("multifield ***: "+multifieldTabList);
			multifieldTabList.forEach(tabValue -> topNavTabList.add(new Gson().fromJson(tabValue, TabValueList.class)));
		}
	}
	/**
	 * return the topNavTabList object.
	 **/
	public List<TabValueList> getTopNavTabList() {
		return topNavTabList;
	}
	/**
	 * return the assetLink object.
	 **/
	public String getAssetLink() {
		return assetLink;
	}
	/**
	 * return the heading object.
	 **/
	public String getAssetPicker() {
		return assetPicker;

	}
	/**
	 * return the tabHighlight object.
	 **/
	public String getTabHighlight() {
		return tabHighlight;
	}
	/**
	 * return the searchHeading object.
	 **/
	public String getSearchHeading() {
		return searchHeading;
	}
	/**
	 * return the searchLink object.
	 **/
	public String getSearchLink() {
		return searchLink;
	}
	/**
	 * return the multifieldTabList object.
	 **/
	public List<String> getMultifieldTabList() {
		return multifieldTabList;
	}
}
