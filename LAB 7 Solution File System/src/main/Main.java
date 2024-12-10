package main;

import java.io.IOException;

import filesystem.FileSystem;
import outputs.Bluetooth;
import outputs.FileOutput;
import outputs.Internet;
import outputs.filters.Counter;
import outputs.filters.Encryptor;
import outputs.filters.Fork;
import outputs.filters.LinesFilter;
import outputs.filters.SpaceRemover;

public class Main {

	public static void main(String[] args) throws IOException {
		FileSystem system = new FileSystem();

		// -- Diseño de Referencia
		// Cambios: Añadir 3 clases (LinesFilter, SpacesFilter y Encriptor)

		system.copyFile("privado.txt", new LinesFilter(new FileOutput("privado2.txt")));
		system.copyFile("privado.txt", new Encryptor(new Internet("156.17.11.196")));
		system.copyFile("privado.txt", new Encryptor(new SpaceRemover(new Bluetooth("Galaxy de Raúl"))));

		// -- Modificación 1: que se pueda encriptar al escribir en fichero
		// Cambios: Ninguno

		System.out.println("\nMODIFICACIÓN 1");

		system.copyFile("privado.txt", new LinesFilter(new FileOutput("a.txt")));
		system.copyFile("privado.txt", new LinesFilter(new Encryptor(new FileOutput("b.txt"))));
		system.copyFile("privado.txt", new LinesFilter(new Encryptor(new Internet("156.17.11.196"))));

		// -- Modificación 2: en Internet que se pueda normalizar y/o eliminar espacios
		// Cambios: Ninguno

		System.out.println("\nMODIFICACIÓN 2");

		system.copyFile("privado.txt", new LinesFilter(new Internet("156.17.11.196")));
		system.copyFile("privado.txt", new SpaceRemover(new Internet("156.17.11.196")));
		system.copyFile("privado.txt", new SpaceRemover(new LinesFilter(new Internet("156.17.11.196"))));

		// -- Modificación 3: Contar caracteres escritos
		// Cambios: Añadir clase Counter

		System.out.println("\nMODIFICACIÓN 3");

		Counter counter = new Counter(new Internet("156.17.11.196"));
		system.copyFile("privado.txt", new LinesFilter(counter));
		System.out.println("Modificacion 3a: " + counter.getCounter());

		// (Otra forma de hacerlo:)
		counter = new Counter(new Internet("156.17.11.196"));
		system.copyFile("privado.txt", new LinesFilter(counter = new Counter(new Internet("156.17.11.196"))));
		System.out.println("Modificacion 3b: " + counter.getCounter());

		// -- Modificación 4: Poder contar caracteres también antes de normalizar

		System.out.println("\nMODIFICACIÓN 4");

		Counter after = new Counter((new Internet("156.17.11.196")));
		Counter before = new Counter(new LinesFilter(after));
		system.copyFile("privado.txt", before);
		System.out.println("Modificacion 4a: " + before.getCounter() + " -> " + after.getCounter());

		// Otra forma de hacerlo:
		system.copyFile("privado.txt",
				before = new Counter(new LinesFilter(after = new Counter((new Internet("156.17.11.196"))))));
		System.out.println("Modificacion 4b: " + before.getCounter() + " -> " + after.getCounter());

		// -- Modificación 5: Poder contar caracteres también antes de normalizar
		// Cambios: Añadir clase Fork

		System.out.println("\nMODIFICACIÓN 5");

		system.copyFile("privado.txt",
				new Encryptor(new Fork(new Internet("156.17.11.196"), new Bluetooth("Galaxy de Raúl"))));
		system.copyFile("privado.txt",
				new SpaceRemover(new Fork(new Internet("156.17.11.196"), new Bluetooth("Galaxy de Raúl"))));
	}
}
