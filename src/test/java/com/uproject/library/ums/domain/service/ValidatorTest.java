package com.uproject.library.ums.domain.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;
import com.uproject.library.ums.domain.repository.RuleRepository;
import com.uproject.library.ums.domain.repository.entity.Data;
import com.uproject.library.ums.domain.repository.entity.Rule;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.validator.UValidatorOperator;
import com.uproject.library.ums.service.UValidator;

/**
 * ValidatorTest for training.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class ValidatorTest {
	
	/** The repository. */
	@Mock
	RuleRepository repo;
	
	/** The validator. */
	@InjectMocks
	UValidator validator;
	
	/** The message. */
	String message = "DEFAULT_MESSAGE";
	
	/** The condition. */
	ApiMethod condition = ApiMethod.ADD;
	
	/** The table. */
	String table = "data";
	
	/** The column. */
	String column = "value";
	
	/** The value. */
	String value;
	
	/** The expected. */
	String expected;
	
	/** The rule. */
	Rule rule;
	
	/**
	 * Setup mock.
	 */
	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);
		value="VALUE";
		expected=value;
	}
	
	/**
	 * Test data.
	 *
	 * @param value The value
	 * @param operator The operator
	 * @throws Exception The exception
	 */
	void testData(
			String value,
			String operator) 
					throws Exception {
		Data data = new Data("id", "data_message", value);
		setRepo(operator);
		validator.isValid(data, condition);
	}
	
	/**
	 * Test data fail.
	 *
	 * @param value The value
	 * @param operator The operator
	 * @throws Exception The exception
	 */
	void testDataFail(
			String value,
			String operator) 
					throws Exception {
		Data data = new Data("id", "data_message", value);
		setRepo(operator);
		validator.isValid(data, condition);
	}
	
	/**
	 * Sets the repository.
	 *
	 * @param operator The new repository
	 */
	void setRepo(String operator) {
		List<Rule> rules1 = new ArrayList<>();
		rule = new Rule(
				"1"
				, "DEFAULT_CODE"
				, message
				, table
				, "value"
				, condition.toString()
				, expected
				, operator);
		rules1.add(rule);
		when(repo
				.findByColumn(condition.toString(), table, "value"))
		.thenReturn(rules1);
		List<Rule> rules2 = new ArrayList<>();
		rule = new Rule(
				"2"
				, "DEFAULT_CODE"
				, message
				, table
				, "message"
				, condition.toString()
				, "data_message"
				, operator);
		rules2.add(rule);
		when(repo
				.findByColumn(condition.toString(), table, "message"))
		.thenReturn(rules2);	
	}
	
	/**
	 * Validation equal success.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationEqualSuccess() throws Exception {
		testData(value, UValidatorOperator.EQUAL.toString());
	}
	
	/**
	 * Validation not equal success.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UValidatorException.class)
	public void validationNotEqualSuccess() throws Exception {
		value=expected+"_OTHER";
		testData(value, UValidatorOperator.NOT_EQUAL.toString());
	}
	
	/**
	 * Validation not table.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationNotTable() throws Exception {
		String data = "DATA";
		setRepo(UValidatorOperator.EQUAL.toString());
		try {
			validator.isValid(data, condition);
		} catch (Exception e) {
			assertThat(e).isEqualToComparingFieldByFieldRecursively(new UException(ErrorCode.NOT_TABLE));
		}
	}
	
	/**
	 * Validation data null.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationDataNull() throws Exception {
		try {
			validator.isValid(null, condition);
		} catch (Exception e) {
			assertThat(e).isEqualToComparingFieldByFieldRecursively(new UException(ErrorCode.DATA_NULL));
		}
	}
	
	/**
	 * Validation no rules.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationNoRules() throws Exception {
		Data data = new Data("id", "data_message", value);
		when(repo.findByColumn(condition.toString(), table, column))
		.thenReturn(new ArrayList<>());
		validator.isValid(data, condition);
	}
	
	/**
	 * Validation rule null.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationRuleNull() throws Exception {
		Data data = new Data("id", "data_message", value);
		when(repo.findByColumn(condition.toString(), table, column))
		.thenReturn(null);
		try {
			validator.isValid(data, condition);
		} catch (Exception e) {
			assertThat(e).isEqualToComparingFieldByFieldRecursively(new UException(ErrorCode.NOT_RULE));
		}
		
	}
	
	/**
	 * Validation value null.
	 *
	 * @throws Exception The exception
	 */
	@Test
	public void validationValueNull() throws Exception {
		value = null;
		Data data = new Data("id", "data_message", value);
		when(repo
				.findByColumn(condition.toString(), table, column))
		.thenReturn(new ArrayList<>());
		validator.isValid(data, condition);
	}
	
	/**
	 * Validation equal fail.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UValidatorException.class)
	public void validationEqualFail() throws Exception {
		value=expected+"_OTHER_VALUE";
		testDataFail(value, UValidatorOperator.EQUAL.toString());
	}
	
	/**
	 * Validation not equal fail.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UValidatorException.class)
	public void validationNotEqualFail() throws Exception {
		value=expected;
		testDataFail(value, UValidatorOperator.NOT_EQUAL.toString());
	}
	
	/**
	 * Validation operator null.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UException.class)
	public void validationOperatorNull() throws Exception {
		value=expected;
		testData(value, null);
	}
	
	/**
	 * Validation operator unknown.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UException.class)
	public void validationOperatorUnknown() throws Exception {
		value=expected;
		testData(value, "unknown");
	}
	
	/**
	 * Validation expected null.
	 *
	 * @throws Exception The exception
	 */
	@Test(expected=UException.class)
	public void validationExpectedNull() throws Exception {
		expected=null;
		testData(value, UValidatorOperator.EQUAL.toString());
	}
}
