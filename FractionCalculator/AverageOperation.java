package uwstout.cs145.section004;

public class AverageOperation implements Operation {

	@Override
	public String getSymbol() {
		return "Avg";
	}

	@Override
	public String getName() {
		return "Average";
	}

	@Override
	public Fraction execute(Fraction a, Fraction b) {
		Fraction n = new Fraction(2,1);
		return (a.add(b)).divide(n);
	}

	
}
