package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests RectangleSprite.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class RectangleSpriteTest {

	/**
	 * This tests the contains method.
	 */
	@Test
	public void testContains() {
		RectangleSprite r1 = new RectangleSprite("test1", 0, 0, 100, 100);
		
		RectangleSprite r2 = new RectangleSprite("test2", 300, 150, 100, 50);
		
		assertTrue(r1.contains(50,  50));
		assertTrue(r1.contains(0, 25));
		assertFalse(r1.contains(150,  50));
		
		assertTrue(r2.contains(350, 175));
		assertTrue(r2.contains(301, 199));
		assertFalse(r2.contains(50, 25));

	}

}
