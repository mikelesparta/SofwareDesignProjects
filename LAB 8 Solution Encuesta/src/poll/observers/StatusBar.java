package poll.observers;

import poll.Observer;
import poll.Poll;

public class StatusBar implements Observer {

	@Override
	public void update(Poll poll) {
		System.out.println("Barra de estado: número de síes: " + poll.getYeses() + " número de nos: " + poll.getNos());
	}
}
