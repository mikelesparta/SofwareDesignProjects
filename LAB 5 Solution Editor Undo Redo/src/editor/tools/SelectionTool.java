package editor.tools;

import java.awt.Point;

import editor.actions.MoveAction;
import editor.core.Editor;
import editor.core.Figure;
import editor.core.Tool;

public class SelectionTool implements Tool {

	private Editor editor;

	private Figure figure;

	private Point lastPosition;

	// El total acumulado de desplazamiento horizontal y vertical que se aplicó
	// a la figura. Se han añadido respecto a la versión anterior del editor
	// para poder crear una acción que registre sólo el movimiento total acumulado,
	// y que luego pueda ser deshecha.
	private int dX, dY;

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
		// Si no se está arrastrando una figura no tiene efecto
		if (figure == null)
			return;

		figure.moveBy(x - lastPosition.x, y - lastPosition.y);

		// Se incrementa el desplazamiento total acumulado
		this.dX += (x - lastPosition.x);
		this.dY += (y - lastPosition.y);

		// Se actualiza la posición del cursor
		lastPosition = new Point(x, y);
	}

	@Override
	public void release() {
		figure = null;

		// Se añade la acción deshacer
		editor.getHistory().addUndoableAction(new MoveAction(figure, dX, dY));
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
