package editor.figures.rectangle;

import java.awt.Point;

import editor.core.Editor;
import editor.figures.Figure;
import editor.tools.AbstractCreationTool;

public class RectangleCreationTool extends AbstractCreationTool {

	public RectangleCreationTool(Editor editor) {
		super(editor);
	}

	@Override
	protected Figure createFigure(Point upperLeft, Point lowerRight) {
		return new Rectangle(upperLeft, lowerRight);
	}

	@Override
	public String toString() {
		return "Herramienta de creación de rectángulo";
	}
}
