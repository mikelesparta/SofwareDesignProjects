package model;

public class Coordinates {
	
	private double longitude, latitude;
	
	public Coordinates(double longitude, double latitude) {
		this.longitude = longitude;
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	@Override
	public String toString() {
		return "[longitude = " + longitude + ", latitude = " + latitude + "]";
	}
}