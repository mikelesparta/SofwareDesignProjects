package editor.tools;

import java.awt.Point;

import editor.core.Editor;
import editor.figures.Figure;

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

		Figure newFigure = createFigure(upperLeft, lowerRight);
		editor.getDrawing().addFigure(newFigure);
		editor.toolDone(); // Vuelve a SelectionTool
	}

	// FACTORY METHOD: para la creación de la figura, la subclases deciden cual
	protected abstract Figure createFigure(Point upperLeft, Point lowerRight);

	@Override
	public void unselect() {
		start = end = null;
	}

	// Calcula las esquinas independientemente de hacia dónde se arrastre
	private void setCorners() {
		upperLeft = new Point();
		upperLeft.x = (start.x <= end.x) ? start.x : end.x;
		upperLeft.y = (start.y <= end.y) ? start.y : end.y;

		lowerRight = new Point();
		lowerRight.x = (end.x >= start.x) ? end.x : start.x;
		lowerRight.y = (end.y >= start.y) ? end.y : start.y;
	}

	@Override
	public String toString() {
		return "Herramienta de creación abstracta";
	}
}
