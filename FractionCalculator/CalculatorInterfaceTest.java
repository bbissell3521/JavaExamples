package uwstout.cs145.section004;

import static org.junit.Assert.*;



import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.control.Label;
import javafx.stage.Stage;

public class CalculatorInterfaceTest extends ApplicationTest {

	
	@Override
	public void start(Stage mainStage) throws Exception {
		
		CalculatorInterface calc = new CalculatorInterface();
		calc.start(mainStage);
		
	}

	@Override 
	public void stop() throws Exception {
		FxToolkit.hideStage();
	}
	
	@Test
	public void testInput() {
		
		clickOn("#button3");
		clickOn("#button<>");
		clickOn("#button5");
		
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		assertEquals("3/5", input.getFraction().toString());
	}
	
	@Test
	public void testInput0() {
		
		clickOn("#button3");
		clickOn("#button<>");
		clickOn("#button0");
		
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		assertNull(input.getFraction());
	}
	
	@Test
	public void testInput0top() {
		
		clickOn("#button0");
		clickOn("#button<>");
		clickOn("#button7");
		
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		assertEquals("0/1", input.getFraction().toString());
	}

	@Test
	public void testInputLeadingZero() {
		
		clickOn("#button0");
		clickOn("#button0");
		clickOn("#button0");
		clickOn("#button0");
		clickOn("#button0");
		clickOn("#button0");
		clickOn("#button<>");
		clickOn("#button0");
		clickOn("#button5");
		
		
		Label num = 
				lookup("#input_num").queryAs(Label.class);
		
		Label denom = 
				lookup("#input_denom").queryAs(Label.class);
		
		assertEquals("0", num.getText());
		assertEquals("5", denom.getText());
		
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		assertEquals("0/1", input.getFraction().toString());
	}
	
	@Test
	public void testOperation() { 
		
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		Label output = lookup("#calcOutput").queryAs(Label.class);
		//3/5 + 1/2 = 11/10
		
		write("3\t5");
		assertEquals("3/5", input.getFraction().toString());
		assertEquals("0/1", output.getText());
		
		clickOn("#button+");
		
		assertEquals("3/5", output.getText());
		
		write("1\t2");
		assertEquals("1/2", input.getFraction().toString());
		
		clickOn("#button+");
		
		assertEquals("11/10", output.getText());
	}
	
	@Test
	public void testClear() {
		CalculatorInput input =
				lookup("#textID").queryAs(CalculatorInput.class);
		write("3\t5");
		assertEquals("3/5", input.getFraction().toString());
		clickOn("#buttonC");
		assertEquals("0/1", input.getFraction().toString());
		
	}
}
