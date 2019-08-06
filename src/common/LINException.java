package common;

public class LINException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LINException(String message, Throwable cause) {
		super(message, cause);
	}

	public LINException(String message) {
		super(message);
	}

	public LINException(Throwable cause) {
		super(cause);
	}

}
