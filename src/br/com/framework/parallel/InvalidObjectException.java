package br.com.framework.parallel;

public class InvalidObjectException extends Exception {

	private static final long serialVersionUID = 6273583991797904353L;

	public InvalidObjectException(String message) {
		super(message);
	}

	public InvalidObjectException(String message, Exception e) {
		super(message, e);
	}
}
