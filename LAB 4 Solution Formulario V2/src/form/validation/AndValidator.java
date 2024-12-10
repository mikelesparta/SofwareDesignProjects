package form.validation;

import form.Validator;

public class AndValidator extends CompoundValidator {

	public AndValidator(Validator... validators) {
		super(validators);
	}

	@Override
	public boolean isValid(String value) {
		for (Validator validator : validators)
			if (!validator.isValid(value))
				return false;

		return true;
	}

	@Override
	protected String getConjuction() {
		return "y";
	}
}
