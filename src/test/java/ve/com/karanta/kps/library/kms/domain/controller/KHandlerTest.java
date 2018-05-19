package ve.com.karanta.kps.library.kms.domain.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.AccessDeniedException;

import org.junit.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javassist.NotFoundException;
import ve.com.karanta.kps.library.kms.controller.KHandlerController;
import ve.com.karanta.kps.library.kms.domain.exception.KException;
import ve.com.karanta.kps.library.kms.domain.exception.KValidatorException;
import ve.com.karanta.kps.library.kms.domain.response.KValidatorResponse;
import ve.com.karanta.kps.library.kms.domain.util.ErrorCode;

/**
 * Unit test for {@link KHandlerController}.
 * 
 * @author Wilever Gomez [gomezw@karanta.com.ve]
 */
public class KHandlerTest {
	
	/** The Karanta handler controller. */
	private KHandlerController kHand = new KHandlerController();
	
	/**
	 * HttpStatus of response.
	 */
	private HttpStatus status;
	
	/**
	 * Header of response. It have ExceptionName and ExceptionMessage.
	 */
	private HttpHeaders header = new HttpHeaders();

	/**
	 * Error code of request.
	 */
	private ErrorCode errorCode;
	
	/**
	 * Response evaluated.
	 */
	private ResponseEntity<?> response;

	/**
	 * Test handler default exception.
	 */
	@Test
	public void KHDefaultException() {
		ResponseEntity<?> response = kHand.Default(new Exception());
		header.add("ExceptionName", "Exception");
		header.add("ExceptionMessage", null);
		errorCode=ErrorCode.BAD_REQUEST;
		status=HttpStatus.BAD_REQUEST;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
	}
	
	/**
	 * Test handler not found exception.
	 */
	@Test
	public void KHDefaultNotFoundException() {
		response = kHand.Default(new NotFoundException(null));
		header.add("ExceptionName", "NotFoundException");
		header.add("ExceptionMessage", null);
		errorCode=ErrorCode.NOT_FOUND;
		status=HttpStatus.NOT_FOUND;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
		}
	
	 /**
	  * Test handler access denied exception
	  */
	@Test
	public void KHDefaultAccessDeniedException() {
		response = kHand.Default(new AccessDeniedException(null));
		header.add("ExceptionName", "AccessDeniedException");
		header.add("ExceptionMessage", null);
		errorCode=ErrorCode.UNAUTHORIZED;
		status=HttpStatus.UNAUTHORIZED;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
		}

	/**
	 * Test handler illegal access exception.
	 */
	@Test
	public void KHDefaultIllegalAccessException() {
		ResponseEntity<?> response = kHand.Default(new IllegalAccessException());
		header.add("ExceptionName", "IllegalAccessException");
		header.add("ExceptionMessage", null);
		errorCode=ErrorCode.FORBIDDEN;
		status=HttpStatus.FORBIDDEN;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
		}
	
	/**
	 * 
	 * Test handler {@link KException}.
	 */
	@Test
	public void KHException() {
		ResponseEntity<?> response = kHand.kException(new KException(ErrorCode.NOT_EXPECTED));
		errorCode=ErrorCode.NOT_EXPECTED;
		status= HttpStatus.BAD_REQUEST;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
		}
	
	/**
	 * Test handler {@link KValidatorException}.
	 */
	@Test
	public void KHValidator() {
		ResponseEntity<?> response = kHand.kEValidator(new KValidatorException(ErrorCode.ERROR_MULTIPLE, null));
		errorCode=ErrorCode.ERROR_MULTIPLE;
		status=HttpStatus.FAILED_DEPENDENCY;
		assertThat(response)
			.isEqualToComparingFieldByFieldRecursively(expected());
		}
	/**
	 * Generate response expected.
	 * 
	 * @return Response expected
	 */
	private ResponseEntity<?> expected(){
		return new ResponseEntity<>(
				new KValidatorResponse(
						errorCode.getCode(), 
						errorCode.getMessage(), 
						null),
				header
				,status);
	}
}
