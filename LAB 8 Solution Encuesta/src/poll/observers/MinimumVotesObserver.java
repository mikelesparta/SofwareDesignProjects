package poll.observers;

import poll.Observer;
import poll.Poll;

public class MinimumVotesObserver implements Observer {

	private int minimumVotes;

	private Observer observer;

	// PATRÓN DECORATOR
	public MinimumVotesObserver(int minimumVotes, Observer observer) {
		this.minimumVotes = minimumVotes;
		this.observer = observer;
	}

	@Override
	public void update(Poll poll) {
		if (poll.getYeses() + poll.getNos() >= minimumVotes)
			observer.update(poll);
	}
}
