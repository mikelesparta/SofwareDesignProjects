package editor.figures.circle;

import java.io.PrintWriter;

import editor.core.Figure;

public class Circle implements Figure {

	private int x, y; // coordenadas
	private int radius;

	public Circle(int x, int y, int radious) {
		this.x = x;
		this.y = y;
		this.radius = radious;
	}

	@Override
	public void draw(PrintWriter output) {
		output.format("Círculo: (%d, %d), radio = %d", x, y, radius);
	}

	@Override
	public boolean contains(int x, int y) {
		double distance = Math.sqrt(Math.pow(x - getX(), 2) + Math.pow(y - getY(), 2));
		return distance < getRadious();
	}

	@Override
	public void moveBy(int dx, int dy) {
		x += dx;
		y += dy;
	}

	@Override
	public String toString() {
		return String.format("Círculo: centro = (%d, %d), radio = %d", x, y, radius);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadious() {
		return radius;
	}
}
