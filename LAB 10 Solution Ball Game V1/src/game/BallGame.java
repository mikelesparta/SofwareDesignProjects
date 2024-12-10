package game;

import java.awt.Point;

import platform.Image2D;
import platform.PlatformAPI;

public class BallGame {

	private PlatformAPI platformAPI;

	public BallGame(PlatformAPI platformAPI) {
		this.platformAPI = platformAPI;
	}

	public void play() {
		Image2D image = loadImage("ball.png");

		// lógica principal del juego
		for (int i = 0; i < 10; i++) {
			Point point = getPosition();
			drawBall(image, point);
		}
	}

	private Image2D loadImage(String file) {
		return platformAPI.loadImage(file);
	}

	private Point getPosition() {
		return platformAPI.getPosition();
	}

	private void drawBall(Image2D image, Point point) {
		platformAPI.drawBall(point.x, point.y, image);
	}

	public PlatformAPI getDevice() {
		return platformAPI;
	}

	public void setDevice(PlatformAPI platformAPI) {
		this.platformAPI = platformAPI;
	}
}
