package editor.macros;

import java.util.ArrayList;
import java.util.List;

import editor.macros.actions.Action;
import editor.model.Document;

// Patrón COMPOSITE & COMMAND
// Los nodos son las acciones individuales

public class Macro implements Action {

	private List<Action> actions = new ArrayList<Action>();

	public void addAction(Action action) {
		actions.add(action);
	}

	@Override
	public void execute(Document doc) {
		for (Action action : actions)
			action.execute(doc);
	}
}
