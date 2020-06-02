package uwstout.cs145.section004;

public class SubtractOperation extends AbstractOperation {

	public SubtractOperation() {
		super("-", "Subtract");
	}

	@Override
	public Fraction execute(Fraction a, Fraction b) {
		return a.subtract(b);
	}
}
