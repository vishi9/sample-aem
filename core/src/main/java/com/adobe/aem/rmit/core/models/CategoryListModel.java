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
 * values from Category List Component.
 * 
 * @author Soorya Sreedharan
 * @since 26-09-2018
 */
public class CategoryListModel {
	private static final Logger LOG = LoggerFactory.getLogger(IconListSlingModel.class);
	private List<CategoryList> categoryList = new ArrayList<>();
	private List<String> multifieldList;

	@Inject
	@Optional
	private String[] categoryListValue;

	@Inject
	@Optional
	private String categoryheading;

	@Inject
	@Optional
	@Default(values = "false")
	@Named("openOnMobile")
	private Boolean isOpenOnMobile;

	/**
	 * Inside init() ,adding the logic to add the multifield property values in
	 * to list object .
	 * 
	 **/
	
	@PostConstruct
	protected void init() {
		if (ArrayUtils.isNotEmpty(categoryListValue)) {
			multifieldList = Arrays.asList(categoryListValue);
			multifieldList.forEach(e -> categoryList.add(new Gson().fromJson(e, CategoryList.class)));
		}
	}

	/**
	 * return the categoryList object.
	 **/
	public List<CategoryList> getCategoryList() {
		return categoryList;
	}

	/**
	 * return the multifieldList object.
	 **/
	public List<String> getMultifieldList() {
		return multifieldList;
	}



	/**
	 * return the categoryheading object.
	 **/
	public String getCategoryheading() {
		return categoryheading;

	}

	/**
	 * return the isOpenOnMobile object.
	 **/
	public Boolean getIsOpenOnMobile() {
		return isOpenOnMobile;
	}
}
