package editor.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import editor.figures.rectangle.Rectangle;

public class DrawingTest {

	private Drawing drawing;
	private Rectangle r1, r2, r3;
	
	@Before
	public void setUp() {
		drawing = new Drawing();
		r1 = new Rectangle(100, 80, 150, 120); // de la (100, 80) a la (250, 200)
		r2 = new Rectangle(135, 120, 190, 60); // de la (135, 120) a la (325, 180)
		r3 = new Rectangle(800, 600, 200, 220); // de la (800, 600) a la (1000, 820
		drawing.addFigure(r1);
		drawing.addFigure(r2);
		drawing.addFigure(r3);
	}

	@Test
	public void testFindFigureAt() {
		// pruebas con ractángulos...
		assertEquals(r1, drawing.findFigureAt(120, 90));
		assertEquals(r3, drawing.findFigureAt(800, 625));
		// comprueba que también se encuentra si pulsamos justo en uno de los bordes
		assertEquals(r3, drawing.findFigureAt(880, 600));
		assertEquals(r3, drawing.findFigureAt(800, 710));
		// o exactamente en un vértice
		assertEquals(r3, drawing.findFigureAt(1000, 820));
		// en el caso de figuras que se solapan, se debe devolver la última
		// figura añadida (la que está encima de las otras que hubiera en el mismo punto)
		assertEquals(r2, drawing.findFigureAt(160, 145));		
	}
}
