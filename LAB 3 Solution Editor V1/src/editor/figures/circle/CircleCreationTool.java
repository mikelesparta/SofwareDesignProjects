package editor.figures.circle;

import java.awt.Point;

import editor.core.Editor;
import editor.figures.Figure;
import editor.tools.AbstractCreationTool;
import editor.tools.Tool;

public class CircleCreationTool extends AbstractCreationTool implements Tool {

	public CircleCreationTool(Editor editor) {
		super(editor);
	}

	@Override
	protected Figure createFigure(Point upperLeft, Point lowerRight) {
		int width = lowerRight.x - upperLeft.x;
		int height = lowerRight.y = upperLeft.y;
		int size = Math.max(width, height);
		int radious = size / 2;

		int x = upperLeft.x + radious;
		int y = upperLeft.y + radious;

		return new Circle(x, y, radious);
	}

	@Override
	public String toString() {
		return "Herramienta de creación de círculo";
	}
}
