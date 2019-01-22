package com.adobe.aem.rmit.core.components;

import java.io.IOException;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Node;
import javax.jcr.Property;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.Value;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
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

import com.adobe.aem.rmit.core.components.impl.GetTagUrlImpl;

/**
 * The Class TestGetTagUrl.
 */
public class TestGetTagUrl {

	/** The get tag url impl. */
	@InjectMocks
	GetTagUrlImpl getTagUrlImpl;

	/** The resource resolver factory. */
	@Mock
	private ResourceResolverFactory resourceResolverFactory;

	/** The config admin. */
	@Mock
	ConfigurationAdmin configAdmin;

	/** The resource resolver. */
	@Mock
	ResourceResolver resourceResolver;

	/** The session. */
	@Mock
	Session session;

	/** The configuration. */
	@Mock
	Configuration configuration;

	/** The node. */
	@Mock
	Node node;

	/** The property. */
	@Mock
	Property property;

	/** The value. */
	@Mock
	Value value;

	/**
	 * Sets the up.
	 *
	 * @throws LoginException
	 *             the login exception
	 * @throws RepositoryException
	 *             the repository exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings("unchecked")
	@Before
	public void setUp() throws LoginException, RepositoryException, IOException {
		MockitoAnnotations.initMocks(this);
		Map<String, Object> map = new HashMap<>();
		map.put("path", "path");
		Dictionary<String, Object> dictionary = MapUtil.toDictionary(map);
		Mockito.when(configAdmin.getConfiguration("com.adobe.aem.rmit.core.models.rmittags.configPageResourcePath"))
				.thenReturn(configuration);
		Mockito.when(configuration.getProperties()).thenReturn(dictionary);

		Mockito.when(resourceResolverFactory.getServiceResourceResolver(Mockito.any(Map.class)))
				.thenReturn(resourceResolver);
		Mockito.when(resourceResolver.adaptTo(Session.class)).thenReturn(session);
		Mockito.when(session.getNode(Mockito.anyString())).thenReturn(node);
		Mockito.when(node.getProperty(Mockito.anyString())).thenReturn(property);
		Value[] values = { value, value };
		Mockito.when(property.getValues()).thenReturn(values);
		String tagslinklistValue = "{\"tags\": \"abc\",\"tagsUrl\": \"url1\"}";
		Mockito.when(value.toString()).thenReturn(tagslinklistValue);
	}

	/**
	 * Test get title.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws LoginException
	 *             the login exception
	 * @throws RepositoryException
	 *             the repository exception
	 */
	@Test
	public void testGetTitle() throws IOException, LoginException, RepositoryException {
		String tagUrlByTags = getTagUrlImpl.getTagUrlByTags("abc");
		Assert.assertEquals("url1", tagUrlByTags);
	}
}
