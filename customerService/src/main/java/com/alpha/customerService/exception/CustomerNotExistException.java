package com.alpha.customerService.exception;

public class CustomerNotExistException extends RuntimeException {

	public CustomerNotExistException(String message) {
		super(message);
	}

}
