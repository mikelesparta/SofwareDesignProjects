package editor.figures.rectangle;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import editor.core.*;
import editor.tools.Tool;

public class RectangleCreationToolTest {

	private Editor editor;
	private Tool tool;
	
	@Before
	public void setUp() {
		editor = new Editor();
		tool = new RectangleCreationTool(editor);
	}
	
	@Test
	public void testNoClick() {
		tool.moveTo(300, 200);
		tool.release();
		// no se debería haber creado ninguna figura, al no haber hecho pulsación inicial
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
	}

	@Test
	public void testSimpleCreation() {
		tool.clickOn(100, 50);
		tool.moveTo(300, 200);
		// todavía no se debería haber creado la figura
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
		tool.release();
		checkRectangle();
	}
	
	// Comprueba que se haya creado el rectángulo que va de la (100, 50) a la (300, 200).
	//
	private void checkRectangle() {
		Rectangle r1 = (Rectangle) editor.getDrawing().getAllFigures().get(0);
		assertEquals(100, r1.getX());
		assertEquals(50, r1.getY());
		assertEquals(200, r1.getWidth());
		assertEquals(150, r1.getHeight());
	}
	
	//$ Creación del rectángulo moviéndonos en distintas direcciones ----------

	@Test
	public void testMoveLeftUpwards() {
		tool.clickOn(300, 200);
		tool.moveTo(100, 50);
		tool.release();
		checkRectangle();
	}

	@Test
	public void testMoveRightUpwards() {
		tool.clickOn(100, 200);
		tool.moveTo(300, 50);
		tool.release();
		checkRectangle();
	}

	@Test
	public void testMoveLeftDownwards() {
		tool.clickOn(300, 50);
		tool.moveTo(100, 200);
		tool.release();
		checkRectangle();
	}

	@Test
	public void testMoveInSeveralDirections() {
		tool.clickOn(100, 50);
		tool.moveTo(300, 200);
		tool.moveTo(50, 300);
		tool.moveTo(80, 20);
		tool.moveTo(500, 500);
		tool.moveTo(300, 200);
		tool.release();
		checkRectangle();
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
