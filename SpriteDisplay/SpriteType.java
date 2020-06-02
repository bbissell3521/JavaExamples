package uwstout.cs145.projects.project1.drawing;

/**
 * This shows what type of sprite it is.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public enum SpriteType {

	/**
	 * This shows it is a rectangle type.
	 */
	RECTANGLE,
	/**
	 * This shows it is a ellipse type.
	 */
	ELLIPSE;
	
	/**
	 * this will return a string form of the spritetype.
	 * 
	 * @return
	 * 	If a ellipse is selected then
	 *  it will return that as a string.
	 * 	If a rectangle is selected then it
	 *  will return that as a string.
	 */
	public String toString() {
		String str = null;
	
		if (this == ELLIPSE) {
			str = "Ellipse";
		}
		if (this == RECTANGLE) {
			str = "Rectangle";
		}
		
		return str;
	}
}
