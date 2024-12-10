package editor.figures.triangle;

import java.awt.Point;
import java.io.PrintWriter;

import editor.core.Figure;

public class Triangle implements Figure {
	
	public Triangle(Point v1, Point v2, Point v3) {
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	@Override
	public void draw(PrintWriter output) {
		output.format("Triángulo: (%d, %d), (%d, %d), (%d, %d)", v1.x, v1.y, v2.x, v2.y, v3.x, v3.y);
	}

	// En la simplificadísima implementación actual se considera que un punto
	// pertenece al triángulo sólo si coincide con uno de los tres vértices.
	//
	@Override
	public boolean contains(int x, int y) {
		Point point = new Point(x, y);
		return point.equals(v1) || point.equals(v2) || point.equals(v3);
	}

	@Override
	public void moveBy(int dx, int dy) {
		v1.translate(dx, dy);
		v2.translate(dx, dy);
		v3.translate(dx, dy);
	}
	
	public Point[] getVertices() {
		return new Point[] { v1, v2, v3 };
	}
	
	@Override
	public String toString() {
		return String.format("Triángulo: [%s, %s, %s]", v1, v2, v3);
	}
	
	private Point v1, v2, v3;
}
