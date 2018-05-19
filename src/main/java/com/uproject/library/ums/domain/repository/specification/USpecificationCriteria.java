package com.uproject.library.ums.domain.repository.specification;

/**
 * The Class SearchCriteria.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
public class USpecificationCriteria {
	
	/** The key. */
	private String key;
    
    /** The operation. */
    private USpecificationOperator operation;
    
    /** The value. */
    private Object value;
    
	/**
	 * Instantiates a new search criteria.
	 *
	 * @param key The key
	 * @param operation The operation
	 * @param value The value
	 */
	public USpecificationCriteria(String key, USpecificationOperator operation, Object value) {
		this.key = key;
		this.operation = operation;
		this.value = value;
	}
	
	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}
	
	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the operation.
	 *
	 * @return the operation
	 */
	public USpecificationOperator getOperation() {
		return operation;
	}
	
	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	public void setOperation(USpecificationOperator operation) {
		this.operation = operation;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	
	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Object value) {
		this.value = value;
	}
}
