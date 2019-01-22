package com.adobe.aem.rmit.core.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.adobe.aem.rmit.core.components.NewsArticleTags;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class)
/**
 * The NewsTags class implements an application that return the tag list object
 * of News Article template page in which the News date component has been added
 * 
 * @author Soorya Sreedharan
 * @since 11-09-2018
 */
public class NewsTags {

	private static final Logger LOG = LoggerFactory.getLogger(NewsTags.class);
	private Date newsTemplateDate = new Date();
	private String newsDate;
	private String dateFormat = "dd MMMM YYYY";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);
	private String pagePath = null;
	ResourceResolver resolver = null;
	PageManager pageManager = null;

	/** Injecting the service **/

	@Inject
	NewsArticleTags newsArticleTags;
	@Inject
	private Page currentPage;

	@SlingObject
	private SlingHttpServletRequest request;

	/**
	 * In this method ,calling the doInit() from service "NewsArticleTags" by
	 * passing parameter as currentPage. Getting the News date property value from
	 * the current page in which News date component.
	 * 
	 * @param currentPage.
	 * @return Nothing.
	 * @exception ParseException.
	 */
	@PostConstruct
	public void init() throws ParseException {
		try {
			if (!currentPage.getPath().startsWith("/content")) {
				ResourceResolver resourceResolver = request.getResourceResolver();
				PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
				pagePath = currentPage.getTemplate().getPath() + "/initial";
				currentPage = pageManager.getPage(pagePath);
			}
			newsArticleTags.doInit(currentPage);
			if (currentPage.getPath().startsWith("/content")) {
				ValueMap pageProps = currentPage.getProperties();
				newsTemplateDate = pageProps.get("newsDate", Date.class);
				newsDate = simpleDateFormat.format(newsTemplateDate);
			} else
				newsDate = "";
		} catch (Exception e) {
			LOG.error("Error in NewsTags:", e);
		}
	}

	/**
	 * This method will call the categoryList from newsArticleTags service and
	 * return the list
	 * 
	 * @param args
	 *            unused.
	 * @return categoryList.
	 */
	public List<RmitTags> getCategoryList() {
		return newsArticleTags.getCategoryList();
	}

	/**
	 * This method will call the TopicList from newsArticleTags service and return
	 * the list
	 * 
	 * @param args
	 *            unused.
	 * @return topicList.
	 */

	public List<String> getTopicList() {
		return newsArticleTags.getTopicList();
	}

	/**
	 * This method will call the TypeList from newsArticleTags service and return
	 * the list
	 * 
	 * @param args
	 *            unused.
	 * @return typeList.
	 */
	public List<String> getTypeList() {
		return newsArticleTags.getTypeList();
	}

	/**
	 * This method will call the pageList from newsArticleTags service and return
	 * the list
	 * 
	 * @param args
	 *            unused.
	 * @return pageList.
	 */

	public List<String> getPageList() {
		return newsArticleTags.getPageList();
	}

	/**
	 * This method will get the news date property value from the current page and
	 * return
	 * 
	 * @param args
	 *            unused.
	 * @return newsdate.
	 */
	public String getNewsDate() {
		return newsDate;
	}

	/**
	 * This method will call the tagList from newsArticleTags service and return the
	 * list
	 * 
	 * @param args
	 *            unused.
	 * @return pageList.
	 */
	public List<String> getTagList() {
		return newsArticleTags.getTagList();
	}

}
