package uwstout.cs145.projects.project1.drawing;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * This will display sprites. It allows you to drag and create
 * sprites around the screen.
 * 
 * @author Blake Bissell
 * @version v1.0
 *
 */
public class SpriteDisplay extends Application {

	private Sprite s;
	private double x;
	private double y;
	private Rectangle out;
	
	private Pane draw;
	private ComboBox<SpriteType> types;
	private SpriteManager sprites;
	private SpriteController control;
	private Handler eh;
	private Button delete;
	
	private Rectangle sel;
	private Stage ms;
	@Override
	public void start(Stage mainStage) throws Exception {

		out = null;
		
		ms = mainStage;
		
		eh = new Handler();
		
		sprites = new SpriteManager(5);
		sprites.setOnChange(eh);
		
		control = new SpriteController(sprites);
		control.setOnSelect(eh);
		
		draw = new Pane();
		draw.setId("spriteDisplay");
		draw.setPrefSize(600, 600);
		draw.setOnMousePressed(this::onMousePressed);
		draw.setOnMouseDragged(this::onMouseDragged);
		draw.setOnMouseReleased(this::onMouseReleased);
		
		types = new ComboBox<SpriteType>();
		ObservableList<SpriteType> list = types.getItems();
		list.addAll(SpriteType.RECTANGLE, SpriteType.ELLIPSE);
		types.setId("types");
		types.getSelectionModel().select(SpriteType.RECTANGLE);
		
		delete = new Button("delete");
		delete.setId("delete");
		delete.setDisable(true);
		delete.setOnAction(this::del);
		
		Button clear = new Button("clear");
		clear.setId("clear");
		clear.setOnAction(this::clr);
		
		Button quit = new Button("quit");
		quit.setId("quit");
		quit.setOnAction(this::qut);
		
		Region r1 = new Region();
		r1.setPrefHeight(20);
		
		Region r2 = new Region();
		r2.setPrefHeight(20);
		
		GridPane buttons = new GridPane();
		buttons.add(types, 0, 0);
		buttons.add(delete, 0, 2);
		buttons.add(r2, 0, 1);
		buttons.add(r1, 0, 4);
		buttons.add(clear, 0, 3);
		buttons.add(quit, 0, 5);
		
		BorderPane bp = new BorderPane();
		bp.setCenter(draw);
		bp.setRight(buttons);
		Scene scene = new Scene(bp, 800, 600);

		mainStage.setScene(scene);
		mainStage.setTitle("Sprites");
		// **********************************
		mainStage.show();

	}

	/*public static void main(String[] args) {
		launch(args);
	}
	*/
	/**
	 * This will get the SpriteManager stored in this class.
	 * 
	 * @return
	 * 	This returns a spritemanager.
	 */
	public SpriteManager getSprites() {
		return sprites;
	}
	
	/**
	 * This will return the controller stored in this class.
	 * @return
	 * 	This returns a SpriteController.
	 */
	public SpriteController getController() {
		return control;
	}
	
	/**
	 * this will handle all events. that spriteEvent makes. 
	 * 
	 * @author Blake Bissell
	 * @version v1.0
	 *
	 */
	private class Handler implements SpriteEvent {

		@Override
		public void change(SpriteEventType type, Sprite obj) {
			ObservableList<Node> l;
			switch(type) {
			case ADD:
				l = draw.getChildren();
				l.add(obj.getGraphic());
				break;
			case DELETE:
				l = draw.getChildren();
				l.remove(obj.getGraphic());
				break;
			case SELECTED:
				sel = new Rectangle(obj.getX(),
						obj.getY(), obj.getWidth(), obj.getHeight());
				sel.setFill(null);
				sel.setStroke(Color.RED);
			    l = draw.getChildren();
				l.add(sel);
				delete.setDisable(false);
				break;
			case UNSELECTED:
				l = draw.getChildren();
				l.remove(sel);
				delete.setDisable(true);
				break;
			default:
				break;
				
			}
			
		}
		
	}

	private void onMousePressed(MouseEvent e) {
		if (control.contains((int) e.getX(), (int) e.getY()) != null) {
			s = control.contains((int) e.getX(), (int) e.getY());
			e.consume();
			return;
		} else {

			x = e.getX();
			y = e.getY();

			out = new Rectangle(x, y, 0, 0);
			out.setFill(Color.WHITE);
			out.setStroke(Color.RED);

			ObservableList<Node> l = draw.getChildren();
			l.add(out);

			e.consume();
		}
	}

	private void onMouseDragged(MouseEvent e) {
		if (out != null) {
			
			if (e.getX() < x) {
				out.setX(e.getX());
				out.setWidth(x - e.getX());
			} else {
				out.setX(x);
				out.setWidth(e.getX() - x);
			}
			if (e.getY() < y) {
				out.setY(e.getY());
				out.setHeight(y - e.getY());
			} else {
				out.setY(y);
				out.setHeight(e.getY() - y);
			}
		}
		
		

		e.consume();
	}
	
	
	private void onMouseReleased(MouseEvent e) {
		if (out != null) {
			ObservableList<Node> l = draw.getChildren();
			l.remove(out);
			
			control.add(types.getSelectionModel().getSelectedItem(),
					(int) x, (int) y, (int) e.getX(), (int) e.getY());
			
			control.clearSelected();
			out = null;
			
			
		} else {
			if (control.contains((int) e.getX(), (int) e.getY()) == s) {
				control.select((int) e.getX(), (int) e.getY()); 
			}
		}
		e.consume();
	}
	
	private void del(ActionEvent e) {
		control.remove();
	}
	
	private void clr(ActionEvent e) {
		control.reset();
	}
	
	private void qut(ActionEvent e) {
		ms.close();
	}
}
