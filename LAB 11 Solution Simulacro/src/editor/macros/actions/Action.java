package editor.macros.actions;

import editor.model.Document;

public interface Action {

	// Patrón COMMAND
	// El Invoker son las macros grabadas, almacenadas a su vez en MacroRecorder.
	// El Receiver es el documento sobre el que operan las ConcreteCommand.

	void execute(Document doc);
}
