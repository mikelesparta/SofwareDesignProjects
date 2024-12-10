package form.validation;

import form.Validator;

public class GreaterThanValidator implements Validator {

	private int value;

	public GreaterThanValidator(int value) {
		this.value = value;
	}

	@Override
	public boolean isValid(String valor) {
		try {
			return Integer.parseInt(valor) > this.value;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String getMessage() {
		return "mayor que " + value;
	}
}
