package ve.com.karanta.kps.library.kms.domain.exception;

import ve.com.karanta.kps.library.kms.domain.util.ErrorCode;

/**
 * Exceptions generate by Karanta MicroServices.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KException extends Exception{

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
	public KException(ErrorCode code) {
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
	public KException(String code, String message) {
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
