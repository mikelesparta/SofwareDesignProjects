package main;

import java.io.IOException;

import editor.core.*;
import editor.figures.circle.CircleCreationTool;
import editor.figures.rectangle.RectangleCreationTool;
import editor.figures.triangle.TriangleCreationTool;

public class Main  {
	
	public static void main(String[] args) throws IOException {
				
		Editor editor = new Editor();
		
		// Se crean y registran las herramientas
		editor.addTool("rectangulo", new RectangleCreationTool(editor));
		editor.addTool("circulo", new CircleCreationTool(editor));
		editor.addTool("triangulo", new TriangleCreationTool(editor));
		
		editor.run();
	}	
}
