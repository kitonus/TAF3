package com.indivaragroup.event_manager.event_manager.exception;

public class DataNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public DataNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataNotFoundException(String msg) {
		super(msg);
	}


	
}
