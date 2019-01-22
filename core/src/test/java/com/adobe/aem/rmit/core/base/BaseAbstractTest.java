package com.adobe.aem.rmit.core.base;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.testing.mock.sling.ResourceResolverType;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletRequest;
import org.apache.sling.testing.mock.sling.servlet.MockSlingHttpServletResponse;
import org.junit.Rule;

import io.wcm.testing.mock.aem.junit.AemContext;

/**
 * The Class BaseAbstractTest.
 */
public abstract class BaseAbstractTest {

	/** The context. */
	@Rule
	public AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);

	/**
	 * Gets the context.
	 *
	 * @return the context
	 */
	protected AemContext getContext() {
		return context;
	}

	/**
	 * Gets the resource resolver.
	 *
	 * @return the resource resolver
	 */
	protected ResourceResolver getResourceResolver() {
		return context.resourceResolver();
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	protected MockSlingHttpServletRequest getRequest() {
		return context.request();
	}

	/**
	 * Gets the response.
	 *
	 * @return the response
	 */
	protected MockSlingHttpServletResponse getResponse() {
		return context.response();
	}

	/**
	 * Gets the current resource.
	 *
	 * @return the current resource
	 */
	protected Resource getCurrentResource() {
		return context.currentResource();
	}
}
