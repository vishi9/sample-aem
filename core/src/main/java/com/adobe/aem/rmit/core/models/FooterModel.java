package com.adobe.aem.rmit.core.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import org.apache.commons.lang.ArrayUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

@Model(adaptables = Resource.class)
/**
 * slingModel for returning the multifield property values and other property
 * values from Footer Component.
 * 
 * @author Amulya Bhavana Ommi
 * @since 10-10-2018
 */
public class FooterModel {
	private static final Logger LOG = LoggerFactory.getLogger(FooterModel.class);
	private List<LeftList> leftList = new ArrayList<>();
	private List<SocialList> socialList = new ArrayList<>();
	private List<LegalList> legalList = new ArrayList<>();
	private List<RightList> rightList = new ArrayList<>();
	private List<String> multifieldLeftList;
	private List<String> multifieldSocialList;
	private List<String> multifieldLegalList;
	private List<String> multifieldRightList;

	@Inject
	@Optional
	private String[] leftListValue;

	@Inject
	@Optional
	private String[] rightListValue;

	@Inject
	@Optional
	private String[] socialListValue;

	@Inject
	@Optional
	private String[] legalListValue;

	@Inject
	@Optional
	private String logoUrl;

	/**
	 * Inside init() ,adding the logic to add the multifield property values in
	 * to list object .
	 * 
	 **/

	@PostConstruct
	protected void init() {
		if (ArrayUtils.isNotEmpty(leftListValue)) {
			multifieldLeftList = Arrays.asList(leftListValue);
			multifieldLeftList.forEach(leftValue -> leftList.add(new Gson().fromJson(leftValue, LeftList.class)));
		}
		if (ArrayUtils.isNotEmpty(socialListValue)) {
			multifieldSocialList = Arrays.asList(socialListValue);
			multifieldSocialList.forEach(socialValue -> socialList.add(new Gson().fromJson(socialValue, SocialList.class)));
		}
		if (ArrayUtils.isNotEmpty(legalListValue)) {
			multifieldLegalList = Arrays.asList(legalListValue);
			multifieldLegalList.forEach(legalValue -> legalList.add(new Gson().fromJson(legalValue, LegalList.class)));
		}
		if (ArrayUtils.isNotEmpty(rightListValue)) {
			multifieldRightList = Arrays.asList(rightListValue);
			multifieldRightList.forEach(rightValue -> rightList.add(new Gson().fromJson(rightValue, RightList.class)));
		}
		
	}

	
	/**
	 * return the leftList object.
	 **/
	public List<LeftList> getLeftList() {
		return leftList;
	}

	/**
	 * return the socialList object.
	 **/
	public List<SocialList> getSocialList() {
		return socialList;
	}

	/**
	 * return the legalList object.
	 **/
	public List<LegalList> getLegalList() {
		return legalList;
	}

	/**
	 * return the rightList object.
	 **/
	public List<List<RightList>> getRightList() {
		List<List<RightList>> rightSubList = new ArrayList<List<RightList>>();
		int listSize = rightList.size()-1;
		if(listSize<5){
			rightSubList.add(rightList);
		}
		else{
			for(int i=0;i<=listSize;i=i+5 ){
				rightSubList.add(rightList.subList(i, (i+5)>listSize?(listSize+1):(i+5)));
			}
		}
		return rightSubList;
	}
	
	/**
	 * return the multifieldLeftList object.
	 **/
	public List<String> getMultifieldLeftList() {
		return multifieldLeftList;
	}

	/**
	 * return the multifieldSocialList object.
	 **/
	public List<String> getMultifieldSocialList() {
		return multifieldSocialList;
	}

	/**
	 * return the multifieldLeftList object.
	 **/
	public List<String> getMultifieldRightList() {
		return multifieldRightList;
	}

	/**
	 * return the multifieldSocialList object.
	 **/
	public List<String> getMultifieldLegalList() {
		return multifieldLegalList;
	}

	
	/**
	 * return the logoUrl object.
	 **/
	public String getLogoUrl() {
		return logoUrl;

	}
	
	
	public String getDesignClass() {
		if(rightList.size()>4){
			return "footerCol";
		}
		else if(rightList.size()<=3){
			return "col-md-4";
		}
		return "col-md-3";
	}
}
