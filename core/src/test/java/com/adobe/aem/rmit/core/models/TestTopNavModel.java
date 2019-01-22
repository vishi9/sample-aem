package com.adobe.aem.rmit.core.models;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;

/**
 * The Class TestTopNavModel.
 */
public class TestTopNavModel extends BaseAbstractTest {

	/** The top nav model. */
	@InjectMocks
	private TopNavModel topNavModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get top nav tab list null.
	 */
	@Test
	public void testGetTopNavTabListNull() {
		List<TabValueList> topNavTabList = topNavModel.getTopNavTabList();
		Assert.assertNotNull(topNavTabList);
		Assert.assertTrue(topNavTabList.isEmpty());
	}

	/**
	 * Test get top nav tab list not null.
	 */
	@Test
	public void testGetTopNavTabListNotNull() {
		initTabList();
		List<TabValueList> topNavTabList = topNavModel.getTopNavTabList();
		Assert.assertNotNull(topNavTabList);
		Assert.assertTrue(topNavTabList.size() == 2);

	}

	/**
	 * Test get multifield tab list null.
	 */
	@Test
	public void testGetMultifieldTabListNull() {
		List<String> multifieldList = topNavModel.getMultifieldTabList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.isEmpty());
	}

	/**
	 * Test get multifield tab list not null.
	 */
	@Test
	public void testGetMultifieldTabListNotNull() {
		initTabList();
		List<String> multifieldList = topNavModel.getMultifieldTabList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Test get asset link null.
	 */
	@Test
	public void testGetAssetLinkNull() {
		String assetLink = topNavModel.getAssetLink();
		Assert.assertNull(assetLink);
	}

	/**
	 * Test get asset link.
	 */
	@Test
	public void testGetAssetLink() {
		String assetLink = "testAssetLink";
		setProperty("assetLink", assetLink);
		String assetLinkActual = topNavModel.getAssetLink();
		Assert.assertEquals(assetLink, assetLinkActual);
	}

	/**
	 * Test get asset picker null.
	 */
	@Test
	public void testGetAssetPickerNull() {
		String assetPicker = topNavModel.getAssetPicker();
		Assert.assertNull(assetPicker);
	}

	/**
	 * Test asset picker link.
	 */
	@Test
	public void testAssetPickerLink() {
		String assetPicker = "testAssetPicker";
		setProperty("assetPicker", assetPicker);
		String assetPickerActual = topNavModel.getAssetPicker();
		Assert.assertEquals(assetPicker, assetPickerActual);
	}

	/**
	 * Test get tab highlight null.
	 */
	@Test
	public void testGetTabHighlightNull() {
		String tabHighlight = topNavModel.getTabHighlight();
		Assert.assertNull(tabHighlight);
	}

	/**
	 * Test tab highlight.
	 */
	@Test
	public void testTabHighlight() {
		String tabHighlight = "testTabHighlight";
		setProperty("tabHighlight", tabHighlight);
		String tabHighlightActual = topNavModel.getTabHighlight();
		Assert.assertEquals(tabHighlight, tabHighlightActual);
	}

	/**
	 * Test get search heading null.
	 */
	@Test
	public void testGetSearchHeadingNull() {
		String searchHeading = topNavModel.getSearchHeading();
		Assert.assertNull(searchHeading);
	}

	/**
	 * Test search heading.
	 */
	@Test
	public void testSearchHeading() {
		String searchHeading = "testSearchHeading";
		setProperty("searchHeading", searchHeading);
		String searchHeadingActual = topNavModel.getSearchHeading();
		Assert.assertEquals(searchHeading, searchHeadingActual);
	}

	/**
	 * Test get search link null.
	 */
	@Test
	public void testGetSearchLinkNull() {
		String tabHighlight = topNavModel.getSearchLink();
		Assert.assertNull(tabHighlight);
	}

	/**
	 * Test search link.
	 */
	@Test
	public void testSearchLink() {
		String searchLink = "testSearchLink";
		setProperty("searchLink", searchLink);
		String searchLinkActual = topNavModel.getSearchLink();
		Assert.assertEquals(searchLink, searchLinkActual);
	}

	/**
	 * Inits the tab list.
	 */
	private void initTabList() {
		String[] tabList = {
				"{\"tabHeading\": \"Tab Heading1\",\"tabUrl\": \"Tab URL 1\",\"tabHighlight\": \"Tab Highlight 1\",\"menuList\": [{\"menuItemLabel\":\"label\",\"menuItemLink\":\"url1\"},{\"menuItemLabel\":\"label\",\"menuItemLink\":\"url1\"}]}",
				"{\"tabHeading\": \"Tab Heading 2\",\"tabUrl\": \"Tab URL 2\",\"tabHighlight\": \"Tab Highlight 2\",\"menuList\": [{\"menuItemLabel\":\"label\",\"menuItemLink\":\"url1\"},{\"menuItemLabel\":\"label\",\"menuItemLink\":\"url1\"}]}", };

		setProperty("tabList", tabList);
		topNavModel.init();

	}

	/**
	 * Sets the property.
	 *
	 * @param PropertyName
	 *            the property name
	 * @param PropertyValue
	 *            the property value
	 */
	private void setProperty(String PropertyName, Object PropertyValue) {
		try {
			Field canonicalUrlField = topNavModel.getClass().getDeclaredField(PropertyName);
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(topNavModel, PropertyValue);

		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setProperty()" + e.getMessage());
		}
	}

}
