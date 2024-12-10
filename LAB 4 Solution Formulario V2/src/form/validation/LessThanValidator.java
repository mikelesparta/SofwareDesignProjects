package form.validation;

import form.Validator;

public class LessThanValidator implements Validator {

	private int value;

	public LessThanValidator(int value) {
		this.value = value;
	}

	@Override
	public boolean isValid(String valor) {
		try {
			return Integer.parseInt(valor) < this.value;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public String getMessage() {
		return "menor que " + value;
	}
}
