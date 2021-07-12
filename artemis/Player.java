package tempart;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

/*
 * This class holds all of the player properties. 
 */
public class Player {

	private String name;
	private int playerID;
	private int playerBalance;
	private int playerPosition;
	private boolean isBankrupt;

	private final static int STARTING_POSITION = 1;
	private final static int STARTING_BALANCE = 200;
	private final static int PASS_GO = 100;
	private final static int NUMBER_OF_SQUARES = 12;

	private final static String ERROR_MESSAGE_SQUARES = "INVALID";

	/**
	 * default constructor
	 */
	public Player() {

	}

	/**
	 * Creates a player - constructor with args and validation rules
	 * 
	 * @param name
	 * @param playerID
	 * @param playerBalance
	 * @param playerPosition {@value 0} to {@value #NUMBER_OF_SQUARES}
	 * @param isBankrupt
	 */
	public Player(String name, int playerID) {
		super();
		this.setName(name);
		this.setPlayerID(playerID);
		this.playerBalance = STARTING_BALANCE;
		this.setPlayerPosition(STARTING_POSITION);
		this.isBankrupt = false;
	}

	/**
	 * gets the player's name
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * sets the player's name
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * get the player ID
	 * 
	 * @return the number of players
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * Set method with 2-4 player validation rules
	 * 
	 * @param playerID the playerID is set
	 * @throws IllegalArgumentException
	 */
	public void setPlayerID(int playerID) throws IllegalArgumentException {

		this.playerID = playerID;
	}

	/**
	 * gets the player balance
	 * 
	 * @return the player balance
	 */
	public double getPlayerBalance() {
		return playerBalance;
	}

	/**
	 * sets the player balance
	 * 
	 * @param playerBalance the player balance is set
	 */
	public void setPlayerBalance(int playerBalance) {
		this.playerBalance = playerBalance;
	}

	/**
	 * gets the players position
	 * 
	 * @return the players position
	 */
	public int getPlayerPosition() {
		return playerPosition;
	}

	/**
	 * sets the players position Valid {@value 0} to {@value #NUMBER_OF_SQUARES}
	 * 
	 * @param playerPosition
	 * @throws IllegalArgumentException
	 */
	public void setPlayerPosition(int playerPosition) throws IllegalArgumentException {
		if ((playerPosition >= 1) && (playerPosition <= NUMBER_OF_SQUARES)) {
			this.playerPosition = playerPosition;
		} else {
			throw new IllegalArgumentException(ERROR_MESSAGE_SQUARES);

		}

	}

	/**
	 * Get method that determines if a player is bankrupt
	 * 
	 * @return a boolean (true or false) to bankrupt
	 */
	public boolean isBankrupt() {
		return isBankrupt;
	}

	/**
	 * sets boolean bankrupt
	 * 
	 * @param isBankrupt
	 */
	public void setBankrupt(boolean isBankrupt) {
		this.isBankrupt = isBankrupt;
	}

	/**
	 * Method to decrease players balance
	 */
	public void decreaseBalance(int price) {
		if ((this.playerBalance - price) > 0) {
			int balance = this.playerBalance -= price;
			this.setPlayerBalance(balance);
		} else {
			this.setBankrupt(true);
			System.out.println("Unlucky, You Are Bankrupt!");
		}
	}

	/**
	 * Method to increase players balance
	 */
	public void increaseBalance(int amount) {
		this.playerBalance += amount;
	}

	/**
	 * A display all method for all player data
	 */
	public void displayAllPlayerInfo() {
		System.out.println("Player's name          \t:" + this.name);
		System.out.println("Player's ID            \t:" + this.playerID);
		System.out.println("Player's balance       \t:" + this.playerBalance);
		System.out.println("Player's position      \t:" + this.playerPosition);
		System.out.println("");

	}

	/*
	 * A method to update player location
	 */
	public void movePlayer() {
		Dice dice = new Dice();
		int numMove = dice.rollDice();
		updatePlayerPosition(numMove);

	}

	/*
	 * Method for updating player position
	 */
	public void updatePlayerPosition(int numMove) {
		int tempPosition = this.playerPosition; // sets player position to temp int
		tempPosition += numMove;
		if (tempPosition > 12) {
			tempPosition -= 12;
			this.playerBalance += PASS_GO; // updates player balance when pass go
		}

		this.setPlayerPosition(tempPosition);
	}

}