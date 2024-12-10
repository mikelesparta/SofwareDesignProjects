package main;

import form.Field;
import form.Form;
import form.validation.NumberValidator;
import form.validation.PredefinedValidator;
import form.validation.TextValidator;

public class Main {

	public static void main(String[] args) {
		Form form = new Form();

		form.addField(new Field("Nombre", new TextValidator()));
		form.addField(new Field("Apellidos", new TextValidator()));
		form.addField(new Field("Teléfono", new NumberValidator()));
		form.addField(new Field("Ciudad", new PredefinedValidator("Leon", "Oviedo", "Madrid", "Haro")));

		form.askUser();
	}
}
