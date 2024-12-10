package editor.core;

import java.util.Stack;

public class History {

	private Stack<Action> undoableActions = new Stack<>();
	private Stack<Action> redoableActions = new Stack<>();

	public void addUndoableAction(Action action) {
		clear();
		undoableActions.push(action);
	}

	public void undo() {
		if (!canBeUndone())
			throw new IllegalStateException("No hay acciones que deshacer");

		Action action = undoableActions.pop();
		action.undo();

		redoableActions.push(action);
	}

	public void redo() {
		if (!canBeRedone())
			throw new IllegalStateException("No hay acciones que repetir");

		Action action = redoableActions.pop();
		action.redo();

		undoableActions.push(action);
	}

	public boolean canBeRedone() {
		return !redoableActions.isEmpty();
	}

	public boolean canBeUndone() {
		return !undoableActions.isEmpty();
	}

	// Limpia el historial de acciones deshechas (es decir, que pueden repetirse).
	// Se llama cada vez que se ejecuta una nueva acción en el editor, de manera
	// normal (es decir, a través de la interfaz de usuario y no como resultado
	// de volver a ejecutar una acción previamente deshecha), ya que lo contrario
	// no tendría sentido desde el punto de vista de la secuencia temporal.
	//
	private void clear() {
		redoableActions.clear();
	}
}
