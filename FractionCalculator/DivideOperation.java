package uwstout.cs145.section004;

public class DivideOperation extends AbstractOperation {

	public DivideOperation() {
		super("/", "Divide");
	}
	
	public Fraction execute(Fraction a, Fraction b) {
		return a.divide(b);
	}
}
