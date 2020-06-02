package uwstout.cs145.section004;

public class AddOperation implements Operation{

	@Override
	public String getSymbol() {
		return "+";
	}

	@Override
	public String getName() {
		return "Addition";
	}

	@Override
	public Fraction execute(Fraction a, Fraction b) {
		return a.add(b);
	}

}
