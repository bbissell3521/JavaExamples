package uwstout.cs145.projects.project1.drawing;

/**
 * This will process events. It works between SpriteEvent and SpriteManager.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteController {

	private SpriteManager man;
	private Sprite s;
	private SpriteEvent event;
	private static int id = 100;

	/**
	 * This makes a SpriteController object.
	 * 
	 * @param manager
	 *            This is the manager you want the controller to control.
	 */
	public SpriteController(SpriteManager manager) {
		if (manager == null) {
			throw new IllegalArgumentException();
		}
		man = manager;
		s = null;
		event = null;
	}

	/**
	 * This gets the manager.
	 * 
	 * @return This returns the SpriteManager.
	 */
	public SpriteManager getSprites() {
		return man;
	}

	/**
	 * This returns the selected Sprite.
	 * 
	 * @return This returns the Sprite stored.
	 */
	public Sprite getSelected() {
		return s;
	}

	/**
	 * This gets the selected event.
	 * 
	 * @return This returns the SpriteEvent.
	 */
	public SpriteEvent getOnSelect() {
		return event;
	}

	/**
	 * This sets the type of event.
	 * 
	 * @param ev
	 *            This is the type of the event to set event to.
	 */
	public void setOnSelect(SpriteEvent ev) {
		event = ev;
	}

	/**
	 * This adds a new sprite to the manager.
	 * 
	 * @param st
	 *            This is the type of sprite wanted to add.
	 * @param x1
	 *            This is the first x coordinate.
	 * @param y1
	 *            This is the first y coordinate.
	 * @param x2
	 *            This is the second x coordinate.
	 * @param y2
	 *            This is the second y coordinate.
	 */
	public void add(SpriteType st, int x1, int y1, int x2, int y2) {

		if (st != null) {

			String nm = "Sprite" + id;
			int x;
			int y;
			int width;
			int height;

			if (x1 > x2) {
				x = x2;
				width = x1 - x2;
			} else {
				x = x1;
				width = x2 - x1;
			}
			if (y1 < y2) {
				y = y1;
				height = y2 - y1;
			} else {
				y = y2;
				height = y1 - y2;
			}
			if (width > 0 && height > 0) {
				if (st == SpriteType.RECTANGLE) {
					RectangleSprite rs = new RectangleSprite(
							nm, x, y, width, height);

					man.addSprite(rs);
				}

				else {
					EllipseSprite es = new EllipseSprite(
							nm, x, y, width, height);

					man.addSprite(es);
				}
				id++;
			}

		}
	}

	/**
	 * This will select the most recent sprite that contains the given point.
	 * 
	 * @param x
	 *            This is the x of the given point.
	 * @param y
	 *            This is the y of the given point.
	 */
	public void select(int x, int y) {
		for (int i = man.size() - 1; i > -1; i--) {
			if (man.getSprite(i).contains(x, y)) {
				if (man.getSprite(i) != s) {
					if (s != null && event != null) {
						event.change(SpriteEventType.UNSELECTED, s);
					}
					s = man.getSprite(i);
					if (event != null) {
						event.change(SpriteEventType.SELECTED, s);
					}
					return;
				} else {
					return;
				}
			}

		}
		if (s != null && event != null) {
			event.change(SpriteEventType.UNSELECTED, s);
		}
		s = null;

	}

	/**
	 * This will remove the currently selected sprite.
	 */
	public void remove() {
		if (s != null) {
			man.removeSprite(s.getId());
			if (event != null) {
				event.change(SpriteEventType.UNSELECTED, s);
			}
			s = null;
		}
	}

	/**
	 * This will reset the class to a default state.
	 *  That is an empty manager with
	 * nothing selected.
	 */
	public void reset() {
		if (event != null && s != null) {
			event.change(SpriteEventType.UNSELECTED, s);
			s = null;
		}
		if (man != null) {
			man.clear();
		}
		
		s = null;
	}
	
	/**
	 * This clears the selected sprite. 
	 */
	public void clearSelected() {
		
		if (event != null) {
			event.change(SpriteEventType.UNSELECTED, s);
		}
		s = null;
	}
	
	/**
	 * This checks if a sprite contains the given x,y point.
	 * It will return the last sprite added that contains that point.
	 * @param x
	 * 	This is the x point to search
	 * @param y
	 * 	This is the y point to search
	 * @return
	 * 	This returns the sprite if one was found 
	 * otherwise it returns null.
	 */
	public Sprite contains(int x, int y) {
		Sprite sp = null;
		for (int i = 0; i < man.size(); i++) {
			if (man.getSprite(i).contains(x, y)) {
				sp = man.getSprite(i);
			}
		}
		return sp;
	}

}
