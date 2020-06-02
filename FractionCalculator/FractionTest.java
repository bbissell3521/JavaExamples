package uwstout.cs145.section004;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionTest {

	private static final double DELTA = 0.0001;

	@Test
	public void testFractionIntInt() {

		// create an object
		Fraction obj = new Fraction(1, 2);
		// Call a method
		// check results
		assertEquals(1, obj.getNum());
		assertEquals(2, obj.getDenom());

		Fraction obj2 = new Fraction(3, 4);
		assertEquals("testing 3/4", 3, obj2.getNum());
		assertEquals("testing 3/4", 4, obj2.getDenom());
	}

	@Test
	public void testFractionFraction() {
		Fraction obj1 = new Fraction(1, 2);
		Fraction obj2 = new Fraction(obj1);

		assertEquals(obj1.getNum(), obj2.getNum());
		assertEquals(obj1.getDenom(), obj2.getDenom());
		assertEquals(1, obj1.getNum());
		assertEquals(1, obj2.getNum());
		assertEquals(2, obj1.getDenom());
		assertEquals(2, obj2.getDenom());

	}

	@Test
	public void testSetFrac() {
		fail("Not yet implemented");
	}

	@Test
	public void testMultiply() {
		// small numbers
		Fraction obj1 = new Fraction(1, 2);
		Fraction obj2 = new Fraction(7, 9);
		Fraction product = obj1.multiply(obj2);
		assertEquals(7, product.getNum());
		assertEquals(18, product.getDenom());

		// negative

		// zero

		// large numbers

		// obj2 *obj1

		// did obj1 or obj2 change

	}

	@Test
	public void testToDecimal() {

		Fraction obj1 = new Fraction(1, 2);
		assertEquals(.5, obj1.toDecimal(), DELTA);

		Fraction obj2 = new Fraction(7, 10);
		assertEquals(.7, obj2.toDecimal(), DELTA);

	}

	@Test
	public void testToString() {
		Fraction obj1 = new Fraction(3, 5);
		assertEquals("3/5", obj1.toString());

		Fraction obj2 = new Fraction(-3, 5);
		assertEquals("-3/5", obj2.toString());

		Fraction obj3 = new Fraction(3, -5);
		assertEquals("-3/5", obj3.toString());

		Fraction obj4 = new Fraction(-3, -5);
		assertEquals("3/5", obj4.toString());
	}

	@Test
	public void testNegative() {
		fail();
	}

	@Test (expected = IllegalArgumentException.class)
	public void testZero() {
		Fraction obj1 = new Fraction(2, 0);
		assertEquals(0, obj1.getNum());
		assertEquals(1, obj1.getDenom());

	}

	@Test
	public void testReduce() {
		Fraction obj1 = new Fraction(5,7);
		assertEquals(5, obj1.getNum());
		assertEquals(7, obj1.getDenom());
		
		Fraction obj2 = new Fraction(4,8);
		assertEquals(1, obj2.getNum());
		assertEquals(2, obj2.getDenom());
		
		Fraction obj3 = new Fraction(6,24);
		assertEquals(1, obj3.getNum());
		assertEquals(4, obj3.getDenom());
		
		Fraction obj4 = new Fraction(18,6);
		assertEquals(3, obj4.getNum());
		assertEquals(1, obj4.getDenom());
		
		Fraction obj5 = new Fraction(4,4);
		assertEquals(1, obj5.getNum());
		assertEquals(1, obj5.getDenom());
		
		Fraction obj6 = new Fraction(20,30);
		assertEquals(2, obj6.getNum());
		assertEquals(3, obj6.getDenom());
		
		Fraction obj7 = new Fraction(-5,-10);
		assertEquals(1, obj7.getNum());
		assertEquals(2, obj7.getDenom());
		
		Fraction obj8 = new Fraction(5,-10);
		assertEquals(-1, obj8.getNum());
		assertEquals(2, obj8.getDenom());
		
		Fraction obj9 = new Fraction(-5,10);
		assertEquals(-1, obj9.getNum());
		assertEquals(2, obj9.getDenom());
		
		Fraction obj10 = new Fraction(0,10);
		assertEquals(0,obj10.getNum());
		assertEquals(1,obj10.getDenom());
	}

	@Test
	public void testSubtract() {
		Fraction obj1 = new Fraction(1,2);
		Fraction obj2 = new Fraction(2,3);
		Fraction diff = obj1.subtract(obj2);
		
		assertEquals(-1, diff.getNum());
		assertEquals(6, diff.getDenom());
	}
	@Test(expected = IllegalArgumentException.class)
	public void testInvert1() {
		Fraction obj3 = new Fraction(0,1);
		obj3.invert();
	}
	
	@Test 
	public void testInvert2 () {
		try {
		Fraction f = new Fraction(0,1);
		f.invert();
		fail();
		} catch(IllegalArgumentException e) {
			
		} catch(Exception e) {
			fail();
		}
		
		
	}
	@Test
	public void testDivide() {
		Fraction obj1 = new Fraction(2,3);
		Fraction obj2 = new Fraction(1,2);
		Fraction diff = obj1.divide(obj2);
		assertEquals(4, diff.getNum());
		assertEquals(3, diff.getDenom());
				
	}
}
