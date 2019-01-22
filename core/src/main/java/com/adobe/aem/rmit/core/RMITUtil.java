package com.adobe.aem.rmit.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;

public class RMITUtil extends WCMUsePojo  {
	private static final Logger log = LoggerFactory.getLogger(RMITUtil.class);

	@Override
	public void activate() throws Exception {
		
	}
	
	
	private static final String damPath = "/content/dam";
    private static final String hyperText = "http";
    private static final String securedHyperText = "http";
    private static final String dotHtml = ".html";
    private static final String contentPath = "/content";
    
    
    public String getUrl(String path) {
        if (path == null || path.equals("")) {
            return "";
        }
        else if (path.startsWith(securedHyperText) || path.startsWith(hyperText)
                || path.startsWith(damPath)) {
            return path;
        }
        else if (path.startsWith(contentPath)
                && path.indexOf(dotHtml) == -1) {
           return path + dotHtml;
        }
        else {
            return path;
        }
    }
    public String getUrl(String path,String runMode) {
        if (path == null || path.equals("")) {
            return "";
        }
        else if (path.startsWith(securedHyperText) || path.startsWith(hyperText)
                || path.startsWith(damPath)) {
            return path;
        }
        else if (path.startsWith(contentPath)
                && path.indexOf(dotHtml) == -1) {
            if(runMode.equals("author")){
        	return path + dotHtml;
            }
            else
            return path ;
        }
        else if (path.startsWith(contentPath)
                && path.indexOf(dotHtml) != -1) {
            if(!runMode.equals("author")){
            	if (path.indexOf(dotHtml) != -1){
            		path = path.replaceFirst(dotHtml, "");
            	}
        	return path;
            }
            else{
            return path ;
            }
        }
        else {
            return path;
        }
    }

}