package editor.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;

import editor.macros.MacroRecorder;
import editor.macros.actions.InsertAction;
import editor.macros.actions.OpenAction;
import editor.macros.actions.RemoveLastWordAction;
import editor.macros.actions.ReplaceAction;
import editor.model.Document;

//     Ahora en vez de llamar directamente a los métodos del documento se crea
//     la correspondiente acción (ConcreteCommand) que se pasa al grabador de
//     de macros, quien la ejecuta y guardar si fuera preciso (si se está grabando una macro).

public class EditorApp {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

	private PrintWriter out = new PrintWriter(System.out, true);

	private Document doc = new Document();

	// Para grabar y ejecutar macros
	private MacroRecorder macroRecorder = new MacroRecorder(doc);

	public void run() throws IOException {
		showHelp();

		do {
			out.print("> ");
			out.flush();

			// No se comprueba que el número de palabras sea el adecuado
			String[] line = in.readLine().split(" ");

			if (line[0].equals("salir"))
				return;

			if (line[0].equals("abre")) {
				String filename = line[1];

				macroRecorder.execute(new OpenAction(filename));

			} else if (line[0].startsWith("ins")) {
				String[] wordsToAppend = Arrays.copyOfRange(line, 1, line.length);

				macroRecorder.execute(new InsertAction(wordsToAppend));

			} else if (line[0].startsWith("borr")) {
				macroRecorder.execute(new RemoveLastWordAction());

			} else if (line[0].startsWith("reem")) {
				String source = line[1];
				String destionation = line[2];

				macroRecorder.execute(new ReplaceAction(source, destionation));

			} else if (line[0].startsWith("graba")) {
				macroRecorder.record(line[1]);
			} else if (line[0].startsWith("para")) {
				macroRecorder.stop();
			} else if (line[0].startsWith("ejecuta")) {
				macroRecorder.play(line[1]);
			} else {
				out.println("Instrucción desconocida");
			}

			out.println(doc.getText());

		} while (true);
	}

	private void showHelp() {
		out.println("Acciones");
		out.println("--------");
		out.println("abre <fichero>");
		out.println("inserta <texto>\t\t// inserta las palabras al final del texto");
		out.println("borra\t\t\t// borra la última palabra");
		out.println("reemplaza <a> <b>\t// reemplaza la cadena <a> por la <b> en todo el texto");
		out.println("salir");
		out.println();
		out.println("Tareas");
		out.println("------");
		out.println("graba <macro>\t\t// comienza la grabación de una macro");
		out.println("para\t\t\t// finaliza la grabación");
		out.println("ejecuta <macro>\t\t// ejecuta la macro cuyo nombre se indique");
		out.println();
	}
}
