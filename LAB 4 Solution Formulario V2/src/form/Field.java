package form;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import form.validation.NumberValidator;
import form.validation.TextValidator;

public class Field {

	public static final Validator TEXT = new TextValidator();
	public static final Validator NUMBER = new NumberValidator();

	private String label, value;

	private Validator validator;

	public Field(String label, Validator validator) {
		this.label = label;
		this.validator = validator;
	}

	public void askUser() {
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			try {
				System.out.print(label + ": ");
				String value = input.readLine();

				if (validator.isValid(value)) {
					this.value = value;
					return;
				}

				System.out.println("El valor introducido para \"" + label + "\" no es válido: " + value + " ("
						+ validator.getMessage() + ")");
			} catch (IOException e) {
				System.out.println("No se pudo leer el valor del campo: " + e);
			}

			System.out.println("Inténtelo de nuevo...");
		}
	}

	String getValue() {
		return value;
	}

	@Override
	public String toString() {
		return label + ": " + value;
	}
}