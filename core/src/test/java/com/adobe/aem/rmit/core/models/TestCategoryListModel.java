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
 * The Class TestCategoryListModel.
 */
public class TestCategoryListModel extends BaseAbstractTest {

	/** The category list model. */
	@InjectMocks
	private CategoryListModel categoryListModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get category list null.
	 */
	@Test
	public void testGetCategoryListNull() {
		List<CategoryList> categoryList = categoryListModel.getCategoryList();
		Assert.assertNotNull(categoryList);
		Assert.assertTrue(categoryList.isEmpty());
	}

	/**
	 * Test get category list not null.
	 */
	@Test
	public void testGetCategoryListNotNull() {
		String[] categoryListValue = { "{\"categoryLinkLabel\": \"abc\",\"categoryLinkUrl\": \"url1\"}",
				"{\"categoryLinkLabel\": \"abc2\",\"categoryLinkUrl\": \"url2\"}" };
		try {
			Field canonicalUrlField = categoryListModel.getClass().getDeclaredField("categoryListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(categoryListModel, categoryListValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in testGetCategoryListNotNull()" + e.getMessage());
		}

		categoryListModel.init();
		List<CategoryList> categoryList = categoryListModel.getCategoryList();
		Assert.assertNotNull(categoryList);
		Assert.assertTrue(categoryList.size() == 2);

	}

	/**
	 * Test get categoryheading.
	 */
	@Test
	public void testGetCategoryheading() {
		String categoryheading = "categoryheading";
		try {
			Field canonicalUrlField = categoryListModel.getClass().getDeclaredField("categoryheading");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(categoryListModel, categoryheading);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in testGetCategoryheading()" + e.getMessage());
		}
		String categoryheadingActual = categoryListModel.getCategoryheading();
		Assert.assertTrue(categoryheading.equals(categoryheadingActual));
	}

	/**
	 * Test get categoryheading null.
	 */
	@Test
	public void testGetCategoryheadingNull() {
		String categoryheading = categoryListModel.getCategoryheading();
		Assert.assertNull(categoryheading);
	}

	/**
	 * Test get is open on mobile.
	 */
	@Test
	public void testGetIsOpenOnMobile() {
		Boolean isOpenOnMobile = Boolean.TRUE;
		try {
			Field canonicalUrlField = categoryListModel.getClass().getDeclaredField("isOpenOnMobile");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(categoryListModel, isOpenOnMobile);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in testGetIsOpenOnMobile()" + e.getMessage());
		}
		Boolean isOpenOnMobileActual = categoryListModel.getIsOpenOnMobile();
		Assert.assertTrue(isOpenOnMobileActual.equals(isOpenOnMobileActual));

	}

	/**
	 * Test get is open on mobile null.
	 */
	@Test
	public void testGetIsOpenOnMobileNull() {
		Boolean isOpenOnMobile = categoryListModel.getIsOpenOnMobile();
		Assert.assertNull(isOpenOnMobile);

	}

	/**
	 * Test get multifield list.
	 */
	@Test
	public void testGetMultifieldList() {
		String[] categoryListValue = { "{\"categoryLinkLabel\": \"abc\",\"categoryLinkUrl\": \"url1\"}",
				"{\"categoryLinkLabel\": \"abc2\",\"categoryLinkUrl\": \"url2\"}" };
		try {
			Field canonicalUrlField = categoryListModel.getClass().getDeclaredField("categoryListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(categoryListModel, categoryListValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in testGetIsOpenOnMobile()" + e.getMessage());
		}

		categoryListModel.init();
		List<String> multifieldList = categoryListModel.getMultifieldList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Test get multifield list null.
	 */
	@Test
	public void testGetMultifieldListNull() {
		List<String> multifieldList = categoryListModel.getMultifieldList();
		Assert.assertNull(multifieldList);
	}
}
