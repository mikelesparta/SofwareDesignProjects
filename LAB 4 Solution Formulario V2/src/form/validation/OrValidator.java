package form.validation;

import form.Validator;

public class OrValidator extends CompoundValidator {

	public OrValidator(Validator... validators) {
		super(validators);
	}

	@Override
	public boolean isValid(String value) {
		for (Validator validator : validators)
			if (validator.isValid(value))
				return true;

		return false;
	}

	@Override
	protected String getConjuction() {
		return "o";
	}
}
