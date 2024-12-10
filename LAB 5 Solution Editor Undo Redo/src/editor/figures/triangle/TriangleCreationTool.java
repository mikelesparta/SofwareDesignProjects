package editor.figures.triangle;

import java.awt.Point;

import editor.actions.CreateFigureAction;
import editor.core.Action;
import editor.core.Editor;
import editor.core.Tool;

public class TriangleCreationTool implements Tool {

	private int numberOfVertices;
	private Point[] vertices = new Point[3];

	private Editor editor;

	public TriangleCreationTool(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void clickOn(int x, int y) {
		vertices[numberOfVertices++] = new Point(x, y);

		if (numberOfVertices == 3) {
			Action createTriangle = new CreateFigureAction(new Triangle(vertices[0], vertices[1], vertices[2]),
					editor.getDrawing());

			createTriangle.redo();
			editor.getHistory().addUndoableAction(createTriangle);
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
}
