package ve.com.karanta.kps.library.kms.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ve.com.karanta.kps.library.kms.domain.util.ApiMethod;
import ve.com.karanta.kps.library.kms.domain.util.SuccessCode;

/**
 * The Class KSuccessResponse. 
 * It is return when the request return a success response.
 *
 * @param <T> The generic type
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@ApiModel(value="Success response")
public class KSuccessResponse<T> {
	
	/** The code. */
	@ApiModelProperty(
			value="Code of response. It indicate method executed",
			required=true,
			example="AMXXX")
	private final String code;
	
	/** The message. */
	@ApiModelProperty(
			value="Message of response by default",
			required=true,
			example="Data saved successfully!")
	private final String message;
	
	/** The data. 
	 * It can be Primary Key for 
	 * any Entity that was save on database. */
	@ApiModelProperty(
			value="Response data. It can be pimary key of data saved",
			required=true,
			example="id")
	private final T data;

	/**
	 * Instantiates a new k success response.
	 *
	 * @param code The code
	 * @param message The message
	 * @param data The data
	 */
	public KSuccessResponse(String code, String message, T data) {
		this.code = code;
		this.message = message;
		this.data = data;
	}

	/**
	 * Instantiates a new k success response.
	 *
	 * @param code {@link SuccessCode}
	 * @param data The data
	 */
	public KSuccessResponse(SuccessCode code, T data) {
		this.data =data;
		this.code = code.getCode();
		this.message = code.getMessage();
	}
	
	/**
	 * Instantiates a new k success response.
	 *
	 * @param condition The condition, +info: {@link ApiMethod}
	 * @param data The data
	 */
	public KSuccessResponse(ApiMethod condition, T data) {
		this.data =data;
		this.code = condition.getCode();
		this.message = condition.getMessage();
	}
	
	/**
	 * Gets the data.
	 *
	 * @return The data
	 */
	public T getData() {
		return data;
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
}
