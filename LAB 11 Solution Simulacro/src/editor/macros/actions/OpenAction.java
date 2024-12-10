package editor.macros.actions;

import java.io.IOException;

import editor.model.Document;

public class OpenAction implements Action {

	private String filename;

	public OpenAction(String filename) {
		this.filename = filename;
	}

	@Override
	public void execute(Document doc) {
		try {
			doc.open(filename);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
