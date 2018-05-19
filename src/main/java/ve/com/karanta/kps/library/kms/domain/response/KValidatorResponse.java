package ve.com.karanta.kps.library.kms.domain.response;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorMessage;

/**
 * Responses to show for exceptions generate by data validations of Karanta MicroServices.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@ApiModel(value="Response from validator")
public class KValidatorResponse {

	/** The code. */
	@ApiModelProperty(
			value="Error code",
			required=true,
			example="EXXX")
	private final String code;
	
	/** The message. */
	@ApiModelProperty(
			value="Error message by default",
			required=true,
			example="Error: Error message!")
	private final String message;
	
	/** The message list, +info: {@link KValidatorMessage}. */
	@ApiModelProperty(
			value="List of error detected by rule failed",
			required=true)
	private final List<KValidatorMessage> messageList;

	/**
	 * Instantiates a new k validation response.
	 *
	 * @param code the code
	 * @param message the message
	 * @param messageList The message list, +info: {@link KValidatorMessage}
	 */
	public KValidatorResponse(String code, String message, List<KValidatorMessage> messageList) {
		this.code = code;
		this.message = message;
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
