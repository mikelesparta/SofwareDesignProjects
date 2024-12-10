package editor.tools;

import java.awt.Point;

import editor.actions.CreateFigureAction;
import editor.core.Action;
import editor.core.Editor;
import editor.core.Figure;
import editor.core.Tool;

public abstract class AbstractCreationTool implements Tool {

	private Editor editor;

	private Point start, end, upperLeft, lowerRight;

	protected AbstractCreationTool(Editor editor) {
		this.editor = editor;
	}

	@Override
	public void clickOn(int x, int y) {
		start = new Point(x, y);
	}

	@Override
	public void moveTo(int x, int y) {
		// Se debe haber pulsado antes
		if (start == null)
			return;

		end = new Point(x, y);
	}

	@Override
	public void release() {
		// Se debe haber pulsado antes
		if (start == null)
			return;

		setCorners();

		// Crea la nueva figura
		Figure newFigure = createFigure(upperLeft, lowerRight);

		Action createFigure = new CreateFigureAction(newFigure, editor.getDrawing());
		createFigure.redo();

		// Añade la acción a deshacer
		editor.getHistory().addUndoableAction(createFigure);
		editor.toolDone();
	}

	protected abstract Figure createFigure(Point upperLeft, Point lowerRight);

	// Calcula las esquinas con independencia de hacia dónde
	// se haya arrastrado el cursor
	private void setCorners() {
		upperLeft = new Point();
		upperLeft.x = (start.x <= end.x) ? start.x : end.x;
		upperLeft.y = (start.y <= end.y) ? start.y : end.y;
		lowerRight = new Point();
		lowerRight.x = (end.x >= start.x) ? end.x : start.x;
		lowerRight.y = (end.y >= start.y) ? end.y : start.y;
	}

	@Override
	public void unselect() {
		start = end = null;
	}

	@Override
	public String toString() {
		return "Herramienta de creación abstracta";
	}
}
