package main;

import java.io.IOException;

import vm.ProgramException;
import vm.VirtualMachine;
import vm.parser.Parser;
import vm.parser.ParserException;

public class Main {

	public static void main(String[] args) {

		Parser parser = new Parser();

		VirtualMachine vm = new VirtualMachine();

		try {
			vm.executeProgram(parser.parse("factorial.txt"));
		} catch (ParserException | ProgramException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("Error de E/S al leer el fichero de entrada");
		}
	}
}
