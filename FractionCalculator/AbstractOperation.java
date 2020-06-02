package uwstout.cs145.section004;

public abstract class AbstractOperation implements Operation {

	private String sym;
	private String name;
	
	public AbstractOperation(String nSym, String nName) {
		sym = nSym;
		name = nName;
	}
	public String getName() {
		return name;
	}
	
	public String getSymbol() {
		return sym;
	}
	
	public abstract Fraction execute(Fraction a, Fraction b);
}

