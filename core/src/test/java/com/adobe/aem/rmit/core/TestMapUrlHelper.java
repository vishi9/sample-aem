package com.adobe.aem.rmit.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;

/**
 * The Class TestMapUrlHelper.
 */
public class TestMapUrlHelper extends BaseAbstractTest {

	/** The map url helper. */
	@InjectMocks
	MapUrlHelper mapUrlHelper;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get map URL.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Test
	public void testGetMapURL() throws Exception {
		Assert.assertNotNull(MapUrlHelper.getMapURL(getRequest(), ""));
	}

	/**
	 * Test append html.
	 */
	@Test
	public void testAppendHtml() {
		MapUrlHelper.appendHtml("test.html");
	}

	/**
	 * Test append html null.
	 */
	@Test
	public void testAppendHtmlNull() {
		MapUrlHelper.appendHtml(null);
	}

	/**
	 * Test append html back slash.
	 */
	@Test
	public void testAppendHtmlBackSlash() {
		MapUrlHelper.appendHtml("test/");
	}

	/**
	 * Test append html default.
	 */
	@Test
	public void testAppendHtmlDefault() {
		MapUrlHelper.appendHtml("test");
	}

}
