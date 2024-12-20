package outputs;

import java.io.FileWriter;
import java.io.IOException;

public class FileOutput implements Output {

	private FileWriter file;

	public FileOutput(String fileName) throws IOException {
		file = new FileWriter(fileName);
	}

	public void send(char c) throws IOException {
		file.append(c);
	}

	public void close() throws IOException {
		file.close();
	}
}
