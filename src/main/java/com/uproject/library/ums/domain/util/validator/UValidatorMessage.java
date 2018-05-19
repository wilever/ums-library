package com.uproject.library.ums.domain.util.validator;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Messages of exceptions generate by data validations of Karanta MicroServices.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@ApiModel(value="Validation error message")
public class UValidatorMessage {

	/** The code. */
	@ApiModelProperty(
			value="Error code",
			required=true,
			example="XXX")
	private final String code;
	
	/** The message. */
	@ApiModelProperty(
			value="Error message by default",
			required=true,
			example="Error: Error message!")
	private final String message;
	
	/**
	 * Instantiates a new k validator message.
	 *
	 * @param code The code
	 * @param message The message
	 */
	public UValidatorMessage(String code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
}
