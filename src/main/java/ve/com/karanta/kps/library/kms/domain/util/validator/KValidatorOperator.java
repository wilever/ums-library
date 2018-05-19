package ve.com.karanta.kps.library.kms.domain.util.validator;

import ve.com.karanta.kps.library.kms.service.KValidator;

/**
 * Operator to be validate by {@link KValidator}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public enum KValidatorOperator {
	
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
	public static KValidatorOperator getValue(String value) {
		try {
			return KValidatorOperator.valueOf(value);
		} catch (IllegalArgumentException e) {
			return KValidatorOperator.UNKNOWN;
		}
	}
}
