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
 * The Class TestPrimaryNavModel.
 */
public class TestPrimaryNavModel extends BaseAbstractTest {

	/** The primary nav model. */
	@InjectMocks
	private PrimaryNavModel primaryNavModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test promo reference null.
	 */
	@Test
	public void testPromoReferenceNull() {
		Assert.assertNull(primaryNavModel.getPromoReference());
	}

	/**
	 * Test get promo reference not null.
	 */
	@Test
	public void testGetPromoReferenceNotNull() {
		String promoReference = "Promo Reference";
		try {
			Field canonicalUrlField = primaryNavModel.getClass().getDeclaredField("promoReference");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(primaryNavModel, promoReference);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setUp()" + e.getMessage());
		}
		String promoReferenceActual = primaryNavModel.getPromoReference();
		Assert.assertNotNull(promoReference);
		Assert.assertEquals(promoReference, promoReferenceActual);

	}

	/**
	 * Test get primary nav list null.
	 */
	@Test
	public void testGetPrimaryNavListNull() {
		List<String> primaryNavList = primaryNavModel.getPrimaryNavList();
		Assert.assertNull(primaryNavList);
	}

	/**
	 * Test get primary nav list not null.
	 */
	@Test
	public void testGetPrimaryNavListNotNull() {
		initPrimaryNavLinks();
		List<String> primaryNavList = primaryNavModel.getPrimaryNavList();
		Assert.assertNotNull(primaryNavList);
		Assert.assertTrue(primaryNavList.size() == 2);

	}

	/**
	 * Test get primary nav menu null.
	 */
	@Test
	public void testGetPrimaryNavMenuNull() {
		List<PrimaryNavMenu> primaryNavMenu = primaryNavModel.getPrimaryNavMenu();
		Assert.assertNotNull(primaryNavMenu);
		Assert.assertTrue(primaryNavMenu.isEmpty());
	}

	/**
	 * Test get primary nav menu not null.
	 */
	@Test
	public void testGetPrimaryNavMenuNotNull() {
		initPrimaryNavLinks();
		List<PrimaryNavMenu> primaryNavMenu = primaryNavModel.getPrimaryNavMenu();
		Assert.assertNotNull(primaryNavMenu);
		Assert.assertTrue(primaryNavMenu.size() == 2);

	}

	/**
	 * Inits the primary nav links.
	 */
	private void initPrimaryNavLinks() {
		String[] primaryNavLinks = {
				"{\"menuHeading\": \"abc\",\"menuLink\": \"abc\",\"subMenuItems\": [{\"subMenuLabel\":\"label\",\"subMenuLink\":\"url1\"},{\"subMenuLabel\":\"label\",\"subMenuLink\":\"url1\"}]}",
				"{\"menuHeading\": \"abc2\",\"menuLink\": \"abc\",\"subMenuItems\": [{\"subMenuLabel\":\"label\",\"subMenuLink\":\"url1\"},{\"subMenuLabel\":\"label\",\"subMenuLink\":\"url1\"}]}", };
		try {
			Field canonicalUrlField = primaryNavModel.getClass().getDeclaredField("primaryNavLinks");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(primaryNavModel, primaryNavLinks);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in initPrimaryNavLinks()" + e.getMessage());
		}

		primaryNavModel.init();
	}

}
