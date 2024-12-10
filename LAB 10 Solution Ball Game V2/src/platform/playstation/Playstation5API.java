package platform.playstation;

import java.awt.Point;

import platform.Image2D;

public class Playstation5API {

	private Point point = new Point(0, 0);

	public Image2D loadGraphics(String name) {
		System.out.println("(PS5) Cargada imagen '" + name + "' del DVD.");
		return new Image2D(name, 10, 10);
	}

	public void render(int x, int y, Image2D image) {
		System.out.println("(PS5) Dibujando '" + image.getName() + "' en [" + x + "," + y + "]");
	}

	public Point getJoystick() {
		point.translate(10, 10);
		return new Point(point);
	}
}