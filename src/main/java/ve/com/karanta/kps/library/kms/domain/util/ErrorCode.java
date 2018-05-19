package ve.com.karanta.kps.library.kms.domain.util;

/**
 * Error code for Karanta MicroServices
 * 
 * @author [gomezw@karanta.com.ve]
 *
 */
public enum ErrorCode {
	
	/**
	 * Request had at least one error.
	 */
	BAD_REQUEST,
	/**
	 * Table of entity was not found on database.
	 */
	NOT_TABLE,
	
	/**
	 * Rule was not found on database.
	 */
	NOT_RULE,
	
	/**
	 * Value processed was not expected.
	 */
	NOT_EXPECTED,
	
	/**
	 * Operator unknown.
	 */
	OPERATOR_UNKNOWN,
	
	/**
	 * Data processed was null.
	 */
	DATA_NULL,
	
	/**
	 * Data processed was not active.
	 */
	DATA_NOT_ACTIVE,
	
	/**
	 * Data processed was active.
	 */
	DATA_ACTIVE,
	
	/**
	 * Data was not save.
	 */
	DATA_NOT_SAVE,
	
	/**
	 * Multiple errors detected.
	 */
	ERROR_MULTIPLE,
	
	/**
	 * PK was not found on database.
	 */
	PK_NOT_AVAILABLE,
	
	/**
	 * Method was not available.
	 */
	METHOD_NOT_AVAILABLE,
	/**
	 * Authentication is needed to get requested response.
	 */
	UNAUTHORIZED,
	/**
	 * Client does not have access rights to the content so server is rejecting to give proper response.
	 */
	FORBIDDEN,
	/**
	 * Server can not find requested resource.
	 */
	NOT_FOUND;
	
	/**
	 * Code of error.
	 */
	private String code;
	
	/**
	 * Message by default of error.
	 */
	private String message;
	
	/**
	 * Assign codes and messages for errors.
	 */
	static {
		BAD_REQUEST.code="EXXX";
			BAD_REQUEST.message="Error: Request had at least one error!";
		NOT_TABLE.code="EXX0";
			NOT_TABLE.message="Error: Table of entity was not found on database!";
		NOT_RULE.code="EXX1";
			NOT_RULE.message="Error: Rule was not found on database!";
		NOT_EXPECTED.code="EXX2";
			NOT_EXPECTED.message="Error: Value processed was not expected!";
		OPERATOR_UNKNOWN.code="EXX3";
			OPERATOR_UNKNOWN.message="Error: Operator unknown";
		DATA_NULL.code="EXX4";
			DATA_NULL.message="Error: Data processed was null!";
		DATA_NOT_ACTIVE.code="EXX5";
			DATA_NOT_ACTIVE.message="Error: Data processed was not active!";
		DATA_ACTIVE.code="EXX6";
			DATA_ACTIVE.message="Error: Data processed was active!";
		DATA_NOT_SAVE.code="EXX7";
			DATA_NOT_SAVE.message="Error: Data was not save!";
		ERROR_MULTIPLE.code= "EXX8";
			ERROR_MULTIPLE.message= "Error: Multiple errors detected!";
		PK_NOT_AVAILABLE.code= "EXX9";
			PK_NOT_AVAILABLE.message= "Error: Primary key was not found on database";
		METHOD_NOT_AVAILABLE.code="EX10";
			METHOD_NOT_AVAILABLE.message="Error: Method was not available";
		UNAUTHORIZED.code="EX11";
			UNAUTHORIZED.message="Error: Authentication is needed to get requested response!";
		FORBIDDEN.code="EX12";
			FORBIDDEN.message="Error: Client does not have access rights to the content so server is rejecting to give proper response!";
		NOT_FOUND.code="EX13";
			NOT_FOUND.message="Error: Server can not find requested resource!";
	}
	
	/**
	 * Get message of error.
	 * 
	 * @return {@link #message}
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Get code of error.
	 * 
	 * @return {@link #code}
	 */
	public String getCode() {
		return code;
	}
}
