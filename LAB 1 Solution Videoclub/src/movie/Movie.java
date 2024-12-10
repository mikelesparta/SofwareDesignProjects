package movie;

import movie.type.ChildrenMovie;
import movie.type.NewReleaseMovie;
import movie.type.RegularMovie;

public class Movie {

	public static final MovieType REGULAR = new RegularMovie();
	public static final MovieType NEW_RELEASE = new NewReleaseMovie();
	public static final MovieType CHILDRENS = new ChildrenMovie();

	private String title;

	private MovieType movieType;

	public Movie(String title, MovieType movieType) {
		this.title = title;
		this.movieType = movieType;
	}

	public String getTitle() {
		return title;
	}

	public void setType(MovieType movieType) {
		this.movieType = movieType;
	}

	public double getCharge(int daysRented) {
		return movieType.getCharge(daysRented);
	}

	public int getFrequentRenterPoints(int daysRented) {
		return movieType.getFrequentRenterPoints(daysRented);
	}
}
