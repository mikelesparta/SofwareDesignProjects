package editor.actions;

import editor.core.Action;
import editor.core.Figure;

public class MoveAction implements Action {

	private Figure figure;

	private int dx, dy;

	public MoveAction(Figure figure, int dx, int dy) {
		this.figure = figure;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void redo() {
		figure.moveBy(dx, dy);
	}

	@Override
	public void undo() {
		figure.moveBy(-dx, -dy);
	}

	@Override
	public String toString() {
		return String.format("Mover %s %d píxeles en horizontal y %d en vertical", figure, dx, dy);
	}
}
