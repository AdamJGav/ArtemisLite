package tempart;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

/*
 * Elements extends this square class. This class is used to give a unique ID to
 * each square.
 */
public class Square {

	// instance vars
	private int squareNum;

	/*
	 * Default Constructor
	 */
	public Square() {
	}

	/**
	 * @param squareNum constructor with arguments
	 */
	public Square(int squareNum) {
		super();
		this.squareNum = squareNum;
	}

	/*
	 * Getters & Setters for squareNum
	 */
	public int getSquareNum() {
		return squareNum;
	}

	public void setSquareNum(int squareNum) {
		this.squareNum = squareNum;
	}
}
