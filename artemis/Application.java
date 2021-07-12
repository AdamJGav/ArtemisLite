package tempart;

import java.util.ArrayList;

import java.util.InputMismatchException;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

import java.util.Scanner;

/*
 * This class functions as the main game processor. 
 * This class integrates the components to make a functioning game system. 
 */
public class Application {

	public static Scanner sc = new Scanner(System.in);
	// array of players
	public static ArrayList<Player> players = new ArrayList<>();

	public static boolean GAME_OVER = false;
	public static boolean GAME_ON = false;

	public static ArrayList<Element> elements;

	public static void main(String[] args) throws InterruptedException {
		GAME_ON = false;
		showMenu();
		gamePlayLoop();

	}

	/**
	 * This method displays the main game menu
	 * 
	 * @throws InterruptedException
	 */
	public static void showMenu() throws InterruptedException {
		System.out.println("╔═════════════════════════╗");
		System.out.println("║ ▒ THE ARTEMIS PROJECT ▒ ║");
		System.out.println("╚═════════════════════════╝");
		elements = BoardGame.setUp(); // setup game
		int userEntry = 0;
		System.out.println("Welcome to the Artemis Mission! We at NASA have enlisted your aid to acquire funding and complete \nresearch into various new technologies that will help us to get the first woman and next man on the Moon!\n");
		System.out.println("Travel round the board, buying elements as you go. Your goal is to acquire all the elements in one \nsystem and proceed to develop them with 3 minor developments and one final Major Development. When all the \nelements in all systems are complete, you win!\n");
		System.out.println("Be careful though, as if you land on a square owned by another player, they can charge you rent, and \nif you aren't careful with your funds when renting/developing, you could go banrupt- ending the game for everyone!");
		System.out.println("\nGood Luck, and we'll see you on the Moon!\n");
		do {
			// info explaining game
			System.out.println("1. Start Game");
			System.out.println("2. Show Board Info");
			System.out.println("3. Quit game");

			try {
				userEntry = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				sc.nextLine();
			}

			switch (userEntry) {
			case 1:
				try {
					startGame();
					GAME_ON = true;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:

				displayGameSetup();

				break;
			case 3:
				quitGame();
				break;
			default:
			}
		} while (userEntry != 1 && userEntry != 3);
	}

	/**
	 * This method validates player numbers & sets player names.
	 * 
	 * @throws InterruptedException
	 */
	public static void startGame() throws InterruptedException {
		int numOfPlayers = 0;
		while (numOfPlayers < 2 || numOfPlayers > 4) {
			try {
				System.out.println("How many players? (2-4)");
				numOfPlayers = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				sc.nextLine();
			}
		}

		System.out.println("Players name:");
		for (int loop = 1; loop <= numOfPlayers; loop++) {
			Player p = new Player();
			System.out.println("Enter Player Name");
			String name = sc.next();
			p = new Player(name, loop);
			if (loop == 1) {
				p = new Player(name, loop);
			} else if (loop > 1) {
				for (Player player : players) {
					if (player.getName().equalsIgnoreCase(name)) {
						do {
							System.out.println("Try another name...");
							name = sc.next();
						} while (player.getName().equalsIgnoreCase(name));
						p = new Player(name, loop);

					} else {
						p = new Player(name, loop);
					}
				}
			}
			players.add(p);
		}
		displayPlayers();
	}

	/**
	 * This method displays the full game setup.
	 */
	public static void displayGameSetup() {
		System.out.printf("%-5s %-33s %-28s %-12s %-12s %-12s\n", "", "Element Name", "System", "Cost", "Development*",
				"Rent*");
		System.out.println(
				"═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
		for (Element element : elements) {
			element.displayElementInfo2();
		}
	}

	/**
	 * This method displays the player info in the game.
	 */
	public static void displayPlayers() {
		System.out.println("╔════════════════════════╗");
		System.out.println("║ ■ ■ Player Details ■ ■ ║ ");
		System.out.println("╚════════════════════════╝");
		for (Player player : players) {
			player.displayAllPlayerInfo();
		}
	}

	/**
	 * Method to display all elements owned by a player
	 * 
	 * @param element
	 * @param player
	 */
	public void displayOwnedElements(ArrayList<Element> element, Player player) {

		for (Element e : element) {
			if (e.getOwnedBy() == player.getPlayerID()) {
				System.out.println("Element System     \t" + e.getSystem());
				System.out.println("Element Name     \t" + e.getElementName());
				if (!e.isDev1()) {
					System.out.println("Minor Development Progress 0/3");
				} else if (e.isDev1() && !e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 1/3");
				} else if (e.isDev1() && e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 2/3");
				} else if (e.isDev1() && e.isDev2() && e.isDev3()) {
					System.out.println("Minor Development Progress 3/3");
				} else if (e.isDev1() && e.isDev2() && e.isDev3() && e.isMajorDev()) {
					System.out.println("Major Development Complete");
				}

			}
		}
	}

	/**
	 * This method starts a new turn while in the gameplay loop.
	 * 
	 * @param player
	 */
	public static void newTurn(Player player) {

		boolean hasMoved = false;
		int userEntry = 0;
		do {

			System.out.println("═════════════════════════════");
			System.out.println(" ■ ■ Player " + player.getPlayerID() + "'s turn ■ ■  ");
			System.out.println(player.getName() + ", you are on Square " + player.getPlayerPosition());
			System.out.println("═════════════════════════════");
			System.out.println("1. Move");
			System.out.println("2. Buy Square");
			System.out.println("3. Offer Square");
			System.out.println("4. Develop Square");
			System.out.println("5. Show Game Info");
			System.out.println("6. Show Your Info");
			System.out.println("7. End Turn");
			System.out.println("8. Quit Game");

			try {
				userEntry = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Please enter a number.");
				sc.nextLine();

			}

			switch (userEntry) {
			case 1: // move

				if (hasMoved == false) {
					player.movePlayer();
					hasMoved = true;
					checkRent(player);
				} else {
					System.out.println("You have already moved, try another option...");
				}

				for (Element element : elements) {
					if (element.getSquareNum() == player.getPlayerPosition()) {
						displayElement2Heading();
						element.displayElementInfo2();
					}
				}

				if (player.getPlayerPosition() == 7) {
					System.out.println("You landed on a rest - hard luck!");
					userEntry = 7;
				}

				break;
			case 2: // buy square

				checkBuy(player);

				break;
			case 3: // offer square
				int position = player.getPlayerPosition();
				offerSquare(position);

				break;
			case 4: // develop square
				displayDevelopmentOptions(player);

				break;
			case 5: // show game info
				displayGameStatus(elements, players);
				break;
			case 6: // show your info
				System.out.println("╔══════════════════╗");
				System.out.println("║ ■ Your Details ■ ║ ");
				System.out.println("╚══════════════════╝");
				player.displayAllPlayerInfo();
				System.out.println("═══════════════════");
				System.out.println(" ■ Your Elements ■ ");
				System.out.println("═══════════════════");
				displayPlayerElements(elements, player);
				System.out.println("");
				break;
			case 7: // end turn

				break;
			case 8:
				quitGame();
				break;
			default:
			}

			checkGameStatus();

		} while (userEntry != 7);
	}

	/**
	 * This method displays the current game status
	 * 
	 * @param player
	 */
	public static void displayGameStatus(ArrayList<Element> element, ArrayList<Player> players) {
		System.out.println("╔═════════════════════╗");
		System.out.println("║ ■ ■ Game Status ■ ■ ║ ");
		System.out.println("╚═════════════════════╝");
		for (Element e : elements) {
			if (e.getSquareNum() == 1 || e.getSquareNum() == 7) {
				continue;
			} else {
				System.out.println("Element System:    \t" + e.getSystem());
				System.out.println("Element Name:    \t" + e.getElementName());
				if (!e.isDev1()) {
					System.out.println("Minor Development Progress 0/3");
				} else if (e.isDev1() && !e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 1/3");
				} else if (e.isDev1() && e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 2/3");
				} else if (e.isDev1() && e.isDev2() && e.isDev3()) {
					System.out.println("Minor Development Progress 3/3");
				}

				if (e.isDev1() && e.isDev2() && e.isDev3() && e.isMajorDev()) {
					System.out.println("Major Development Complete");
				} else {
					System.out.println("Major Development Not Complete");
				}

				int ownedBy = e.getOwnedBy();

				for (Player player : players) {
					if (ownedBy == player.getPlayerID()) {
						System.out.println("Owned by : " + player.getPlayerID() + "(" + player.getName() + ")");
					} else {

					}
				}
				if (e.getOwnedBy() == 0) {
					System.out.println("Square is available for purchase.");
					System.out.println("Square Cost: " + e.getCost());
				}
				System.out.println("");
			}
		}
	}

	/**
	 * This method displays all of the elements in which a player owns.
	 * 
	 * @param element
	 * @param p
	 */
	public static void displayPlayerElements(ArrayList<Element> element, Player p) {
		int count = 0;
		for (Element e : elements) {
			if (e.getOwnedBy() == p.getPlayerID()) {
				count++;
				System.out.println("Element System     \t" + e.getSystem());
				System.out.println("Element Name     \t" + e.getElementName());
				if (!e.isDev1()) {
					System.out.println("Minor Development Progress 0/3");
				} else if (e.isDev1() && !e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 1/3");
				} else if (e.isDev1() && e.isDev2() && !e.isDev3()) {
					System.out.println("Minor Development Progress 2/3");
				} else if (e.isDev1() && e.isDev2() && e.isDev3()) {
					System.out.println("Minor Development Progress 3/3");
				} else if (e.isDev1() && e.isDev2() && e.isDev3() && e.isMajorDev()) {
					System.out.println("Major Development Complete");
				}

			} else if (e.getOwnedBy() == 0) {
				continue;
			}
			System.out.println("");

		}
		if (count == 0) {
			System.out.println("You do not own any elements.");
		}
	}

	/**
	 * This method calls the play through method to run through a turn of each
	 * player while GAME_OVER is set to false.
	 */
	public static void gamePlayLoop() {
		do {
			playThru();

		} while (GAME_OVER == false);
	}

	/**
	 * This method gives each player a turn in the player array.
	 */
	public static void playThru() {
		for (Player player : players) {
			newTurn(player);
		}
	}

	/**
	 * This method enables the player to force quit the game. A prompt is used here
	 * to make sure the player is certain they wish to quit the game.
	 */
	public static void quitGame() {
		System.out.println("Are you sure you want to quit the game? (y/n)");
		String userInput = sc.next();
		if (userInput.equalsIgnoreCase("y")) {

			System.out.println("╔═════════════════════════════════════╗");
			System.out.println("║ ■ ■ GAME OVER - Mission Failed! ■ ■ ║ ");
			System.out.println("╚═════════════════════════════════════╝");

			System.exit(0);

		} else if (userInput.equalsIgnoreCase("n")) {

			if (GAME_ON == false) {
				try {
					showMenu();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if (GAME_ON == true) {
				return;

			}

		} else {
			quitGame();
		}
	}

	/**
	 * This method is used to show the player which elements are available for them
	 * to develop.
	 * 
	 * @param player
	 */
	public static void displayDevelopmentOptions(Player player) {
		int orion_count = 0;
		int SLS_count = 0;
		int EGS_count = 0;
		int gateway_count = 0;
		for (Element element : elements) {
			if (player.getPlayerID() == element.getOwnedBy()) {
				switch (element.getSystem()) {
				case EXPLORATION_GROUND_SYSTEM:
					EGS_count++;
					break;
				case ORION:
					orion_count++;
					break;
				case SPACE_LAUNCH_SYSTEM:
					SLS_count++;
					break;
				case GATEWAY:
					gateway_count++;
					break;

				default:
					break;
				}

			}
		}

		if (orion_count == 2 || SLS_count == 2 || gateway_count == 3 || EGS_count == 3) {

			System.out.println("Which element would you like to develop? (Enter Square Number)");
			displayElement2Heading();

			if (SLS_count == 2) {
				for (Element element : elements) {
					if (element.getSystem() == Systems.SPACE_LAUNCH_SYSTEM) {
						if (!element.isMajorDev()) {
							element.displayElementInfo2();
						}

					}
				}
			}

			if (gateway_count == 3) {
				for (Element element : elements) {
					if (element.getSystem() == Systems.GATEWAY) {
						if (!element.isMajorDev()) {
							element.displayElementInfo2();
						}

					}
				}
			}

			if (orion_count == 2) {
				for (Element element : elements) {
					if (element.getSystem() == Systems.ORION) {
						if (!element.isMajorDev()) {
							element.displayElementInfo2();
						}

					}
				}
			}

			if (EGS_count == 3) {
				for (Element element : elements) {
					if (element.getSystem() == Systems.EXPLORATION_GROUND_SYSTEM) {
						if (!element.isMajorDev()) {
							element.displayElementInfo2();
						}
					}
				}
			}

			int userInput = sc.nextInt();

			for (Element element : elements) {
				if (userInput == element.getSquareNum()) {
					Transactions.developElement(player, element);
				}
			}
		} else {
			System.out.println("═════════════════════════════════════");
			System.out.println(" ■ You Have No Elements To Develop ■  ");
			System.out.println("═════════════════════════════════════");
			System.out.println("YOU MUST OWN ALL ELEMENTS IN A SYSTEM");
		}
	}

	/**
	 * This method is called every time a player lands on an element. If player
	 * lands on a element that is owned by another player, it calls the rent method.
	 * 
	 * @param player
	 */
	public static void checkRent(Player player) {
		int position = player.getPlayerPosition();
		for (Element element : elements) {
			if ((element.getSquareNum() == position)) {
				if (element.getOwnedBy() != 0 && element.getOwnedBy() != player.getPlayerID()) {
					int ownedBy = element.getOwnedBy();
					for (Player payee : players) {
						if (payee.getPlayerID() == ownedBy) {
							Transactions.payRent(player, payee, element);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * This method is called at the end of every turn to check if the game is either
	 * lost or won. This will call mission failed or mission accomplished based off
	 * parameters.
	 */
	public static void checkGameStatus() {

		for (Player player : players) {
			if (player.isBankrupt()) {
				missionFailed();
			}
		}

		int count = 0;
		for (Element element : elements) {
			if (element.isMajorDev()) {
				count++;
			}
		}
		if (count == 10) {
			missionAccomplished();
		}

	}

	/**
	 * This method is a console display heading.
	 */
	public static void displayElement2Heading() {
		System.out.printf("%-5s %-33s %-28s %-12s %-12s %-12s\n", "", "Element Name", "System", "Cost", "Development*",
				"Rent*");
		System.out.println(
				"═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
	}

	/**
	 * This method is a console display heading.
	 */
	public static void displayFinalElementHeading() {
		System.out.printf("%-5s %-33s %-28s %-28s\n", "", "Element Name", "System", "Development Status");
		System.out.println(
				"═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
	}

	/**
	 * This method is called when a player tries to purchase a square and checks if
	 * they are able to purchase it.
	 * 
	 * @param p
	 */
	public static void checkBuy(Player p) {
		for (Element element : elements) {
			if (element.getSquareNum() != 1 || element.getSquareNum() != 7) {

				if (element.getSquareNum() == p.getPlayerPosition()) {
					Transactions.buyElement(p, element);

				}
			} else {
				System.out.println("You cannot purchase these squares.");

			}
		}

	}

	/**
	 * This method is called when all systems and their elements are Major
	 * Developed.
	 */

	public static void missionAccomplished() {

		int count = 0;
		for (Element element : elements) {
			if (element.isMajorDev()) {
				count++;
			}
		}
		if (count == 10) {
			System.out.println("╔══════════════════════════╗");
			System.out.println("║ ☑ ☑ Mission Complete ☑ ☑ ║");
			System.out.println("╚══════════════════════════╝");
			for (Player player : players) {
				player.displayAllPlayerInfo();
			}
			displayFinalElementHeading();
			for (Element element : elements) {
				element.displayFinalElementInfo();
			}

			int playerOwnedCount = 0;
			for (Player player : players) {
				for (Element element : elements) {
					if (player.getPlayerID() == element.getOwnedBy()) {
						playerOwnedCount++;
					}
				}
				System.out.println("Player " + player.getPlayerID() + " finished the game owning " + playerOwnedCount
						+ " systems.");
				System.out.println("");

			}
			
			System.out.println("2021: Space Launch System Complete");
			System.out.println("2022: Russian Space Federation announces renewed Space Race");
			System.out.println("2023: Gateway System Established");
			System.out.println("2024: ORION completed and first test rocket fired");
			System.out.println("2026: Exploration Ground System research brought to a close successfully");
			System.out.println("2028: Artemis lands on the Moon");
			System.out.println("Mission Success!");
			System.out.println("Well Done.");
			
			System.out.println("╔════════════════════════════╗");
			System.out.println("║ ☾ ☄ Thanks For Playing ☄ ☾ ║");
			System.out.println("╚════════════════════════════╝");
			System.exit(0);
		}
	}

	/**
	 * This method is called when any of the players are bankrupt to end the game.
	 */
	public static void missionFailed() {

		for (Player player : players) {
			if (player.isBankrupt()) {
				System.out.println("Game over, player " + player.getPlayerID() + "is bankrupt, better luck next time!");

				System.out.println("╔════════════════════════╗");
				System.out.println("║ ☑ ☑ Mission FAILED ☑ ☑ ║");
				System.out.println("╚════════════════════════╝");
				for (Player p : players) {
					p.displayAllPlayerInfo();
				}
				displayFinalElementHeading();
				for (Element element : elements) {
					element.displayFinalElementInfo();
				}

				int playerOwnedCount = 0;
				for (Player p : players) {
					for (Element element : elements) {
						if (p.getPlayerID() == element.getOwnedBy()) {
							playerOwnedCount++;
						}
					}
					System.out.println("Player " + player.getPlayerID() + " finished the game owning "
							+ playerOwnedCount + " systems.");
					System.out.println("");

				}
				System.out.println("╔════════════════════════════╗");
				System.out.println("║ ☾ ☄ Thanks For Playing ☄ ☾ ║");
				System.out.println("╚════════════════════════════╝");
				System.exit(0);
			}
		}
	}

	/**
	 * This method is called when a player lands on a square that they do not wish
	 * to purchase. This allows them to offer the square to another player.
	 * 
	 * @param position
	 */
	public static void offerSquare(int position) {
		for (Element element : elements) {
			if (element.getSquareNum() == position) {
				if (element.getOwnedBy() == 0 && element.getSquareNum() != 7 && element.getSquareNum() != 1) {
					System.out.println("Which player would you like to offer it to... (Enter Player Number)");
					for (Player player : players) {
						System.out.println("Player " + player.getPlayerID() + " - " + player.getName());
					}
					int playerOffered = sc.nextInt();
					for (Player player : players) {
						if (player.getPlayerID() == playerOffered) {
							System.out.println("Player " + player.getPlayerID()
									+ ", would you like to purchase this element? (y/n)");
							String playerChoice = sc.next();
							if (playerChoice.equalsIgnoreCase("y")) {
								for (Element e : elements) {
									if (e.getSquareNum() == position) {
										Transactions.buyElement(player, e);
									}
								}
							} else if (playerChoice.equalsIgnoreCase("n")) {
								continue;
							}
						}
					}

				} else {
					System.out.println("╔════════════════════════════╗");
					System.out.println("║ Not Available For Purchase ║");
					System.out.println("╚════════════════════════════╝");

				}
			}
		}

	}

}
