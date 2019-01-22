package com.adobe.aem.rmit.core.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.jcr.RepositoryException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.PersistenceException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;
import org.apache.sling.models.annotations.Default;
import org.apache.sling.models.annotations.Exporter;
import org.apache.sling.models.annotations.ExporterOption;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.InjectionStrategy;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.aem.rmit.core.components.RmitTags;
import com.adobe.granite.asset.api.Asset;
import com.adobe.aem.rmit.core.components.GridList;
import com.adobe.aem.rmit.core.components.NewsArticleTags;
import com.adobe.aem.rmit.core.components.GetTagUrl;
import com.day.cq.commons.RangeIterator;
import com.day.cq.search.Predicate;
import com.day.cq.search.SimpleSearch;
import com.day.cq.search.result.Hit;
import com.day.cq.search.result.SearchResult;
import com.day.cq.tagging.TagManager;
import com.day.cq.wcm.api.NameConstants;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.day.cq.wcm.api.components.Component;
import com.day.cq.wcm.api.designer.Style;

/**
 * This class returns list of grid object and json using sling model exporter
 * 
 * @author cvalluru
 *
 */
@Model(adaptables = SlingHttpServletRequest.class, resourceType = { "rmit/components/content/gridlist" })
@Exporter(name = "jackson", extensions = "json", options = {
		@ExporterOption(name = "SerializationFeature.WRITE_DATES_AS_TIMESTAMPS", value = "true") })

public class GridListModel {

	private static final Logger LOGGER = LoggerFactory.getLogger(GridListModel.class);

	private static final int LIMIT_DEFAULT = 100;
	
	private static final int PN_DEPTH_DEFAULT = 1;
	
	private static final String TAGS_MATCH_ANY_VALUE = "any";
	
	public static final String PN_SEARCH_IN = "searchIn";

	public static final String PN_PARENT_PAGE = "parentPage";

	public static final String PN_TAGS_PARENT_PAGE = "tagsSearchRoot";

	public static final String PN_TAGS = "tags";

	public static final String PN_TAGS_MATCH = "tagsMatch";

	public static final String MORE_OPTIONS = "showDescription";

	public static final String SHOW_MORE_LABEL = "showMoreLabel";

	public static final String SHOW_MORE_LINK = "showMoreLink";

	public static final String PN_SORT_ORDER = "sortOrder";

	public static final String PN_ORDER_BY = "orderBy";

	public static final String SHOW_ARTICLE_DATE = "articleDate";

	public static final String SHOW_CATEGORY_TAG = "categoryTag";

	public static final String PN_SOURCE = "listFrom";

	public static final String PN_PAGES = "pages";

	public static final String DEFAULT_IMAGE = "defaultImage";

	private String dateFormat = "dd MMM YYYY";
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

	@ScriptVariable
	private ValueMap properties;

	@ScriptVariable
	private Style currentStyle;

	@ScriptVariable
	private Page currentPage;

	@SlingObject
	private ResourceResolver resourceResolver;

	@SlingObject
	private Resource resource;

	@Self
	private SlingHttpServletRequest request;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(intValues = LIMIT_DEFAULT)
	private int limit;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(intValues = PN_DEPTH_DEFAULT)
	private int childDepth;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(values = StringUtils.EMPTY)
	private String query;

	@ValueMapValue(injectionStrategy = InjectionStrategy.OPTIONAL)
	@Default(intValues = 0)
	private int maxItems;

	@Inject
	private Component component;

	@Inject
	@Optional
	@Via("resource")
	public String componentID;

	@Inject
	NewsArticleTags newsArticleTags;

	@Inject
	GetTagUrl getTagUrl;
	
	private String startIn;
	
	private SortOrder sortOrder;
	
	private OrderBy orderBy;
	
	private String showArticleDate;

	private String showCategoryTag;
	
	private String defaultImage;

	private PageManager pageManager;
	
	private ArrayList<GridList> gridList;

	private String templateValue;

	protected java.util.List<Page> listItems;

	private String pageLimit;

	private String componentId;

	private boolean hideLoadMore = false;

	@PostConstruct
	private void initModel() {
		pageManager = resourceResolver.adaptTo(PageManager.class);
		readProperties();

	}

	/**
	 * Read dialog properties
	 */
	private void readProperties() {
		try {
			LOGGER.info("Resource path is :" + resource.getPath().toString());
			// to generate dynamic component id for differentiate components in
			// the page
			componentId = component.getName() + "-" + (new Date()).getTime() + System.nanoTime();
			ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
			if (properties != null) {
				properties.put("componentID", componentId);
				resourceResolver.commit();
			}
		} catch (PersistenceException persistenceException) {
			LOGGER.error("PersistenceException in readProperties method " + persistenceException);
		}

		// read edit config properties
		startIn = properties.get(PN_SEARCH_IN, currentPage.getPath());
		sortOrder = SortOrder.fromString(properties.get(PN_SORT_ORDER, SortOrder.ASC.value));
		orderBy = OrderBy.fromString(properties.get(PN_ORDER_BY, StringUtils.EMPTY));

		showArticleDate = properties.get(SHOW_ARTICLE_DATE, "true");

		showCategoryTag = properties.get(SHOW_CATEGORY_TAG, "true");
		defaultImage = properties.get(DEFAULT_IMAGE, String.class);

		templateValue = properties.get("newsTemplate", "/conf/rmit/settings/wcm/templates/news-article");
		String listFromValue = properties.get(PN_SOURCE, currentStyle.get(PN_SOURCE, StringUtils.EMPTY));
		populateListItems(listFromValue);

	}

	/**
	 * @return the gridList
	 */
	public ArrayList<GridList> getGridList() {
		return gridList;
	}

	/**
	 * This method is used to call respective method based on dialog option
	 * 
	 * @param listType
	 * @return List of GridList
	 */
	private ArrayList<GridList> populateListItems(String listType) {
		switch (listType) {
		case "static":
			listItems = populateStaticListItems();
			break;
		case "children":
			listItems = populateChildListItems();
			break;
		case "tags":
			listItems = populateTagListItems();
			break;
		case "search":
			listItems = populateSearchListItems();
			break;
		default:
			listItems = new ArrayList<>();
			break;
		}
		listItems = sortListItems();
		listItems = setMaxItems();
		gridList = retrievePages(listItems);
		return gridList;

	}

	/**
	 * This method is used to prepare json from the results
	 * 
	 * @param listItems
	 * @return List of GridList
	 */
	private ArrayList<GridList> retrievePages(java.util.List<Page> listItems) {
		Date newsDate;
		String imgAltText = null;
		ArrayList<GridList> gridList = new ArrayList<GridList>();

		for (Page currentPage : listItems) {
			GridList gridObj = new GridList();
			if (getNewsTemplate(currentPage)) {
				newsArticleTags.doInit(currentPage);
				java.util.List<RmitTags> categoryList = newsArticleTags.getCategoryList();
				ValueMap properties = currentPage.getProperties();
				gridObj.setShortDescription(checkNullValue(properties.get("jcr:description", String.class)));
				gridObj.setImage(properties.get("gelImage", String.class) != null
						? properties.get("gelImage", String.class) : defaultImage);
				gridObj.setTitle(checkNullValue(properties.get("jcr:title", String.class)));
				gridObj.setPath(currentPage.getPath().toString() + ".html");
				imgAltText = properties.get("gelImage", String.class) != null
						? properties.get("gelImage", String.class) : defaultImage;
				if(StringUtils.isNotBlank(imgAltText)){
					gridObj.setImageAltText(getAltText(imgAltText,resourceResolver));
				}else{
					gridObj.setImageAltText("");
				}
				
				
				if (StringUtils.equalsIgnoreCase(showArticleDate, "true")) {
					newsDate = properties.get("newsDate", Date.class);
					gridObj.setArticleDate(simpleDateFormat.format(newsDate));
				} else {
					gridObj.setArticleDate("");
				}
				if (StringUtils.equalsIgnoreCase(showCategoryTag, "true")) {
					if (categoryList.size() > 0) {
						gridObj.setCategoryTag(categoryList.get(0).getTags());
						gridObj.setCategoryTagUrl(categoryList.get(0).getTagsUrl());
					}
				} else {
					gridObj.setCategoryTag("");
					gridObj.setCategoryTagUrl("");
				}

				gridList.add(gridObj);
			}
		}

		return gridList;

	}

	public  String getAltText(String imagePath, ResourceResolver resourceResolver) {
		String imageAltText = null;
		if (StringUtils.isNotBlank(imagePath)) {
			
			Resource imageResource = resourceResolver.getResource(imagePath + "/jcr:content/metadata");
			if (imageResource != null) {
				ValueMap imageMap = imageResource.adaptTo(ValueMap.class);
				if (imageMap != null) {
					imageAltText = imageMap.get("dc:alttag", String.class) != null
							? imageMap.get("dc:alttag", String.class)
							: "";
					if (StringUtils.isBlank(imageAltText)) {
						getImageTitle(imagePath, resourceResolver);
					}
				}
			}
		}
		return imageAltText;
	}
	
	public String getImageTitle(String imagePath, ResourceResolver resourceResolver) {
		String imageName = null;
		Resource jcrResource = resourceResolver.getResource(imagePath);
		if (jcrResource != null) {
			Asset asset = jcrResource.adaptTo(Asset.class);
			if (asset != null)
				imageName = asset.getName();
		}
		return imageName;
	}
	/**
	 * This method is used to check null and empty value and to trim non empty
	 * values
	 * 
	 * @param value
	 * @return value
	 */
	private String checkNullValue(String value) {
		if (StringUtils.isNotBlank(value)) {
			return value.trim();
		}
		return StringUtils.EMPTY;

	}

	/**
	 * This method is used to check whether page is News Article Template or not
	 * 
	 * @param page
	 * @return newsBoolean
	 */
	private boolean getNewsTemplate(Page page) {
		boolean newsBoolean = false;
		ValueMap properties = page.getProperties();
		String templatePath = properties.get("cq:template", String.class);

		if (StringUtils.equalsIgnoreCase(templatePath, templateValue)) {
			newsBoolean = true;
		}
		return newsBoolean;

	}

	/**
	 * This method is used to read authored pages and add it to list if it is
	 * news template
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> populateStaticListItems() {
		listItems = new ArrayList<>();
		String[] pagesPaths = properties.get(PN_PAGES, new String[0]);
		for (String path : pagesPaths) {
			Page page = pageManager.getContainingPage(path);
			if (page != null) {
				if (getNewsTemplate(page))
					listItems.add(page);
			}
		}
		return listItems;
	}

	/**
	 * This method is used to read all the child pages under the rootpath add it
	 * to list if it is news template
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> populateChildListItems() {
		listItems = new ArrayList<>();
		Page rootPage = getRootPage(PN_PARENT_PAGE);
		if (rootPage != null) {
			listItems = collectChildren(rootPage.getDepth(), rootPage);
		}
		return listItems;
	}

	/**
	 * This method is used to read all the child pages and depth of the pages
	 * 
	 * @param startLevel
	 * @param parent
	 * @return List of Pages
	 */
	private java.util.List<Page> collectChildren(int startLevel, Page parent) {
		Iterator<Page> childIterator = parent.listChildren();
		while (childIterator.hasNext()) {
			Page child = childIterator.next();
			if (getNewsTemplate(child)) {
				if (child != null) {
					listItems.add(child);
					if (child.getDepth() - startLevel < childDepth) {
						collectChildren(startLevel, child);
					}
				}
			}

		}
		return listItems;
	}

	/**
	 * This method is used to get the matched tag pages
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> populateTagListItems() {
		listItems = new ArrayList<>();
		String[] tags = properties.get(PN_TAGS, new String[0]);
		boolean matchAny = properties.get(PN_TAGS_MATCH, TAGS_MATCH_ANY_VALUE).equals(TAGS_MATCH_ANY_VALUE);
		if (ArrayUtils.isNotEmpty(tags)) {
			Page rootPage = getRootPage(PN_TAGS_PARENT_PAGE);
			if (rootPage != null) {
				TagManager tagManager = resourceResolver.adaptTo(TagManager.class);
				if (tagManager != null) {
					RangeIterator<Resource> resourceRangeIterator = tagManager.find(rootPage.getPath(), tags, matchAny);
					if (resourceRangeIterator != null) {
						while (resourceRangeIterator.hasNext()) {
							Page containingPage = pageManager.getContainingPage(resourceRangeIterator.next());
							if (containingPage != null) {
								if (getNewsTemplate(containingPage))
									listItems.add(containingPage);
							}
						}
					}
				}
			}
		}
		return listItems;
	}

	/**
	 * This method is used to get the pages matched with query string
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> populateSearchListItems() {
		listItems = new ArrayList<>();
		if (!StringUtils.isBlank(query)) {
			SimpleSearch search = resource.adaptTo(SimpleSearch.class);
			if (search != null) {
				search.setQuery(query);
				search.setSearchIn(startIn);
				search.addPredicate(new Predicate("type", "type").set("type", NameConstants.NT_PAGE));
				search.setHitsPerPage(limit);
				try {
					collectSearchResults(search.getResult());
				} catch (RepositoryException repositoryException) {
					LOGGER.error("Unable to retrieve search results for query.", repositoryException);
				}
			}
		}
		return listItems;
	}

	/**
	 * This method is used to check pages are News article Template
	 * 
	 * @param result
	 * @return List of Pages
	 */
	private java.util.List<Page> collectSearchResults(SearchResult result) throws RepositoryException {
		for (Hit hit : result.getHits()) {
			Page containingPage = pageManager.getContainingPage(hit.getResource());
			if (containingPage != null) {
				if (getNewsTemplate(containingPage)){
					listItems.add(containingPage);
				}
					
			}
		}
		return listItems;
	}

	/**
	 * This method is used to sort the pages
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> sortListItems() {
		if (orderBy != null) {
			listItems.sort(new ListSort(orderBy, sortOrder));
		}
		return listItems;
	}

	/**
	 * This method is used to get configured pages(Max limit) if page has query
	 * parameter then read it and send json as requested from query parameter
	 * 
	 * @return List of Pages
	 */
	private java.util.List<Page> setMaxItems() {
		if (StringUtils.isNotBlank(request.getParameter("limit"))) {
			java.util.List<Page> tmpListItems = new ArrayList<>();
			pageLimit = request.getParameter("limit").toString();
			int pageCount = Integer.parseInt(pageLimit);
			if (pageCount > 0 && maxItems > 0) {
				int initialCount = (maxItems * pageCount);
				int finalCount = (maxItems * pageCount) + maxItems - 1;

				for (int i = initialCount; i <= finalCount; i++) {
					if (i > (listItems.size() - 1)) {
						hideLoadMore = true;
						break;

					}else{
						if(finalCount==(listItems.size()-1)){
							hideLoadMore = true;
						}
					}
					tmpListItems.add(listItems.get(i));
				}
				listItems = tmpListItems;
			}
		} else {
			if (maxItems != 0) {
				java.util.List<Page> tmpListItems = new ArrayList<>();
				for (Page item : listItems) {
					if (tmpListItems.size() < maxItems) {
						tmpListItems.add(item);
					} else {
						break;
					}
				}
				// To hide load more button if no. of results is less than initial load limit
				if(listItems.size() <= maxItems){
					hideLoadMore = true;
				}
				listItems = tmpListItems;
			}
		}

		return listItems;
	}

	/**
	 * This method is used to get the root page
	 * 
	 * @param fieldName
	 * @return page
	 */
	private Page getRootPage(String fieldName) {
		String parentPath = properties.get(fieldName, currentPage.getPath());
		return pageManager.getContainingPage(resourceResolver.getResource(parentPath));
	}

	/**
	 * This class is used for page options
	 *
	 */
	protected enum Source {
		CHILDREN("children"), STATIC("static"), SEARCH("search"), TAGS("tags"), EMPTY(StringUtils.EMPTY);

		private String value;

		Source(String value) {
			this.value = value;
		}

		public static Source fromString(String value) {
			for (Source s : values()) {
				if (StringUtils.equals(value, s.value)) {
					return s;
				}
			}
			return null;
		}
	}

	/**
	 * This class is used to sort the list of pages by ASC or DESC
	 *
	 */
	private enum SortOrder {
		ASC("asc"), DESC("desc");

		private String value;

		SortOrder(String value) {
			this.value = value;
		}

		public static SortOrder fromString(String value) {
			for (SortOrder s : values()) {
				if (StringUtils.equals(value, s.value)) {
					return s;
				}
			}
			return ASC;
		}
	}

	/**
	 * This class is used to mention the sort order
	 *
	 */
	private enum OrderBy {
		TITLE("title"), MODIFIED("modified"), NEWS("articleDate");

		private String value;

		OrderBy(String value) {
			this.value = value;
		}

		public static OrderBy fromString(String value) {
			for (OrderBy s : values()) {
				if (StringUtils.equals(value, s.value)) {
					return s;
				}
			}
			return null;
		}
	}

	/**
	 * This class is used to sort the list of pages based on Modified Date,
	 * Title, News Date
	 *
	 */
	private static class ListSort implements Comparator<Page>, Serializable {

		private static final long serialVersionUID = 204096578105548876L;
		private SortOrder sortOrder;
		private OrderBy orderBy;

		ListSort(OrderBy orderBy, SortOrder sortOrder) {
			this.orderBy = orderBy;
			this.sortOrder = sortOrder;
		}

		@Override
		public int compare(Page item1, Page item2) {
			int i = 0;
			if (orderBy == OrderBy.MODIFIED) {
				// getLastModified may return null, define null to be after
				// nonnull values
				i = ObjectUtils.compare(item1.getLastModified(), item2.getLastModified(), true);
			} else if (orderBy == OrderBy.TITLE) {
				// getTitle may return null, define null to be greater than
				// nonnull values
				i = ObjectUtils.compare(item1.getTitle(), item2.getTitle(), true);
			} else if (orderBy == OrderBy.NEWS) {
				// getTitle may return null, define null to be greater than
				// nonnull values
				ValueMap firstPage = item1.getProperties();
				Date firstPageDate = firstPage.get("newsDate", Date.class);
				ValueMap secondPage = item2.getProperties();
				Date secondPageDate = secondPage.get("newsDate", Date.class);
				i = ObjectUtils.compare(firstPageDate, secondPageDate, true);
			}

			if (sortOrder == SortOrder.DESC) {
				i = i * -1;
			}
			return i;
		}
	}

	/**
	 * This method is used to generate dynamic component id to each component
	 * 
	 * @return componentId
	 */
	public String getComponentId() {
		return componentId;
	}

	public boolean getHideLoadMore() {
		return hideLoadMore;
	}
}