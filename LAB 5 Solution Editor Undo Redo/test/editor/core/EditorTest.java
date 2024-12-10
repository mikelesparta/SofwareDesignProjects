package editor.core;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import editor.figures.circle.*;
import editor.figures.rectangle.*;
import editor.figures.triangle.*;
import editor.tools.SelectionTool;

public class EditorTest {
	
	private Editor editor;
	private Tool selectionTool, rectangleTool, circleTool, triangleTool;
	
	@Before
	public void setUp() {
		editor = new Editor();
		// creamos las herramientas
		rectangleTool = new RectangleCreationTool(editor);
		circleTool = new CircleCreationTool(editor);
		triangleTool = new TriangleCreationTool(editor);
		// se añaden al editor
		editor.addTool("selección", selectionTool);
		editor.addTool("rectángulo", rectangleTool);
		editor.addTool("triángulo", circleTool);
		editor.addTool("círculo", triangleTool);
		// obtenemos la de selección (la predeterminada en la implementación actual)
		selectionTool = editor.getCurrentTool();
	}
	
	@Test
	public void testDefaultTool() {
		Editor editor = new Editor();
		assertNotNull(editor.getCurrentTool());
		assertTrue(editor.getCurrentTool() instanceof SelectionTool);
	}

	@Test
	public void testSimpleCreation() {
		clickOn(100, 50);
		moveTo(300, 200);
		release();
		// no se había seleccionado ninguna herramienta de creación
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
		
		// Ahora sí: creamos un rectángulo
		editor.selectTool(rectangleTool);
		clickOn(100, 50);
		// todavía no se debería haber creado el rectángulo
		assertTrue(editor.getDrawing().getAllFigures().isEmpty());
		moveTo(300, 200);
		release();
		// ahora sí
		checkRectangle();
		
		// Comprobamos que esté activa la herramienta predeterminada
		assertEquals(selectionTool, editor.getCurrentTool());
		
		// Creamos un círculo
		editor.selectTool(circleTool);
		clickOn(200, 100);
		moveTo(400, 300);
		// todavía no se debería haber creado el círculo
		assertTrue(editor.getDrawing().getAllFigures().size() == 1);
		release();
		// ahora sí
		checkCircle();
		
		// Comprobamos que esté activa la herramienta predeterminada
		assertEquals(selectionTool, editor.getCurrentTool());

		// Creamos un triángulo
		editor.selectTool(triangleTool);
		clickOn(100, 100);
		clickOn(50, 200);
		// todavía no se debería haber creado el triángulo (falta el tercer clic)
		assertTrue(editor.getDrawing().getAllFigures().size() == 2);
		clickOn(150, 200);
		release();
		// ahora sí
		checkTriangle();
		
		// Comprobamos que esté activa la herramienta predeterminada
		assertEquals(selectionTool, editor.getCurrentTool());
	}
	
	//$ Métodos auxiliares --------------------------------
	
	private void clickOn(int x, int y) {
		editor.getCurrentTool().clickOn(x, y);
	}
	
	private void moveTo(int x, int y) {
		editor.getCurrentTool().moveTo(x, y);
	}
	
	private void release() {
		editor.getCurrentTool().release();
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
	
	// Comprueba que se haya creado el círculo con centro en (300, 200) y radio 100.
	//
	private void checkCircle() {
		Circle circle = (Circle) editor.getDrawing().getAllFigures().get(0);
		assertEquals(300, circle.getX());
		assertEquals(200, circle.getY());
		assertEquals(100, circle.getRadious());
	}

	// Comprueba que se haya creado el triángulo cuyos vértices son: 
	// (100, 100), (50, 200), (150, 200).
	//
	private void checkTriangle() {
		Point v1 = new Point(100, 100);
		Point v2 = new Point(50, 200);
		Point v3 = new Point(150, 200);
		Triangle triangle = (Triangle) editor.getDrawing().getAllFigures().get(0);
		assertEquals(v1, triangle.getVertices()[0]);
		assertEquals(v2, triangle.getVertices()[1]);
		assertEquals(v3, triangle.getVertices()[2]);
	}
}
