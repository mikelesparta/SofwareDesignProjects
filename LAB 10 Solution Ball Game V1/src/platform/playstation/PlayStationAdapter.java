package platform.playstation;

import java.awt.Point;

import platform.Image2D;
import platform.PlatformAPI;

public class PlayStationAdapter implements PlatformAPI {

	private Playstation5API playstation;

	public PlayStationAdapter(Playstation5API playstation) {
		this.playstation = playstation;
	}

	@Override
	public Point getPosition() {
		return playstation.getJoystick();
	}

	@Override
	public void drawBall(int x, int y, Image2D image) {
		playstation.render(x, y, image);
	}

	@Override
	public Image2D loadImage(String file) {
		return playstation.loadGraphics(file);
	}
}