package com.uproject.library.ums.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;

import com.uproject.library.ums.domain.exception.UException;
import com.uproject.library.ums.domain.exception.UValidatorException;

public interface UController<E, P, V> {

	public ResponseEntity<Object> get(
			String search,
			String filter,
			Pageable pageable,
			PagedResourcesAssembler<V> assembler)
					throws 
					IllegalAccessException, 
					UException, 
					UValidatorException;
	
	public ResponseEntity<Object> add(E data) 
					throws 
						IllegalAccessException,	
						UException,
						UValidatorException;
	
	public ResponseEntity<Object> update(E data) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException;
	public ResponseEntity<Object> delete(P id,
			boolean logical) 
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException;
	public ResponseEntity<Object> restore(P id)
					throws 
						IllegalAccessException, 
						UException, 
						UValidatorException;
	
}
