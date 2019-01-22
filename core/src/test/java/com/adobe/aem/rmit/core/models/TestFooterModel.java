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
 * The Class TestFooterModel.
 */
public class TestFooterModel extends BaseAbstractTest {

	/** The footer model. */
	@InjectMocks
	private FooterModel footerModel;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] leftListValue = { "{\"leftListLinkLabel\": \"abc\",\"leftListLinkUrl\": \"url1\"}",
				"{\"leftListLinkLabel\": \"abc2\",\"leftListLinkUrl\": \"url2\"}" };

		String[] rightListValue = {
				"{\"heading\": \"abc\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}",
				"{\"heading\": \"abc2\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}",
				"{\"heading\": \"abc2\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}",
				"{\"heading\": \"abc2\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}",
				"{\"heading\": \"abc2\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}",
				"{\"heading\": \"abc2\",\"links\": [{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"},{\"linkLabel\":\"label\",\"linkUrl\":\"url1\"}]}" };

		String[] socialListValue = { "{\"listTitle\": \"abc\",\"logoLink\": \"url1\",\"logoLabel\": \"label1\"}",
				"{\"listTitle\": \"abc2\",\"logoLink\": \"url2\",\"logoLabel\": \"label2\"}" };
		String[] legalListValue = { "{\"fieldLabel\": \"abc\",\"fieldUrl\": \"url1\"}",
				"{\"fieldLabel\": \"abc2\",\"fieldUrl\": \"url2\"}" };
		try {
			Field canonicalUrlField = footerModel.getClass().getDeclaredField("leftListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(footerModel, leftListValue);
			canonicalUrlField = footerModel.getClass().getDeclaredField("rightListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(footerModel, rightListValue);
			canonicalUrlField = footerModel.getClass().getDeclaredField("socialListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(footerModel, socialListValue);
			canonicalUrlField = footerModel.getClass().getDeclaredField("legalListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(footerModel, legalListValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setUp()" + e.getMessage());
		}
	}

	/**
	 * Test get left list null.
	 */
	@Test
	public void testGetLeftListNull() {
		List<LeftList> leftList = footerModel.getLeftList();
		Assert.assertNotNull(leftList);
		Assert.assertTrue(leftList.isEmpty());
	}

	/**
	 * Test get left list not null.
	 */
	@Test
	public void testGetLeftListNotNull() {

		footerModel.init();
		List<LeftList> leftList = footerModel.getLeftList();
		Assert.assertNotNull(leftList);
		Assert.assertTrue(leftList.size() == 2);

	}

	/**
	 * Test get right list null.
	 */
	@Test
	public void testGetRightListNull() {
		List<List<RightList>> rightList = footerModel.getRightList();
		Assert.assertNotNull(rightList);
		Assert.assertTrue(rightList.size() == 1);
	}

	/**
	 * Test get right list not null.
	 */
	@Test
	public void testGetRightListNotNull() {

		footerModel.init();
		List<List<RightList>> rightList = footerModel.getRightList();
		Assert.assertNotNull(rightList);
		Assert.assertTrue(rightList.size() == 2);

	}

	/**
	 * Test get social list null.
	 */
	@Test
	public void testGetSocialListNull() {
		List<SocialList> socialList = footerModel.getSocialList();
		Assert.assertNotNull(socialList);
		Assert.assertTrue(socialList.isEmpty());
	}

	/**
	 * Test get social list not null.
	 */
	@Test
	public void testGetSocialListNotNull() {

		footerModel.init();
		List<SocialList> socialList = footerModel.getSocialList();
		Assert.assertNotNull(socialList);
		Assert.assertTrue(socialList.size() == 2);

	}

	/**
	 * Test get legal list null.
	 */
	@Test
	public void testGetLegalListNull() {
		List<LegalList> legalList = footerModel.getLegalList();
		Assert.assertNotNull(legalList);
		Assert.assertTrue(legalList.isEmpty());
	}

	/**
	 * Test get social legal not null.
	 */
	@Test
	public void testGetSocialLegalNotNull() {

		footerModel.init();
		List<LegalList> legalList = footerModel.getLegalList();
		Assert.assertNotNull(legalList);
		Assert.assertTrue(legalList.size() == 2);

	}

	/**
	 * Test get multifield left list null.
	 */
	@Test
	public void testGetMultifieldLeftListNull() {
		List<String> multifieldList = footerModel.getMultifieldLeftList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield left list.
	 */
	@Test
	public void testGetMultifieldLeftList() {

		footerModel.init();
		List<String> multifieldList = footerModel.getMultifieldLeftList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Test get multifield right list null.
	 */
	@Test
	public void testGetMultifieldRightListNull() {
		List<String> multifieldList = footerModel.getMultifieldRightList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield right list.
	 */
	@Test
	public void testGetMultifieldRightList() {

		footerModel.init();
		List<String> multifieldList = footerModel.getMultifieldRightList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 6);

	}

	/**
	 * Test get multifield left social null.
	 */
	@Test
	public void testGetMultifieldLeftSocialNull() {
		List<String> multifieldList = footerModel.getMultifieldSocialList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield social list.
	 */
	@Test
	public void testGetMultifieldSocialList() {

		footerModel.init();
		List<String> multifieldList = footerModel.getMultifieldSocialList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Test get multifield legal list null.
	 */
	@Test
	public void testGetMultifieldLegalListNull() {
		List<String> multifieldList = footerModel.getMultifieldLegalList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield legal list.
	 */
	@Test
	public void testGetMultifieldLegalList() {

		footerModel.init();
		List<String> multifieldList = footerModel.getMultifieldLegalList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);

	}

	/**
	 * Test get logo url.
	 */
	@Test
	public void testGetLogoUrl() {
		String logoUrl = footerModel.getLogoUrl();
		Assert.assertNull(logoUrl);
	}

	/**
	 * Test get design class 4 col.
	 */
	@Test
	public void testGetDesignClass4Col() {
		String designClass = footerModel.getDesignClass();
		Assert.assertEquals("col-md-4", designClass);
	}

	/**
	 * Test get design class footer col.
	 */
	@Test
	public void testGetDesignClassFooterCol() {
		footerModel.init();
		String designClass = footerModel.getDesignClass();
		Assert.assertEquals("footerCol", designClass);
	}

}
