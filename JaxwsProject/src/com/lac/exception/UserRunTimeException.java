package com.lac.exception;

public class UserRunTimeException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserRunTimeException() {
		super();
	}

	public UserRunTimeException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UserRunTimeException(String arg0) {
		super(arg0);
	}

	public UserRunTimeException(Throwable arg0) {
		super(arg0);
	}
}
