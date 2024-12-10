package platform.windows;

import java.awt.Point;

import game.BallGame;
import platform.Image2D;

public class WindowsBallGame extends BallGame {

	private WindowsAPI windows;

	public WindowsBallGame(WindowsAPI windows) {
		this.windows = windows;
	}

	@Override
	public Image2D loadImage(String file) {
		return windows.loadFile(file);
	}

	@Override
	public Point getPosition() {
		return windows.getMouseClick();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		windows.paint(point.x, point.y, image);
	}
}
