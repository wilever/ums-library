package ve.com.karanta.kps.library.kms.domain.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import ve.com.karanta.kps.library.kms.domain.util.ApiMethod;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorOperator;
import ve.com.karanta.kps.library.kms.service.KValidator;

/**
 * Rule to be validate by {@link KValidator}
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@Entity
@Table(name="rule")
public class Rule implements Serializable{
	/**
	 * Auto-generate by {@link Serializable}
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Identifier.
	 */
	@Id
	@Column(name="r_id")
	private String id;
	/**
	 * Code to be show in case of fail.
	 */
	@Column(name="r_code")
	private String code;
	/**
	 * Message by default to be show in case of fail. 
	 */
	@Column(name="r_message")
	private String message;
	/**
	 * Table to be validate.
	 */
	@Column(name="r_table")
	private String table;
	/**
	 * Column to be validate.
	 */
	@Column(name="r_column")
	private String column;
	/**
	 * Condition to be validate, +info: {@link ApiMethod}
	 */
	@Column(name="r_condition")
	private String condition;
	/**
	 * Value expected.
	 */
	@Column(name="r_expected")
	private String expected;
	/**
	 * Operator to be validate, +info: {@link KValidatorOperator}
	 */
	@Column(name="r_operator")
	private String operator;
	/**
	 * Generate a new rule.
	 */
	public Rule() {
	}

	/**
	 * Generate a rule with fields
	 * 
	 * @param id {@link #id}
	 * @param code {@link #code}
	 * @param message {@link #message}
	 * @param table {@link #table}
	 * @param column {@link #column}
	 * @param condition {@link #condition}
	 * @param expected {@link #expected}
	 * @param operator {@link #operator}
	 */
	public Rule(
			String id,
			String code,
			String message,
			String table,
			String column,
			String condition,
			String expected,
			String operator) {
		this.id = id;
		this.code = code;
		this.message = message;
		this.table = table;
		this.column = column;
		this.condition = condition;
		this.expected = expected;
		this.operator = operator;
	}
	/**
	 * Get id.
	 * 
	 * @return {@link #id}
	 */
	public String getId() {
		return id;
	}
	/**
	 * Set id.
	 * 
	 * @param id {@link #id}
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * Get code.
	 * 
	 * @return {@link #code}
	 */
	public String getCode() {
		return code;
	}
	/**
	 * Set code.
	 * 
	 * @param code {@link #code}
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * Get message. It is generate by Value, {@link #operator} and {@link #expected}.
	 * 
	 * @param value Value to validate.
	 * @return {@link #message}
	 */
	public String getMessage(String value) {
		if (value!=null && operator!=null && expected!=null) {
			message=message+". Evaluation FAIL: Value = "+value+", Operator = "+operator+", Expected = "+expected;
		} else if (value!=null && operator!=null) {
			message=message+". Rule: "+value+" "+operator+" was FAIL";
		}
		return message;
	}
	/**
	 * Set message.
	 * 
	 * @param message {@link #message}
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * Get table.
	 * 
	 * @return {@link #table}
	 */
	public String getTable() {
		return table;
	}
	/**
	 * Set table.
	 * 
	 * @param table {@link #table}
	 */
	public void setTable(String table) {
		this.table = table;
	}
	/**
	 * Get column.
	 * 
	 * @return {@link #column}
	 */
	public String getColumn() {
		return column;
	}
	/**
	 * Set column.
	 * 
	 * @param column {@link #column}
	 */
	public void setColumn(String column) {
		this.column = column;
	}
	/**
	 * Get condition.
	 * 
	 * @return {@link #condition}
	 */
	public String getCondition() {
		return condition;
	}
	/**
	 * Set condition.
	 * 
	 * @param condition {@link #condition}
	 */
	public void setCondition(String condition) {
		this.condition = condition;
	}
	/**
	 * Get expected.
	 * 
	 * @return {@link #expected}
	 */
	public String getExpected() {
		return expected;
	}
	/**
	 * Set expected.
	 * 
	 * @param expected {@link #expected}
	 */
	public void setExpected(String expected) {
		this.expected = expected;
	}
	/**
	 * Get operator.
	 * 
	 * @return {@link #operator}
	 */
	public String getOperator() {
		return operator;
	}
	/**
	 * Set operator.
	 * 
	 * @param operator {@link #operator}
	 */
	public void setOperator(String operator) {
		this.operator = operator;
	}
}
