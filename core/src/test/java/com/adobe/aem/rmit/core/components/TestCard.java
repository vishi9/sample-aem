package com.adobe.aem.rmit.core.components;

import java.lang.reflect.Field;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.components.impl.CardImpl;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;


/**
 * The Class TestCard.
 */
public class TestCard {

	
	/** The card impl. */
	@InjectMocks
	private CardImpl cardImpl;

	
	/** The mock page manager. */
	@Mock
	PageManager mockPageManager;

	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);

	}

	
	/**
	 * Sets the mocks.
	 */
	private void setMocks() {
		String articlePath = "/content/rmit";
		try {
			Field canonicalUrlField = cardImpl.getClass().getDeclaredField("articlePath");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(cardImpl, articlePath);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setMocks()" + e.getMessage());
		}
		Page mockPage = Mockito.mock(Page.class);
		Mockito.when(mockPageManager.getPage(Mockito.anyString())).thenReturn(mockPage);
		Mockito.when(mockPage.getTitle()).thenReturn("Article Title");
		Mockito.when(mockPage.getDescription()).thenReturn("Article Description");
		Mockito.when(mockPage.getPath()).thenReturn("Article Path");
		cardImpl.init();
	}

	
	/**
	 * Test get title null.
	 */
	@Test
	public void testGetTitleNull() {
		String title = cardImpl.getTitle();
		Assert.assertNull(title);
	}

	
	/**
	 * Test get title article.
	 */
	@Test
	public void testGetTitleArticle() {
		setMocks();
		String title = cardImpl.getTitle();
		Assert.assertNotNull(title);
		Assert.assertEquals("Article Title", title);
	}

	
	/**
	 * Test get title override.
	 */
	@Test
	public void testGetTitleOverride() {
		String titleOverride = "Override Title";
		try {
			Field canonicalUrlField = cardImpl.getClass().getDeclaredField("titleOverride");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(cardImpl, titleOverride);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in testGetTitleOverride()" + e.getMessage());
		}
		String title = cardImpl.getTitle();
		Assert.assertNotNull(title);
		Assert.assertEquals("Override Title", title);
	}

	
	/**
	 * Test get description null.
	 */
	@Test
	public void testGetDescriptionNull() {
		String description = cardImpl.getDescription();
		Assert.assertNull(description);
	}

	
	/**
	 * Test get description article.
	 */
	@Test
	public void testGetDescriptionArticle() {
		setMocks();
		String description = cardImpl.getDescription();
		Assert.assertNotNull(description);
		Assert.assertEquals("Article Description", description);
	}

	
	/**
	 * Test get link path null.
	 */
	@Test
	public void testGetLinkPathNull() {
		String linkPath = cardImpl.getLinkPath();
		Assert.assertNull(linkPath);
	}

	
	/**
	 * Test get link path article.
	 */
	@Test
	public void testGetLinkPathArticle() {
		setMocks();
		String linkPath = cardImpl.getLinkPath();
		Assert.assertNotNull(linkPath);
		Assert.assertEquals("Article Path", linkPath);
	}

	
	/**
	 * Test get image src null.
	 */
	@Test
	public void testGetImageSrcNull() {
		setMocks();
		String imageSrc = cardImpl.getImageSrc();
		Assert.assertNull(imageSrc);
	}

	
	/**
	 * Test is empty article page not null.
	 */
	@Test
	public void testIsEmptyArticlePageNotNull() {
		setMocks();
		Assert.assertFalse(cardImpl.isEmpty());
	}

	
	/**
	 * Test is empty article page null.
	 */
	@Test
	public void testIsEmptyArticlePageNull() {
		Assert.assertTrue(cardImpl.isEmpty());
	}

}
