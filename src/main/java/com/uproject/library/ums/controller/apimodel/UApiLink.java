package com.uproject.library.ums.controller.apimodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model of link structure.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * 
 */
@ApiModel(value="Link resource structure")
public class UApiLink {

	/** The href. */
	@ApiModelProperty(
			value="Reference link",
			required=true,
			example="*/controller")
	private final String href;
	
	/** The rel. */
	@ApiModelProperty(
			value="Relation link",
			required=true,
			allowableValues= "first,prev,self,next,last")
	private final String rel;
	
	/**
	 * Instantiates a new KA link.
	 *
	 * @param href the href
	 * @param rel the rel
	 */
	public UApiLink(String href, String rel) {
		this.href = href;
		this.rel = rel;
	}
	
	/**
	 * Gets the href.
	 *
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	
	/**
	 * Gets the rel.
	 *
	 * @return the rel
	 */
	public String getRel() {
		return rel;
	}
}
