package editor.figures.circle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import editor.core.*;
import editor.tools.Tool;

public class CircleCreationToolTest {

	private Editor editor;
	private Tool tool;
	
	@Before
	public void setUp() {
		editor = new Editor();
		tool = new CircleCreationTool(editor);
	}
	
	@Test
	public void testNoClick() {
		tool.moveTo(400, 300);
		tool.release();
		// no se debería haber creado, al no haber hecho pulsación inicial
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
	}

	@Test
	public void testSimpleCreation() {
		tool.clickOn(200, 100);
		tool.moveTo(400, 300);
		// todavía no se debería haber creado el círculo
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
		tool.release();
		checkCircle();
	}
	
	// Comprueba que se haya creado el círculo con centro en (300, 200) y radio 100.
	//
	private void checkCircle() {
		Circle circle = (Circle) editor.getDrawing().getAllFigures().get(0);
		assertEquals(300, circle.getX());
		assertEquals(200, circle.getY());
		assertEquals(100, circle.getRadious());
	}
	
	// Creación de un círculo cuando el área definida no es cuadrada, sino rectangular
	//
	@Test
	public void testNonSquareArea() {
		tool.clickOn(200, 100);
		tool.moveTo(400, 120);
		tool.release();
		checkCircle();
		// ahora en vertical
		tool.clickOn(280, 100);
		tool.moveTo(320, 400);
		tool.release();
	}
	
	//$ Creación del círculo moviéndonos en distintas direcciones ----------

	@Test
	public void testMoveLeftUpwards() {
		tool.clickOn(400, 300);
		tool.moveTo(200, 100);
		tool.release();
		checkCircle();
	}

	@Test
	public void testMoveRightUpwards() {
		tool.clickOn(200, 300);
		tool.moveTo(400, 100);
		tool.release();
		checkCircle();
	}

	@Test
	public void testMoveLeftDownwards() {
		tool.clickOn(400, 100);
		tool.moveTo(200, 300);
		tool.release();
		checkCircle();
	}

	@Test
	public void testMoveInSeveralDirections() {
		tool.clickOn(200, 100);
		tool.moveTo(400, 300);
		tool.moveTo(300, 200);
		tool.moveTo(50, 300);
		tool.moveTo(80, 20);
		tool.moveTo(500, 500);
		tool.moveTo(400, 300);
		tool.release();
		checkCircle();
	}
	
	//$ Casos especiales ----------------------------------

	@Test
	public void testUnselect() {
		tool.clickOn(100, 50);
		tool.moveTo(300, 200);
		tool.unselect();
		tool.release();
		// no se debería haber creado nada
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
	}
}
