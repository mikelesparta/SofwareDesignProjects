package vm;

import java.util.List;

public class VirtualMachine {

	private Context context;

	public void executeProgram(List<Instruction> instructions) throws ProgramException {
		context = new Context(instructions);

		while (context.getIp() < instructions.size())
			instructions.get(context.getIp()).execute(context);
	}

	/*
	 * Este método se proporciona (con visibilidad de paquete) únicamente para que
	 * esté accesible para las pruebas. Aparte de eso, no sería necesario en
	 * absoluto (las instrucciones no hacen uso de él, porque ya reciben el contexto
	 * de ejecución como parámetro). Se podría eliminar el método (eliminando las
	 * pruebas unitarias), manteniendo así la ocultación de la información de la
	 * máquina virtual.
	 */
	Context getContext() {
		if (context == null)
			throw new IllegalStateException("El contexto sólo se crea al ejecutar un programa");

		return context;
	}
}
