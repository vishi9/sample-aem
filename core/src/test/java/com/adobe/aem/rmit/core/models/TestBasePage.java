package com.adobe.aem.rmit.core.models;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.day.cq.wcm.api.Page;

/**
 * The Class TestBasePage.
 */
public class TestBasePage extends BaseAbstractTest {

	/** The base page. */
	@InjectMocks
	private BasePage basePage;

	/** The mock resource. */
	@Mock
	private Resource mockResource;

	/** The mock page. */
	@Mock
	private Page mockPage;

	/** The mock resource resolver. */
	@Mock
	private ResourceResolver mockResourceResolver;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get canonical url.
	 */
	@Test
	public void testGetCanonicalUrl() {
		String canonicalUrl = "testcanonicalUrl";
		setProperty("canonicalUrl", canonicalUrl);
		String actualCanonicalUrl = basePage.getCanonicalUrl();
		Assert.assertEquals(canonicalUrl, actualCanonicalUrl);
	}

	/**
	 * Test get canonical url null.
	 */
	@Test
	public void testGetCanonicalUrlNull() {
		String canonicalUrl = basePage.getCanonicalUrl();
		Assert.assertNull(canonicalUrl);
	}

	/**
	 * Test get hreflang tagging null.
	 */
	@Test
	public void testGetHreflangTaggingNull() {
		Assert.assertNull(basePage.getHreflangTagging());

	}

	/**
	 * Test get hreflang tagging.
	 */
	@Test
	public void testGetHreflangTagging() {

		String hreflangTagging = "hreflangTagging";
		setProperty("hreflangTagging", hreflangTagging);
		String actualHreflangTagging = basePage.getHreflangTagging();
		Assert.assertEquals(hreflangTagging, actualHreflangTagging);

	}

	/**
	 * Test get google title.
	 */
	@Test
	public void testGetGoogleTitle() {
		Assert.assertNull(basePage.getGoogleTitle());
	}

	/**
	 * Test get meta description.
	 */
	@Test
	public void testGetMetaDescription() {
		Assert.assertNull(basePage.getMetaDescription());
	}

	/**
	 * Test get component title.
	 */
	@Test
	public void testGetComponentTitle() {
		Assert.assertNull(basePage.getComponentTitle());
	}

	/**
	 * Test get short description.
	 */
	@Test
	public void testGetShortDescription() {
		Assert.assertNull(basePage.getShortDescription());
	}

	/**
	 * Test get google description.
	 */
	@Test
	public void testGetGoogleDescription() {
		Assert.assertNull(basePage.getGoogleDescription());
	}

	/**
	 * Test get google language.
	 */
	@Test
	public void testGetGoogleLanguage() {
		Assert.assertNull(basePage.getGoogleLanguage());
	}

	/**
	 * Test get google author.
	 */
	@Test
	public void testGetGoogleAuthor() {
		Assert.assertNotNull(basePage.getGoogleAuthor());
		Assert.assertEquals("RMIT University", basePage.getGoogleAuthor());
	}

	/**
	 * Test get google copyright.
	 */
	@Test
	public void testGetGoogleCopyright() {
		Assert.assertNotNull(basePage.getGoogleCopyright());
		Assert.assertEquals("RMIT University", basePage.getGoogleCopyright());
	}

	/**
	 * Test get facebook og title.
	 */
	@Test
	public void testGetFacebookOgTitle() {
		Assert.assertNull(basePage.getFacebookOgTitle());
	}

	/**
	 * Test get facebook og type.
	 */
	@Test
	public void testGetFacebookOgType() {
		Assert.assertNotNull(basePage.getFacebookOgType());
		Assert.assertEquals("article", basePage.getFacebookOgType());
	}

	/**
	 * Test get facebook og image.
	 */
	@Test
	public void testGetFacebookOgImage() {
		Assert.assertNull(basePage.getFacebookOgImage());
	}

	/**
	 * Test get facebook og url.
	 */
	@Test
	public void testGetFacebookOgUrl() {
		Assert.assertNull(basePage.getFacebookOgUrl());
	}

	/**
	 * Test get facebook og description.
	 */
	@Test
	public void testGetFacebookOgDescription() {
		Assert.assertNull(basePage.getFacebookOgDescription());
	}

	/**
	 * Test get twitter card.
	 */
	@Test
	public void testGetTwitterCard() {
		Assert.assertNotNull(basePage.getTwitterCard());
		Assert.assertEquals("summary", basePage.getTwitterCard());
	}

	/**
	 * Test get twitter title.
	 */
	@Test
	public void testGetTwitterTitle() {
		Assert.assertNull(basePage.getTwitterTitle());
	}

	/**
	 * Test get twitter description.
	 */
	@Test
	public void testGetTwitterDescription() {
		Assert.assertNull(basePage.getTwitterDescription());
	}

	/**
	 * Test get twitter image.
	 */
	@Test
	public void testGetTwitterImage() {
		Assert.assertNull(basePage.getTwitterImage());
	}

	/**
	 * Test get current page url.
	 */
	@Test
	public void testGetCurrentPageUrl() {
		Assert.assertNull(basePage.getCurrentPageUrl(mockResource));
	}

	/**
	 * Test get page colour.
	 */
	@Test
	public void testGetPageColour() {
		Assert.assertNull(basePage.getPageColour());
	}

	/**
	 * Test get background position.
	 */
	@Test
	public void testGetBackgroundPosition() {
		Assert.assertNotNull(basePage.getBackgroundPosition());
		Assert.assertEquals(StringUtils.EMPTY, basePage.getBackgroundPosition());
	}

	/**
	 * Test get turn off left nav.
	 */
	@Test
	public void testGetTurnOffLeftNav() {
		Assert.assertFalse(basePage.getTurnOffLeftNav());
	}

	/**
	 * Test get background image.
	 */
	@Test
	public void testGetBackgroundImage() {
		Assert.assertNotNull(basePage.getBackgroundImage());
		Assert.assertEquals(StringUtils.EMPTY, basePage.getBackgroundImage());
	}

	/**
	 * Test get email share.
	 */
	@Test
	public void testGetEmailShare() {
		Assert.assertNotNull(basePage.getEmailShare());
	}

	/**
	 * Sets the property.
	 *
	 * @param PropertyName
	 *            the property name
	 * @param PropertyValue
	 *            the property value
	 */
	private void setProperty(String PropertyName, String PropertyValue) {
		try {
			Field canonicalUrlField = basePage.getClass().getDeclaredField(PropertyName);
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(basePage, PropertyValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setProperty()" + e.getMessage());
		}
	}
}
