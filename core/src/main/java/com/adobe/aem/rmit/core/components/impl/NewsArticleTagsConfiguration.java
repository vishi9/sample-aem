package com.adobe.aem.rmit.core.components.impl;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tag Service Configuration", description = "Define Tag path")

/**
 * This class is used to create the configuaration for setting the path for
 * category,Type,Topic,Page tags group.
 * 
 * @author Soorya Sreedharan
 * @since 11-09-2018
 */
public @interface NewsArticleTagsConfiguration {

	@AttributeDefinition(name = "Categories", description = "News Category tag Path")
	String getCategory() default "/content/cq:tags/rmit/content_type/news/news_category"; 

	@AttributeDefinition(name = "Topics", description = "News Topic tag Path")
	String getTopic() default "/content/cq:tags/rmit/content_type/news/news-topic"; 

	@AttributeDefinition(name = "Page", description = "News Page tag Path")
	String getPage() default "/content/cq:tags/rmit/topic"; 

	@AttributeDefinition(name = "Type", description = "News Type tag Path")
	String getType() default "/content/cq:tags/rmit/content_type/news/central_news"; 
	
	

}