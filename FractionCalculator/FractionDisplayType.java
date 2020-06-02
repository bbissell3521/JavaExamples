package uwstout.cs145.section004;
/**
 * Long Description
 * 
 * @author Student
 *
 */

public enum FractionDisplayType {
	/**
	 * This displays it as an improper fraction.
	 */
	Fraction,
	/**
	 * This displays it as a mixed number.
	 */
	MixedNumber,
	/**
	 * This displays it as a decimal.
	 */
	Decimal;
	
	public String toString() {
		String str;
		switch(this) {
		case Fraction:
			str = "Fraction (#/#)";
			break;
		case MixedNumber:
			str = "Mixed Number (# #/#)";
			break;
		default:
			str = "Decimal (#.##)";
			break;
		}
		return str;
	}
}
