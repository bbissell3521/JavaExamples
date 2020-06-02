package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * This tests SpriteController
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteControllerTest {

	/**
	 * This is used to create a controller quicker.
	 * 
	 * @return Returns a controller.
	 */
	private SpriteController createController() {
		SpriteManager sprites = new SpriteManager(5);
		SpriteController control = new SpriteController(sprites);

		// add a few sprites
		// rectangle at (50, 100) with a size of 50 x 75
		control.add(SpriteType.RECTANGLE, 50, 100, 100, 175);
		// ellipse at (75, 100) with a size of 50 x 50
		// overlaps the rectangle slightly
		control.add(SpriteType.ELLIPSE, 75, 100, 125, 150);

		return control;
	}

	/**
	 * This tests the contructor.
	 */
	@Test
	public void testSpriteController() {
		SpriteManager manager = new SpriteManager(5);
		manager.addSprite(new Sprite("test1", 10, 25, 5, 5));
		SpriteController control = new SpriteController(manager);

		assertEquals(manager, control.getSprites());
		assertEquals(manager.getSprite(0), control.getSprites().getSprite(0));
	}

	/**
	 * this tests to ensure that the contructor throws an exception.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testSpriteController2() {
		SpriteController control = new SpriteController(null);
		
		assertEquals(control, control);
	}

	/**
	 * This tests to make sure events are being set correctly.
	 */
	@Test
	public void testGetOnSelect() {
		TestingSpriteEvent event = new TestingSpriteEvent(10);
		SpriteController control = new SpriteController(new SpriteManager(5));

		control.setOnSelect(event);
		assertEquals(event, control.getOnSelect());
	}

	/**
	 * This tests our add in SpriteController.
	 */
	@Test
	public void testAdd() {
		SpriteManager sprites = new SpriteManager(5);
		SpriteController control = new SpriteController(sprites);

		RectangleSprite rs = new RectangleSprite("test1", 50, 100, 50, 75);
		EllipseSprite es = new EllipseSprite("test2", 75, 100, 50, 50);

		control.add(SpriteType.RECTANGLE, 50, 100, 100, 175);

		control.add(SpriteType.ELLIPSE, 75, 100, 125, 150);

		// tests the rectangle
		assertEquals(rs.getX(), control.getSprites().getSprite(0).getX());
		assertEquals(rs.getY(), control.getSprites().getSprite(0).getY());
		assertEquals("Sprite100", control.getSprites().getSprite(0).getId());
		assertEquals(rs.getWidth(),
				control.getSprites().getSprite(0).getWidth());
		assertEquals(rs.getHeight(),
				control.getSprites().getSprite(0).getHeight());

		// tests the ellipse
		assertEquals(es.getX(), control.getSprites().getSprite(1).getX());
		assertEquals(es.getY(), control.getSprites().getSprite(1).getY());
		assertEquals("Sprite101", control.getSprites().getSprite(1).getId());
		assertEquals(es.getWidth(),
				control.getSprites().getSprite(1).getWidth());
		assertEquals(es.getHeight(),
				control.getSprites().getSprite(1).getHeight());

	}

	/**
	 * Tests basic select functionality
	 */
	@Test
	public void testSelect() {
		SpriteController control = createController();

		// add SpriteEvent to SpriteController so that we can see
		// when the selection changes
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		control.setOnSelect(events);

		control.select(55, 125); // should select the rectangle sprite
		// check getSprite
		// the one selected should be the first one in the list
		// can use equals here since they should be the same object

		assertEquals(control.getSprites().getSprite(0), control.getSelected());

		// check if event occurred
		// exactly one event should have occurred

		assertEquals(1, events.size());

		// it should have been a selected event

		assertEquals(SpriteEventType.SELECTED, events.getEvent(0));

		// it should have been the same sprite as before

		assertEquals(control.getSprites().getSprite(0), events.getSprite(0));

		// test when nothing gets selected

		events.clear(); // get rid of old events
		control.select(5, 225); // should select nothing

		assertNull(control.getSelected());

		// check if event occurred
		// exactly one event should have occurred - an unselected

		assertEquals(1, events.size());
		// it should have been an unselected event

		assertEquals(SpriteEventType.UNSELECTED, events.getEvent(0));

		// it should have been the sprite selected previously

		assertEquals(control.getSprites().getSprite(0), events.getSprite(0));
	}
	
	/**
	 * This tests select without events. 
	 */
	@Test
	public void testSelect2() {
		SpriteController control = createController();
		control.select(60, 110);
		
		assertEquals(control.getSprites().getSprite(0), control.getSelected());
		
		control.select(60, 110);
		

		control.select(5, 225);

		assertNull(control.getSelected());
		
		

	}

	// ----------------------------------------------------------
	/**
	 * Tests basic remove functionality
	 */
	@Test
	public void testRemove() {
		SpriteController control = createController();

		// add SpriteEvent to SpriteController so that we can see
		// when the selection changes
		TestingSpriteEvent events = new TestingSpriteEvent(10);
		control.setOnSelect(events);

		// try removing
		control.remove();
		// nothing was selected, so nothing was removed
		assertEquals(2, control.getSprites().size());

		// event
		assertEquals(0, events.size()); // no event either

		// select the ellipse
		control.select(105, 125);
		assertEquals(control.getSprites().getSprite(1), control.getSelected());
		Sprite ellipse = control.getSprites().getSprite(1);
		events.clear(); // reset events for remove
		// remove
		control.remove(); // should work now
		assertEquals(1, control.getSprites().size());
		// event
		assertEquals(1, events.size());
		// it should have been an unselected event
		assertEquals(SpriteEventType.UNSELECTED, events.getEvent(0));
		// it should have been the sprite selected previously
		assertEquals(ellipse, events.getSprite(0));
	}
	
	/**
	 * This tests remove without events.
	 */
	@Test
	public void testRemove2() {
		SpriteController control = createController();

		// try removing
		control.remove();
		// nothing was selected, so nothing was removed
		assertEquals(2, control.getSprites().size());


		// select the ellipse
		control.select(105, 125);
		assertEquals(control.getSprites().getSprite(1), control.getSelected());
		
		control.remove();
		
		assertEquals(null, control.getSprites().getSprite(1));
		
	}

	/**
	 * This tests reset.
	 */
	@Test
	public void testReset() {
		SpriteController control = createController();

		TestingSpriteEvent events = new TestingSpriteEvent(5);
		RectangleSprite rs = new RectangleSprite("test1", 50, 100, 50, 75);
		control.setOnSelect(events);

		control.select(60, 125);

		assertEquals(1, events.size());
		assertEquals(rs.getX(), control.getSelected().getX());
		control.reset();
		assertEquals(2, events.size());
		assertEquals(null, control.getSelected());
	}

	/**
	 * This tests reset without events.
	 */
	@Test
	public void testReset2() {
		SpriteController control = createController();

		RectangleSprite rs = new RectangleSprite("test1", 50, 100, 50, 75);

		control.select(60, 125);

		assertEquals(rs.getX(), control.getSelected().getX());
		control.reset();
		assertEquals(null, control.getSelected());
	}

}
