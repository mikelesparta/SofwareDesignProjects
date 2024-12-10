package main;

import java.util.ArrayList;
import java.util.List;

import movie.Rental;

public class Customer {

	private String name;

	private List<Rental> rentals = new ArrayList<Rental>();

	public Customer(String name) {
		this.name = name;
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		String result = "Rental Record for " + getName() + "\n";

		for (Rental rental : rentals)
			result += "\t" + rental.getMovie().getTitle() + "\t" + String.valueOf(rental.getCharge()) + "\n";

		// Añade las líneas de total
		result += "Amount owed is " + String.valueOf(getTotalAmount()) + "\n";
		result += "You earned " + String.valueOf(getTotalFrequentRenterPoints()) + " frequent renter points";

		return result;
	}

	private double getTotalAmount() {
		double result = 0;

		for (Rental rental : rentals)
			result += rental.getCharge();

		return result;
	}

	private int getTotalFrequentRenterPoints() {
		int result = 0;

		for (Rental each : rentals)
			result += each.getFrequentRenterPoints();

		return result;
	}
}
