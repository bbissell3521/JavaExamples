package uwstout.cs145.section004;

public class CalculatorController {

	public static OperationList operations;

	private CalculatorController() {
		// stop creation of objects
	}

	private static void createList() {
		// setup list
		operations = new OperationList();
		
		operations.addOperation(new AddOperation());
		operations.addOperation(new AverageOperation());
		operations.addOperation(new MultiplyOperation());
		operations.addOperation(new DivideOperation());
		operations.addOperation(new SubtractOperation());

	}

	public static OperationList getOperations() {
		if (operations == null) {
			createList();
		}
		return operations;
	}

}
