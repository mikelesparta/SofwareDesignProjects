package vm.instructions;

import java.util.Scanner;

import vm.Context;
import vm.Instruction;
import vm.ProgramException;

public class Input extends AbstractInstruction implements Instruction {

	private Scanner console = new Scanner(System.in);

	@Override
	protected void run(Context context) throws ProgramException {
		System.out.print("Escriba un número entero: ");
		
		int value = readValue();
		context.push(value);
	}

	private int readValue() {
		while (!console.hasNextInt()) {
			System.out.println("¡Debe ser un valor entero!");
			System.out.print("Escriba un entero: ");
			console.next();
		}

		return console.nextInt();
	}

	@Override
	public String toString() {
		return "INPUT";
	}
}
