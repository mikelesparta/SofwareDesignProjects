package game;

import java.awt.Point;

import platform.Image2D;

// PATRÓN TEMPLATE METHOD
public abstract class BallGame {

	protected abstract Image2D loadImage(String file);

	protected abstract Point getPosition();

	protected abstract void drawBall(Image2D image, Point point);

	public void play() {
		Image2D image = loadImage("ball.png");

		// Lógica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = getPosition();
			drawBall(image, point);
		}
	}
}
