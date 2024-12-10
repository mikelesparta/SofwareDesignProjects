package editor.macros.actions;

import editor.model.Document;

public class ReplaceAction implements Action {

	private String source, destination;

	public ReplaceAction(String source, String destination) {
		this.source = source;
		this.destination = destination;
	}

	@Override
	public void execute(Document doc) {
		doc.replace(source, destination);
	}
}
