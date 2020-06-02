package uwstout.cs145.section004;

/**
 * This class will handle our fractions as one object instead of 2 variables
 * 
 * 
 * @author Blake Bissell
 * @version 1/25/2018
 *
 */
public class Fraction {

	// data

	private int num, denom;

	// constructors

	// num and denom
	public Fraction(int _num, int _denom) 
			throws IllegalArgumentException {
		num = _num;
		denom = _denom;
		if (denom == 0) {
			throw new IllegalArgumentException(
					"Cant have a denom of 0.");
		} else {
			fixNegative();
			reduce();
		}
	}

	// decimal

	// copy constructor
	public Fraction(Fraction obj) {
		num = obj.num;
		denom = obj.denom;
	}

	/* methods */

	// getters/setters
	public int getNum() {
		return num;
	}

	public int getDenom() {
		return denom;
	}

	public void setFrac(int _num, int _denom) 
		throws IllegalArgumentException {
		if (_denom != 0) {
			num = _num;
			denom = _denom;
			fixNegative();
			reduce();
		}
		else {
			throw new IllegalArgumentException(
					"Can't have 0 as a denominator.");
		}
	}

	// Fixing negative
	private void fixNegative() {
		// Both negatives
		if (num < 0 && denom < 0) {
			num = num * -1;
			denom = denom * -1;
		}
		// denom is negative num is pos
		else if (num > 0 && denom < 0) {
			num = num * -1;
			denom = denom * -1;
		}
	}

	private void reduce() {
		int larger;
		int smaller;
		int r;
		if (num == 0) {
			denom = 1;
		} else {
			if (Math.abs(num) > denom) {
				larger = Math.abs(num);
				smaller = denom;
			} else {
				larger = denom;
				smaller = Math.abs(num);
			}
			r = larger % smaller;
			while (r != 0) {
				larger = smaller;
				smaller = r;
				r = larger % smaller;
			}
			num /= smaller;
			denom /= smaller;
		}

	}

	// Add
	public Fraction add(Fraction obj) {
		Fraction sum = new Fraction((num * obj.denom) + (obj.num * denom), denom * obj.denom);
		return sum;
	}

	// Subtract
	public Fraction subtract(Fraction obj) {
		Fraction diff = new Fraction((num * obj.denom) - (obj.num * denom), denom * obj.denom);
		return diff;
	}

	// Multiply
	public Fraction multiply(Fraction obj) {
		int pNum = num * obj.num;
		int pDenom = denom * obj.denom;
		Fraction total = new Fraction(pNum, pDenom);
		return total;
	}

	// Divide
	public Fraction divide(Fraction obj) {
		Fraction f = new Fraction(
				(num*obj.denom),(denom*obj.num));
		return f;
		
	}
	// Invert
	public Fraction invert()
	throws IllegalArgumentException {
		Fraction f = new Fraction(denom,num);
		return f;
	}
	
	// Convert to decimal
	public double toDecimal() {
		double result;
		result = (double) num / denom;
		return result;
	}

	// converts to a string
	public String toString() {
		String str;
		str = num + "/" + denom;
		return str;
	}
	// Mixed numbers?
	// Display

}
