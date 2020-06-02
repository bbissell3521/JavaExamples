package uwstout.cs145.section004;

import java.text.DecimalFormat;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;

public class CalculatorOutput extends GridPane {

	private Fraction fDisplay;
	private Label output; 
	private ComboBox<FractionDisplayType> types;
	
	public CalculatorOutput(Fraction nValue, FractionDisplayType type) { 
		fDisplay = nValue; 
		output = new Label();
		output.setId("calcOutput");
		types = new ComboBox<FractionDisplayType>();
		ObservableList<FractionDisplayType> list = types.getItems();
		
		//fast way
		list.addAll(FractionDisplayType.values());
		
		output.setPrefWidth(125);
		output.setFont(Font.font(24));
		types.setPrefWidth(75);
		types.getSelectionModel().select(type);
		types.setOnAction(this::comboBoxUpdate);
		
		add(output, 0, 0);
		add(types, 1, 0);
		
		updateLabel();
	}
	
	private void updateLabel()	{ 
		
		FractionDisplayType type = types.getValue();
		String str;
		
		switch (type) {
		
		case Decimal:
			DecimalFormat fmt = new DecimalFormat("0.####");
			str = "" + fmt.format(fDisplay.toDecimal());
			break;
		case Fraction:
			str = fDisplay.toString();
			break;
		default:
			// # #/#
			int whole = fDisplay.getNum() / fDisplay.getDenom();
			int newNum = fDisplay.getNum() % fDisplay.getDenom();
			str = whole + " " + newNum + "/" + fDisplay.getDenom();
			break;
		}
		
		output.setText(str);
	}
	
	private void comboBoxUpdate(ActionEvent e) {
		updateLabel();
	}
	
	public void setOutput(Fraction frac) { 
		fDisplay = frac;
		updateLabel();
	}
	
	public Fraction getOutput() { 
		return fDisplay;
	}
	
	
}
