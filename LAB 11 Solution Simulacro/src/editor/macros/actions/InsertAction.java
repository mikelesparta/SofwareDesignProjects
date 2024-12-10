package editor.macros.actions;

import editor.model.Document;

public class InsertAction implements Action {

	private String[] wordsToAppend;

	public InsertAction(String[] words) {
		this.wordsToAppend = words;
	}

	@Override
	public void execute(Document doc) {
		doc.insert(wordsToAppend);
	}
}
