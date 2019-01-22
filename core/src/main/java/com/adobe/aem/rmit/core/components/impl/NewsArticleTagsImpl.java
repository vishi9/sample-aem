package com.adobe.aem.rmit.core.components.impl;

import java.util.ArrayList;
import java.util.List;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.adobe.aem.rmit.core.components.NewsArticleTags;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.adobe.aem.rmit.core.models.RmitTagsModel;
import javax.inject.Inject;
import org.osgi.service.component.annotations.Reference;
import com.adobe.aem.rmit.core.components.GetTagUrl;

@Component(service = NewsArticleTags.class, immediate = true)
@Designate(ocd = NewsArticleTagsConfiguration.class)

/**
 * Implemented the service interface NewsArticleTags.This service is getting the
 * NewsArticleConfiguaration values. Base on the values fetched from the
 * configuaration, segregating the News Article Template page's Tags in to
 * Different Tags group list.
 * 
 * @author Soorya Sreedharan
 * @since 13-09-2018
 */

public class NewsArticleTagsImpl implements NewsArticleTags {

	private NewsArticleTagsConfiguration config;

	private static final Logger LOG = LoggerFactory.getLogger(NewsArticleTagsImpl.class);

	@Activate
	public void activate(NewsArticleTagsConfiguration configuration) {
		config = configuration;
	}

	@Reference
	GetTagUrl getTagUrl;

	@Inject
	RmitTagsModel rmitTagsModel;

	List<RmitTags> categoryList;
	List<String> typeList;
	List<String> pageList;
	List<String> topicList;
	List<String> tagList;

	/**
	 * This method which makes categoryList,TypeList,TopicList,pageList based on the
	 * Tag service configuration paths set in system console. Also getting the
	 * tagList which contains all the tags associated with the page in a List.
	 * 
	 * @param currentPage.
	 * @return Nothing.
	 */
	public void doInit(Page currentPage) {

		categoryList = new ArrayList<RmitTags>();
		typeList = new ArrayList<String>();
		pageList = new ArrayList<String>();
		topicList = new ArrayList<String>();
		tagList = new ArrayList<String>();

		RmitTags rmitTags = null;
		Tag[] tags = currentPage.getTags();
		for (int i = 0; i < tags.length; i++) {

			if (tags[i].getPath().contains(config.getTopic())) {
				topicList.add(tags[i].getTitle());
			} else if (tags[i].getPath().contains(config.getCategory())) {
				// LOG.error("*getTagUrlByTags* :" + getTagUrl.getTagUrlByTags(tags[i].getTagID()));
				rmitTags = new RmitTags(tags[i].getTitle(), getTagUrl.getTagUrlByTags(tags[i].getTagID()));
				categoryList.add(rmitTags);
			} else if (tags[i].getPath().contains(config.getPage())) {
				pageList.add(tags[i].getTitle());
			} else if (tags[i].getPath().contains(config.getType())) {
				typeList.add(tags[i].getTitle());
			}
			tagList.add(tags[i].getTitle());
		}

	}

	/**
	 * This method which makes TopicList Tags based on the Tag service configuration
	 * paths set in system console.
	 * 
	 * @param args
	 *            unused.
	 * @return topicList.
	 */
	@Override
	public List<String> getTopicList() {
		return topicList;
	}

	/**
	 * This method which makes categoryList Tags based on the Tag service
	 * configuration paths set in system console.
	 * 
	 * @param args
	 *            unused.
	 * @return categoryList.
	 */

	@Override
	public List<RmitTags> getCategoryList() {
		return categoryList;
	}

	/**
	 * This method which makes typeList Tags based on the Tag service configuration
	 * paths set in system console.
	 * 
	 * @param args
	 *            unused.
	 * @return typeList.
	 */
	@Override
	public List<String> getTypeList() {
		return typeList;
	}

	/**
	 * This method which makes pageList Tags based on the Tag service configuration
	 * paths set in system console.
	 * 
	 * @param args
	 *            unused.
	 * @return pageList.
	 */
	@Override
	public List<String> getPageList() {
		return pageList;
	}

	/**
	 * This method which makes a list of all tags associated with the News Article
	 * Template pages.
	 * 
	 * @param args
	 *            unused.
	 * @return tagList.
	 */
	@Override
	public List<String> getTagList() {
		return tagList;
	}

}