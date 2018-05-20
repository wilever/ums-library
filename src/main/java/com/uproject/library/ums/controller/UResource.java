package com.uproject.library.ums.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.PagedResources.PageMetadata;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
/**
 * Custom paged resource. 
 * This class allow to convert Page<T> 
 * to a resources with full content, 
 * links and metadata from page.
 * 
 * @param <T> Entity to convert.
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@ApiModel(value="Data resources")
public class UResource<T> {
	/**
	 * Collection of elements to be show.
	 */
	 @ApiModelProperty(value="Data collected from database")
	 private final Collection<T> content;
	 /**
	  * Links with another pages of information.
	  */
	 @ApiModelProperty(value="Link of pages",required=true)
	 private final List<Link> links;
	 /**
	  * Metadata of actual page.
	  */
	 @ApiModelProperty(value= "Data of pages", required=true)
	 private final PageMetadata metadata;
	/**
	 * Generate a {@link UResource}
	 * 
	 * @param content {@link #content}
	 * @param links {@link #links}
	 * @param metadata {@link #metadata}
	 */
	public UResource(Collection<T> content, List<Link> links, PageMetadata metadata) {
		this.content = content;
		this.links = links;
		this.metadata = metadata;
	}
	/**
	 * Get content.
	 * 
	 * @return {@link #content}
	 */
	public Collection<T> getContent() {
		return content;
	}
	/**
	 * Get links.
	 * 
	 * @return {@link #links}
	 */
	public List<Link> getLinks() {
		return links;
	}
	/**
	 * Get metadata.
	 * 
	 * @return {@link #metadata}
	 */
	public PageMetadata getMetadata() {
		return metadata;
	}
	/**
	 * Get a new {@link UResource}
	 * 
	 * @param assembler Assembler with type to convert
	 * @param data Data to add
	 * @return {@link UResource}
	 */
	public static <T> UResource<Resource<T>> getResource(PagedResourcesAssembler<T> assembler, Page<T> data){ 
	PagedResources<Resource<T>> resource = assembler.toResource(data);
		if (resource!=null && !resource.getContent().isEmpty()) {
			return new UResource<>(
					resource.getContent(), 
					resource.getLinks(), 
					resource.getMetadata());
		}else {
			return null;
		}
	}
}
