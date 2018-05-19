package ve.com.karanta.kps.library.kms.controller.apimodel;

import java.util.Collection;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ve.com.karanta.kps.library.kms.controller.KResource;

/**
 * Model of {@link KResource} for use of swagger documentation.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * 
 */
@ApiModel(value="Data resources structure")
public class KApiResource {

	/**
	 * Collection of elements to be show.
	 */
	 @ApiModelProperty(
			 value="Data collected from database")
	 private final Collection<KApiContent> content;
	 /**
	  * Links with another pages of information.
	  */
	 @ApiModelProperty(
			 value="Link of pages",
			 required=true)
	 private final List<KApiLink> links;
	 /**
	  * Metadata of actual page.
	  */
	 @ApiModelProperty(
			 value= "Data of pages", 
			 required=true)
	 private final KApiMetadata metadata;
	
	 /**
 	 * Instantiates a new k api resource.
 	 *
 	 * @param content the content
 	 * @param links the links
 	 * @param metadata the metadata
 	 */
 	public KApiResource(Collection<KApiContent> content, List<KApiLink> links, KApiMetadata metadata) {
		this.content = content;
		this.links = links;
		this.metadata = metadata;
	 }

	/**
	 * Gets the content.
	 *
	 * @return the content
	 */
	public Collection<KApiContent> getContent() {
		return content;
	}

	/**
	 * Gets the links.
	 *
	 * @return the links
	 */
	public List<KApiLink> getLinks() {
		return links;
	}

	/**
	 * Gets the metadata.
	 *
	 * @return the metadata
	 */
	public KApiMetadata getMetadata() {
		return metadata;
	}
}
