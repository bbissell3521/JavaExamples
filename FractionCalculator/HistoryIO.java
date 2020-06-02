package uwstout.cs145.section004;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HistoryIO {

	//Stored in a file:
	// <num> <denom>
	
	public CalculatorHistory 
		read(String filename) throws FileNotFoundException {
		CalculatorHistory history = 
				new CalculatorHistory();
		
		File file = new File(filename);
		Scanner input = new Scanner(file);
		
		input.close();
		return history;
		
		//open file 
		
		//go through file 
		//read fraction
		//add to history
		
		//close file
		
		//return history
	}
}
