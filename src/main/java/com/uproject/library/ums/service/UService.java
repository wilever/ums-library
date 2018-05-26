package com.uproject.library.ums.service;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;
import com.uproject.library.ums.domain.response.USuccessResponse;
import com.uproject.library.ums.domain.util.ApiMethod;
import com.uproject.library.ums.domain.util.ErrorCode;

/**
 * It indicate what methods need to be implemented by controllers.
 *
 * @author Wilever Gomez [wilevergomez@gmail.com]
 * @param <T> Entity to manage
 */
public interface UService<T> {
	
	/**
	 * Gets data from database.
	 *
	 * @param search The search parameter
	 * @param filter The filter parameter
	 * @param pageable The pageable parameter
	 * @param assembler The assembler
	 * @return The response entity
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UException the k exception
	 * @throws UValidatorException the KE validator
	 */
	public ResponseEntity<Object> get(
			String search, 
			String filter,
			Pageable pageable,
			PagedResourcesAssembler<T> assembler) throws IllegalAccessException, UException, UValidatorException;
	
	/**
	 * Adds new data to database.
	 *
	 * @param data The data
	 * @return The response entity
	 * @throws IllegalAccessException The illegal access exception
	 * @throws UException The k exception
	 * @throws UValidatorException The KE validator
	 */
	public ResponseEntity<Object> add(
			T data) throws IllegalAccessException, UException, UValidatorException;
	
	/**
	 * Update data on database.
	 *
	 * @param data The data
	 * @return The response entity
	 * @throws IllegalAccessException The illegal access exception
	 * @throws UException The k exception
	 * @throws UValidatorException The KE validator
	 */
	public ResponseEntity<Object> update(
			T data) throws IllegalAccessException, UException, UValidatorException;
	
	/**
	 * Logically delete data on database.
	 *
	 * @param id The id
	 * @return The response entity
	 * @throws IllegalAccessException The illegal access exception
	 * @throws UException The k exception
	 * @throws UValidatorException The KE validator
	 */
	public ResponseEntity<Object> delete(
			String id, boolean logical) throws IllegalAccessException, UException, UValidatorException;
	
	/**
	 * Logically restore data on database.
	 *
	 * @param id The id
	 * @return The response entity
	 * @throws IllegalAccessException The illegal access exception
	 * @throws UException The k exception
	 * @throws UValidatorException The KE validator
	 */
	public ResponseEntity<Object> restore(
			String id) throws IllegalAccessException, UException, UValidatorException;
	
	/**
	 * Save data on database.
	 *
	 * @param data Data to save
	 * @return PK of data saved
	 */
	public Object save(T data);
	
	/**
	 * Delete data on database.
	 *
	 * @param data Data to delete
	 * @return PK of data deleted
	 */
	public Object delete(T data);
	
	/**
	 * Execute service for POST,PUT,DELETE and PATCH methods: Pre-conditions, validation, process, saved and generate response
	 *
	 * @param data The data
	 * @param method The method
	 * @return The response entity
	 * @throws IllegalAccessException The illegal access exception
	 * @throws UException The k exception
	 * @throws UValidatorException The KE validator
	 */
	public default ResponseEntity<Object> execute(
			T data, 
			ApiMethod method) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException {
			if (data==null) {
				throw new UException(ErrorCode.DATA_NULL);
			} else {
				preCondition(data, method);
				validate(data, method);
				if(method.equals(ApiMethod.DELETE)) {
					return getResponse(delete(data),method);	
				}else {
					return getResponse(
							save(processData(data, method)), method);
				}
			}
	}
	
	/**
	 * Execute GET method.
	 *
	 * @param data the data
	 * @return the response entity
	 * @throws UException 
	 */
	public default ResponseEntity<Object> execute(Object data) throws UException {
		preGet();
	return getResponse(data, ApiMethod.GET);
	}
	
	/**
	 * Validate data.
	 *
	 * @param data the data
	 * @param method the method
	 * @return true, if successful
	 * @throws IllegalAccessException the illegal access exception
	 * @throws UException the k exception
	 * @throws UValidatorException the KE validator
	 */
	public void validate(T data, ApiMethod method) throws IllegalAccessException, UException, UValidatorException;
	 
	/**
	 * Pre condition for execute methods.
	 *
	 * @param data The data
	 * @param method The method
	 * @throws UException The k exception
	 */
	public default void preCondition(T data, ApiMethod method) throws UException {
			switch (method) {
			case ADD:
				preAdd(data);
				break;
			case UPDATE:
				preUpdate(data);
				break;
			case DELETE:
				preDelete(data);
				break;
			case DELETE_LOGICAL:
				preDeleteLogical(data);
				break;
			case RESTORE:
				preRestore(data);
				break;
			default:
				break;
			}
	}
	
	/**
	 * Pre-conditions for get data.
	 *
	 * @throws UException The k exception
	 */
	public void preGet() throws UException;
	
	/**
	 * Pre-conditions for add new data.
	 *
	 * @param data The data
	 * @throws UException The k exception
	 */
	public void preAdd(T data) throws UException;
	
	/**
	 * Pre-conditions for update data.
	 *
	 * @param data The data
	 * @throws UException The k exception
	 */
	public void preUpdate(T data) throws UException;
	
	/**
	 * Pre-conditions for delete data.
	 *
	 * @param data The data
	 * @throws UException The k exception
	 */
	public void preDelete(T data) throws UException;
	
	/**
	 * Pre-conditions for logical delete data.
	 *
	 * @param data The data
	 * @throws UException The k exception
	 */
	public void preDeleteLogical(T data) throws UException;
	
	/**
	 * Pre-conditions for logically restore data.
	 *
	 * @param data The data
	 * @throws UException The k exception
	 */
	public void preRestore(T data) throws UException;
	
	/**
	 * Process data before save.
	 *
	 * @param data The data
	 * @param method The method
	 * @return The t
	 */
	public default T processData(T data, ApiMethod method) {
		
		switch (method) {
		case ADD:
			data = processAdd(data);
			break;

		case UPDATE:
			data = processUpdate(data);
			break;
		case DELETE:
			data = processDelete(data);
			break;
		case DELETE_LOGICAL:
			data = processDeleteLogical(data);
			break;
		case RESTORE:
			data = processRestore(data);
			break;
		default:
			break;
		}
		return data;
	}
	
	/**
	 * Process data to add before save.
	 *
	 * @param data The data
	 * @return The t
	 */
	public T processAdd(T data);
	
	/**
	 * Process data to update before save.
	 *
	 * @param data The data
	 * @return The t
	 */
	public T processUpdate(T data);
	
	/**
	 * Process data to delete before save.
	 *
	 * @param data the data
	 * @return the t
	 */
	public T processDelete(T data);
	
	/**
	 * Process data to logically delete before save.
	 *
	 * @param data the data
	 * @return the t
	 */
	public T processDeleteLogical(T data);
	
	/**
	 * Process data to logically restore before save.
	 *
	 * @param data The data
	 * @return The t
	 */
	public T processRestore(T data);
	
	/**
	 * Gets the response.
	 *
	 * @param content The content
	 * @param method The method
	 * @return The response
	 */
	public default ResponseEntity<Object> getResponse(Object content, ApiMethod method){
		HttpStatus status;
		switch (method) {
		case GET:
			if(content!=null) {
				status = HttpStatus.OK;
			}else {
				status = HttpStatus.NO_CONTENT;
			}
			break;
		case ADD:
			status = HttpStatus.CREATED;
			content = new USuccessResponse<>(
								method, 
								content);
			break;
		default:
			status=HttpStatus.OK;
			content = new USuccessResponse<>(
					method, 
					content);
			break;
		}
		return new ResponseEntity<>(content,status);
	}
}
