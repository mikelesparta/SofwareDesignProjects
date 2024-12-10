package editor.core;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Drawing {

	private List<Figure> figures = new ArrayList<>();

	public void addFigure(Figure figure) {
		figures.add(0, figure);
	}

	public void removeFigure(Figure figure) {
		figures.remove(figure);
	}

	// Devuelve la figura en esa posición si la hay
	// Si existan varias devuelve la última que se añadió
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

	public List<Figure> getAllFigures() {
		return Collections.unmodifiableList(figures);
	}
}
