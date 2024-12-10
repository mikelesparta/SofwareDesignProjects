package outputs.filters;

import java.io.IOException;

import outputs.Output;

public abstract class AbstractFilter implements Output {

	protected Output output;

	public AbstractFilter(Output output) {
		this.output = output;
	}

	public void close() throws IOException {
		output.close();
	}
}