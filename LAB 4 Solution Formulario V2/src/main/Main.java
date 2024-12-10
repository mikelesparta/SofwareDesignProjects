package main;

import form.Field;
import form.Form;
import form.Validator;
import form.validation.AndValidator;
import form.validation.GreaterThanValidator;
import form.validation.LengthValidator;
import form.validation.LessThanValidator;
import form.validation.OrValidator;
import form.validation.PredefinedValidator;

public class Main {

	public static void main(String[] args) {
		Form form = new Form();

		form.addField(new Field("Nombre", Field.TEXT));
		form.addField(new Field("Apellidos", Field.TEXT));
		form.addField(new Field("Teléfono", Field.NUMBER));

		Validator cities = new PredefinedValidator("Leon", "Oviedo", "Madrid");
		form.addField(new Field("Ciudad", cities));

		// Ampliación: PATRÓN COMPOSITE

		form.addField(new Field("Código de producto", new LengthValidator(4)));

		Validator postalCode = new AndValidator(Field.NUMBER, new LengthValidator(5));
		form.addField(new Field("Código postal", postalCode));

		form.addField(new Field("Edad", new GreaterThanValidator(18)));

		form.addField(
				new Field("Sueldo", new AndValidator(new GreaterThanValidator(800), new LessThanValidator(1200))));

		form.addField(new Field("Ubicación", new OrValidator(cities, postalCode)));

		form.addField(new Field("Código de promoción",
				new OrValidator(Field.TEXT, new AndValidator(Field.NUMBER, new LengthValidator(3)))));

		form.askUser();
	}
}
