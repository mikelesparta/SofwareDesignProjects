package vm;

public interface Instruction {

	void execute(Context context) throws ProgramException;
}
