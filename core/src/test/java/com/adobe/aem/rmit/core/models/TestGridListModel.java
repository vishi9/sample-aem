package com.adobe.aem.rmit.core.models;

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

import com.adobe.aem.rmit.core.DamUtils;
import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.adobe.granite.asset.api.Asset;

/**
 * The Class TestGridListModel.
 */
public class TestGridListModel extends BaseAbstractTest {

	/** The grid list model. */
	@InjectMocks
	private GridListModel gridListModel;
	
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
	 * Test get grid list null.
	 */
	@Test
	public void testGetGridListNull() {

		Assert.assertNull(gridListModel.getGridList());

	}

	/**
	 * Test get component id.
	 */
	@Test
	public void testGetComponentId() {
		Assert.assertNull(gridListModel.getComponentId());
	}

	/**
	 * Test get hide load more.
	 */
	@Test
	public void testGetHideLoadMore() {
		Assert.assertFalse(gridListModel.getHideLoadMore());
	}
	/**
	 * Test get AltText is null.
	 */
	@Test
	public void testGetAltTextNull(){
		Assert.assertNull(DamUtils.getAltText("/content/dam/rmit/gridlist-component-1-up.jpg",resourceResolver));
	}
	/**
	 * Test get AltText is not null.
	 */
	@Test
	public void testGetAltTextNotNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(ValueMap.class)).thenReturn(valueMap);
		Mockito.when(valueMap.get(Mockito.anyString(), Mockito.any())).thenReturn("gridlist-component");
		Assert.assertEquals("gridlist-component", DamUtils.getAltText("/content/dam/rmit/gridlist-component-1-up.jpg",resourceResolver));
	}
	
	/**
	 * Test get AltText when Image is null.
	 */
	@Test
	public void testGetAltTextImageNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(ValueMap.class)).thenReturn(valueMap);
		String dcTitle = DamUtils.getAltText("/content/dam/rmit/gridlist-component-1-up.jpg",resourceResolver);
		Assert.assertNotNull(dcTitle);
		Assert.assertTrue(dcTitle.isEmpty());
	}
	/**
	 * Test get ImageTitle is null.
	 */
	@Test
	public void testGetImageTitleNull(){
		Assert.assertNull(DamUtils.getImageTitle("/content/dam/rmit/gridlist-component-1-up.jpg",resourceResolver));
	}
	
	/**
	 * Test get ImageTitle is not null.
	 */
	@Test
	public void testGetImageTitleNotNull(){
		Mockito.when(resourceResolver.getResource(Mockito.anyString())).thenReturn(mockResource);
		Mockito.when(mockResource.adaptTo(Asset.class)).thenReturn(asset);
		Mockito.when(asset.getName()).thenReturn("gridlist-component");
		Assert.assertEquals("gridlist-component",DamUtils.getImageTitle("/content/dam/rmit/gridlist-component-1-up.jpg",resourceResolver));
	}
}
