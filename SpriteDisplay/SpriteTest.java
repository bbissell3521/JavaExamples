package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is the test class for Sprite
 * 
 * @author Blake B
 * @version v1.0
 * 
 */
public class SpriteTest {

	private static final double DELTA = 0.0001;

	/**
	 * This tests the constructor and getters.
	 * 
	 */
	@Test
	public void testSprite() {
		Sprite obj1 = new Sprite("ABC123", 1, 2, 3, 4);
		assertEquals("ABC123", obj1.getId());
		assertEquals(1, obj1.getX());
		assertEquals(2, obj1.getY());
		assertEquals(3, obj1.getWidth());
		assertEquals(4, obj1.getHeight());
	}

	/**
	 * This tests a empty string.
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testSprite2() {
		@SuppressWarnings("unused")
		Sprite obj1 = new Sprite("", 0, 0, 0, 0);
		Sprite t = new Sprite("a", 0, 0, 0, 0);
		assertEquals("a", t.getId());
	}

	/**
	 * This tests invalid width.
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testSprite3() {
		@SuppressWarnings("unused")
		Sprite obj1 = new Sprite("AB", 0, 0, -2, 0);
		Sprite t = new Sprite("a", 0, 0, 0, 0);
		assertEquals("a", t.getId());
	}

	/**
	 * This tests invalid height.
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testSprite4() {
		@SuppressWarnings("unused")
		Sprite obj1 = new Sprite("AB", 0, 0, 0, -3);
		Sprite t = new Sprite("a", 0, 0, 0, 0);
		assertEquals("a", t.getId());

	}

	/**
	 * This tests null strings
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testSprite5() {
		@SuppressWarnings("unused")
		Sprite obj1 = new Sprite(null, 0, 0, 0, 0);
		Sprite t = new Sprite("a", 0, 0, 0, 0);
		assertEquals("a", t.getId());
	}
	
	/**
	 * This test for invalid height.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSprite6() {
		@SuppressWarnings("unused")
		Sprite obj1 = new Sprite("ABC", 0, 0, 5, -10);
		Sprite t = new Sprite("a", 0, 0, 0, 0);
		assertEquals("a", t.getId());
	}

	/**
	 * This tests get aspect ratio.
	 */
	@Test
	public void testGetAspectRatio() {
		Sprite obj1 = new Sprite("ABC123", 1, 2, 3, 4);
		assertEquals(0.75, obj1.getAspectRatio(), DELTA);
	}
	
	/**
	 * This tests contain.
	 */
	@Test
	public void testContains() {
		Sprite obj1 = new Sprite("ABC123", 1, 2, 3, 4);
		assertFalse(obj1.contains(20, 4));
	}

}
