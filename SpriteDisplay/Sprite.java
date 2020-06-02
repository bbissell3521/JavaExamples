package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;

/**
 * This class will represent an object that will be displayed in a GUI.
 * 
 * 
 * @author Blake Bissell
 * @version v1.0
 * 
 *
 */
public class Sprite {

	private int x;
	private int y;
	private int width;
	private int height;
	private String id;

	/**
	 * This constructor will take paramters
	 *  to define each of the class variables.
	 * 
	 * @param nId
	 *            This takes the wanted ID.
	 * @param nX
	 *            This takes the wanted x location.
	 * @param nY
	 *            This takes the wanted y location.
	 * @param nWidth
	 *            This takes the wanted width.
	 * @param nHeight
	 *            This takes the wanted Height.
	 */
	public Sprite(String nId, int nX, int nY,
			int nWidth, int nHeight) throws IllegalArgumentException {
		if (nId == null || nId.equals("")) {
			throw new IllegalArgumentException(
					"ID must not be null or empty string");
		}
		if (nWidth <= 0) {
			throw new IllegalArgumentException(
					"Width must be greater then 0");
		}
		if (nHeight <= 0) { 
			throw new IllegalArgumentException(
					"Height must be greater then 0");
		}
		height = nHeight;
		width = nWidth;
		id = nId;
		x = nX;
		y = nY;

	}

	/**
	 * This gets the id.
	 * 
	 * @return returns the id as a string.
	 */
	public String getId() {
		return id;
	}

	/**
	 * This gets the x coordinate.
	 * 
	 * @return returns x as a integer
	 */
	public int getX() {
		return x;
	}

	/**
	 * This gets the y coordinate.
	 * 
	 * @return returns y as a integer.
	 */
	public int getY() {
		return y;
	}

	/**
	 * This gets the width.
	 * 
	 * @return returns width as an integer.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * This gets height.
	 * 
	 * @return returns height as an integer.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * This gets the aspect ratio.
	 * 
	 * @return this returns the aspect ratio as a double.
	 */
	public double getAspectRatio() {
		return (double) width / height;
	}
	
	/**
	 * This checks to see if the given point is inside the sprite
	 * @param a
	 * 	The x of the given point. 
	 * @param b
	 * 	The y of the given point.
	 * @return
	 * 	Always returns false because Sprite is not a shape. 
	 */
	public boolean contains(int a, int b) {
		return false;
	}
	
	/**
	 * This returns the graphic associated with
	 * the class. 
	 * 
	 * @return
	 * 	returns a Node with the graphic tied to class.
	 */
	public Node getGraphic() {
		return null;
	}

}
