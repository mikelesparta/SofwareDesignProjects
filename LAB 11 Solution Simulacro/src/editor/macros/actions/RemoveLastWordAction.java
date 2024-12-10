package editor.macros.actions;

import editor.model.Document;

public class RemoveLastWordAction implements Action {

	@Override
	public void execute(Document doc) {
		doc.removeLastWord();
	}
}
