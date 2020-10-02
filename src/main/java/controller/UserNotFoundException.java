package controller;

public class UserNotFoundException extends RuntimeException  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7926102944337723517L;

	public UserNotFoundException() {
		
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
