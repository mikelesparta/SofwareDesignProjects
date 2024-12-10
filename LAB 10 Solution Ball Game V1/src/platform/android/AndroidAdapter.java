package platform.android;

import java.awt.Point;

import platform.Image2D;
import platform.PlatformAPI;

public class AndroidAdapter implements PlatformAPI {

	private AndroidAPI android;

	public AndroidAdapter(AndroidAPI android) {
		this.android = android;
	}

	@Override
	public Point getPosition() {
		return android.getTouch();
	}

	@Override
	public void drawBall(int x, int y, Image2D image) {
		android.draw(x, y, image);
	}

	@Override
	public Image2D loadImage(String file) {
		return android.loadResource(file);
	}
}
