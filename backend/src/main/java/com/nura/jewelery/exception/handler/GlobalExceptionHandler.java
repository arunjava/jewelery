package com.nura.jewelery.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.nura.jewelery.utils.ServiceResponse;
import com.nura.jewelery.utils.ServiceResponseWrapper;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.ok(new ServiceResponseWrapper<>().wrapServiceResponse(errors, "Invalid Request",
				HttpStatus.BAD_REQUEST.value()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ServiceResponse<String>> handleAllExceptions(Exception ex) {
		return ResponseEntity.ok(new ServiceResponseWrapper<String>().wrapServiceResponse(ex.getLocalizedMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR.value()));
	}
}