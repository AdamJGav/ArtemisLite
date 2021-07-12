package tempart;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */


/*
 * This class holds all of the element proporties. 
 */

public class Element extends Square {

	/*
	 * Instance vars
	 */
	private Systems system;
	private String elementName;
	private int cost;
	private int rentPrice;
	private int devPrice;
	private int maxDevelopment;
	private boolean dev1;
	private boolean dev2;
	private boolean dev3;
	private boolean majorDev;
	private int ownedBy;

	/*
	 * Default Constructor
	 */
	public Element() {
	}

	/**
	 * Constructor with args
	 * 
	 * @param system
	 * @param elementName
	 * @param cost
	 * @param rentPrice
	 * @param baseDevPrice
	 * @param maxDevelopment
	 * @param dev1
	 * @param dev2
	 * @param dev3
	 * @param majorDev
	 * @param ownedBy
	 */
	public Element(int squareNum, Systems system, String elementName, int cost) {
		super(squareNum);
		this.system = system;
		this.elementName = elementName;
		this.cost = cost;
		this.rentPrice = this.cost / 2;
		this.devPrice = this.cost / 2;
		this.maxDevelopment = this.cost * 2;
		this.dev1 = false;
		this.dev2 = false;
		this.dev3 = false;
		this.majorDev = false;
		this.ownedBy = 0;
	}

	/*
	 * Getters and Setters
	 */
	public Systems getSystem() {
		return system;
	}

	public void setSystem(Systems system) {
		this.system = system;
	}

	public String getElementName() {
		return elementName;
	}

	public void setElementName(String elementName) {
		this.elementName = elementName;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getRentPrice() {
		return rentPrice;
	}

	public void setRentPrice(int rentPrice) {
		this.rentPrice = rentPrice;
	}

	public int getdevPrice() {
		return devPrice;
	}

	public void setdevPrice(int baseDevPrice) {
		this.devPrice = baseDevPrice;
	}

	public int getMaxDevelopment() {
		return maxDevelopment;
	}

	public void setMaxDevelopment(int maxDevelopment) {
		this.maxDevelopment = maxDevelopment;
	}

	public boolean isDev1() {
		return dev1;
	}

	public void setDev1(boolean dev1) {
		this.dev1 = dev1;
	}

	public boolean isDev2() {
		return dev2;
	}

	public void setDev2(boolean dev2) {
		this.dev2 = dev2;
	}

	public boolean isDev3() {
		return dev3;
	}

	public void setDev3(boolean dev3) {
		this.dev3 = dev3;
	}

	public boolean isMajorDev() {
		return majorDev;
	}

	public void setMajorDev(boolean majorDev) {
		this.majorDev = majorDev;
	}

	public int getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(int ownedBy) throws IllegalArgumentException {
		if (ownedBy < 1 || ownedBy > 4) {
			throw new IllegalArgumentException("Invalid Number");
		} else {
			this.ownedBy = ownedBy;
		}
	}

	public void displayElementInfo() {

		if (this.getSquareNum() == 1 || this.getSquareNum() == 7) {
			System.out.println("Element square number :     \t:" + this.getSquareNum());
			System.out.println("Element name          \t:" + this.getElementName());
			System.out.println("");
		} else {
			System.out.println("Element square number :     \t:" + this.getSquareNum());
			System.out.println("Element name          \t:" + this.getElementName());
			System.out.println("System name            \t:" + this.getSystem());
			System.out.println("Cost     \t:" + this.getCost());
			System.out.println("Development cost      \t:" + this.getdevPrice());
			System.out.println("Rent cost     \t:" + this.getRentPrice());
			if (this.getOwnedBy() == 0) {
				System.out.println("This element is available for purchase.");
			} else {
				System.out.println("Element owned by :\t Player " + this.getOwnedBy());
			}

			System.out.println("");
		}

	}

	public void displayElementInfo2() {

		if (this.getSquareNum() == 1 || this.getSquareNum() == 7) {
			System.out.printf("%-5s %-33s\n\n", this.getSquareNum(), this.getElementName());

		} else {
			System.out.printf("%-5s %-33s %-28s %-12s %-12s %-12s", this.getSquareNum(), this.getElementName(),
					this.getSystem(), this.getCost(), this.getdevPrice(), this.getRentPrice());
			if (this.getOwnedBy() == 0) {
				System.out.println("This element is available for purchase.");
			} else {
				System.out.println("Element owned by :\t Player " + this.getOwnedBy());
			}

			System.out.println("");
		}

	}

	public void displayFinalElementInfo() {
		String majDev = "null";
		if (this.isMajorDev()) {
			majDev = "Major Development Complete";
		}
		if (this.getSquareNum() == 1 || this.getSquareNum() == 7) {
			System.out.printf("%-5s %-33s\n\n", this.getSquareNum(), this.getElementName());

		} else {
			System.out.printf("%-5s %-33s %-28s %-28s", this.getSquareNum(), this.getElementName(), this.getSystem(),
					majDev);

			if (this.getOwnedBy() == 0) {
				System.out.println("This element is available for purchase.");
			} else {
				System.out.println("Element owned by :\t Player " + this.getOwnedBy());
			}

			System.out.println("");
		}

	}

}