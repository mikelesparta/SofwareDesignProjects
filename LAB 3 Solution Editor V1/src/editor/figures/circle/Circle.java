package editor.figures.circle;

import java.io.PrintWriter;

import editor.figures.Figure;

public class Circle implements Figure {

	private int x, y; // coordenadas centro del círculo
	private int radious;

	public Circle(int x, int y, int radious) {
		this.x = x;
		this.y = y;
		this.radious = radious;
	}

	@Override
	public void draw(PrintWriter output) {
		output.format("Círculo: (%d, %d), radio = %d", x, y, radious);
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
		return String.format("Círculo: centro = (%d, %d), radio = %d", x, y, radious);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getRadious() {
		return radious;
	}
}
