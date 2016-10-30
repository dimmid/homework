package exceptions;

public class ParseException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ParseException(String reason) {
		super(reason);
	}

}
