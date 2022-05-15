package com.nura.jewelery.utils;

import org.springframework.http.ResponseEntity;

public class ResponseEntityWrapper {

	private ResponseEntityWrapper() {

	}

	public static <T> ResponseEntity<ServiceResponse<T>> wrapServiceResponse(T obj, String message, int statusCode) {
		ServiceResponse<T> response = new ServiceResponse<>();
		response.setMessage(message);
		response.setResult(obj);
		response.setStatusCode(statusCode);
		return ResponseEntity.ok(response);
	}

}
