package tempart;

import java.util.Scanner;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

/*
 * This class processes all transactions within the game.
 */
public class Transactions {

	/**
	 * 
	 * @param p       - the player who wishes to buy an element
	 * @param element - the element in which the player wants to buy If the player
	 *                wishes to buy an unowned element that isn't GO or REST and has
	 *                an adequate balance this method will complete the transaction.
	 * 
	 * 
	 */
	public static void buyElement(Player p, Element element) {
		if (element.getSquareNum() != 1 && element.getSquareNum() != 7 && element.getOwnedBy() == 0) {
			p.decreaseBalance(element.getCost());
			if (!p.isBankrupt()) {
				System.out.println("╔═══════════════════════════╗");
				System.out.println("║ « ■ Purchase Complete ■ » ║ ");
				System.out.println("╚═══════════════════════════╝");
				element.setOwnedBy(p.getPlayerID());
				System.out.println("Element bought by " + p.getName() + " for : " + element.getCost());

				System.out.println(element.getSystem() + "\n" + element.getElementName());
				System.out.println("");
			} else if (p.isBankrupt()) {
				p.setBankrupt(true);
			}
		} else {
			System.out.println("╔═════════════════════════════════════════╗");
			System.out.println("║ « ■ You Cannot Purchase This Square ■ » ║ ");
			System.out.println("╚═════════════════════════════════════════╝");
		}
	}

	/**
	 * 
	 * @param payer
	 * @param payee
	 * @param e     If the player lands on an element that is owned by another and
	 *              they wish to charge them rent then this method will complete the
	 *              transaction.
	 */
	public static void payRent(Player payer, Player payee, Element e) {
		String userInput = null;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(payer.getName() + " has landed on " + e.getElementName() + ". This element is owned by "
					+ payee.getName() + ". " + payee.getName() + " would you like to charge " + payer.getName()
					+ " a rental price of " + e.getRentPrice() + "? (y/n).");
			userInput = scanner.next();
			if (userInput.equalsIgnoreCase("y")) {
				int rent = e.getRentPrice(); // gets element rent price
				if (payer.getPlayerBalance() >= e.getRentPrice()) {

					payer.decreaseBalance(rent);
					payee.increaseBalance(rent);
				} else {
					System.out.println(payer.getName() + ", you don't have enough money. You're bankrupt!");
					payer.setBankrupt(true);
				}
				System.out.println(payer.getName() + " just paid " + payee.getName() + " : " + rent);
			} else if (userInput.equalsIgnoreCase("n")) {
				System.out.println("Rent not charged.");
			}
		} while (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n"));
	}

	/**
	 * 
	 * @param player
	 * @param element If the player owns all elements within a system and they wish
	 *                to develop one of their elements this method will complete the
	 *                transaction.
	 */
	public static void developElement(Player player, Element element) {

		int playerID = player.getPlayerID();
		int elementOwnedBy = element.getOwnedBy();

		if (playerID == elementOwnedBy) {

			if (!element.isDev1()) {
				player.decreaseBalance(element.getdevPrice());
				element.setDev1(true);
				System.out.println("╔═══════════════════════════════╗");
				System.out.println("║ ■ Completed - Development 1 ■ ║ ");
				System.out.println("╚═══════════════════════════════╝");
			} else if (element.isDev1() && !element.isDev2()) {
				player.decreaseBalance(element.getdevPrice());
				element.setDev2(true);
				System.out.println("╔═══════════════════════════════════╗");
				System.out.println("║ ■ ■ Completed - Development 2 ■ ■ ║ ");
				System.out.println("╚═══════════════════════════════════╝");
			} else if (element.isDev1() && element.isDev2() && !element.isDev3()) {
				player.decreaseBalance(element.getdevPrice());
				element.setDev3(true);
				System.out.println("╔═══════════════════════════════════════╗");
				System.out.println("║ ■ ■ ■ Completed - Development 3 ■ ■ ■ ║ ");
				System.out.println("╚═══════════════════════════════════════╝");
			} else if (element.isDev1() && element.isDev2() && element.isDev3()) {
				player.decreaseBalance(element.getMaxDevelopment());
				element.setMajorDev(true);
				System.out.println("╔═════════■■■■■■■■■■■■■■■■■═════════╗");
				System.out.println("║  Completed - Major Development 3  ║ ");
				System.out.println("╚═════════■■■■■■■■■■■■■■■■■═════════╝");
			}
		}

	}
}
