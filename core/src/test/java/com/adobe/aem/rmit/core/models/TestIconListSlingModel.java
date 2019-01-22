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
 * The Class TestIconListSlingModel.
 */
public class TestIconListSlingModel extends BaseAbstractTest {

	/** The icon list sling model. */
	@InjectMocks
	private IconListSlingModel iconListSlingModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] iconListValue = { "{\"icon\": \"abc\",\"linkLabel\": \"url1\",\"linkUrl\": \"url1\"}",
				"{\"icon\": \"abc2\",\"linkLabel\": \"url2\",\"linkUrl\": \"url1\"}" };
		try {
			Field canonicalUrlField = iconListSlingModel.getClass().getDeclaredField("iconListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(iconListSlingModel, iconListValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setUp()" + e.getMessage());
		}
	}

	/**
	 * Test get icon list null.
	 */
	@Test
	public void testGetIconListNull() {
		List<IconList> iconList = iconListSlingModel.getIconList();
		Assert.assertNotNull(iconList);
		Assert.assertTrue(iconList.isEmpty());
	}

	/**
	 * Test get icon list not null.
	 */
	@Test
	public void testGetIconListNotNull() {

		iconListSlingModel.init();
		List<IconList> iconList = iconListSlingModel.getIconList();
		Assert.assertNotNull(iconList);
		Assert.assertTrue(iconList.size() == 2);

	}

	/**
	 * Test get multifield list null.
	 */
	@Test
	public void testGetMultifieldListNull() {
		List<String> multifieldList = iconListSlingModel.getMultifieldList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield list not null.
	 */
	@Test
	public void testGetMultifieldListNotNull() {
		iconListSlingModel.init();
		List<String> multifieldList = iconListSlingModel.getMultifieldList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);
	}

	/**
	 * Test get layout.
	 */
	@Test
	public void testGetLayout() {
		Assert.assertNull(iconListSlingModel.getLayout());
	}

	/**
	 * Testget heading.
	 */
	@Test
	public void testgetHeading() {
		Assert.assertNull(iconListSlingModel.getHeading());
	}

	/**
	 * Testget is fullwidth.
	 */
	@Test
	public void testgetIsFullwidth() {
		Assert.assertNull(iconListSlingModel.getIsFullwidth());
	}

}
