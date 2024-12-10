package platform.playstation;

import java.awt.Point;

import game.BallGame;
import platform.Image2D;

public class PlayStationBallGame extends BallGame {

	private Playstation5API play;

	public PlayStationBallGame(Playstation5API play) {
		this.play = play;
	}

	@Override
	public Image2D loadImage(String file) {
		return play.loadGraphics(file);
	}

	@Override
	public Point getPosition() {
		return play.getJoystick();
	}

	@Override
	public void drawBall(Image2D image, Point point) {
		play.render(point.x, point.y, image);
	}

}
