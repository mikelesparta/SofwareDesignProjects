package editor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import editor.figures.circle.CircleCreationTool;
import editor.figures.rectangle.RectangleCreationTool;
import editor.figures.triangle.TriangleCreationTool;
import editor.tools.SelectionTool;
import editor.tools.Tool;

public class Editor {

	private Drawing drawing;

	private PrintWriter output = new PrintWriter(System.out, true);

	private Tool currentTool, defaultTool;
	private Tool selection, rectangle, circle, triangle;

	public Editor() {
		this(new Drawing());
	}

	public Editor(Drawing drawing) {
		setDrawing(drawing);

		// Se crean las distintas herramientas
		selection = defaultTool = currentTool = new SelectionTool(this);
		rectangle = new RectangleCreationTool(this);
		circle = new CircleCreationTool(this);
		triangle = new TriangleCreationTool(this);
	}

	// HERRAMIENTAS
	public void toolDone() {
		selectTool(defaultTool);
	}

	private void selectTool(Tool tool) {
		if (tool != currentTool)
			currentTool.unselect();

		currentTool = tool;
	}

	// MÉTODOS DE DIBUJO
	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}

	public Drawing getDrawing() {
		return drawing;
	}

	public void draw() {
		output.println("Herramienta seleccionada: " + currentTool);
		drawing.draw(output);
	}

	// MENÚ PRINCIPAL (IU)
	public void run() throws IOException {

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		showHelp(output);

		do {
			output.print("> ");
			output.flush();

			// Pregunta al usuario por consola el nombre de la herramienta o acción
			// (pulsar 100, 100 ---> tokens = [ "pulsar", "100, 100" ]
			String[] tokens = input.readLine().split("[ ]", 2);
			String action = tokens[0];

			// Comprueba que las acciones que no requieren parámetros
			if (!action.equals("pulsar") && !action.equals("mover"))
				if (tokens.length > 1) {
					output.printf("Error de sintaxis: \"%s\" no tiene parámetros\n", action);
					return;
				}

			if (action.equals("salir")) {
				output.println("¡Adios!");
				return;
			}

			// Eventos del ratón

			if (action.equals("pulsar")) {
				try {
					// Funciona separado por coma o coma y espacio
					String[] arguments = tokens[1].split("\\s*,\\s*");

					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);

					currentTool.clickOn(x, y);

				} catch (NumberFormatException e) {
					output.println(
							"Error de sintaxis: se esperaban las coordenadas del punto en que se pulsó: pulsar <x>, <y>");
				}

			} else if (action.equals("mover")) {
				try {
					// Funciona separado por coma o coma y espacio
					String[] arguments = tokens[1].split("\\s*,\\s*");

					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);

					currentTool.moveTo(x, y);

				} catch (NumberFormatException e) {
					output.println(
							"Error de sintaxis: se esperaban las coordenadas del punto adonde se movió el cursor: mover <x>, <y>");
				}

			} else if (action.equals("soltar")) {
				currentTool.release();
			}

			// Herramientas

			else if (action.equals("seleccion")) {
				selectTool(selection);
			} else if (action.equals("rectangulo")) {
				selectTool(rectangle);
			} else if (action.equals("circulo")) {
				selectTool(circle);
			} else if (action.equals("triangulo")) {
				selectTool(triangle);
			}

			// Acciones del editor

			else if (action.equals("dibujar")) {
				draw();
			} else if (action.equals("ayuda")) {
				showHelp(output);
			} else {
				output.println("Acción desconocida");
				showHelp(output);
			}

		} while (true);
	}

	private void showHelp(PrintWriter output) {
		output.println("");
		output.println("Herramientas: seleccion - rectangulo - circulo - triangulo");
		output.println("Acciones del ratón: pulsar <x>,<y> - mover <x>,<y> - soltar");
		output.println("Otras acciones: dibujar - ayuda - salir");
		output.println("-----------------------------------------------------------");
	}
}
