package com.adobe.aem.rmit.core.models;

import java.net.URL;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.commons.lang3.StringUtils;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.jcr.base.util.AccessControlUtil;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;

/**
 * This class to setting Json for Rmit Analytics
 * 
 * @author Shardendu
 *
 */
@Model(adaptables = SlingHttpServletRequest.class)
public class RmitAnalyticsModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(RmitAnalyticsModel.class);

	private String datalayerJson;
	private String pageName;
	private String url;
	private String url_;
	private String buildVersion;
	private String buildDate;
	private String language;
	private String registration;
	private String userId;
	private String userType;
	private String primaryCategory;
	private String subCategoryL1;
	private String subCategoryL2;
	private String subCategoryL3;
	private String subCategoryL4;
	private String subCategoryL5;
	private String subCategoryL6;
	private String subCategoryL7;

	@ScriptVariable
	private Page currentPage;

	@SlingObject
	private ResourceResolver resourceResolver;

	@SlingObject
	private Resource resource;

	@Self
	private SlingHttpServletRequest request;

	@Inject
	ConfigurationAdmin configAdmin;

	@PostConstruct
	protected void init() {
		getDatalayerProperties();
	}

	/**
	 * get Datalayer Properties
	 */
	private void getDatalayerProperties() {
		try {
			Configuration conf = configAdmin.getConfiguration("com.adobe.aem.rmit.core.models.rmitAnalytics");
			Externalizer externalizer = resourceResolver.adaptTo(Externalizer.class);
			String loggedin = conf.getProperties().get("registration-loggedin") != null ? conf.getProperties().get("registration-loggedin").toString() : "";
			String loggedout = conf.getProperties().get("registration-loggedout") != null ? conf.getProperties().get("registration-loggedout").toString() : "";
			String staff = conf.getProperties().get("usertype-staff") != null ? conf.getProperties().get("usertype-staff").toString() : "";
			String students = conf.getProperties().get("usertype-students") != null ? conf.getProperties().get("usertype-students").toString() : "";
			
			String currentPagepath = currentPage.getPath();
			url = externalizer.publishLink(resourceResolver, currentPagepath).replace("http://", "https://");
			url_ = url;
			url = StringUtils.replace(url, "-", " "); // converting "-" to " "
			URL aURL = new URL(url);
			String shortURL = aURL.getPath();
			pageName = "rmit" + StringUtils.replace(shortURL, "/", ":");

			buildVersion = conf.getProperties().get("buildVersion") != null
					? conf.getProperties().get("buildVersion").toString()
					: "";

			buildDate = conf.getProperties().get("buildDate") != null ? conf.getProperties().get("buildDate").toString()
					: "";
			
			userId = getCurrentUserId(request).toLowerCase();					
			registration = "unknown";
			userType = "unknown";
			if (!userId.equalsIgnoreCase("anonymous") && userId.length() > 0) 
			{
			if(userId.charAt(0) == 'e')
				userType = staff;
			if(userId.charAt(0) == 's')
				userType = students;
			
			registration = StringUtils.isNotBlank(userId) ? loggedin : loggedout;
			}
			
			String[] strCategory = StringUtils.split(shortURL, "/");
			primaryCategory = "rmit";
			int catLen = strCategory.length;
			
			subCategoryL1 = catLen > 0 ? strCategory[0] : "";
			subCategoryL2 = catLen > 1 ? strCategory[1] : "";
			subCategoryL3 = catLen > 2 ? strCategory[2] : "";
			subCategoryL4 = catLen > 3 ? strCategory[3] : "";
			subCategoryL5 = catLen > 4 ? strCategory[4] : "";
			subCategoryL6 = catLen > 5 ? strCategory[5] : "";
			subCategoryL7 = catLen > 6 ? strCategory[6] : "";
			setJsonDataLayer();
		} catch (Exception e) {
			LOGGER.error("*Error in getDatalayerProperties*", e);
		}
	}

	/**
	 * set Json DataLayer
	 */
	private void setJsonDataLayer()
	{
		datalayerJson = "<script>\n"
		+"var digitalData={\n"  
		+"       page:{  \n"
		+"         pageInfo:{  \n"
		+"          pageId:{ \n"
		+"             pageName:'"+pageName+"',\n"
		+"             url:'"+url_+"',\n"
		+"             buildVersion:'"+buildVersion+"',\n"
		+"             pageNotFound:'na',\n"
		+"             buildDate:'"+buildDate+"'\n"
		+"          },\n"
		+"            category:{  \n"
		+"            primaryCategory:'"+primaryCategory+"',\n"
		+"            subCategoryL1:'"+subCategoryL1+"',\n"
		+"            subCategoryL2:'"+subCategoryL2+"',\n"
		+"            subCategoryL3:'"+subCategoryL3+"',\n"
		+"            subCategoryL4:'"+subCategoryL4+"',\n"
		+"            subCategoryL5:'"+subCategoryL5+"',\n"
		+"            subCategoryL6:'"+subCategoryL6+"',\n"
		+"            subCategoryL7:'"+subCategoryL7+"'\n"
		+"         },\n"
		+"	         attributes:{  \n"
		+"	            language:'en',\n"
		+"	            contentType:'International'\n"
		+"	         }\n"
		+"	      }\n"
		+"	   },\n"
		+"	   user:{  \n"
		+"	      userInfo:{ \n" 
		+"            userId:'"+userId+"',\n"
		+"            userType:'"+userType+"',\n"
		+"            registration:'"+registration+"'\n"
		+"      }}};\n"
		+" </script>";
			
	}
	
	/**
	 * @param request
	 * @return CurrentUser
	 */
	public String getCurrentUserId(SlingHttpServletRequest request) {
		String value = StringUtils.EMPTY;
		Session session = null;
		try {
			session = request.getResourceResolver().adaptTo(Session.class);
			UserManager userManager = AccessControlUtil.getUserManager(session);
			User user = (User) userManager.getAuthorizable(session.getUserID());
			if(null != user){
				value = user.getID();
			}
		} catch (RepositoryException repositoryException) {
			LOGGER.error("RepositoryException in getCurrentUserId" + repositoryException);
		}
		return value;
	}
	
	/**
	 * @return the pageName
	 */
	public String getPageName() {
		return pageName;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the url_
	 */
	public String getUrl_() {
		return url_;
	}
	
	/**
	 * @return the buildVersion
	 */
	public String getBuildVersion() {
		return buildVersion;
	}

	/**
	 * @return the buildDate
	 */
	public String getBuildDate() {
		return buildDate;
	}

	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}

	/**
	 * @return the registration
	 */
	public String getRegistration() {
		return registration;
	}

	/**
	 * @return the primary Category
	 */
	public String getPrimaryCategory() {
		return primaryCategory;
	}

	/**
	 * @return the sub Category L1
	 */
	public String getSubCategoryL1() {
		return subCategoryL1;
	}

	/**
	 * @return the sub Category L2
	 */
	public String getSubCategoryL2() {
		return subCategoryL2;
	}

	/**
	 * @return the sub Category L3
	 */
	public String getSubCategoryL3() {
		return subCategoryL3;
	}

	/**
	 * @return the sub Category L4
	 */
	public String getSubCategoryL4() {
		return subCategoryL4;
	}

	/**
	 * @return the get SubCategory L5
	 */
	public String getSubCategoryL5() {
		return subCategoryL5;
	}

	/**
	 * @return the subCategory L6
	 */
	public String getSubCategoryL6() {
		return subCategoryL6;
	}

	/**
	 * @return the subCategory L7
	 */
	public String getSubCategoryL7() {
		return subCategoryL7;
	}

	/**
	 * @param title
	 *            the pageName to set
	 */
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	/**
	 * @param title
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @param title
	 *            the buildVersion to set
	 */
	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}

	/**
	 * @param title
	 *            the url to set
	 */
	public void setBuildDate(String buildDate) {
		this.buildDate = buildDate;
	}
	
		
	/**
	 * @return the get userType
	 */
	public String getUserType() {
		return userType;
	}
	/**
	 * @return the datalayerJson
	 */
	public String getDatalayerJson() {
		return datalayerJson;
	}
	
}