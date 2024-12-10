package main;

import java.io.IOException;

import poll.Poll;
import poll.observers.BarChart;
import poll.observers.FrequencyObserver;
import poll.observers.MinimumVotesObserver;
import poll.observers.PieChart;
import poll.observers.ResultSaver;
import poll.observers.StatusBar;
import ui.Interviewer;

public class Main {

	public static void main(String[] args) throws IOException {
		Poll poll = new Poll("¿Está a favor de la energia nuclear?");

		BarChart barChart = new BarChart();

		poll.addObserver(new PieChart());
		poll.addObserver(barChart);
		poll.addObserver(new ResultSaver());

		// MODIFICACIÓN 1
		// Una línea de estado es una línea de texto que indica el estado actual de la
		// votación (nº de síes = <x>, nº de noes = <y>).
		poll.addObserver(new StatusBar());

		// MODIFICACIÓN 2
		// Quitar el gráfico de barras
		poll.removeObserver(barChart);

		// MODIFICACIÓN 3
		// El gráfico circular se dibuje a partir del tercer voto
		poll.addObserver(new MinimumVotesObserver(3, new BarChart()));

		// MODIFICACIÓN 4
		// Vuelve el gráfico de barras, pero se dibuja cada tres votos
		// (primero, cuarto, séptimo...)
		poll.addObserver(new FrequencyObserver(3, new BarChart()));

		// MODIFICACIÓN 5
		// La línea de estado se imprime a partir del cuarto voto y a partir de ahí,
		// se imprime cada dos: (cuarto, sexto, octavo...)
		poll.addObserver(new MinimumVotesObserver(4, new FrequencyObserver(2, new StatusBar())));

		// Inicio del cuestionario
		Interviewer interviewer = new Interviewer();
		interviewer.fill(poll);
	}
}
