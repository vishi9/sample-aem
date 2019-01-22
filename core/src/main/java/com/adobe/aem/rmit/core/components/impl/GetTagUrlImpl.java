package com.adobe.aem.rmit.core.components.impl;

import javax.inject.Named;
import com.google.gson.Gson;
import javax.jcr.Node;
import javax.jcr.Value;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.adobe.aem.rmit.core.components.GetTagUrl;
import org.apache.commons.lang3.StringUtils;
import org.osgi.service.cm.ConfigurationAdmin;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.api.resource.ResourceResolverFactory;
import java.util.HashMap;
import java.util.Map;
import javax.jcr.Session;

@Component(service = GetTagUrl.class, immediate = true)
public class GetTagUrlImpl implements GetTagUrl {

	private static final Logger LOG = LoggerFactory.getLogger(GetTagUrlImpl.class);

	@Reference
	ConfigurationAdmin configAdmin;

	@Reference
	ResourceResolverFactory resourceResolverFactory;

	/**
	 * return the Tag Url.
	 **/
	public String getTagUrlByTags(String tag) {
		LOG.error("**In getTagUrlByTags()* Tag "+tag);
		String tagUrl = StringUtils.EMPTY;
		ResourceResolver resolver = null;
		Session session = null;
		Map<String, Object> authInfoParam = new HashMap<String, Object>();
		String nodePath = StringUtils.EMPTY;
		try {
			//*make sure user mapper is updated*
			//"org.apache.sling.serviceusermapping.impl.ServiceUserMapperImpl": com.adobe.aem.rmit-sites:rmitservice=rmitSystemUser
			authInfoParam.put(ResourceResolverFactory.SUBSERVICE, "rmitservice");
			resolver = resourceResolverFactory.getServiceResourceResolver(authInfoParam);

			/**
			 * resourcePath=/content/page/path/jcr:content/root/responsivegrid/rmittags
			 **/
			String resourcePath = configAdmin
					.getConfiguration("com.adobe.aem.rmit.core.models.rmittags.configPageResourcePath").getProperties()
					.get("path").toString();
			session = resolver.adaptTo(Session.class);
			Node node = session.getNode(resourcePath);
		    LOG.error("**node*-* ::" +node);
			if (node != null) {
				Resource myResource = resolver.getResource(resourcePath);

				Integer jcrIndex = resourcePath.lastIndexOf("/jcr:content/");
				if (jcrIndex > 0) {
					nodePath = resourcePath.substring(0, jcrIndex);
					nodePath = resourcePath.replaceAll(nodePath + "/jcr:content/", "");
				}
				// LOG.error("**nodePath** :: " +nodePath);
				RmitTags rmitTags = null;
				for (Value v : node.getProperty("tagslinklistValue").getValues()) {
					rmitTags = new Gson().fromJson(v.toString(), RmitTags.class);
					// LOG.error("tags....." + rmitTags.getTags());
					if (rmitTags.getTags().equalsIgnoreCase(tag)) {
						tagUrl = rmitTags.getTagsUrl();
						// LOG.error("**rmitTags.getTagsUrl** ::" + rmitTags.getTagsUrl());
						break;
					}
				}
			}
		} catch (Exception e) {
			LOG.error("*Error in getTagUrlByTags*", e);
		} finally {
			if (resolver != null) {
				resolver.close();
				resolver = null;
			}
			if (session != null) {
				session.logout();
				session = null;
			}
		}
		return tagUrl;
	}

}