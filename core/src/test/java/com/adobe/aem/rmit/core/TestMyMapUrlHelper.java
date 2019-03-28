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
public class TestMyMapUrlHelper extends BaseAbstractTest {

	/** The map url helper. */
	@InjectMocks
	MyMapUrlHelper mapUrlHelper;

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
	public void testGetMyMapURL() throws Exception {
		Assert.assertNotNull(MyMapUrlHelper.getMyMapURL(getRequest(), ""));
	}

	/**
	 * Test append html.
	 */
	@Test
	public void testAppendMyHtml() {
		MyMapUrlHelper.appendMyHtml("test.html");
	}

	/**
	 * Test append html null.
	 */
	@Test
	public void testAppendMyHtmlNull() {
		MyMapUrlHelper.appendMyHtml(null);
	}

	/**
	 * Test append html back slash.
	 */
	@Test
	public void testAppendMyHtmlBackSlash() {
		MyMapUrlHelper.appendMyHtml("test/");
	}

	/**
	 * Test append html default.
	 */
	@Test
	public void testAppendMyHtmlDefault() {
		MyMapUrlHelper.appendMyHtml("test");
	}

}
