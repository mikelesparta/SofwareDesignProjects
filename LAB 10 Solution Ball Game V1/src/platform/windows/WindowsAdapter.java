package platform.windows;

import java.awt.Point;

import platform.Image2D;
import platform.PlatformAPI;

public class WindowsAdapter implements PlatformAPI {

	private WindowsAPI windows;

	public WindowsAdapter(WindowsAPI windows) {
		this.windows = windows;
	}

	@Override
	public Point getPosition() {
		return windows.getMouseClick();
	}

	@Override
	public void drawBall(int x, int y, Image2D image) {
		windows.paint(x, y, image);
	}

	@Override
	public Image2D loadImage(String file) {
		return windows.loadFile(file);
	}
}
