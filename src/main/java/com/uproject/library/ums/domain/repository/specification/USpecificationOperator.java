package com.uproject.library.ums.domain.repository.specification;

import org.springframework.data.jpa.domain.Specification;

/**
 * Operation for search data by {@link Specification} on database.
 * IMPORTANT: Operation just search by words, 
 * other symbols are not supported for now.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
public enum USpecificationOperator {
    
    /** The equality, */
    EQUALITY, 
    
    /** The negation. */
    NEGATION, 
    
    /** The greater than. */
    GREATER_THAN, 
    
    /** The less than. */
    LESS_THAN,
    
    /** The starts with. */
    //Implicit operations
    	STARTS_WITH, 
    	
	    /** The ends with. */
	    ENDS_WITH, 
    	
	    /** The contains. */
	    CONTAINS;
 
    /** The Constant SIMPLE_OPERATION_SET. */
    public static final String[] SIMPLE_OPERATION_SET = { ":", "!", ">", "<"};
 
    /** The Constant FILTER. */
    public static final String FILTER="FILTER";
    
    /** The Constant SEARCH. */
    public static final String SEARCH="SEARCH";
    
    /** The Constant SEPARATOR. */
    public static final String SEPARATOR=",";
    
    /**
     * Gets the simple operation.
     *
     * @param input The input
     * @return The simple operation
     */
    public static USpecificationOperator getSimpleOperation(char input) {
        switch (input) {
        case ':':
            return EQUALITY;
        case '!':
            return NEGATION;
        case '>':
            return GREATER_THAN;
        case '<':
            return LESS_THAN;
        default:
            return null;
        }
    }
    
    /** The operator. */
    public char operator;
    
    static {
    	EQUALITY.
    		operator=SIMPLE_OPERATION_SET[0].charAt(0);
    	NEGATION.
    		operator=SIMPLE_OPERATION_SET[1].charAt(0);
    	GREATER_THAN.
    		operator=SIMPLE_OPERATION_SET[2].charAt(0);
    	LESS_THAN.
    		operator=SIMPLE_OPERATION_SET[3].charAt(0);
    }

    /**
     * Gets the operator.
     *
     * @return The operator
     */
    public char getOperator() {
    	return operator;
    }
}
