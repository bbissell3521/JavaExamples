package uwstout.cs145.section004;

import static org.junit.Assert.*;

import org.junit.Test;

public class FractionDisplayTypeTest {

	@Test
	public void testToString() {
		FractionDisplayType t = FractionDisplayType.Fraction;
		FractionDisplayType m = FractionDisplayType.MixedNumber;
		FractionDisplayType d = FractionDisplayType.Decimal;
		
		assertEquals("Fraction (#/#)", t.toString());
		assertEquals("Mixed Number (# #/#)",m.toString());
		assertEquals("Decimal (#.##)", d.toString());
		
	}

}
