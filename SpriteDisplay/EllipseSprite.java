package uwstout.cs145.projects.project1.drawing;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

/**
 * This will represent an elipses.
 * 
 * @author Blake Bissell
 * @version v1.0
 * 
 *
 */
public class EllipseSprite extends Sprite {

	private Ellipse es;
	/**
	 * This creates an object through the parent constructor.
	 * @param nId
	 * 	This is the ID of the ellipse
	 * @param nX
	 * 	This is the x coordinate of the top left corner
	 *  of the box the elipse would fit inside.
	 * @param nY
	 * 	This is the y coordinate of the top left corner
	 *  of the box the ellipse would fit inside.
	 * @param nWidth
	 * 	This is the width of the box the elipse would fit in.
	 * @param nHeight
	 * 	This is the height of the box the elipse would fit in.
	 */
	public EllipseSprite(String nId, int nX, int nY,
			int nWidth, int nHeight) {
		super(nId, nX, nY, nWidth, nHeight);
		es = new Ellipse(nX + (nWidth / 2), nY + (nHeight / 2),
				nWidth / 2, nHeight / 2);
		es.setFill(Color.GREEN);

	}
	
	/**
	 * This tests if a point is contained inside the elipse.
	 * @param x
	 * 	This is the point to be tested.
	 * @param y
	 * 	This is the point to be tested.
	 * @return
	 * 	This returns true if te point is in the elipse.
	 * 	This returns false if the points is not in the elipse. 
	 */
	public boolean contains(int x, int y) {
		int cX = super.getX() + (super.getWidth() / 2);
		int cY = super.getY() + (super.getHeight() / 2);
		int rX = super.getWidth() / 2;
		int rY = super.getHeight() / 2;
	
		return (((Math.pow(x - cX, 2) / Math.pow(rX, 2))
				+ (Math.pow(y - cY, 2) / (Math.pow(rY, 2))) <= 1));
		
	}
	
	/**
	 * this returns the Ellipse that is created by this class.
	 * @return
	 * 	returns a node to display.
	 */
	public Node getGraphic() {
		return es;
	}
	


}
