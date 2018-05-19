package com.uproject.library.ums.domain.util;

/**
 * Methods to be implemented by MicroServices for manage data on database.
 * 
 *  @author Wilever Gomez [gomezw@karanta.com.ve]
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
	RESTORE;
	
	/** The code. */
	private String code;
	
	/** The message. */
	private String message;
	
	static {
		GET.code="AMXX1";
			GET.message="Data get successfully!";
		ADD.code="AMXX1";
			ADD.message="Data added successfully!";
		UPDATE.code="AMXX2";
			UPDATE.message="Data updated successfully!";
		DELETE.code="AMXX3";
			DELETE.message="Data deleted successfully!";
		RESTORE.code="AMXX4";
			RESTORE.message="Data restored successfully!";
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
