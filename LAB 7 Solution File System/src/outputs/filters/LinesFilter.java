package outputs.filters;

import java.io.IOException;

import outputs.Output;

public class LinesFilter extends AbstractFilter {

	public LinesFilter(Output output) {
		super(output);
	}

	public void send(char c) throws IOException {
		if (c != '\r')
			output.send(c);
	}
}
