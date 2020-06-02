package uwstout.cs145.section004;

public class MultiplyOperation extends AbstractOperation {

	public MultiplyOperation() {
		super("x", "Multiply");
	}

	@Override
	public Fraction execute(Fraction a, Fraction b) {
		return a.multiply(b);
	}

	
	
}
