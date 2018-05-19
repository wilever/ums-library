package ve.com.karanta.kps.library.kms.domain.exception;

import java.util.List;

import ve.com.karanta.kps.library.kms.domain.util.ErrorCode;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorMessage;

/**
 * Exceptions generate by data validations of Karanta MicroServices.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KValidatorException extends Exception{
	
	/** Default Serialization. */
	private static final long serialVersionUID = 1L;

	/** The code. */
	private final String code;
	
	/** The message. */
	private final String message;
	
	/** The message list, +info: {@link KValidatorMessage}. */
	private final List<KValidatorMessage> messageList;
	
	/**
	 * Instantiates a new k validation exception.
	 *
	 * @param code The code, +info: {@link ErrorCode}
	 * @param messageList The message list, +info: {@link KValidatorMessage}
	 */
	public KValidatorException(ErrorCode code, List<KValidatorMessage> messageList) {
		this.code = code.getCode();
		this.message  = code.getMessage();
		this.messageList = messageList;
	}
	
	/**
	 * Instantiates a new k validation exception.
	 *
	 * @param code The code
	 * @param message The message
	 * @param messageList The message list, +info: {@link KValidatorMessage}
	 */
	public KValidatorException(String code, String message, List<KValidatorMessage> messageList) {
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
	public List<KValidatorMessage> getMessageList() {
		return messageList;
	}
}
