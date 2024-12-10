package platform.android;

import java.awt.Point;

import game.BallGame;
import platform.Image2D;

public class AndroidBallGame extends BallGame {

	private AndroidAPI android;

	public AndroidBallGame(AndroidAPI android) {
		this.android = android;
	}

	@Override
	public Image2D loadImage(String file) {
		return android.loadResource(file);
	}

	@Override
	public Point getPosition() {
		return android.getTouch();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		android.draw(point.x, point.y, image);
	}

}
