package com.uproject.library.ums.domain.util;

/**
 * Methods to be implemented by MicroServices for manage data on database.
 * 
 *  @author Wilever Gomez [wilevergomez@gmail.com]
 */
public enum ApiMethod {
	
	/** The GET method. */
	GET,
	
	/** The POST method. */
	ADD,
	
	/** The PUT method for update data. */
	UPDATE,
	
	/** The DELETE method. */
	DELETE,
	
	/** The PUT method for restore data. */
	RESTORE,
	
	/** The Logical DELETE method. */
	DELETE_LOGICAL;
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	static {
		GET.code="AM1";
			GET.message="Data get successfully!";
		ADD.code="AM1";
			ADD.message="Data added successfully!";
		UPDATE.code="AM2";
			UPDATE.message="Data updated successfully!";
		DELETE.code="AM3";
			DELETE.message="Data deleted successfully!";
		RESTORE.code="AM4";
			RESTORE.message="Data restored successfully!";
		DELETE_LOGICAL.code="AM4";
			DELETE_LOGICAL.message="Data logical deleted successfully!";
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
