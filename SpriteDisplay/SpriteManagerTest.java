package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This is the tests for SpriteManager
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteManagerTest {
	/**
	 * This makes the array for us to speed up writing the test
	 * 
	 * @return returns the made SpriteManager
	 */

	private SpriteManager createSprites() {
		SpriteManager obj1 = new SpriteManager(3);
		Sprite s1 = new Sprite("ABC100", 1, 2, 3, 4);
		Sprite s2 = new Sprite("DFG200", 4, 3, 2, 1);
		Sprite s3 = new Sprite("HIJ300", 2, 3, 4, 1);
		Sprite s4 = new Sprite("KLM400", 3, 4, 1, 2);
		obj1.addSprite(s1);
		obj1.addSprite(s2);
		obj1.addSprite(s3);
		obj1.addSprite(s4);
		return obj1;
	}
	
	/**
	 * This tests for a negative size in constructor.
	 */
	@SuppressWarnings("unused")
	@Test (expected = IllegalArgumentException.class)
	public void testSpriteMan() {
		SpriteManager sm = new SpriteManager(-3);
		SpriteManager obj1 = createSprites();
		assertEquals(4, obj1.size());
	}
	
	/**
	 * This tests add to throw exception.
	 */
	@Test (expected = IllegalArgumentException.class)
	public void testAddSprite() {
		Sprite obj1 = null;
		SpriteManager sm = new SpriteManager(1);
		sm.addSprite(obj1);
		SpriteManager obj2 = createSprites();
		assertEquals(4, obj2.size());
	}


	/**
	 * This tests Size.
	 */
	@Test
	public void testSize() {
		SpriteManager obj1 = createSprites();
		assertEquals(4, obj1.size());
	}

	/**
	 * This tests capacity
	 */
	@Test
	public void testCapacity() {
		SpriteManager obj1 = createSprites();
		assertEquals(6, obj1.capacity());
	}

	/**
	 * This tests get sprite.
	 */
	@Test
	public void testGetSprite() {
		SpriteManager obj1 = new SpriteManager(3);
		Sprite s1 = new Sprite("ABC100", 1, 2, 3, 4);
		Sprite s2 = new Sprite("DFG200", 4, 3, 2, 1);
		Sprite s3 = new Sprite("HIJ300", 2, 3, 4, 1);
		Sprite s4 = new Sprite("KLM400", 3, 4, 1, 2);
		obj1.addSprite(s1);
		obj1.addSprite(s2);
		obj1.addSprite(s3);
		obj1.addSprite(s4);

		assertEquals(s1.getId(), obj1.getSprite(0).getId());
		assertEquals(s2.getId(), obj1.getSprite(1).getId());
		assertEquals(s3.getId(), obj1.getSprite(2).getId());
		assertEquals(s4.getId(), obj1.getSprite(3).getId());
		assertEquals(s4.getX(), obj1.getSprite(3).getX());
		assertEquals(s4.getHeight(), obj1.getSprite(3).getHeight());

	}
	/**
	 * This tests to ensure that it is returning null properly.
	 */
	@Test
	public void testGetSprite2() {
		SpriteManager obj1 = createSprites();
		assertEquals(null, obj1.getSprite(7));
		assertEquals(null, obj1.getSprite(-2));
		
	}

	/**
	 * This tests find sprite
	 */
	@Test
	public void testFindSprite() {
		SpriteManager obj1 = createSprites();

		assertEquals(0, obj1.findSprite("ABC100"));
		assertEquals(-1, obj1.findSprite("JSI200"));
		assertEquals(-1, obj1.findSprite(null));
		assertEquals(-1, obj1.findSprite(""));

	}

	/**
	 * This tests remove sprite.
	 */
	@Test
	public void testRemoveSprite() {
		SpriteManager obj1 = createSprites();
		SpriteManager obj2 = createSprites();

		obj1.removeSprite("DFG200");
		assertEquals("HIJ300", obj1.getSprite(1).getId());

		obj2.removeSprite("ABC100");
		assertEquals("DFG200", obj2.getSprite(0).getId());

	}
	
	/**
	 * This tests remove sprite with a ID not in the SM.
	 */
	@Test
	public void testRemoveSprite2() {
		SpriteManager obj1 = createSprites();

		assertEquals(null, obj1.removeSprite("gd"));
		
		SpriteManager obj2 = createSprites();

		assertEquals(0, obj2.findSprite("ABC100"));

	}
	
	/**
	 * This test remove with the new event functions.
	 */
	@Test
	public void testRemoveSprite3() {
		SpriteManager obj1 = createSprites();
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		obj1.setOnChange(events);
		
		obj1.removeSprite("ABC100");
		assertEquals(1, events.size());
		assertEquals(SpriteEventType.DELETE, events.getEvent(0));
	}

	
	// ----------------------------------------------------------
	/**
	 * Basic test of new event functionality in addSprite
	 */
	@Test
	public void testAddSpriteEvent() {
		// create manager
		SpriteManager sprites = new SpriteManager(5);
		// add SpriteEvent to SpriteManager so that we can see
		// when sprites are added/removed
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		sprites.setOnChange(events);

		EllipseSprite e1 = new EllipseSprite("test1", 15, 20, 100, 200);
		sprites.addSprite(e1);

		// check events
		// exactly one event should have occurred
		assertEquals(1, events.size());
		// it should have been a selected event
		assertEquals(SpriteEventType.ADD, events.getEvent(0));
		// it should have been the same sprite as before
		assertEquals(e1, events.getSprite(0));
	}
	
	// ----------------------------------------------------------
	/**
	 * Basic test for events in clear method
	 */
	@Test
	public void testClearEvents() {

		SpriteManager sprites = new SpriteManager(5);
		// add SpriteEvent to SpriteManager so that we can see
		// when sprites are added/removed

		EllipseSprite e1 = new EllipseSprite("test1", 15, 20, 100, 200);
		EllipseSprite e2 = new EllipseSprite("test2", 50, 200, 15, 15);
		sprites.addSprite(e1);
		sprites.addSprite(e2);

		// add SpriteEvent after the adds since we only care about events
		// from calling clear

		TestingSpriteEvent events = new TestingSpriteEvent(10);
		sprites.setOnChange(events);

		sprites.clear();

		// check events
		// exactly one event should have occurred

		assertEquals(2, events.size());

		// they should all be delete events

		assertEquals(SpriteEventType.DELETE, events.getEvent(0));
		assertEquals(SpriteEventType.DELETE, events.getEvent(1));

		// it should have been the same sprites as added
		// the order may need to be changed here if the events are
		// generated in a different order

		assertEquals(e1, events.getSprite(0));
		assertEquals(e2, events.getSprite(1));
	}

}
