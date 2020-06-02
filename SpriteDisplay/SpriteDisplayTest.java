package uwstout.cs145.projects.project1.drawing;

import static org.junit.Assert.*;

import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import javafx.collections.ObservableList;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;

/**
 * This tests our SpriteDisplay. 
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteDisplayTest extends ApplicationTest {
	
	private SpriteDisplay display;

	/**
	 * Presses the left mouse button at x, y on the spriteDisplay control
	 *
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 */
	private void press(int x, int y) {
		Node spriteArea = lookup("#spriteDisplay").queryAs(Node.class);
		moveTo(spriteArea, Pos.TOP_LEFT,
				new Point2D(x, y), Motion.DIRECT);
		press(MouseButton.PRIMARY);
	}

	/**
	 * Clicks the left mouse button at x, y on the spriteDisplay control
	 *
	 * @param x
	 *            The x coordinate
	 * @param y
	 *            The y coordinate
	 */
	private void click(int x, int y) {
		press(x, y);
		release(MouseButton.PRIMARY);
	}

	/**
	 * Drags the mouse with the left button pressed from at
	 *  startx, starty to endx,
	 * endy on the spriteDisplay control
	 *
	 * @param startx
	 *            The starting x coordinate
	 * @param starty
	 *            The starting y coordinate
	 * @param endx
	 *            The ending x coordinate
	 * @param endy
	 *            The ending y coordinate
	 */
	private void drag(int startx, int starty, int endx, int endy) {
		press(startx, starty);
		moveBy(endx - startx, endy - starty);
		release(MouseButton.PRIMARY);
	}
	
	/**
	 * Selects a combo item by name from the ComboBox
	 *
	 * @param comboId
	 *            The id for the combo box. This should not 
	 *            have a # at the front.
	 *            (That is added internally.)
	 *
	 * @param itemText
	 *            The text displayed in the combo box for
	 *             the item being selected.
	 *
	 */
	private void selectComboItem(String comboId, String itemText) {
		assertNotNull("The id for the combo box must not be null",
				comboId);
		assertNotNull("The item's text in the combo box must not be null",
				itemText);
		ComboBox<SpriteType> combo = lookup("#" + comboId).queryComboBox();
		// find the itemText in the combo's data
		int index = -1;
		ObservableList<SpriteType> items = combo.getItems();
		for (int i = 0; i < items.size() && index == -1; i++) {
			if (itemText.equals(items.get(i).toString())) {
				index = i;
			}
		}
		assertNotSame("The item's text (" + itemText + ") was " 
				+ "not found in the combo box.", index == -1);
		int currentIndex = combo.getSelectionModel().getSelectedIndex();
		clickOn(combo); // opens it up
		if (currentIndex < index) {
			// key down to find it
			for (; currentIndex < index; currentIndex++) {
				type(KeyCode.DOWN);
			}
		}
		if (currentIndex > index) {
			// key up to find it
			for (; currentIndex > index; currentIndex--) {
				type(KeyCode.UP);
			}
		}
		type(KeyCode.ENTER);
	}
	
	/**
	 * Checks if a sprite has the location and size expected.
	 *
	 * @param sprite
	 *            Sprite to check
	 * @param x
	 *            The x coordinate of the top left corner
	 * @param y
	 *            The y coordinate of the top left corner
	 * @param width
	 *            The width of the sprite
	 * @param height
	 *            The height of the sprite
	 */
	private void checkSprite(Sprite sprite, int x,
			int y, int width, int height) {
		assertEquals("The sprite's x was incorrect.", x,
				sprite.getX());
		assertEquals("The sprite's y was incorrect.", y,
				sprite.getY());
		assertEquals("The sprite's width was incorrect.",
				width, sprite.getWidth());
		assertEquals("The sprite's height was incorrect.",
				height, sprite.getHeight());
	}
	
	@Override
	public void start(Stage mainStage) throws Exception {
		display = new SpriteDisplay();
		display.start(mainStage);
	}
	
	@Override
	public void stop() throws Exception {
		FxToolkit.hideStage();
	}
	
	
	/**
	 * Tests some basic functionality.
	 * display is a SpriteDisplay object created in
	 * start and stored in a class variable.
	 */
	@Test
	public void test() {
		drag(100, 100, 200, 200);
		drag(50, 50, 25, 25);
		assertEquals(2, display.getSprites().size());
		checkSprite(display.getSprites().getSprite(0), 100, 100, 100, 100);
		checkSprite(display.getSprites().getSprite(1), 25, 25, 25, 25);
		// should select the second rectangle
		click(30, 30);
		assertEquals(display.getSprites().getSprite(1),
				display.getController().getSelected());
		selectComboItem("types", "Ellipse");
		drag(45, 150, 100, 165);
		assertEquals(3, display.getSprites().size());
		checkSprite(display.getSprites().getSprite(2), 45, 150, 55, 15);
		// nothing should be selected now
		assertNull(display.getController().getSelected());
		clickOn("#quit");
	}

	/**
	 * This tests selection.
	 */
	@Test
	public void testSelection() { 
		
		drag(100, 100, 200, 200);
		click(150, 150);
		assertNotNull(display.getController().getSelected());
		clickOn("#quit");
		
	}
	

	/**
	 * This tests delete.
	 */
	@Test
	public void testDelete() { 
		
		drag(100, 100, 200, 200);
		click(150, 150);
		assertNotNull(display.getController().getSelected());
		clickOn("#delete");
		assertNull(display.getController().getSelected());
		assertEquals(0, display.getSprites().size());
		clickOn("#quit");
		
	}
	
	/**
	 * This tests delete. with multiple
	 * objects on the screen.
	 */
	@Test
	public void testDelete2() { 
		drag(100, 100, 200, 200);
		checkSprite(display.getSprites().getSprite(0),
				100, 100, 100, 100);
		drag(10, 10, 35, 40);
		click(150, 150);
		assertNotNull(display.getController().getSelected());
		clickOn("#delete");
		assertNull(display.getController().getSelected());
		assertEquals(1, display.getSprites().size());
		clickOn("#quit");
		
	}
	
	/**
	 * This tests the clear button.
	 */
	@Test
	public void testClear() { 
		
		drag(100, 100, 200, 200);
		drag(75, 80, 90, 100);
		clickOn("#clear");
		assertNull(display.getController().getSelected());
		assertEquals(0, display.getSprites().size());
		clickOn("#quit");
		
	}
	
	/**
	 * This tests the clear button.
	 * This time with both ellipse and rectangles.
	 */
	@Test
	public void testClear2() { 
		
		drag(100, 100, 200, 200);
		selectComboItem("types", "Ellipse");
		drag(75, 80, 90, 100);
		clickOn("#clear");
		assertNull(display.getController().getSelected());
		assertEquals(0, display.getSprites().size());
		clickOn("#quit");
		
	}
	
	/**
	 * This tests the functionality that you can start
	 * in a sprite and drag off it 
	 * and it will not select anything and will not create a new
	 * sprite.
	 * 
	 */
	@Test
	public void testOnSpriteDrag() { 
		
		drag(100, 100, 200, 200);
		drag(150, 150, 300, 300);
		assertNull(display.getController().getSelected());
		assertEquals(1, display.getSprites().size());
		clickOn("#quit");
		
	}
	
	/**
	 * This tests the functionality that you can start
	 * in a sprite and drag off it 
	 * and it will not select anything and will not create a new
	 * sprite.
	 * 
	 */
	@Test
	public void testDeleteIsDisabled() {
		Button but = lookup("#delete").queryAs(Button.class);

		assertTrue(but.isDisabled());
		drag(100, 100, 200, 200);
		click(150, 150);
		assertFalse(but.isDisabled());
		clickOn("#delete");
		assertTrue(but.isDisabled());
		clickOn("#quit");

	}
}
