package zm.co.nhima.nrapi.exception;

public class EventNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EventNotFoundException(String message) {
		super(message);
	}

	public EventNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}
