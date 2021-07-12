package tempart;

import java.util.ArrayList;

/**
 * @author Adam Gavigan 40149571
 * @author Alexander O'Neill 40126881
 * @author Hannah Gallagher 40177816
 * @author Kirsty McQuoid 15940004
 */

/**
 *
 * This class establishes the layout of the board on which the game takes place.
 */
public class BoardGame {

	public static ArrayList<Element> setUp() {

		ArrayList<Element> elements = new ArrayList<>();

		Element e1 = new Element(1, null, "RESEARCH FUNDING", 0);

		elements.add(e1);

		Element e2 = new Element(2, Systems.SPACE_LAUNCH_SYSTEM, "Boosters", 30);

		elements.add(e2);

		Element e3 = new Element(3, Systems.SPACE_LAUNCH_SYSTEM, "Command Pod", 30);

		elements.add(e3);

		Element e4 = new Element(4, Systems.GATEWAY, "Navigation and Control", 50);

		elements.add(e4);

		Element e5 = new Element(5, Systems.GATEWAY, "Power and Propulsion", 50);

		elements.add(e5);

		Element e6 = new Element(6, Systems.GATEWAY, "Halo", 50);

		elements.add(e6);

		Element e7 = new Element(7, null, "SYSTEM GLITCH", 0);

		elements.add(e7);

		Element e8 = new Element(8, Systems.ORION, "Heat-Resistant Shield Technology", 75);

		elements.add(e8);

		Element e9 = new Element(9, Systems.ORION, "Launch Abort System", 75);

		elements.add(e9);

		Element e10 = new Element(10, Systems.EXPLORATION_GROUND_SYSTEM, "Mobile Launcher", 50);

		elements.add(e10);

		Element e11 = new Element(11, Systems.EXPLORATION_GROUND_SYSTEM, "Lightning Tower", 50);

		elements.add(e11);

		Element e12 = new Element(12, Systems.EXPLORATION_GROUND_SYSTEM, "Flame Trench", 50);

		elements.add(e12);

		return elements;
	}

}
