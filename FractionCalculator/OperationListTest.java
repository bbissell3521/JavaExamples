package uwstout.cs145.section004;

import static org.junit.Assert.*;

import org.junit.Test;

public class OperationListTest {

	@Test
	public void testOperationList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOperation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumOperations() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddOperation() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetOperationBySymbol() {
		OperationList list = new OperationList();
		list.addOperation(new AddOperation());
		list.addOperation(new AverageOperation());
		
		Operation op = list.getOperationBySymbol("+");
		assertEquals("+", op.getSymbol());
		assertEquals("Addition", op.getName());
		
		Fraction sum = op.execute(new Fraction(1,2), new Fraction(3,4));
		assertEquals("5/4", sum.toString());
		
		op = list.getOperationBySymbol("Avg");
		assertEquals("Avg", op.getSymbol());
		assertEquals("Average", op.getName());
		
		Fraction avg = op.execute(new Fraction(1,2), new Fraction(3,4));
		assertEquals("5/8", avg.toString());
	}

	@Test
	public void testGetOperationbyName() {
		fail("Not yet implemented");
	}

}
