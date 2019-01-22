package com.adobe.aem.rmit.core.models;

import java.util.List;

/**
 * This pojo Created by Soorya on 15/10/2018.
 */
public class TabValueList {
	private String tabHeading;
	private String tabUrl;
	private String tabHighlight;
	private List<MenuValueList> menuList= null;
	
	public String getTabHighlight() {
		return tabHighlight;
	}

	public void setTabHighlight(String tabHighlight) {
		this.tabHighlight = tabHighlight;
	}
	public String getTabHeading() {
		return tabHeading;
	}

	public void setTabHeading(String tabHeading) {
		this.tabHeading = tabHeading;
	}

	public String getTabUrl() {
		return tabUrl;
	}

	public void setTabUrl(String tabUrl) {
		this.tabUrl = tabUrl;
	}

	public List<MenuValueList> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<MenuValueList> menuList) {
		this.menuList = menuList;
	}



}
