package com.uproject.library.ums.domain.response;

import com.uproject.library.ums.controller.UHandlerController;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Response for {@link UHandlerController}
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@ApiModel(value="Response exception from Handler")
public class UHandlerResponse {
	
	/**
	 * Response code.
	 */
	@ApiModelProperty(
			value="Error code",
			required=true,
			example="EXXX")
	private final String code;
	
	/**
	 * Response message.
	 */
	@ApiModelProperty(
			value="Error message by default",
			required=true,
			example="Error: Error message!")
	private final String message;
	
	/**
	 * Generate a new handler response.
	 * 
	 * @param code {@link #code}
	 * @param message {@link #message}
	 */
	public UHandlerResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Get response code.
	 *  
	 * @return {@link #code}
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Get response message.
	 * 
	 * @return {@link #message}
	 */
	public String getMessage() {
		return message;
	}
}
