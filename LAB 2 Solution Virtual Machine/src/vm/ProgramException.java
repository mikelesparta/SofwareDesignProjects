package vm;

public class ProgramException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public ProgramException(Instruction instruction, String message) {
		super("Error de ejecución en la instrucción " + instruction + ": " + message);
	}
}
