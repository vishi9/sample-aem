package com.adobe.aem.rmit.core;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;

/**
 * The Class TestRMITUtil.
 */
public class TestRMITUtil extends BaseAbstractTest {

	/** The rmit util. */
	@InjectMocks
	RMITUtil rmitUtil;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get url not null.
	 */
	@Test
	public void testGetUrlNotNull() {
		Assert.assertNotNull(rmitUtil.getUrl("test"));
		Assert.assertEquals("test", rmitUtil.getUrl("test"));
	}

	/**
	 * Test get url null.
	 */
	@Test
	public void testGetUrlNull() {
		Assert.assertNotNull(rmitUtil.getUrl(null));
		Assert.assertEquals(StringUtils.EMPTY, rmitUtil.getUrl(null));
	}

	/**
	 * Test get url http.
	 */
	@Test
	public void testGetUrlHttp() {
		Assert.assertNotNull(rmitUtil.getUrl("http://test"));
		Assert.assertEquals("http://test", rmitUtil.getUrl("http://test"));
	}

	/**
	 * Test get url content.
	 */
	@Test
	public void testGetUrlContent() {
		Assert.assertNotNull(rmitUtil.getUrl("/content/rmit"));
		Assert.assertEquals("/content/rmit.html", rmitUtil.getUrl("/content/rmit"));
	}

	/**
	 * Test get url run mode not null.
	 */
	@Test
	public void testGetUrlRunModeNotNull() {
		Assert.assertNotNull(rmitUtil.getUrl("test", "author"));
		Assert.assertEquals("test", rmitUtil.getUrl("test", "author"));
	}

	/**
	 * Test get url run mode null.
	 */
	@Test
	public void testGetUrlRunModeNull() {
		Assert.assertNotNull(rmitUtil.getUrl(null, "author"));
		Assert.assertEquals(StringUtils.EMPTY, rmitUtil.getUrl(null, "author"));
	}

	/**
	 * Test get url run mode http.
	 */
	@Test
	public void testGetUrlRunModeHttp() {
		Assert.assertNotNull(rmitUtil.getUrl("http://test", "author"));
		Assert.assertEquals("http://test", rmitUtil.getUrl("http://test", "author"));
	}

	/**
	 * Test get url run mode content.
	 */
	@Test
	public void testGetUrlRunModeContent() {
		Assert.assertNotNull(rmitUtil.getUrl("/content/rmit", "author"));
		Assert.assertEquals("/content/rmit.html", rmitUtil.getUrl("/content/rmit", "author"));
	}

	/**
	 * Test get url run mode not null publish.
	 */
	@Test
	public void testGetUrlRunModeNotNullPublish() {
		Assert.assertNotNull(rmitUtil.getUrl("test", "publish"));
		Assert.assertEquals("test", rmitUtil.getUrl("test", "publish"));
	}

	/**
	 * Test get url run mode null publish.
	 */
	@Test
	public void testGetUrlRunModeNullPublish() {
		Assert.assertNotNull(rmitUtil.getUrl(null, "publish"));
		Assert.assertEquals(StringUtils.EMPTY, rmitUtil.getUrl(null, "publish"));
	}

	/**
	 * Test get url run mode http publish.
	 */
	@Test
	public void testGetUrlRunModeHttpPublish() {
		Assert.assertNotNull(rmitUtil.getUrl("http://test", "publish"));
		Assert.assertEquals("http://test", rmitUtil.getUrl("http://test", "publish"));
	}

	/**
	 * Test get url run mode content publish.
	 */
	@Test
	public void testGetUrlRunModeContentPublish() {
		Assert.assertNotNull(rmitUtil.getUrl("/content/rmit.html", "publish"));
		Assert.assertEquals("/content/rmit", rmitUtil.getUrl("/content/rmit.html", "publish"));
	}

}
