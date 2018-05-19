package ve.com.karanta.kps.library.kms.domain.repository.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

import ve.com.karanta.kps.library.kms.domain.service.ValidatorTest;

/**
 * Data for be use {@link ValidatorTest}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@Table(name="data")
public class Data {
	
	/** The id. */
	@Id
	@Column(name="id")
	private String id;
	
	/** The message. */
	@Column(name="message")
	private String message;
	
	/** The value. */
	@Column(name="value")
	private String value;
	
	/**
	 * Instantiates a new data.
	 */
	public Data() {
	}

	/**
	 * Instantiates a new data.
	 *
	 * @param id The id
	 * @param message The message
	 * @param value The value
	 */
	public Data(String id, String message, String value) {
		this.id = id;
		this.message = message;
		this.value = value;
	}

	/**
	 * Gets the id.
	 *
	 * @return The id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id The new id
	 */
	public void setId(String id) {
		this.id = id;
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
	 * Sets the message.
	 *
	 * @param message The new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the value.
	 *
	 * @return The value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value The new value
	 */
	public void setValue(String value) {
		this.value = value;
	}
}
