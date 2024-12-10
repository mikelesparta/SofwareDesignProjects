package outputs.filters;

import java.io.IOException;

import outputs.Output;

public class Encryptor extends AbstractFilter {

	public Encryptor(Output output) {
		super(output);
	}

	public void send(char c) throws IOException {
		output.send((char) (Character.isLetterOrDigit(c) ? c + 1 : c));
	}
}