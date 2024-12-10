package editor.tools;

import java.awt.Point;

import editor.core.Editor;
import editor.figures.Figure;

public class SelectionTool implements Tool {

	private Editor editor;

	private Figure figure;

	private Point lastPosition;

	public SelectionTool(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void clickOn(int x, int y) {
		// Selecciona la figura, si hay alguna en esa posición
		figure = editor.getDrawing().findFigureAt(x, y);
		lastPosition = new Point(x, y);
	}

	@Override
	public void moveTo(int x, int y) {
		// Primero se tiene que haber hecho click en una figura
		if (figure == null)
			return;

		// Se mueve la figura y se actualiza el último punto
		figure.moveBy(x - lastPosition.x, y - lastPosition.y);
		lastPosition = new Point(x, y);
	}

	@Override
	public void release() {
		figure = null;
	}

	@Override
	public void unselect() {
		figure = null;
	}

	@Override
	public String toString() {
		return "Herramienta de selección";
	}
}
