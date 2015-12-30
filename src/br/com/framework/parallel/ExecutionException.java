package br.com.framework.parallel;

public class ExecutionException extends Exception {

	private static final long serialVersionUID = -3884293199265312796L;

	public ExecutionException(String message) {
		super(message);
	}

	public ExecutionException(String message, Exception e) {
		super(message, e);
	}
}
