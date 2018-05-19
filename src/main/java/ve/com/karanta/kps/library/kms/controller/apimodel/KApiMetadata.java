package ve.com.karanta.kps.library.kms.controller.apimodel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Model of page metadata structure.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 * 
 */
@ApiModel(value="Page metadata structure")
public class KApiMetadata {

	/** The number. */
	@ApiModelProperty(
			value="Number of actual page. First is 0",
			required=true,
			example="0")
	private final Integer number;
	
	/** The size. */
	@ApiModelProperty(
			value="Size of page. By default 20 elements per page",
			required=true)
	private final Integer size;
	
	/** The total elements. */
	@ApiModelProperty(
			value="Total of elements on database",
			required=true,
			example="99999")
	private final Integer totalElements ;
	
	/** The total pages. */
	@ApiModelProperty(
			value="Total of available pages",
			required=true,
			example="30")
	private final Integer totalPages ;
	
	/**
	 * Instantiates a new KA metadata.
	 *
	 * @param number the number
	 * @param size the size
	 * @param totalElements the total elements
	 * @param totalPages the total pages
	 */
	public KApiMetadata(Integer number, Integer size, Integer totalElements, Integer totalPages) {
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Gets the size.
	 *
	 * @return the size
	 */
	public Integer getSize() {
		return size;
	}

	/**
	 * Gets the total elements.
	 *
	 * @return the total elements
	 */
	public Integer getTotalElements() {
		return totalElements;
	}

	/**
	 * Gets the total pages.
	 *
	 * @return the total pages
	 */
	public Integer getTotalPages() {
		return totalPages;
	}
}
