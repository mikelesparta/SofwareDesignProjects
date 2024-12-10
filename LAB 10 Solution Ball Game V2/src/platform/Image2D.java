package platform;

public class Image2D {

	private String name;
	private int width, height;
	private byte[] pixels;

	public Image2D(String name, int width, int height) {
		this.name = name;
		this.width = width;
		this.height = height;
	}

	public String getName() {
		return name;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public byte[] getPixels() {
		return pixels;
	}
}