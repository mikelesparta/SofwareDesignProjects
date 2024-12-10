package editor.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import editor.tools.SelectionTool;

public class Editor {

	private PrintWriter output = new PrintWriter(System.out, true);

	private Drawing drawing;

	private History history;

	private Tool currentTool, defaultTool;

	// Un LinkedHashMap mantiene la claves en el mismo orden en que se insertaron
	// los elementos y permite recorrer sus elementos en orden
	private Map<String, Tool> tools = new LinkedHashMap<>();

	public Editor() {
		this(new Drawing());
	}

	public Editor(Drawing drawing) {
		setDrawing(drawing);
		defaultTool = currentTool = createDefaultTool();
	}

	// HERRAMIENTAS

	public void addTool(String name, Tool tool) {
		tools.put(name, tool);
	}

	// No tiene sentido un editor sin ninguna herramienta predeterminada,
	// ni en un estado inconsistente hasta que alguien llame a un método
	// setDefaultTool(Tool) o similar. De ahí que se esté creando en un método
	// protegido; si alguna implementación concreta del editor necesitase
	// otra herramienta predeterminada distinta bastaría con que redefiniese
	// este método.
	protected Tool createDefaultTool() {
		Tool defaultTool = new SelectionTool(this);
		tools.put("seleccion", defaultTool);

		return defaultTool;
	}

	// Todas las herramientas lo llaman al finalizar
	public void toolDone() {
		selectTool(defaultTool);
	}

	// Antes de seleccionar la nueva herramienta activa,
	// limpia el estado de la actual
	void selectTool(Tool tool) {
		if (tool != currentTool)
			currentTool.unselect();

		currentTool = tool;
	}

	public Tool getCurrentTool() {
		return currentTool;
	}

	// $ Métodos del dibujo

	public Drawing getDrawing() {
		return drawing;
	}

	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
		this.history = new History();
	}

	public void draw() {
		output.println("Herramienta seleccionada: " + currentTool);
		drawing.draw(output);
	}

	// Deshacr/repetir (undo/redo)
	public History getHistory() {
		return history;
	}

	// $ Menú principal (IU)

	public void run() throws IOException {
		showHelp();

		do {
			// > rectangulo ---> [ "rectangulo" ]
			// > pulsar 100, 100 ---> [ "pulsar", "100, 100" ]
			String[] userInput = askUser();

			// El nombre de la acción, herramienta o evento del ratón
			String option = userInput[0];

			// Los parámetros, si los hubiera: pulsar 100, 100 ---> [ "100, 100" ]
			String[] arguments = parseArguments(userInput);

			if (option.equals("salir")) {
				output.println("¡Adios!");
				return;
			}

			if (option.isEmpty())
				continue;

			// $ Eventos del ratón ---

			if (option.equals("pulsar")) {
				try {
					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);

					currentTool.clickOn(x, y);

				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					output.println("Se necesitan las coordenadas del punto en el que se pulsó");
				}

			} else if (option.equals("mover")) {
				try {
					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);

					currentTool.moveTo(x, y);

				} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
					output.println("Se necesitan las coordenadas del punto al que se ha movido el ratón");
				}

			} else if (option.equals("soltar")) {
				if (arguments.length > 0) {
					output.println("¡Soltar no recibe parámetros!");
					continue;
				}

				currentTool.release();
			}

			// $ Acciones del editor ---

			else if (option.equals("dibujar")) {
				draw();
			} else if (option.equals("deshacer")) {
				if (!history.canBeUndone()) {
					output.println("No hay nada que deshacer");
					continue;
				}

				history.undo();

			} else if (option.equals("repetir")) {
				if (!history.canBeRedone()) {
					output.println("No hay nada que repetir");
					continue;
				}

				history.redo();

			} else if (option.equals("ayuda")) {
				showHelp();
			}

			// $ Herramientas ---

			else if (tools.containsKey(option)) {
				selectTool(tools.get(option));

			} else {
				output.println("Opción desconocida");
				showHelp();
			}
		} while (true);
	}

	private String[] askUser() throws IOException {
		output.print("> ");
		output.flush();

		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));

		return input.readLine().trim().split("\\s+", 2);
	}

	private String[] parseArguments(String[] userInput) {
		// Si no hay parámetros, devuelve un array vacío
		if (userInput.length < 2)
			return new String[] {};

		// Si los hay, separados por comas, se dividen en un array
		return userInput[1].split("\\s*,\\s*");
	}

	private void showHelp() {
		output.println("");
		output.println("Herramientas: " + showTools());
		output.println("Eventos del ratón: pulsar <x>, <y> - mover <x>, <y> - soltar");
		output.println("Otras acciones: dibujar - deshacer - repetir - ayuda - salir");
		output.println("------------------------------------------------------------");
	}

	// Ya puestos, si realmente quisiésemos hacer que el editor no conociese a
	// sus herramientas, como estamos haciendo en esta versión (algo que
	// seguramente no sería realista en un editor real con interfaz gráfica,
	// donde habrá que decidir cómo se organizan éstas en la interfaz), es
	// necesario, para ser consistentes, sacar fuera la parte del menú que las
	// muestra (en la ayuda). Por ejemplo, en nuestra implementación actual,
	// este método devolvería:
	//
	// "seleccion - rectangulo - circulo - triangulo"
	//
	private String showTools() {
		StringBuilder result = new StringBuilder();
		Set<String> toolNames = tools.keySet();
		Iterator<String> iterator = toolNames.iterator();

		while (iterator.hasNext()) {
			result.append(iterator.next());

			// si no es el último elemento, se añade un separador
			if (iterator.hasNext())
				result.append(" - ");
		}

		return result.toString();
	}
}
