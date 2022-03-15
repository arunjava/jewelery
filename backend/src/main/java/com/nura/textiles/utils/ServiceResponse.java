package com.nura.textiles.utils;

import lombok.Data;

@Data
public class ServiceResponse<T> {

	private String message;
	private T result;
	private int statusCode;
}
