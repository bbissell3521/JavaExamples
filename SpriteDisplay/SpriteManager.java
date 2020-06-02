package uwstout.cs145.projects.project1.drawing;

/**
 * This class will manage sprites in an array.
 * 
 * This class will add remove and find sprites in the list.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteManager {

	private Sprite[] slist;
	private int num;
	private static final int GROWTH = 3;
	private SpriteEvent event;

	/**
	 * This will take a size and
	 *  create an array of that size for Sprites.
	 * 
	 * @param size
	 *            This is the size of an array you want.
	 */
	public SpriteManager(int size) {
		if (size < 0) {
			throw new IllegalArgumentException(
					"can't make an array with negative size");
		}
		slist = new Sprite[size];
		num = 0;
		event = null;

	}

	/**
	 * This gets the number of elements in the array.
	 * 
	 * @return this returns the number as an int.
	 */
	public int size() {
		return num;
	}

	/**
	 * This gets the capacity of the array.
	 * 
	 * @return this returns the capacity as an int.
	 */
	public int capacity() {
		return slist.length;
	}

	/**
	 * This gets a Sprite at a certain position in the array.
	 * 
	 * @param pos
	 *            This is the position you want returned.
	 * @return This returns the Sprite at the position. 
	 * or if it cant because the
	 *         position is invalid it returns null.
	 */
	public Sprite getSprite(int pos) {
		if (pos < num && pos >= 0) {
			return slist[pos];
		}
		return null;
	}
	
	/**
	 * This returns the event that the class is doing;
	 * @return
	 * 	This is the event.
	 */
	public SpriteEvent getOnChange() {
		return event;
	}
	
	/**
	 * This sets the event.
	 * @param ev
	 * 	This is the event that happened.
	 */
	public void setOnChange(SpriteEvent ev) {
		event = ev;
	}

	/**
	 * This grows our array by 3.
	 * 
	 * @return returns new array with 3 more spots.
	 */
	private Sprite[] grow() {

		Sprite[] big = new Sprite[slist.length + GROWTH];
		for (int i = 0; i < slist.length; i++) {
			big[i] = slist[i];
		}
		return big;
	}

	/**
	 * This will add a sprite to the array. 
	 * If the array needs to grow it will.
	 * 
	 * @param obj
	 *            This is the Sprite you want added.
	 */
	public void addSprite(Sprite obj) {
		if (obj == null) {
			throw new IllegalArgumentException(
					"cant have a null object to add.");
		}
		if (num == slist.length) {
			slist = grow();
		}

		slist[num] = obj;
		num++;
		if (event != null) {
			event.change(SpriteEventType.ADD, obj);
		}

	}

	/**
	 * This will sort through the 
	 * array to find the position of the id given.
	 * 
	 * @param id
	 *            This is the ID you are searching for.
	 * @return It will either return the position 
	 * the id was found. Or it will
	 *         return -1 if the id was not found.
	 */
	public int findSprite(String id) {
		if (id == null) {
			return -1;
		}
		for (int i = 0; i < num; i++) {
			if (id.equals(slist[i].getId())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * This will remove a sprite from
	 *  the array and move everything back one
	 * position to refill its spot.
	 * 
	 * @param id
	 *            This is the id that will be removed
	 * @return this will return the sprite 
	 * removed or this will return null if the
	 *         id could not be found.
	 */
	public Sprite removeSprite(String id) {
		int rem;
		Sprite temp;
		if (findSprite(id) >= 0) {
			rem = findSprite(id);
			temp = slist[rem];

			for (int i = rem; i < num; i++) {
				if (i < num - 1) {
					slist[i] = slist[i + 1];
				}
			}

			num--;
			if (event != null) {
				event.change(SpriteEventType.DELETE, temp);
			}
			return temp;

		}
		return null;
	}

	/**
	 * This clears out the array.
	 */
	public void clear() {
		for (int i = 0; i < num; i++) {
			if (event != null) {
				event.change(SpriteEventType.DELETE, slist[i]);
			}
			slist[i] = null;
		}
		num = 0;
	}
}
