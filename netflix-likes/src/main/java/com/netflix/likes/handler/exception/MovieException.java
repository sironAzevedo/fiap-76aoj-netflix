package com.netflix.likes.handler.exception;

public class MovieException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MovieException(String message) {
		super(message);
	}
}
