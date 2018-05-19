package com.uproject.library.ums.controller.apimodel;

import java.util.Collection;
import java.util.List;

import com.uproject.library.ums.controller.UResource;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model of {@link UResource} for use of swagger documentation.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * 
 */
@ApiModel(value="Data resources structure")
public class UApiResource {

	/**
	 * Collection of elements to be show.
	 */
	 @ApiModelProperty(
			 value="Data collected from database")
	 private final Collection<UApiContent> content;
	 /**
	  * Links with another pages of information.
	  */
	 @ApiModelProperty(
			 value="Link of pages",
			 required=true)
	 private final List<UApiLink> links;
	 /**
	  * Metadata of actual page.
	  */
	 @ApiModelProperty(
			 value= "Data of pages", 
			 required=true)
	 private final UApiMetadata metadata;
	
	 /**
 	 * Instantiates a new k api resource.
 	 *
 	 * @param content the content
 	 * @param links the links
 	 * @param metadata the metadata
 	 */
 	public UApiResource(Collection<UApiContent> content, List<UApiLink> links, UApiMetadata metadata) {
		this.content = content;
		this.links = links;
		this.metadata = metadata;
	 }

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public Collection<UApiContent> getContent() {
		return content;
	}

	/**
	 * Gets the links.
	 *
	 * @return the links
	 */
	public List<UApiLink> getLinks() {
		return links;
	}

	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public UApiMetadata getMetadata() {
		return metadata;
	}
}
