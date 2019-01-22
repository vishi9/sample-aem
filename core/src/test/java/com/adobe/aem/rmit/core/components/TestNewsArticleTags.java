package com.adobe.aem.rmit.core.components;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.components.impl.NewsArticleTagsConfiguration;
import com.adobe.aem.rmit.core.components.impl.NewsArticleTagsImpl;
import com.day.cq.tagging.Tag;
import com.day.cq.wcm.api.Page;

/**
 * The Class TestNewsArticleTags.
 */
public class TestNewsArticleTags {

	/** The news article tags impl. */
	@InjectMocks
	private NewsArticleTagsImpl newsArticleTagsImpl;

	/** The mock page. */
	@Mock
	Page mockPage;

	/** The mock tag. */
	@Mock
	Tag mockTag;

	/** The news article tags configuration. */
	@Mock
	NewsArticleTagsConfiguration newsArticleTagsConfiguration;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		Tag[] tags = { mockTag, mockTag };
		Mockito.when(mockPage.getTags()).thenReturn(tags);

	}

	/**
	 * Test get title topics.
	 */
	@Test
	public void testGetTitleTopics() {
		Mockito.when(mockTag.getPath()).thenReturn("/content/cq:tags/rmit/content_type/news/news-topic");
		Mockito.when(newsArticleTagsConfiguration.getTopic())
				.thenReturn("/content/cq:tags/rmit/content_type/news/news-topic");
		newsArticleTagsImpl.doInit(mockPage);
		List<String> topicList = newsArticleTagsImpl.getTopicList();
		Assert.assertNotNull(topicList);
		Assert.assertTrue(topicList.size() == 2);
	}

	/**
	 * Test get tag list.
	 */
	@Test
	public void testGetTagList() {
		Mockito.when(mockTag.getPath()).thenReturn("/content/cq:tags/rmit/content_type/news/news-topic");
		Mockito.when(newsArticleTagsConfiguration.getTopic())
				.thenReturn("/content/cq:tags/rmit/content_type/news/news-topic");
		newsArticleTagsImpl.doInit(mockPage);
		List<String> tagList = newsArticleTagsImpl.getTagList();
		Assert.assertNotNull(tagList);
		Assert.assertTrue(tagList.size() == 2);
	}

	/**
	 * Test get title page.
	 */
	@Test
	public void testGetTitlePage() {
		Mockito.when(mockTag.getPath()).thenReturn("/content/cq:tags/rmit/topic");
		Mockito.when(newsArticleTagsConfiguration.getTopic()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getCategory()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getPage()).thenReturn("/content/cq:tags/rmit/topic");
		newsArticleTagsImpl.doInit(mockPage);
		List<String> pageList = newsArticleTagsImpl.getPageList();
		Assert.assertNotNull(pageList);
		Assert.assertTrue(pageList.size() == 2);
	}

	/**
	 * Test get title type.
	 */
	@Test
	public void testGetTitleType() {
		Mockito.when(mockTag.getPath()).thenReturn("/content/cq:tags/rmit/content_type/news/central_news");
		Mockito.when(newsArticleTagsConfiguration.getTopic()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getCategory()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getPage()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getType())
				.thenReturn("/content/cq:tags/rmit/content_type/news/central_news");
		newsArticleTagsImpl.doInit(mockPage);
		List<String> typeList = newsArticleTagsImpl.getTypeList();
		Assert.assertNotNull(typeList);
		Assert.assertTrue(typeList.size() == 2);
	}

	/**
	 * Test get category list.
	 */
	@Test
	public void testGetCategoryList() {
		Mockito.when(mockTag.getPath()).thenReturn("/content/cq:tags/rmit/life-and-work/category");
		Mockito.when(mockTag.getTitle()).thenReturn("TagTitle");
		Mockito.when(mockTag.getTagID()).thenReturn("/content/cq:tags/rmit/topic");
		Mockito.when(newsArticleTagsConfiguration.getTopic()).thenReturn("not to match");
		Mockito.when(newsArticleTagsConfiguration.getCategory())
				.thenReturn("/content/cq:tags/rmit/life-and-work/category");
		List<RmitTags> categoryList = newsArticleTagsImpl.getCategoryList();
		Assert.assertNull(categoryList);
	}
}
