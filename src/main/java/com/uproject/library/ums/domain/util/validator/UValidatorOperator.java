package com.uproject.library.ums.domain.util.validator;

import com.uproject.library.ums.service.UValidator;

/**
 * Operator to be validate by {@link UValidator}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public enum UValidatorOperator {
	
	/** The unknown. */
	UNKNOWN,
	
	/** The equal. */
	EQUAL,
	
	/** The not equal. */
	NOT_EQUAL;
	
	/**
	 * Gets the value.
	 *
	 * @param value The string value
	 * @return The ValidatorOperator.
	 */
	public static UValidatorOperator getValue(String value) {
		try {
			return UValidatorOperator.valueOf(value);
		} catch (IllegalArgumentException e) {
			return UValidatorOperator.UNKNOWN;
		}
	}
}
