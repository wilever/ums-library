package com.uproject.library.ums.domain.exception;

import java.util.List;

import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.validator.UValidatorMessage;

/**
 * Exceptions generate by data validations of UProject MicroServices.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
public class UValidatorException extends Exception{
	
	/** Default Serialization. */
	private static final long serialVersionUID = 1L;

	/** The code. */
	private final String code;
	
	/** The message. */
	private final String message;
	
	/** The message list, +info: {@link UValidatorMessage}. */
	private final List<UValidatorMessage> messageList;
	
	/**
	 * Instantiates a new k validation exception.
	 *
	 * @param code The code, +info: {@link ErrorCode}
	 * @param messageList The message list, +info: {@link UValidatorMessage}
	 */
	public UValidatorException(ErrorCode code, List<UValidatorMessage> messageList) {
		this.code = code.getCode();
		this.message  = code.getMessage();
		this.messageList = messageList;
	}
	
	/**
	 * Instantiates a new k validation exception.
	 *
	 * @param code The code
	 * @param message The message
	 * @param messageList The message list, +info: {@link UValidatorMessage}
	 */
	public UValidatorException(String code, String message, List<UValidatorMessage> messageList) {
		this.code = code;
		this.message  = message;
		this.messageList = messageList;
	}

	/**
	 * Gets the code.
	 *
	 * @return The code
	 */
	public String getCode() {
		return code;
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
	 * Gets the message list.
	 *
	 * @return The message list
	 */
	public List<UValidatorMessage> getMessageList() {
		return messageList;
	}
}
