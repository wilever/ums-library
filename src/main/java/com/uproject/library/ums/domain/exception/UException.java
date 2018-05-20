package com.uproject.library.ums.domain.exception;

import com.uproject.library.ums.domain.util.ErrorCode;

/**
 * Exceptions generate by UProject MicroServices.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
public class UException extends Exception{

	/** Default Serialization. */
	private static final long serialVersionUID = 1L;
	
	/** Error code. */
	private final ErrorCode errorCode;

	/** The code for exception. */
	private final String code;
	
	/** The message by default for exception. */
	private final String message;
	
	/**
	 * Instantiates a new k exception.
	 *
	 * @param code {@link ErrorCode}
	 */
	public UException(ErrorCode code) {
		this.errorCode=code;
		this.code = code.getCode();
		this.message = code.getMessage();
	}
	
	/**
	 * Instantiates a new k exception.
	 *
	 * @param code The code, +info: {@link #code}
	 * @param message The message, +info: {@link #message}
	 */
	public UException(String code, String message) {
		this.errorCode= null;
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Gets error code
	 * @return {@link #errorCode}
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * Gets the code.
	 *
	 * @return {@link #code}
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Get message.
	 * 
	 * @return {@link #message}
	 */
	public String getMessage() {
		return message;
	}
}
