package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This class will represent a rectangle on the screen. 
 * 
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */

public class RectangleSprite extends Sprite {

	private Rectangle rs;
	/**
	 * This uses the parents constructor to create the object.
	 * @param nId
	 * 	This is the name or ID of the object
	 * @param nX
	 * 	This is the X coordinate of the top left coordinate.
	 * @param nY
	 * 	This is the Y coordinate of the top left coordinate. 
	 * @param nWidth
	 * 	This is the width of the rectangle.
	 * @param nHeight
	 * 	This is the height of the rectangle. 
	 */
	public RectangleSprite(String nId, int nX, int nY, int nWidth,
			int nHeight) {
		super(nId, nX, nY, nWidth, nHeight);
		rs = new Rectangle(nX, nY, nWidth, nHeight);
		rs.setFill(Color.BLANCHEDALMOND);
	}
	
	/**
	 * This tests to see if the given point is inside the rectangle.
	 * 
	 * @param x
	 * 	This is the given x coordinate
	 * @param y
	 * 	This is the given y coordinate
	 * 
	 * @return
	 * 	This returns true if the given point is inside the rectangle.
	 * 	This returns false if the given point isnt inside the rectangle.
	 */
	@Override
	public boolean contains(int x, int y) {

		return (x >= getX() && x <= getX() + getWidth() 
			&& y >= getY() && y <= getY() + getHeight());

	}
	
	/**
	 * This returns a rectangle JavaFX element to display.
	 * 
	 * @return
	 * 	This is the node that should be displayed.
	 */
	public Node getGraphic() {
		return rs;
	}
	
}
