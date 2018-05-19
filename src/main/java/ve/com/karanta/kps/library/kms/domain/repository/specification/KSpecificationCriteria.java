package ve.com.karanta.kps.library.kms.domain.repository.specification;

/**
 * The Class SearchCriteria.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KSpecificationCriteria {
	
	/** The key. */
	private String key;
    
    /** The operation. */
    private KSpecificationOperator operation;
    
    /** The value. */
    private Object value;
    
	/**
	 * Instantiates a new search criteria.
	 *
	 * @param key The key
	 * @param operation The operation
	 * @param value The value
	 */
	public KSpecificationCriteria(String key, KSpecificationOperator operation, Object value) {
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
	public KSpecificationOperator getOperation() {
		return operation;
	}
	
	/**
	 * Sets the operation.
	 *
	 * @param operation the new operation
	 */
	public void setOperation(KSpecificationOperator operation) {
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
