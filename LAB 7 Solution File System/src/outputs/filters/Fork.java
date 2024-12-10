package outputs.filters;

import java.io.IOException;

import outputs.Output;

public class Fork extends AbstractFilter {

	private Output output2;

	public Fork(Output output1, Output output2) {
		super(output1);
		this.output2 = output2;
	}

	public Fork(Output output) {
		super(output);
	}

	public void send(char c) throws IOException {
		output.send(c);
		output2.send(c);
	}

	public void close() throws IOException {
		super.close();
		output2.close();
	}
}
