package com.nura.jewelery.utils;

public class ServiceResponseWrapper<T> {

	public ServiceResponse<T> wrapServiceResponse(T obj, String message, int statusCode) {
		ServiceResponse<T> response = new ServiceResponse<>();
		response.setMessage(message);
		response.setResult(obj);
		response.setStatusCode(statusCode);
		return response;
	}

}
