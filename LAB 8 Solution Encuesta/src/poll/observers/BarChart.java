package poll.observers;

import poll.Observer;
import poll.Poll;

public class BarChart implements Observer {

	@Override
	public void update(Poll poll) {
		System.out.println("Dibujando gráfico de barras...");
	}
}
