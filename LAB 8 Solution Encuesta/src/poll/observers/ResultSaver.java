package poll.observers;

import poll.Observer;
import poll.Poll;

public class ResultSaver implements Observer {

	@Override
	public void update(Poll poll) {
		System.out.println("Guardando resultados...");
	}
}
