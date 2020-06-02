package uwstout.cs145.section004;

public class CalculatorHistory {

	// Data

	// bunch of fractions
	private Fraction[] history;
	private int currentSize;

	// constructors

	public CalculatorHistory() {
		history = new Fraction[5];
		currentSize = 0;
	}

	public int size() {
		return currentSize;
	}

	// Operations

	//Grow the size of array 
	
	private Fraction[] grow(Fraction[] old, int newSize) {
		
		Fraction[] big = new Fraction[newSize];
		for (int i = 0; i < old.length; i++) {
			big[i] = old[i];
		}
		return big;
	}
	
	// add a fraction
	public void add(Fraction obj) {
		// TODO finish
		
		if (history.length == currentSize) {
			history = grow(history, currentSize + 2);
		}
		
		// put fraction in array
		history[currentSize] = obj;

		// increase count
		currentSize++;
		
	}

	// get a fraction
	public Fraction get(int pos) {
		if (pos < currentSize && pos >= 0) {
			return history[pos];
		} 
		return null;
	}
	
	public int findFraction(Fraction f) {
		for (int i = 0; i < currentSize; i++) {
			if (f.toString().equals(history[i].toString())) {
				return i;
			}
		}
		return -1;
	}
	// delete/clear
	// undo?
	// save/restore from file
	// get the number of fractions

}
