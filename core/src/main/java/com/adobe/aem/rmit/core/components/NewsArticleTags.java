package com.adobe.aem.rmit.core.components;

import java.util.List;

import com.day.cq.wcm.api.Page;
import com.adobe.aem.rmit.core.components.RmitTags;
/**
 * Interface for NewsArticleTagsImpl java class which can be used as a generic
 * service for getting particular group of Tags in a List object.
 * 
 * @author Soorya Sreedharan
 * @since 13-09-2018
 */
public interface NewsArticleTags {

	public void doInit(Page currentPage);

	List<String> getTopicList();

	List<RmitTags> getCategoryList();

	List<String> getTypeList();
	
	List<String> getPageList();
	
	List<String> getTagList();

}
