package uwstout.cs145.section004;

import static org.junit.Assert.*;

import org.junit.Test;

public class CalculatorHistoryTest {
	
	private CalculatorHistory createHistory() {
		CalculatorHistory hist = 
				new CalculatorHistory();
		
		hist.add(new Fraction(1,2));
		hist.add(new Fraction(3,4));
		hist.add(new Fraction(5,3));
		hist.add(new Fraction(7,9));
		
		return hist;
	}

	@Test
	public void testCalculatorHistory() {
		CalculatorHistory hist = 
				new CalculatorHistory();
		assertEquals(0, hist.size());
	}

	@Test
	public void testSize() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		CalculatorHistory hist = createHistory();
		
		assertEquals(4, hist.size());
		assertEquals("1/2", hist.get(0).toString());
		assertEquals("3/4", hist.get(1).toString());
		assertEquals("5/3", hist.get(2).toString());
		assertEquals("7/9", hist.get(3).toString());
		
		//add more fractions
		
		hist.add(new Fraction(2,7));
		assertEquals(5, hist.size());
		assertEquals("2/7", hist.get(4).toString());
		
		hist.add(new Fraction(12,7));
		assertEquals(6, hist.size());
		assertEquals("12/7", hist.get(5).toString());
		
		hist.add(new Fraction(1,4));
		assertEquals(7, hist.size());
		assertEquals("1/4", hist.get(6).toString());
		
		hist.add(new Fraction(1,4));
		assertEquals(8, hist.size());
		assertEquals("1/4", hist.get(7).toString());
		
	}

	@Test
	public void testGet() {
		//TODO test bad positions
		fail("Not yet implemented");
	}

}
