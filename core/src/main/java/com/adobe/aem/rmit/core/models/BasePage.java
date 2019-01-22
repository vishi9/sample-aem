package com.adobe.aem.rmit.core.models;


import com.adobe.aem.rmit.core.MapUrlHelper;
import com.day.cq.commons.Externalizer;
import com.day.cq.wcm.api.Page;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Model;

import javax.inject.Inject;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import javax.inject.Named;

import org.apache.sling.api.SlingHttpServletRequest;
import com.day.cq.commons.jcr.JcrConstants;

@Model(adaptables = { SlingHttpServletRequest.class })
public  class BasePage {

    @Optional
    @Inject
    @Via("resource")
    @Named(JcrConstants.JCR_TITLE)
    private String componentTitle;
	
	@Optional
	@Inject @Via("resource") @Named(JcrConstants.JCR_DESCRIPTION)
	private String shortDescription;

    @Inject
	@Via("resource")
	@Optional
    private String metaDescription;

    @Inject 
	@Default(values="en")
	@Via("resource") @Named(JcrConstants.JCR_LANGUAGE)
	@Optional
    private String defaultLanguage;

    @Inject 
	@Via("resource")  
	@Optional
    private String canonicalUrl;

    @Inject 
	@Via("resource")  
	@Optional
    private String hreflangTagging;
    
    @Inject 
   	@Via("resource")  
   	@Optional
       private String ampcanonicalUrl;
    
    @Inject 
	@Via("resource") 
	@Optional	
    private String googleTitle;

    @Inject 
	@Via("resource")
	@Optional	
    private String googleDescription;

    @Inject 
	@Via("resource") 
	@Optional
    private String googleLanguage;

    @Inject
	@Via("resource")
	@Optional	
    private String googleAuthor;

    @Inject
	@Via("resource")
	@Optional
    private String googleCopyright;

    @Inject 
	@Via("resource") 
	@Optional	
    private String facebookOgTitle;

    @Inject 
	@Via("resource")
	@Optional
    private String facebookOgType;

    @Inject 
	@Via("resource")  
	@Optional
    private String facebookOgImage;

    @Inject 
	@Via("resource")  
	@Optional
    private String facebookOgUrl;

    @Inject 
	@Via("resource")  
	@Optional
    private String facebookOgDescription;

    @Inject 
	@Via("resource")
	@Optional
    private String twitterCard;

    @Inject 
	@Via("resource")  
	@Optional
    private String twitterTitle;

    @Inject 
	@Via("resource") 
	@Optional	
    private String twitterDescription;

    @Inject 
	@Via("resource")  
	@Optional
    private String twitterImage;

    @Inject
    @Via("resource")
    @Optional
    private String gelImage;

    @Inject
    @Via("resource")
    @Optional
    private String backgroundImage;

    @Inject
    @Via("resource")
    @Optional
    private String backgroundPosition;

    @Inject
    @Via("resource")
    @Optional
    private boolean turnOffLeftNav;

    @Inject
    private Resource resource;

    @Inject
    private ResourceResolver resourceResolver;

    @Inject
    @Via("resource")
    @Optional
    private String pageColour;

    @Inject
    private Page currentPage;
	
	private String emailShare;

    public String getCanonicalUrl() {

        if (!StringUtils.isBlank(canonicalUrl)) {
            return canonicalUrl;
        } else {
            if (resource != null) {
                canonicalUrl = getCurrentPageUrl(resource.getParent());
            }
        }
        return externalizeUrl(canonicalUrl);
    }

    public String getHreflangTagging() {

        if (!StringUtils.isBlank(hreflangTagging)) {
            return hreflangTagging;
        } else {
            if (resource != null) {
            	hreflangTagging = getCurrentPageUrl(resource.getParent());
            }
        }
        return externalizeUrl(hreflangTagging);
    }
    
    /**
     * @return the Page Title if googleTitle is blank
     * else return googleTitle
     */
    public String getGoogleTitle(){
        if(StringUtils.isBlank(googleTitle)){
            return this.componentTitle;
        }
        return this.googleTitle;
    }
	

	 /**
     * @return the metaDescription
     */
    public String getMetaDescription(){
            return this.metaDescription;       
    }

    public String getComponentTitle(){
        return this.componentTitle;
    }

	public String getShortDescription(){
		return this.shortDescription;
	}
	
    /**
     * @return the metaDescription if googleDescription is blank
     * else return googleDescription
     */
  	public String getGoogleDescription(){
		if(StringUtils.isBlank(googleDescription)){
			if(StringUtils.isBlank(metaDescription))
				return this.shortDescription;
			else
				return this.metaDescription;
		}		 
		else
			return this.googleDescription;
	}

    /**
     * @return the defaultLanguage if googleLanguage is blank
     * else return googleLanguage
     */
    public String getGoogleLanguage(){
        if(StringUtils.isBlank(googleLanguage)){
            return this.defaultLanguage;
        }
        return this.googleLanguage;
    }

    /**
     * @return the googleAuthor
     */
    public String getGoogleAuthor() {
        if(StringUtils.isBlank(googleAuthor))
            return "RMIT University";
        return this.googleAuthor;
    }

    /**
     * @return the googleCopyright
     */
    public String getGoogleCopyright() {
        if(StringUtils.isBlank(googleCopyright))
            return "RMIT University";
        return this.googleCopyright;
    }

    /**
     * @return the Page Title  if facebookOgTitle is blank
     * else return facebookOgTitle
     */
    public String getFacebookOgTitle() {
        if(StringUtils.isBlank(facebookOgTitle)){
            return this.componentTitle;
        }
        return this.facebookOgTitle;
    }

    /**
     * @return the facebookOgType
     */
    public String getFacebookOgType() {
        if(StringUtils.isBlank(facebookOgType))
            return "article";
        return this.facebookOgType;
    }

    /**
     * @return the facebookOgImage
     */
    public String getFacebookOgImage() {
        if (StringUtils.isBlank(facebookOgImage))
            facebookOgImage = gelImage;
        return externalizeUrl(facebookOgImage);
    }

    /**
     * @return the facebookOgUrl
     */
    public String getFacebookOgUrl() {

        if (!StringUtils.isBlank(facebookOgUrl)) {
            return this.facebookOgUrl;
        } else {
            if (resource != null) {
            	facebookOgUrl = getCurrentPageUrl(resource.getParent());
            }
        }
        return externalizeUrl(facebookOgUrl);
    }    
    
  

    /**
     * @return the metaDescription if facebookOgDescription is blank
     * else return facebookOgDescription
     */
    public String getFacebookOgDescription() {
        if(StringUtils.isBlank(facebookOgDescription)){
            return this.metaDescription;
        }
        return this.facebookOgDescription;
    }

    /**
     * @return the twitterCard
     */
    public String getTwitterCard() {
        if(StringUtils.isBlank(twitterCard))
            return "summary";
        return this.twitterCard;
    }

    /**
     * @return the Page Title if twitterTitle is blank
     * else return twitterTitle
     */
    public String getTwitterTitle() {
        if(StringUtils.isBlank(twitterTitle)){
            return this.componentTitle;
        }
        return this.twitterTitle;
    }

    /**
     * @return the metaDescription if twitterDescription is blank
     * else return twitterDescription
     */
    public String getTwitterDescription() {
        if(StringUtils.isBlank(twitterDescription)){
            return this.metaDescription;
        }
        return this.twitterDescription;
    }

    /**
     * @return the twitterImage
     */
    public String getTwitterImage() {
        if (StringUtils.isBlank(twitterImage))
            twitterImage = gelImage;
        return externalizeUrl(this.twitterImage);
    }

    /**
     * Returns the Current Page URL of the provided Resource
     *
     * @param resource the resource to get the Page URL 
     * @return the current Page Url 
     *      */
    public  String getCurrentPageUrl(Resource resource) {
        if (resource != null) {
            if (resourceResolver != null) {
                return resourceResolver.map(resource.getPath() + ".html");
            }
        }

        return null;
    }

    /**
     * Returns the Current Page Thumbnail URL of the provided Resource
     *
     * @param resource the resource to get the Page URL
     * @return the current Page Thumbnail Url
     *      */
    private   String getCurrentPageThumbnailUrl(Resource resource) {
        if (resource != null && resourceResolver != null)
            return resourceResolver.map(resource.getPath() + ".thumb.960.500.png?ck=" + System.currentTimeMillis());
        return null;
    }

    private String externalizeUrl(String url) {
        if (url != null) {
            Externalizer externalizer = resourceResolver.adaptTo(Externalizer.class);
            return externalizer.publishLink(resourceResolver, url).replace("http://","https://");
        }
        return null;
    }


    /**
     * @return pagecolor specifed in the page propteries
     *
     */
    public String getPageColour() {
        return pageColour;
    }


    public String getBackgroundPosition(){
        if(StringUtils.isNotEmpty(backgroundPosition)){
            return this.backgroundPosition;
        }
        return StringUtils.EMPTY;
    }

    public boolean getTurnOffLeftNav(){return this.turnOffLeftNav;}

    /* function to return background image for landing template */
    public String getBackgroundImage(){
        if(StringUtils.isNotEmpty(backgroundImage)){
            return this.backgroundImage;
        }
        return  StringUtils.EMPTY;
    }
	
	    /* function to return the share string */
    public String getEmailShare(){
        String current = currentPage.getPath();

        emailShare = "I saw this page on the RMIT website and thought you should see it:" + MapUrlHelper.appendHtml(externalizeUrl(current));
        emailShare += "%0D%0A%0D%0ARMIT has not authorised the content of this email, and anything written in this email does not necessarily reflect RMIT's views or opinions.";
        emailShare += "Please note that neither the email address nor name of the sender have been verified.";
        return emailShare;
    }
}
