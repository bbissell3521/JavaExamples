package uwstout.cs145.section004;

public class OperationList {

	private Operation[] operations;
	private int count;

	public OperationList() {
		operations = new Operation[5];
		count = 0;
	}

	public Operation getOperation(int pos) {
		if (pos >= 0 && pos < count) {
			return operations[pos];
		}
		return null;
	}

	public int getNumOperations() {
		return count;
	}

	private Operation[] grow(Operation[] old, int newSize) {
		Operation[] big = new Operation[newSize];
		for (int i = 0; i < old.length; i++) {
			big[i] = old[i];
		}
		return big;
	}
	public void addOperation(Operation op)
			throws IllegalArgumentException{
		if (op == null) {
			throw new IllegalArgumentException(
					"Wont store null operation");
		}
		if (count == operations.length) {
			operations = grow(operations, count + 2);
		}
		operations[count] = op;
		count++;
	}

	public Operation getOperationBySymbol(String sym) {
		for (int i = 0; i < count; i++) {
			if (operations[i].getSymbol().equals(sym)) {
				return operations[i];
			}
		}
		return null;
	}

	public Operation getOperationbyName(String name) {
		for (int i = 0; i < count; i++) {
			if (operations[i].getName().equals(name)) {
				return operations[i];
			}
		}
		return null;
	}
}
