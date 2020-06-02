package uwstout.cs145.projects.project1.drawing;

/**
 * This will handle changes on sprites and
 * create events that allow our code to
 * communicate.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public interface SpriteEvent {

	/**
	 * This will be used to generate events.
	 * 
	 * @param type
	 *            This is the type of change
	 * @param obj
	 *            This is the object changed.
	 */
	void change(SpriteEventType type, Sprite obj);
}
