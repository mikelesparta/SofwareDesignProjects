package platform;

import java.awt.Point;

public interface PlatformAPI {

	Image2D loadImage(String file);

	Point getPosition();

	void drawBall(int x, int y, Image2D image);
}
