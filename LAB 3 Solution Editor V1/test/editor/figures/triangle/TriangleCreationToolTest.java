package editor.figures.triangle;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import editor.core.*;
import editor.tools.Tool;

public class TriangleCreationToolTest {

	private Editor editor;
	private Tool tool;
	
	@Before
	public void setUp() {
		editor = new Editor();
		tool = new TriangleCreationTool(editor);
	}
	
	@Test
	public void testSimpleCreation() {
		tool.clickOn(100, 100);
		tool.clickOn(50, 200);
		// todavía no se debería haber creado el triángulo (falta el tercer clic)
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
		tool.clickOn(150, 200);
		tool.release();
		checkTriangle();
	}
	
	// Triángulo: (100, 100), (50, 200), (150, 200)
	private void checkTriangle() {
		Point v1 = new Point(100, 100);
		Point v2 = new Point(50, 200);
		Point v3 = new Point(150, 200);
		Triangle triangle = (Triangle) editor.getDrawing().getAllFigures().get(0);
		assertEquals(v1, triangle.getVertices()[0]);
		assertEquals(v2, triangle.getVertices()[1]);
		assertEquals(v3, triangle.getVertices()[2]);
	}
	
	@Test
	public void testUnselect() {
		tool.clickOn(100, 100);
		tool.clickOn(50, 200);
		tool.unselect();
		tool.clickOn(150, 200); // debería ser el primer clic de un nuevo triángulo
		// no se debería haber creado nada
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
	}
}
