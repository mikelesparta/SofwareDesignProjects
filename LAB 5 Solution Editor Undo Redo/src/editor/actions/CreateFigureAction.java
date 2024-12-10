package editor.actions;

import editor.core.Action;
import editor.core.Drawing;
import editor.core.Figure;

public class CreateFigureAction implements Action {

	private Drawing drawing;

	private Figure figure;

	public CreateFigureAction(Figure figure, Drawing drawing) {
		this.figure = figure;
		this.drawing = drawing;
	}

	@Override
	public void redo() {
		drawing.addFigure(figure);
	}

	@Override
	public void undo() {
		drawing.removeFigure(figure);
	}

	@Override
	public String toString() {
		return "Añadir figura: " + figure;
	}
}
