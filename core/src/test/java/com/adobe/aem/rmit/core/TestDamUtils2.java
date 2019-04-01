package com.adobe.aem.rmit.core;

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
 * The Class TestDamUtils.
 */

public class TestDamUtils2 extends BaseAbstractTest{
	/** The DamUtils. */
	@InjectMocks
	private DamUtils2 damUtils;
	
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
	}
	
	/**
	 * Test get AltText is null.
	 */
	@Test
	public void testGetAltTextNull(){
		Assert.assertNull(DamUtils2.getAltText("/content/dam/rmit/feature-component-1-up.jpg",resourceResolver));
	}
	/**
	 * Test get AltText is not null.
	 */
	/*@Test
	public void testGetAltTextNotNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(ValueMap.class)).thenReturn(valueMap);
		Mockito.when(valueMap.get(Mockito.anyString(), Mockito.any())).thenReturn("feature-component");
		Assert.assertEquals("feature-component", DamUtils2.getAltText("/content/dam/rmit/feature-component-1-up.jpg",resourceResolver));
	}
	
	*//**
	 * Test get AltText when Image is null.
	 *//*
	@Test
	public void testGetAltTextImageNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(ValueMap.class)).thenReturn(valueMap);
		String dcTitle = DamUtils2.getAltText("/content/dam/rmit/feature-component-1-up.jpg",resourceResolver);
		Assert.assertNotNull(dcTitle);
		Assert.assertTrue(dcTitle.isEmpty());
	}*/
	/**
	 * Test get ImageTitle is null.
	 */
	@Test
	public void testGetImageTitleNull(){
		Assert.assertNull(DamUtils2.getImageTitle("/content/dam/rmit/feature-component-1-up.jpg",resourceResolver));
	}
	
	/**
	 * Test get ImageTitle is not null.
	 */
	@Test
	public void testGetImageTitleNotNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getName()).thenReturn("feature-component");
		Assert.assertEquals("feature-component",DamUtils2.getImageTitle("/content/dam/rmit/feature-component-1-up.jpg",resourceResolver));
	}

}
