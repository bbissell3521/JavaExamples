
package uwstout.cs145.section004;

import java.util.Scanner;

/**
 * Read in two fractions and print out the product.
 * 
 * Read in two fractions (Numerator then denominator), Multiply them and print
 * the result as n/d.
 * 
 * @author Blake Bissell
 * @version 2018.1.23 v1.0
 * 
 */
public class FractionCalculator {
	/**
	 * Read in two fractions and print out the product.
	 * 
	 * Read in two fractions (Numerator then denominator), Multiply them and print
	 * the result as n/d.
	 * 
	 * @param args
	 *            Command line parameters
	 * 
	 */
	public static void main(String[] args) {

		// variables
		int num1 = 0, num2 = 0;
		int denom1 = 0, denom2 = 0;
		int numR, denomR;
		Scanner in = new Scanner(System.in);

		// Get values
		for (int i = 0; i < 2; i++) {
			System.out.println("Please enter the " + (i + 1)
					+ " fraction. Put numerator down press enter then enter denomenator and press enter.");
			if (i == 0) {
				num1 = in.nextInt();
				denom1 = in.nextInt();
			}
			if (i == 1) {
				num2 = in.nextInt();
				denom2 = in.nextInt();
			}
		}

		// math
		numR = num1 * num2;
		denomR = denom1 * denom2;

		// display
		System.out.println("Your first fraction is: " + num1 + "/" + denom1);
		System.out.println("Your first fraction is: " + num2 + "/" + denom2);
		System.out.println("The answer is:" + numR + "/" + denomR);

		// Closing Programs/Files
		in.close();
	}

	/*
	  public int getSmallest(int one, int two, int three, int four) {
		int[] nums = { one, two, three, four };
		int small = one;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < small) {
				small = nums[i];
			}
		}
		return small;
	}
*/
}
