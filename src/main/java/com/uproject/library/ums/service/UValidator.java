package com.uproject.library.ums.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;
import com.uproject.library.ums.domain.repository.RuleRepository;
import com.uproject.library.ums.domain.repository.entity.Rule;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;
import com.uproject.library.ums.domain.util.SuccessCode;
import com.uproject.library.ums.domain.util.validator.UValidatorMessage;
import com.uproject.library.ums.domain.util.validator.UValidatorOperator;

/**
 * It perform data validation over data before to be save on database. 
 * These validations are for training.
 * 
 * @author Wilever Gomez [wilevergomez@gmail.com]
 */
@Service
public class UValidator {
	
	/** The repository. */
	@Autowired
	RuleRepository repo;
	
	/** The message list. */
	List<UValidatorMessage> messageList = new ArrayList<>();
	
	/** The error message, +info: {@link UValidatorMessage}. */
	UValidatorMessage errorMessage;
	
	/** The Constant response. */
	public static final USuccessResponse<?> response =
			new USuccessResponse<>(SuccessCode.DEFAULT, null);
	
	private List<Rule> rules = new ArrayList<>();
	
	/**
	 * Checks if data is valid for {@link ApiMethod} indicate. 
	 * When result is false it generate a {@link UValidatorException}.
	 *
	 * @param <T> The generic type
	 * @param data The data
	 * @param method The method
	 * @return True, if is valid
	 * @throws UException the k exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UValidatorException the k validator exception
	 */
	public <T> void isValid(
			T data,
			ApiMethod method) 
					throws 	UException, 
							IllegalArgumentException, 
							IllegalAccessException, 
							UValidatorException {
		
		if (data!=null) {
			messageList.clear();
			Class<?> classType = data.getClass();
			Field[] fields = classType.getDeclaredFields(); // Get list of fields of the entity
			Table table = classType.getAnnotation(Table.class);
			String tableName = classType.getName();
			if (table != null) {
				tableName = table.name();
			} else {
				throw new UException(ErrorCode.NOT_TABLE);
			}
			for (Field field : fields) {
	        	  if (!field.isAccessible()) {
	        		  field.setAccessible(true);
	        	  }
	        	  if (field.get(data)!=null && field.get(data)!=new ArrayList<>()) {
	        		  List<UValidatorMessage> newMessageList = isValid(tableName, field, method, field.get(data).toString());
	        		  if (!newMessageList.isEmpty()) {
	        			  messageList.addAll(newMessageList);
	        		  }
	        	  }
	          }
			if (!messageList.isEmpty()){
			  throw new UValidatorException(ErrorCode.ERROR_MULTIPLE, messageList);
			}
		} else {
			throw new UException(ErrorCode.DATA_NULL);
		}
	}
	
	/**
	 * Checks if data is valid for Input indicates. When result is false it generate a {@link UException}.
	 *
	 * @param tableName The table name
	 * @param field The field
	 * @param method The method
	 * @param value The value
	 * @return The list
	 * @throws UException the k exception
	 */
	public List<UValidatorMessage> isValid(
		String tableName,
		Field field,
		ApiMethod method,
		String value) 
				throws UException {
	
		if (errorMessage!=null) {
			errorMessage = null;
		}
		List<UValidatorMessage> messList = new ArrayList<>();
		Column column = field.getAnnotation(Column.class);
		if (column!=null) {
		rules = repo
				  .findByColumn(
						  method.toString(), 
						  tableName, 
						  column.name());
		}else {
			rules.clear();
		}
		if (rules!=null) {
			  if (!rules.isEmpty()) {
				  for (Rule rule : rules) {
					  errorMessage = validate(
							  value,
							  rule.getOperator(),
							  rule.getExpected(),
							  rule.getCode(),
							  rule.getMessage(value));
					  if (errorMessage!=null) {
						  messList.add(errorMessage);
					  }
				  }
		  }
		} else {
			  throw new UException(ErrorCode.NOT_RULE);
		}
		
		return messList;
	}
	
	/**
	 * Validate data.
	 *
	 * @param value The value
	 * @param operator The operator
	 * @param expected The expected
	 * @param code The code
	 * @param message The message
	 * @return The k validator message
	 * @throws UException the k exception
	 */
	private UValidatorMessage validate(
			String value,
			String operator,
			String expected,
			String code,
			String message) 
					throws UException {
		
		if (errorMessage!=null) {
			errorMessage=null;
		}
		if (operator!=null && expected!=null) {
			switch (UValidatorOperator.getValue(operator)) {
				case EQUAL:
					if (!(value.equals(expected))) {
						errorMessage = new UValidatorMessage(code, message);
					}
					break;
				case NOT_EQUAL:
					if (value.equals(expected)) {
						errorMessage = new UValidatorMessage(code, message);
					}
					break;
			default:
				throw new UException(ErrorCode.OPERATOR_UNKNOWN);
			}
		} else {
			throw new UException(ErrorCode.NOT_EXPECTED);
		}
		return errorMessage;
	}
}
