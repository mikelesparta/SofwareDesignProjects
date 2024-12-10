package form;

public interface Validator {

	boolean isValid(String value);

	String getMessage();
}
