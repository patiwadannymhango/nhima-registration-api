package zm.co.nhima.nrapi.exception;

public class CustomException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int code;


	public CustomException(String message) {
		super(message);
	}

	public CustomException(String message, Throwable cause) {
		super(message, cause);
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	



}