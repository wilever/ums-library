package ve.com.karanta.kps.library.kms.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ve.com.karanta.kps.library.kms.controller.KHandlerController;

/**
 * Response for {@link KHandlerController}
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@ApiModel(value="Response exception from Handler")
public class KHandlerResponse {
	
	/**
	 * Response code.
	 */
	@ApiModelProperty(
			value="Error code",
			required=true,
			example="EXXX")
	private final String code;
	
	/**
	 * Response message.
	 */
	@ApiModelProperty(
			value="Error message by default",
			required=true,
			example="Error: Error message!")
	private final String message;
	
	/**
	 * Generate a new handler response.
	 * 
	 * @param code {@link #code}
	 * @param message {@link #message}
	 */
	public KHandlerResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	/**
	 * Get response code.
	 *  
	 * @return {@link #code}
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * Get response message.
	 * 
	 * @return {@link #message}
	 */
	public String getMessage() {
		return message;
	}
}
