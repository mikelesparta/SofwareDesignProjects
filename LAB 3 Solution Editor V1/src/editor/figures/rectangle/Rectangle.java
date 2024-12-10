package editor.figures.rectangle;

import java.awt.Point;
import java.io.PrintWriter;

import editor.figures.Figure;

public class Rectangle implements Figure {

	private int x, y;
	private int width, height;

	public Rectangle(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Rectangle(Point upperLeft, Point lowerRight) {
		this(upperLeft.x, upperLeft.y, lowerRight.x - upperLeft.x, lowerRight.y - upperLeft.y);
	}

	@Override
	public void draw(PrintWriter output) {
		output.format("Rectángulo: (%d, %d), ancho = %d, alto = %d", x, y, width, height);
	}

	@Override
	public boolean contains(int x, int y) {
		return this.x <= x && x <= this.x + width && this.y <= y && y <= this.y + height;
	}

	@Override
	public void moveBy(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	@Override
	public String toString() {
		return String.format("Rectángulo: (%d, %d), ancho = %d, alto = %d", x, y, width, height);
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
