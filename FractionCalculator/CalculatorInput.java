package uwstout.cs145.section004;

import javafx.scene.control.Label;

import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class CalculatorInput extends GridPane {

	private Label num;
	private Label denom;
	private Label slash;
	private boolean addToNum;
	private boolean invNum;
	private boolean invDenom;

	public CalculatorInput() {

		invNum = true;
		invDenom = true;
		addToNum = true;

		num = new Label("0");
		num.setAlignment(Pos.CENTER_RIGHT);
		num.setPrefWidth(90);
		num.setFont(Font.font(24));
		num.setId("input_num");

		denom = new Label("1");
		denom.setAlignment(Pos.CENTER_RIGHT);
		denom.setPrefWidth(90);
		denom.setFont(Font.font(24));
		denom.setId("input_denom");

		slash = new Label("/");
		slash.setAlignment(Pos.CENTER_RIGHT);
		slash.setFont(Font.font(24));

		add(num, 0, 0);
		add(slash, 1, 0);
		add(denom, 2, 0);

		selectSide();
	}

	public void addDigit(int digit) {

		if (digit >= 0 && digit <= 9) {
			if (addToNum) {
				updateLabel(num, digit, invNum);
				invNum = false;
			} else {
				updateLabel(denom, digit, invDenom);
				invDenom = false;
			}
		}

	}

	public Fraction getFraction() {

		try {
			Scanner scan = new Scanner(num.getText() + " " + denom.getText());
			Fraction f = new Fraction(scan.nextInt(), scan.nextInt());
			scan.close();
			return f;

		} catch (Exception e) {
			return null;
		}
	}

	private void updateLabel(Label nLabel, int digit, boolean invalid) {
		Scanner scan = new Scanner(nLabel.getText());
		if (scan.hasNextInt() && !invalid) {
			if (digit != 0 && !nLabel.getText().equals("0")) {
				String str = nLabel.getText() + digit;
				nLabel.setText(str);
			}
		} else {
			nLabel.setText("" + digit);
		}

		scan.close();

	}

	public boolean isAddingNum() {
		return addToNum;
	}

	public boolean isAddingDenom() {
		return !addToNum;
	}

	public void setToNum(boolean num) {
		addToNum = num;
		selectSide();
	}

	public void switchSide() {
		addToNum = !addToNum;
		selectSide();
	}

	public void clear() {
		invNum = true;
		invDenom = true;
		addToNum = true;
		selectSide();
	}
	
	private void selectSide() {
		if (addToNum) {
			num.setStyle("-fx-background-color : lightblue");
			denom.setStyle("");
		} else {
			denom.setStyle("-fx-background-color : lightblue");
			num.setStyle("");
		}
	}

	public void reset() { 
		clear();
		num.setText("0");
		denom.setText("1");
	}
}
