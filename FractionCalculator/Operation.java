package uwstout.cs145.section004;

public interface Operation {

	String getSymbol();
	String getName();
	Fraction execute(Fraction a, Fraction b);
	
}
