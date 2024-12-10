package editor.tools;

import java.awt.Point;

import editor.core.*;

public class SelectionTool implements Tool {
	
	private Editor editor;
	private Figure figure;
	private Point lastPosition;
	
	public SelectionTool(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void clickOn(int x, int y) {
		figure = editor.getDrawing().findFigureAt(x, y);
		lastPosition = new Point(x, y);
	}

	@Override
	public void moveTo(int x, int y) {
		if (figure == null) // Si no se está arrastrando una figura no tiene efecto
			return;
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
