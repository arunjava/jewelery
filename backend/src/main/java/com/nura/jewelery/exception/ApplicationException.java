package com.nura.jewelery.exception;

public class ApplicationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5928542192184294909L;

	public ApplicationException(String errorMessage) {
		super(errorMessage);
	}

}
