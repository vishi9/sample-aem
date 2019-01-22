package com.adobe.aem.rmit.core.models;

import java.util.List;

/**
 * This pojo Created by cvalluru on 24/10/2018.
 */
public class PrimaryNavMenu {

	private String menuHeading;

	private String menuLink;
    
    private String fragmentPath;

	private List<PrimaryNavSubMenu> subMenuItems = null;

	public String getMenuHeading() {
		return menuHeading;
	}

	public void setMenuHeading(String menuHeading) {
		this.menuHeading = menuHeading;
	}

	public String getMenuLink() {
		return menuLink;
	}

	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}

	public List<PrimaryNavSubMenu> getSubMenuItems() {
		return subMenuItems;
	}

	public void setSubMenuItems(List<PrimaryNavSubMenu> subMenuItems) {
		this.subMenuItems = subMenuItems;
	}
    
    /**
    * @return the expFragmentPath
    */
    public String getFragmentPath() {
        return fragmentPath;
    }

    /**
     * @param expFragmentPath the expFragmentPath to set
     */
    public void setFragmentPath(String fragmentPath) {
        this.fragmentPath = fragmentPath;
    }

}
