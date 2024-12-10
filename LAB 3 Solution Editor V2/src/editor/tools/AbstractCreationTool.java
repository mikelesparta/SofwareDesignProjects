package editor.tools;

import java.awt.Point;

import editor.core.*;

public abstract class AbstractCreationTool implements Tool {

	private Editor editor;
	
	private Point start, end;
	private Point upperLeft, lowerRight;
	
	protected AbstractCreationTool(Editor editor) {
		this.editor = editor;		
	}

	@Override
	public void clickOn(int x, int y) {
		start = new Point(x, y);
	}

	@Override
	public void moveTo(int x, int y) {
 		if (start == null) // Sin efecto si no se pulsó antes (no se está arrastrando)
			return;
 		end = new Point(x, y);
	}
	
	@Override
	public void release() {	
		if (start == null) // (No tiene sentido un evento de soltar si no se pulsó antes)
			return;
		setCorners();
		Figure newFigure = createFigure(upperLeft, lowerRight);
		editor.getDrawing().addFigure(newFigure);
		editor.toolDone();
	}
	
	protected abstract Figure createFigure(Point upperLeft, Point lowerRight);

	// Calcula las esquinas superior izquierda e inferior derecha, con independencia
	// de hacia dónde se haya arrastrado el cursor desde la posición inicial.
	//
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
