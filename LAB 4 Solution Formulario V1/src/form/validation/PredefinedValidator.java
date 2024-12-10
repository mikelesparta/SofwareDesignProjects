package form.validation;

import form.Validator;

public class PredefinedValidator implements Validator {

	private String[] predefinedValues;
	private String message;

	public PredefinedValidator(String... predefinedValues) {
		this.predefinedValues = predefinedValues;
		this.message = buildMessage();
	}

	@Override
	public boolean isValid(String value) {
		for (String each : predefinedValues)
			if (each.equalsIgnoreCase(value))
				return true;

		return false;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
	private String buildMessage() {
		StringBuilder result = new StringBuilder("Se necesita uno de los siguientes valores predefinidos: ");
		for (int i = 0; i < predefinedValues.length; i++) {
			result.append(predefinedValues[i]);
			if (i < predefinedValues.length - 2)
				result.append(", ");
			if (i == predefinedValues.length - 2)
				result.append(" o ");
		}

		return result.toString();
	}
}
