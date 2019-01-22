package com.adobe.aem.rmit.core.models;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.adobe.granite.asset.api.Asset;
/**
 * The Class TestFeatureContentModel.
 */
public class TestFeatureContentModel extends BaseAbstractTest {
	/** The Feature Content sling model. */
	@InjectMocks
	private FeatureContentModel featureContentModel;
	
	/** The resource resolver. */
	@Mock
	ResourceResolver resourceResolver;

	@Mock
	Resource mockResource;

	@Mock
	ValueMap valueMap;
	
	@Mock
	Asset asset;
	
	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		String[] cardListValue = { "{\"link\": \"link1\",\"heading\": \"heading1\",\"shortDescription\": \"shortDescription1\",\"ctaLabel\": \"ctaLabel1\",\"image\": \"image1\",\"imageAltText\": \"imageAltText1\"}",
				"{\"link\": \"link2\",\"heading\": \"heading2\",\"shortDescription\": \"shortDescription2\",\"ctaLabel\": \"ctaLabel2\",\"image\": \"image2\",\"imageAltText\": \"imageAltText2\"}" };
		try {
			Field canonicalUrlField = featureContentModel.getClass().getDeclaredField("cardListValue");
			canonicalUrlField.setAccessible(true);
			canonicalUrlField.set(featureContentModel, cardListValue);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			Assert.fail("Exception occurred in setUp()" + e.getMessage());
		}
	}
	

	/**
	 * Test get card list null.
	 */
	@Test
	public void testGetCardListNull() {
		List<FeatureContent> featureContent=featureContentModel.getCardList();
		Assert.assertNotNull(featureContent);
		Assert.assertTrue(featureContent.isEmpty());
	}

	/**
	 * Test get card list not null.
	 */
	@Test
	public void testGetCardListNotNull() {

		featureContentModel.init();
		List<FeatureContent> featureContent=featureContentModel.getCardList();
		Assert.assertNotNull(featureContent);
		Assert.assertTrue(featureContent.size() == 2);

	}

	/**
	 * Test get multifield list null.
	 */
	@Test
	public void testGetMultifieldListNull() {
		List<String> multifieldList = featureContentModel.getMultifieldList();
		Assert.assertNull(multifieldList);
	}

	/**
	 * Test get multifield list not null.
	 */
	@Test
	public void testGetMultifieldListNotNull() {
		featureContentModel.init();
		List<String> multifieldList = featureContentModel.getMultifieldList();
		Assert.assertNotNull(multifieldList);
		Assert.assertTrue(multifieldList.size() == 2);
	}
	

}
