package poll;

import java.util.ArrayList;
import java.util.List;

public class Poll {

	private String question;

	private int yeses, nos;

	private List<Observer> observers = new ArrayList<>();

	public Poll(String question) {
		this.question = question;
	}

	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}

	public void incrementYeses() {
		yeses++;
		notifyObservers();
	}

	public void incrementNos() {
		nos++;
		notifyObservers();
	}

	private void notifyObservers() {
		for (Observer o : observers)
			o.update(this);
	}

	public String getQuestion() {
		return question;
	}

	public int getYeses() {
		return yeses;
	}

	public int getNos() {
		return nos;
	}
}
