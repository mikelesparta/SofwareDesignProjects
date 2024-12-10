package editor.figures.triangle;

import java.awt.Point;

import editor.core.*;

public class TriangleCreationTool implements Tool {	
	
	public TriangleCreationTool(Editor editor) {
		this.editor = editor;		
	}

	@Override
	public void clickOn(int x, int y) {
		vertices[numberOfVertices++] = new Point(x, y);
		if (numberOfVertices == 3) {
			editor.getDrawing().addFigure(new Triangle(vertices[0], vertices[1], vertices[2]));
			editor.toolDone();			
		}
	}

	@Override
	public void moveTo(int x, int y) {
		// No hay que hacer nada
	}

	@Override
	public void release() {
		// No hay que hacer nada
	}
	
	@Override
	public void unselect() {
		vertices = new Point[3];
		numberOfVertices = 0;
	}
	
	@Override
	public String toString() {
		return "Herramienta de creación de triángulo";
	}
	
	private Editor editor;
	private int numberOfVertices;
	private Point[] vertices = new Point[3];	
}
