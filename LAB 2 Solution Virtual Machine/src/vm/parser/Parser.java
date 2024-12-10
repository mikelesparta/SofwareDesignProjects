package vm.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import vm.Instruction;
import vm.instructions.Add;
import vm.instructions.Input;
import vm.instructions.Jump;
import vm.instructions.JumpIfGreaterThan;
import vm.instructions.Load;
import vm.instructions.Mul;
import vm.instructions.Output;
import vm.instructions.Push;
import vm.instructions.Store;
import vm.instructions.Sub;

public class Parser {

	private int lineNumber;

	public List<Instruction> parse(String filename) throws IOException, ParserException {

		List<Instruction> program = new ArrayList<>();

		BufferedReader file = new BufferedReader(new FileReader(filename));

		String line;

		while ((line = file.readLine()) != null) {
			lineNumber++;

			// Salta la línea si está en blanco
			if (line.trim().length() == 0)
				continue;

			program.add(parseLine(lineNumber, line));
		}

		file.close();
		return program;
	}

	private Instruction parseLine(int lineNumber, String line) throws ParserException {

		String[] tokens = line.split(" ");
		String name = tokens[0].toUpperCase();

		if (name.equals("ADD"))
			return new Add();
		if (name.equals("SUB"))
			return new Sub();
		if (name.equals("MUL"))
			return new Mul();
		if (name.equals("LOAD"))
			return new Load();
		if (name.equals("STORE"))
			return new Store();
		if (name.equals("INPUT"))
			return new Input();
		if (name.equals("OUTPUT"))
			return new Output();

		// -- El resto de instrucciones necesitan un valor entero como argumento

		int argument = parseArgument(line);

		if (name.equals("PUSH"))
			return new Push(argument);
		if (name.equals("JMP"))
			return new Jump(argument);
		if (name.equals("JMPG"))
			return new JumpIfGreaterThan(argument);

		throw new ParserException(lineNumber, line, "Instrucción desconocida: " + name);
	}

	private int parseArgument(String line) throws ParserException {
		String[] tokens = line.split(" ");

		try {
			return Integer.parseInt(tokens[1]);
		} catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
			throw new ParserException(lineNumber, line,
					"La instrucción " + tokens[0] + " necesita como argumento un valor entero");
		}
	}
}
