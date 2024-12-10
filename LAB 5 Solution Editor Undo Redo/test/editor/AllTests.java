package editor;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
	editor.core.DrawingTest.class,
	editor.tools.SelectionToolTest.class,
	editor.figures.rectangle.RectangleCreationToolTest.class,
	editor.figures.circle.CircleCreationToolTest.class,
	editor.figures.triangle.TriangleCreationToolTest.class,
	editor.core.EditorTest.class
})
public class AllTests 
{
}
