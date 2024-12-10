package vm;

import java.util.List;

public class Context {

	// El programa (la lista de instrucciones) podría estar perfectamente en la
	// propia máquina virtual y no aquí. Si se ha movido al contexto es
	// simplemente para, en caso de haber algún error, que se pueda mostrar en
	// qué instrucción concreta se produjo, así como para controlar que al
	// modificar el puntero de instrucción el nuevo valor sea uno dentro del
	// área de instrucciones del programa actual.

	private List<Instruction> instructions;

	// $ Estructuras de datos de bajo nivel de la máquina virtual

	private int[] memory = new int[1024];
	private int[] stack = new int[32];

	private int ip = 0, sp = 0;

	Context(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	// $ Operaciones que manipulan la pila

	public void push(int value) throws ProgramException {
		if (sp == stack.length)
			executionError("La pila está llena, no se ha podido añadir el valor: " + value);

		stack[sp++] = value;
	}

	public int pop() throws ProgramException {
		if (sp == 0)
			executionError("La pila está vacía, no se ha podido extraer un valor");

		return stack[--sp];
	}

	// $ Operaciones que manipulan el puntero de instrucciones

	public void setIp(int ip) throws ProgramException {
		if (ip < 0 || ip >= instructions.size())
			executionError("Acceso fuera del área de instrucciones: " + ip);

		this.ip = ip;
	}

	public void incrementIp() {
		ip++;
		assert ip <= instructions.size() : "¿Cómo ha podido llegar a tener IP un valor inválido al incrementarse?";
	}

	int getIp() {
		return ip;
	}

	// $ Operaciones que manipulan la memoria

	public int read(int address) throws ProgramException {
		checkMemoryAddress(address);

		return memory[address];
	}

	public void write(int address, int value) throws ProgramException {
		checkMemoryAddress(address);

		memory[address] = value;
	}

	private void checkMemoryAddress(int address) throws ProgramException {
		if (address < 0 || address > memory.length)
			executionError("Dirección de memoria incorrecta: " + address);
	}

	private void executionError(String message) throws ProgramException {
		throw new ProgramException(instructions.get(ip), message);
	}
}
