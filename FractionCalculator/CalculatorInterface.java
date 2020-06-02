package uwstout.cs145.section004;

import java.util.Scanner;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CalculatorInterface extends Application{

	private Font btnFont;
	
	private CalculatorInput input;
	private CalculatorOutput output;
	private Operation currentOp;
	private CalculatorHistory data;
	
	private static final String[] BUTTON_SYMBOLS = {
			"CE", "C", "?", "<>",
			"7", "8", "9", "+",
			"4", "5", "6", "-",
			"1", "2", "3", "x",
			"?", "0", "=", "/",
	};
	
	
	@Override
	public void start(Stage mainStage) throws Exception {

		btnFont = Font.font(24);
		
		data = new CalculatorHistory();
		currentOp = null;
		
		input = new CalculatorInput();
		input.setId("textID");

		output = new CalculatorOutput(new Fraction(0, 1), FractionDisplayType.Fraction);

		// something for io

		// group
		GridPane controls = new GridPane();
		controls.add(createButtons(), 0, 2);
		controls.add(input, 0, 1);
		controls.add(output, 0, 0);
		controls.setBackground(Background.EMPTY);
		controls.setOnKeyTyped(this::keyed);

		// scene
		Scene scene = new Scene(controls, 400, 650, Color.BLANCHEDALMOND);

		mainStage.setScene(scene);
		mainStage.setTitle("Calculator");
		// **********************
		mainStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	
	//Creates stuffs ------------------------
	
	/**
	 * Creates an array of buttons
	 * 
	 * @return Returns the GridPane of buttons
	 */
	private GridPane createButtons() {
		GridPane grid = new GridPane();
		for (int i = 0; i < BUTTON_SYMBOLS.length; i++) {
			grid.add(createButton(BUTTON_SYMBOLS[i]), i % 4, i / 4);

		}

		return grid;

	}

	private Button createButton(String name) {
		Button butt = new Button(name);
		butt.setPrefSize(50, 50);

		if (name.length() > 1) {
			butt.setFont(Font.font(18));
		} else {
			butt.setFont(btnFont);
		}
		butt.setId("button" + name);
		// Makes the buttons do something
		butt.setOnAction(this::buttonClick);
		butt.setOnMouseEntered(this::entered);
		butt.setOnMouseExited(this::exited);

		return butt;
	}
	
	
	
	
	//Events_____________________________________

	/**
	 * This is what the button is doing
	 * 
	 * @param event
	 *            The event type that is being used.
	 */
	private void buttonClick(ActionEvent event) {

		if (event.getSource() instanceof Button) {
			Button b = (Button) event.getSource();
			processInput(b.getText());
		}
	}

	private void entered(MouseEvent e) {
		Button b = (Button) e.getSource();
		b.setStyle("-fx-background-color:lightblue");
	}

	private void exited(MouseEvent e) {
		Button b = (Button) e.getSource();
		b.setStyle("");
	}

	private void keyed(KeyEvent e) {
		processInput(e.getCharacter());
	}

	private void processInput(String str) {
		Scanner scan = new Scanner(str);

		if (scan.hasNextInt()) {
			input.addDigit(scan.nextInt());
		} else {
			switch (str) {
			
			case "<>":
			case "\t":
				input.switchSide();
				break;
			case "C":
				input.reset();
				break;
			case "CE":
				input.reset();
				output.setOutput(new Fraction(0, 1));
				currentOp = null;
				break;
			default:
				Fraction inFrac = input.getFraction();
				Fraction outFrac = output.getOutput();
				if (currentOp != null) {
					Fraction result = currentOp.execute(outFrac, inFrac);

					output.setOutput(result);
					data.add(result);
				} else {
					output.setOutput(inFrac);
				}
				input.clear();
				currentOp = CalculatorController.getOperations().getOperationBySymbol(str);
				break;
			}
		}
		scan.close();
	}

// TODO
// Input
// Operation
// move input to output
// store operation
// clear input
// add input to history
// Input
// Calculate on next operation
// attach buttons to operation
// repeat

}
