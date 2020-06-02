package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests ElipseSprite
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class EllipseSpriteTest {

	/**
	 * This tests contains on an elipse. 
	 */
	@Test
	public void testContains() {
		EllipseSprite obj1 = new EllipseSprite("test1", 0, 0, 100, 100);
		
		assertTrue(obj1.contains(50, 50));
		assertFalse(obj1.contains(0, 200));
		assertFalse(obj1.contains(-5, 20));
	}

}
