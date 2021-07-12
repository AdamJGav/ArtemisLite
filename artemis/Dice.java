package tempart;

import java.util.Random;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

/*
 * This class creates the dice roll which is used to move players in the game.
 */
public class Dice {

	// instance vars
	private int diceOne;
	private int diceTwo;

	/*
	 * Method for rolling dice. Method calls random function and assigns two random
	 * numbers between 1-6 inclusive to the two dice int variables
	 */
	public int rollDice() {
		// variable used to store total value of both dice rolls
		int totalRoll;

		// new random function
		Random dicerandom = new Random();

		// assign random values to dice variables
		diceOne = dicerandom.nextInt(6) + 1;
		diceTwo = dicerandom.nextInt(6) + 1;

		// assign total roll value to variable
		totalRoll = diceOne + diceTwo;

		// method to display string representation of roll to player
		displayRoll(diceOne, diceTwo);

		// return totalRoll as an int
		return totalRoll;
	}

	/*
	 * Method for visual display of dice roll to user.
	 */
	private static void displayRoll(int diceOne, int diceTwo) {

		int totalRoll = diceOne + diceTwo;
		System.out.println("╔═══════════════╗");
		System.out.println("║ ≡ Dice Roll ≡ ║ ");
		System.out.println("╚═══════════════╝");
		System.out.println("Dice 1 Rolled : " + diceOne);
		System.out.println("");
		System.out.println("Dice 2 Rolled : " + diceTwo);
		System.out.println("");
		System.out.println("You can move " + (totalRoll) + " places...");
		System.out.println("You have landed on...");
		System.out.println("");

	}
}