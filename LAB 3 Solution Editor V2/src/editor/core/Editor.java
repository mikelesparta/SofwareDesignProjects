package editor.core;

import java.io.*;
import java.util.*;

import editor.tools.NoTool;

public class Editor {
	
	private Drawing drawing;
	private PrintWriter output = new PrintWriter(System.out, true);
	
	private Tool currentTool, defaultTool;
	
	private Map<String, Tool> tools = new HashMap<>();
	
	public Editor() {
		this(new Drawing());
	}
	
	public Editor(Drawing drawing) {
		setDrawing(drawing);
		setDefaultTool(new NoTool());
	}
	
	//$ Herramientas ---------------------------------------
	
	public void addTool(String name, Tool tool) {
		tools.put(name, tool);
	}
	
	public void setDefaultTool(Tool tool) {
		defaultTool = currentTool = tool;
	}
	
	public void toolDone() {
		selectTool(defaultTool);
	}
	
	void selectTool(Tool tool) {
		if (tool != currentTool)
			currentTool.unselect();
		currentTool = tool;
	}
	
	Tool getCurrentTool() {
		return currentTool;
	}
	
	//$ Métodos del dibujo --------------------------------
	
	public Drawing getDrawing() {
		return drawing;
	}
		
	public void setDrawing(Drawing drawing) {
		this.drawing = drawing;
	}
		
	public void draw() {
		output.println("Herramienta seleccionada: " + currentTool);
		drawing.draw(output);
	}	
		
    //$ Menú principal (IU) -------------------------------
	
	public void run() throws IOException {
		
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		
		showHelp(output);
		do {
			output.print("> ");
			output.flush();
			
			// Pregunta al usuario por consola y la línea introducida en dos partes: el nombre de
			// la herramienta o acción, por un lado, y todos los parámetros adicionales, en caso
			// de haberlos (todo lo que venga tras el primer espacio), por otro; p. ej.:
			//
			//     > pulsar 100, 100 ---> tokens = [ "pulsar",  "100, 100" ]
			//
			String[] tokens = input.readLine().split("[ ]", 2);
			String action = tokens[0];
			
			// Comprueba que a las acciones que no requieren parámetros, efectivamente no se les
			// pase ninguno (por usabilidad, para que el usuario se dé cuenta de que la última 
			// acción no funciona como él esperaba). Por ejemplo, si por equivocación tecleó:
			//
			// 		"soltar 200, 345"
			//
			// cuando realmente esas coordenadas no son tenidas en cuenta (se debería haber
			// llamado previamente a "mover 200, 345" y luego simplemente "soltar").
			//
			if (!action.equals("pulsar") && !action.equals("mover")) {
				if (tokens.length > 1) {
					output.printf("Error de sintaxis: \"%s\" no tiene parámetros\n", action);
					return;
				}
			}
			
			if (action.equals("salir")) {
				output.println("¡Adios!");
				return;
			}
			
			//$ Eventos del ratón
				
			if (action.equals("pulsar")) {
				try {
					// la siguiente línea es para que funcione independientemente de si las coordenadas
					// están separadas sólo por una coma o si hay espacios entre los números y la coma
					String[] arguments = tokens[1].split("\\s*,\\s*");
					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);
					currentTool.clickOn(x, y);
				} catch (NumberFormatException e) {
					output.println("Error de sintaxis: se esperaban las coordenadas del punto en que se pulsó: pulsar <x>, <y>");
				}
			} else if (action.equals("mover")) {
				try {
					// la siguiente línea es para que funcione independientemente de si las coordenadas
					// están separadas sólo por una coma o si hay espacios entre los números y la coma
					String[] arguments = tokens[1].split("\\s*,\\s*");
					int x = Integer.parseInt(arguments[0]);
					int y = Integer.parseInt(arguments[1]);
					currentTool.moveTo(x, y);
				} catch (NumberFormatException e) {
					output.println("Error de sintaxis: se esperaban las coordenadas del punto adonde se movió el cursor: mover <x>, <y>");
				}
			} else if (action.equals("soltar")) {
				currentTool.release();
				
			//$ Acciones del editor
				
			} else if (action.equals("dibujar")) {
				draw();
			} else if (action.equals("ayuda")) {
				showHelp(output);
			} 			
			
			//$ Herramientas
			
			else if (tools.containsKey(action)) {
				selectTool(tools.get(action));
			}
			
			else {
				output.println("Acción desconocida");
				showHelp(output);
			}
		} while (true);
	}

	private void showHelp(PrintWriter output)
	{
		output.println("");
		output.println("Herramientas: seleccion - rectangulo - circulo - triangulo");
		output.println("Acciones del ratón: pulsar <x>,<y> - mover <x>,<y> - soltar");
		output.println("Otras acciones: dibujar - ayuda - salir");
		output.println("-----------------------------------------------------------");
	}
}
