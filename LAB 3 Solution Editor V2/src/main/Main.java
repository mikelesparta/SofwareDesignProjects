package main;

import java.io.IOException;

import editor.core.Editor;
import editor.core.Tool;
import editor.figures.circle.CircleCreationTool;
import editor.figures.rectangle.RectangleCreationTool;
import editor.figures.triangle.TriangleCreationTool;
import editor.tools.SelectionTool;

public class Main {

	public static void main(String[] args) throws IOException {
		Editor editor = new Editor();

		// Se crean y registran las herramientas
		editor.addTool("rectángulo", new RectangleCreationTool(editor));
		editor.addTool("círculo", new CircleCreationTool(editor));
		editor.addTool("triángulo", new TriangleCreationTool(editor));

		// Ahora la predeterminada
		Tool selection = new SelectionTool(editor);
		editor.addTool("selección", selection);
		editor.setDefaultTool(selection);

		editor.run();
	}
}
