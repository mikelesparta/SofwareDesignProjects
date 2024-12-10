package outputs.filters;

import java.io.IOException;

import outputs.Output;

public class SpaceRemover extends AbstractFilter {

	public SpaceRemover(Output output) {
		super(output);
	}

	public void send(char c) throws IOException {
		if (c != ' ')
			output.send(c);
	}
}
