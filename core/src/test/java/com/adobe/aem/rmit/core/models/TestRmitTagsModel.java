package com.adobe.aem.rmit.core.models;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.adobe.aem.rmit.core.components.RmitTags;

/**
 * The Class TestRmitTagsModel.
 */
public class TestRmitTagsModel extends BaseAbstractTest {

	/** The rmit tags model. */
	@InjectMocks
	private RmitTagsModel rmitTagsModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test get tags with url null.
	 */
	@Test
	public void testGetTagsWithUrlNull() {
		List<RmitTags> tagsWithUrl = rmitTagsModel.getTagsWithUrl();
		Assert.assertNotNull(tagsWithUrl);
		Assert.assertTrue(tagsWithUrl.isEmpty());
	}

	/**
	 * Test get primary nav list not null.
	 */
	@Test
	public void testGetPrimaryNavListNotNull() {
		initTagslinklistValue();
		List<RmitTags> tagsWithUrl = rmitTagsModel.getTagsWithUrl();
		Assert.assertNotNull(tagsWithUrl);
		Assert.assertTrue(tagsWithUrl.size() == 2);

	}

	/**
	 * Test get multifield list null.
	 */
	@Test
	public void testGetMultifieldListNull() {
		List<String> multifieldList = rmitTagsModel.getMultifieldList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get primary nav menu not null.
	 */
	@Test
	public void testGetPrimaryNavMenuNotNull() {
		initTagslinklistValue();
		List<String> multifieldList = rmitTagsModel.getMultifieldList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Inits the tagslinklist value.
	 */
	private void initTagslinklistValue() {
		String[] tagslinklistValue = { "{\"tags\": \"abc\",\"tagsUrl\": \"abc\"}",
				"{\"tags\": \"abc2\",\"tagsUrl\": \"abc\"}", };
		try {
			Field canonicalUrlField = rmitTagsModel.getClass().getDeclaredField("tagslinklistValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(rmitTagsModel, tagslinklistValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in initTagslinklistValue()" + e.getMessage());
		}

		rmitTagsModel.init();
	}

}
