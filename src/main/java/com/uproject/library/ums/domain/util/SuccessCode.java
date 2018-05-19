package com.uproject.library.ums.domain.util;

import com.uproject.library.ums.domain.response.USuccessResponse;

/**
 * The Enum SuccessCode for {@link USuccessResponse}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public enum SuccessCode {

	/** The default code. */
	DEFAULT,
	NO_CONTENT;
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	static {
		DEFAULT.code="SXX1";
			DEFAULT.message="Success!";
		NO_CONTENT.code="SXX2";
			NO_CONTENT.message="Data got successfully but there was no content!";
	}
	
	/**
	 * Gets the message.
	 *
	 * @return The message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Gets the code.
	 *
	 * @return The code
	 */
	public String getCode() {
		return code;
	}
}
