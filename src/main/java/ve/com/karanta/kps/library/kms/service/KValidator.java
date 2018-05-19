package ve.com.karanta.kps.library.kms.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ve.com.karanta.kps.library.kms.domain.exception.KException;
import ve.com.karanta.kps.library.kms.domain.exception.KValidatorException;
import ve.com.karanta.kps.library.kms.domain.repository.RuleRepository;
import ve.com.karanta.kps.library.kms.domain.repository.entity.Rule;
import ve.com.karanta.kps.library.kms.domain.response.KSuccessResponse;
import ve.com.karanta.kps.library.kms.domain.util.ApiMethod;
import ve.com.karanta.kps.library.kms.domain.util.ErrorCode;
import ve.com.karanta.kps.library.kms.domain.util.SuccessCode;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorMessage;
import ve.com.karanta.kps.library.kms.domain.util.validator.KValidatorOperator;

/**
 * It perform data validation over data before to be save on database. 
 * These validations are for training.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
@Service
public class KValidator {
	
	/** The repository. */
	@Autowired
	RuleRepository repo;
	
	/** The message list. */
	List<KValidatorMessage> messageList = new ArrayList<>();
	
	/** The error message, +info: {@link KValidatorMessage}. */
	KValidatorMessage errorMessage;
	
	/** The Constant response. */
	public static final KSuccessResponse<?> response =
			new KSuccessResponse<>(SuccessCode.DEFAULT, null);
	
	private List<Rule> rules = new ArrayList<>();
	
	/**
	 * Checks if data is valid for {@link ApiMethod} indicate. 
	 * When result is false it generate a {@link KValidatorException}.
	 *
	 * @param <T> The generic type
	 * @param data The data
	 * @param method The method
	 * @return True, if is valid
	 * @throws KException the k exception
	 * @throws IllegalArgumentException the illegal argument exception
	 * @throws IllegalAccessException the illegal access exception
	 * @throws KValidatorException the k validator exception
	 */
	public <T> void isValid(
			T data,
			ApiMethod method) 
					throws 	KException, 
							IllegalArgumentException, 
							IllegalAccessException, 
							KValidatorException {
		
		if (data!=null) {
			messageList.clear();
			Class<?> classType = data.getClass();
			Field[] fields = classType.getDeclaredFields(); // Get list of fields of the entity
			Table table = classType.getAnnotation(Table.class);
			String tableName = classType.getName();
			if (table != null) {
				tableName = table.name();
			} else {
				throw new KException(ErrorCode.NOT_TABLE);
			}
			for (Field field : fields) {
	        	  if (!field.isAccessible()) {
	        		  field.setAccessible(true);
	        	  }
	        	  if (field.get(data)!=null && field.get(data)!=new ArrayList<>()) {
	        		  List<KValidatorMessage> newMessageList = isValid(tableName, field, method, field.get(data).toString());
	        		  if (!newMessageList.isEmpty()) {
	        			  messageList.addAll(newMessageList);
	        		  }
	        	  }
	          }
			if (!messageList.isEmpty()){
			  throw new KValidatorException(ErrorCode.ERROR_MULTIPLE, messageList);
			}
		} else {
			throw new KException(ErrorCode.DATA_NULL);
		}
	}
	
	/**
	 * Checks if data is valid for Input indicates. When result is false it generate a {@link KException}.
	 *
	 * @param tableName The table name
	 * @param field The field
	 * @param method The method
	 * @param value The value
	 * @return The list
	 * @throws KException the k exception
	 */
	public List<KValidatorMessage> isValid(
		String tableName,
		Field field,
		ApiMethod method,
		String value) 
				throws KException {
	
		if (errorMessage!=null) {
			errorMessage = null;
		}
		List<KValidatorMessage> messList = new ArrayList<>();
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
			  throw new KException(ErrorCode.NOT_RULE);
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
	 * @throws KException the k exception
	 */
	private KValidatorMessage validate(
			String value,
			String operator,
			String expected,
			String code,
			String message) 
					throws KException {
		
		if (errorMessage!=null) {
			errorMessage=null;
		}
		if (operator!=null && expected!=null) {
			switch (KValidatorOperator.getValue(operator)) {
				case EQUAL:
					if (!(value.equals(expected))) {
						errorMessage = new KValidatorMessage(code, message);
					}
					break;
				case NOT_EQUAL:
					if (value.equals(expected)) {
						errorMessage = new KValidatorMessage(code, message);
					}
					break;
			default:
				throw new KException(ErrorCode.OPERATOR_UNKNOWN);
			}
		} else {
			throw new KException(ErrorCode.NOT_EXPECTED);
		}
		return errorMessage;
	}
}
