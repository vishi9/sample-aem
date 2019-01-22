package com.adobe.aem.rmit.core.models;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.adobe.aem.rmit.core.components.NewsArticleTags;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.Template;

/**
 * The Class TestNewsTags.
 */
public class TestNewsTags extends BaseAbstractTest {

	/** The news tags. */
	@InjectMocks
	NewsTags newsTags;

	/** The mock page. */
	@Mock
	Page mockPage;

	/** The mock news article tags. */
	@Mock
	NewsArticleTags mockNewsArticleTags;

	/** The page props. */
	@Mock
	ValueMap pageProps;

	/** The date. */
	@Mock
	Date date;

	/** The request. */
	@Mock
	SlingHttpServletRequest request;

	/** The resource resolver. */
	@Mock
	ResourceResolver resourceResolver;

	/** The page manager. */
	@Mock
	PageManager pageManager;

	/** The template. */
	@Mock
	Template template;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		try {
			Mockito.when(mockPage.getPath()).thenReturn("content");
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(PageManager.class)).thenReturn(pageManager);
			Mockito.when(mockPage.getTemplate()).thenReturn(template);
			Mockito.when(template.getPath()).thenReturn("/content");
			Mockito.when(pageManager.getPage(Mockito.anyString())).thenReturn(mockPage);
			newsTags.init();
		} catch (ParseException e) {
			Assert.fail("Exception occurred in testInit()" + e.getMessage());
		}
	}

	/**
	 * Test category list.
	 */
	@Test
	public void testCategoryList() {
		List<RmitTags> categoryList = newsTags.getCategoryList();
		Assert.assertNotNull(categoryList);
		Assert.assertTrue(categoryList.isEmpty());
	}

	/**
	 * Test topic list.
	 */
	@Test
	public void testTopicList() {
		List<String> topicList = newsTags.getTopicList();
		Assert.assertNotNull(topicList);
		Assert.assertTrue(topicList.isEmpty());
	}

	/**
	 * Test type list.
	 */
	@Test
	public void testTypeList() {
		List<String> typeList = newsTags.getTypeList();
		Assert.assertNotNull(typeList);
		Assert.assertTrue(typeList.isEmpty());
	}

	/**
	 * Test page list.
	 */
	@Test
	public void testPageList() {
		List<String> pageList = newsTags.getPageList();
		Assert.assertNotNull(pageList);
		Assert.assertTrue(pageList.isEmpty());
	}

	/**
	 * Test news date.
	 */
	@Test
	public void testNewsDate() {
		String newsDate = newsTags.getNewsDate();
		Assert.assertNotNull(newsDate);
		Assert.assertTrue(StringUtils.isEmpty(newsDate));
	}

	/**
	 * Test tag list.
	 */
	@Test
	public void testTagList() {
		List<String> tagList = newsTags.getTagList();
		Assert.assertNotNull(tagList);
		Assert.assertTrue(tagList.isEmpty());
	}

}
