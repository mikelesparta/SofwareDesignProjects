package poll.observers;

import poll.Observer;
import poll.Poll;

public class FrequencyObserver implements Observer {

	private int counter, frequency;

	private Observer observer;

	// PATRÓN DECORATOR
	public FrequencyObserver(int frequency, Observer observer) {
		this.observer = observer;
		this.counter = this.frequency = frequency;
	}

	@Override
	public void update(Poll poll) {
		if (counter == frequency) {
			observer.update(poll);
			counter = 1;
		} else
			counter++;
	}
}
