package editor.macros;

import java.util.HashMap;
import java.util.Map;

import editor.macros.actions.Action;
import editor.model.Document;

public class MacroRecorder {

	private Document doc;

	private Map<String, Macro> macros = new HashMap<>();

	private Macro currentMacro;

	private String name;

	public MacroRecorder(Document doc) {
		this.doc = doc;
	}

	private boolean isRecording() {
		return currentMacro != null;
	}

	public void record(String name) {
		this.currentMacro = new Macro();
		this.name = name;
	}

	public void stop() {
		this.macros.put(name, currentMacro);
		this.currentMacro = null;
	}

	public void play(String name) {
		Macro macro = macros.get(name);
		execute(macro);
	}

	public void execute(Action action) {
		action.execute(doc);

		if (isRecording())
			currentMacro.addAction(action);
	}
}