package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.Gson;
import javax.jcr.Node;
import javax.jcr.Value;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import com.adobe.aem.rmit.core.components.RmitTags;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.api.resource.Resource;

/**
 * This model class for RMIT Tags and custome properties
 * 
 * @author Shardendu
 *
 */

@Model(adaptables = Resource.class)
public class RmitTagsModel {
	private static final Logger LOG = LoggerFactory.getLogger(RmitTagsModel.class);
	private List<RmitTags> tagswithlinkList = new ArrayList<>();
	private List<String> multifieldList;
	private String tagsLinkValue;

	@Inject
	@Named("tagslinklistValue")
	@Optional
	private String[] tagslinklistValue;

	/**
	 * Inside init() ,adding the logic to add the multifield property values in to
	 * list object .
	 * 
	 **/
	@PostConstruct
	protected void init() {
		try {
			if (ArrayUtils.isNotEmpty(tagslinklistValue)) {
				multifieldList = Arrays.asList(tagslinklistValue);
				multifieldList.forEach(e -> tagswithlinkList.add(new Gson().fromJson(e, RmitTags.class)));
			}
			LOG.error("**multifieldList** ::" + multifieldList);
		} catch (Exception e) {
			LOG.error("*Error in RmitTagsModel Init*", e);
		}
	}
	
	/**
	 * return the tagswithlinkList object.
	 **/
	public List<RmitTags> getTagsWithUrl() {
		return tagswithlinkList;
	}

	/**
	 * return the multifieldList object.
	 **/
	public List<String> getMultifieldList() {
		return multifieldList;
	}

}