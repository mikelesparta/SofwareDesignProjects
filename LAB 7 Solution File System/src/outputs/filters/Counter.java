package outputs.filters;

import java.io.IOException;

import outputs.Output;

public class Counter extends AbstractFilter {

	private int counter;

	public Counter(Output output) {
		super(output);
	}

	public void send(char c) throws IOException {
		output.send(c);
		counter++;
	}

	public int getCounter() {
		return counter;
	}
}
