package uwstout.cs145.section004;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorControllerTest {

	@Test
	public void testGetOperations() {
		OperationList list = CalculatorController
				.getOperations();
		
		assertNotNull(list);
		assertEquals(5, list.getNumOperations());
		
		Operation op = list.getOperationBySymbol("+");
		Fraction a = new Fraction(1, 2);
		Fraction b = new Fraction(7, 8);
		
		assertEquals("11/8", op.execute(a,b).toString());
		
		op = list.getOperationBySymbol("-");
		assertEquals("-3/8", op.execute(a,b).toString());
		
		
	}

}
