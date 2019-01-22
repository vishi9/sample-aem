package com.adobe.aem.rmit.core.models;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import org.apache.jackrabbit.api.security.user.User;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.osgi.MapUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

import com.adobe.aem.rmit.core.base.BaseAbstractTest;
import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;

/**
 * The Class TestRmitAnalyticsModel.
 */
public class TestRmitAnalyticsModel extends BaseAbstractTest {

	/** The rmit analytics model. */
	@InjectMocks
	RmitAnalyticsModel rmitAnalyticsModel;

	/** The config admin. */
	@Mock
	ConfigurationAdmin configAdmin;

	/** The conf. */
	@Mock
	Configuration conf;

	/** The resource resolver. */
	@Mock
	ResourceResolver resourceResolver;

	/** The mock page. */
	@Mock
	Page mockPage;

	/** The externalizer. */
	@Mock
	Externalizer externalizer;

	/** The request. */
	@Mock
	SlingHttpServletRequest request;

	/** The session. */
	@Mock
	Session session;

	/** The user. */
	@Mock
	User user;

	/**
	 * Sets the up.
	 */
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		try {
			Mockito.when(configAdmin.getConfiguration(Mockito.anyString())).thenReturn(conf);
			Map<String, Object> map = new HashMap<>();
			map.put("registration-loggedin", "registration-loggedin");
			map.put("registration-loggedout", "registration-loggedout");
			map.put("usertype-staff", "usertype-staff");
			map.put("usertype-students", "usertype-students");
			Dictionary<String, Object> dictionary = MapUtil.toDictionary(map);
			Mockito.when(conf.getProperties()).thenReturn(dictionary);
			Mockito.when(mockPage.getPath()).thenReturn("/content/rmit-ui");
			Mockito.when(resourceResolver.adaptTo(Externalizer.class)).thenReturn(externalizer);
			Mockito.when(externalizer.publishLink(Mockito.any(ResourceResolver.class), Mockito.anyString()))
					.thenReturn("http://www.rmit.au/content/rmit.html");
			Mockito.when(request.getResourceResolver()).thenReturn(resourceResolver);
			Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);

		} catch (IOException e) {
			Assert.fail("Exception occurred in init()" + e.getMessage());
		}
		rmitAnalyticsModel.init();
	}

	/**
	 * Test get current user id.
	 */
	@Test
	public void testGetCurrentUserId() {
		Assert.assertNotNull(rmitAnalyticsModel.getCurrentUserId(getRequest()));
	}

	/**
	 * Test get page name.
	 */
	@Test
	public void testGetPageName() {
		String pageName = rmitAnalyticsModel.getPageName();
		Assert.assertNotNull(pageName);
		Assert.assertEquals("rmit:content:rmit.html", pageName);
	}

	/**
	 * Test get url.
	 */
	@Test
	public void testGetUrl() {
		String url = rmitAnalyticsModel.getUrl();
		Assert.assertNotNull(url);
		Assert.assertEquals("https://www.rmit.au/content/rmit.html", url);
	}

	/**
	 * Test get url.
	 */
	@Test
	public void testGetUrl_() {
		String url_ = rmitAnalyticsModel.getUrl_();
		Assert.assertNotNull(url_);
		Assert.assertEquals("https://www.rmit.au/content/rmit.html", url_);
	}

	/**
	 * Test get build version.
	 */
	@Test
	public void testGetBuildVersion() {
		String buildVersion = rmitAnalyticsModel.getBuildVersion();
		Assert.assertNotNull(buildVersion);
		Assert.assertTrue(buildVersion.isEmpty());
	}

	/**
	 * Test get build date.
	 */
	@Test
	public void testGetBuildDate() {
		String buildDate = rmitAnalyticsModel.getBuildDate();
		Assert.assertNotNull(buildDate);
		Assert.assertTrue(buildDate.isEmpty());
	}

	/**
	 * Test get language.
	 */
	@Test
	public void testGetLanguage() {
		Assert.assertNull(rmitAnalyticsModel.getLanguage());
	}

	/**
	 * Test get registration.
	 */
	@Test
	public void testGetRegistration() {
		String registration = rmitAnalyticsModel.getRegistration();
		Assert.assertNotNull(registration);
		Assert.assertEquals("unknown", registration);
	}

	/**
	 * Test get primary category.
	 */
	@Test
	public void testGetPrimaryCategory() {
		String primaryCategory = rmitAnalyticsModel.getPrimaryCategory();
		Assert.assertNotNull(primaryCategory);
		Assert.assertEquals("rmit", primaryCategory);
	}

	/**
	 * Test get sub category L 1.
	 */
	@Test
	public void testGetSubCategoryL1() {
		String subCategoryL1 = rmitAnalyticsModel.getSubCategoryL1();
		Assert.assertNotNull(subCategoryL1);
		Assert.assertEquals("content", subCategoryL1);
	}

	/**
	 * Test get sub category L 2.
	 */
	@Test
	public void testGetSubCategoryL2() {
		String subCategoryL2 = rmitAnalyticsModel.getSubCategoryL2();
		Assert.assertNotNull(subCategoryL2);
		Assert.assertEquals("rmit.html", subCategoryL2);
	}

	/**
	 * Test get sub category L 3.
	 */
	@Test
	public void testGetSubCategoryL3() {
		String subCategoryL3 = rmitAnalyticsModel.getSubCategoryL3();
		Assert.assertNotNull(subCategoryL3);
		Assert.assertTrue(subCategoryL3.isEmpty());
	}

	/**
	 * Test get sub category L 4.
	 */
	@Test
	public void testGetSubCategoryL4() {
		String subCategoryL4 = rmitAnalyticsModel.getSubCategoryL4();
		Assert.assertNotNull(subCategoryL4);
		Assert.assertTrue(subCategoryL4.isEmpty());
	}

	/**
	 * Test get sub category L 5.
	 */
	@Test
	public void testGetSubCategoryL5() {
		String subCategoryL5 = rmitAnalyticsModel.getSubCategoryL5();
		Assert.assertNotNull(subCategoryL5);
		Assert.assertTrue(subCategoryL5.isEmpty());
	}

	/**
	 * Test get sub category L 6.
	 */
	@Test
	public void testGetSubCategoryL6() {
		String subCategoryL6 = rmitAnalyticsModel.getSubCategoryL6();
		Assert.assertNotNull(subCategoryL6);
		Assert.assertTrue(subCategoryL6.isEmpty());
	}

	/**
	 * Test get sub category L 7.
	 */
	@Test
	public void testGetSubCategoryL7() {
		String subCategoryL7 = rmitAnalyticsModel.getSubCategoryL7();
		Assert.assertNotNull(subCategoryL7);
		Assert.assertTrue(subCategoryL7.isEmpty());
	}

	/**
	 * Test get user type.
	 */
	@Test
	public void testGetUserType() {
		String userType = rmitAnalyticsModel.getUserType();
		Assert.assertNotNull(userType);
		Assert.assertEquals("unknown", userType);
	}

}
