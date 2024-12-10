package editor.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import editor.figures.Figure;

public class Drawing {

	private List<Figure> figures = new ArrayList<>();

	public void addFigure(Figure figure) {
		figures.add(0, figure);
	}

	public void removeFigure(Figure figure) {
		figures.remove(figure);
	}

	public List<Figure> getAllFigures() {
		return Collections.unmodifiableList(figures);
	}

	// Devuelve la figura en esa posición, si es que hay alguna
	// Si existen varias, devuelve la que está encima del resto (última en añadirse)
	public Figure findFigureAt(int x, int y) {
		for (Figure figure : figures)
			if (figure.contains(x, y))
				return figure;

		return null;
	}

	public void draw(PrintWriter output) {
		if (figures.isEmpty())
			return;

		output.println("Figuras: ");

		for (Figure figure : figures) {
			output.print(" - ");
			figure.draw(output);
			output.println();
		}
	}
}
