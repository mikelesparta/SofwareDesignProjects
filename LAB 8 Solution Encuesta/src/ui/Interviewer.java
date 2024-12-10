package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import poll.Poll;

public class Interviewer {

	private BufferedReader input;

	public void fill(Poll poll) throws IOException {
		input = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Acciones: [salir] - [si] - [no]");

		while (true) {
			System.out.println("\nPregunta: " + poll.getQuestion() + "> ");

			String[] line = input.readLine().split(" ");

			if (line[0].equals("salir"))
				return;

			if (line[0].equals("si"))
				poll.incrementYeses();

			if (line[0].equals("no"))
				poll.incrementNos();
		}
	}
}
