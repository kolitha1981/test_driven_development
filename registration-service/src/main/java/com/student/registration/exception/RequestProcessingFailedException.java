package com.student.registration.exception;

public class RequestProcessingFailedException extends RuntimeException {

	public RequestProcessingFailedException(String message, Throwable cause) {
		super(message, cause);
	}

	public RequestProcessingFailedException(String message) {
		super(message);
	}

}
