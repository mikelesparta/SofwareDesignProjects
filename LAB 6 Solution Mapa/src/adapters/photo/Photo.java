package adapters.photo;

import model.Coordinates;

public class Photo {

	private String description, user;

	private Coordinates coordinates;

	public Photo(String description, String user, Coordinates coordinates) {
		this.description = description;
		this.user = user;
		this.coordinates = coordinates;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void show() {
		System.out.printf("Abriendo la foto %s...\n", description);
	}
}
