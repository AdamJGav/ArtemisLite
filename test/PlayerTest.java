package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tempart.Player;

class PlayerTest {


	Player p;

	String name;

	int validLowerPlayerID, validUpperPlayerID, invalidLowerPlayerID, invalidUpperPlayerID;

	int validLowerPlayerPosition, validUpperPlayerPosition, invalidLowerPlayerPosition, invalidUpperPlayerPosition;

	int validPlayerBalance, invalidLowerPlayerBalance, invalidUpperPlayerBalance;

	@BeforeEach
	void setUp() throws Exception {

		name = "name";

		validLowerPlayerID = 1;
		validUpperPlayerID = 4;

		invalidLowerPlayerID = 0;
		invalidUpperPlayerID = 5;

		validLowerPlayerPosition = 1;
		validUpperPlayerPosition = 12;
		invalidLowerPlayerPosition = 0;
		invalidUpperPlayerPosition = 13;

		validPlayerBalance = 200;
		invalidLowerPlayerBalance = 199;
		invalidUpperPlayerBalance = 201;

	}

	@Test
	void testDefaultConstPlayer() {

		p = new Player();

		assertNotNull(p);
	}

	@Test
	void testConstWArgsPlayer() {

		p = new Player(name, validLowerPlayerID);

		assertEquals("name", name);
		assertEquals(1, validLowerPlayerID);
		assertEquals(200, p.getPlayerBalance());
		assertEquals(1, p.getPlayerPosition());
		assertEquals(false, p.isBankrupt());

	}

	@Test
	void testGetSetName() {
		p = new Player();

		p.setName(name);

		assertEquals(name, p.getName());
	}

	@Test
	void testGetSetPlayerIDValid() {

		p = new Player();

		p.setPlayerID(validLowerPlayerID);

		assertEquals(validLowerPlayerID, p.getPlayerID());

		p.setPlayerID(validUpperPlayerID);

		assertEquals(validUpperPlayerID, p.getPlayerID());
	}


	@Test
	void testGetSetPlayerBalanceValid() {

		p = new Player();

		p.setPlayerBalance(validPlayerBalance);

		assertEquals(validPlayerBalance, p.getPlayerBalance());
	}


	@Test
	void testGetSetPlayerPositionValid() {

		p = new Player();

		p.setPlayerPosition(validLowerPlayerPosition);

		assertEquals(validLowerPlayerPosition, p.getPlayerPosition());

		p.setPlayerPosition(validUpperPlayerPosition);

		assertEquals(validUpperPlayerPosition, p.getPlayerPosition());
	}

	@Test
	void testGetSetPlayerPositionInvalid() {
		p = new Player();

		IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> {
			p.setPlayerPosition(invalidLowerPlayerPosition);
		});

		assertEquals("INVALID", ex.getMessage());

		ex = assertThrows(IllegalArgumentException.class, () -> {
			p.setPlayerPosition(invalidUpperPlayerPosition);
		});
		assertEquals("INVALID", ex.getMessage());
	}

	@Test
	void testIsSetBankrupt() {

		p = new Player();

		p.setBankrupt(true);

		assertTrue(p.isBankrupt());

	}

	@Test
	void testDecreaseBalance() {
		// player created with 200 by default
		p = new Player(name, validLowerPlayerID);
		// decrease by 100, leaving 100 overall
		p.decreaseBalance(100);

		assertEquals(100, p.getPlayerBalance());
	}

	@Test
	void testIncreaseBalance() {
		// player created with 200 by default
		p = new Player(name, validLowerPlayerID);
		// increase by 100, leaving 300 overall
		p.increaseBalance(100);

		assertEquals(300, p.getPlayerBalance());
	}

	@Test
	void testUpdatePlayerPosition() {
		p = new Player();
		
		
		p.setPlayerPosition(1);
		assertEquals(1, p.getPlayerPosition());
		
		p.updatePlayerPosition(4);
		assertEquals(5, p.getPlayerPosition());
		
		//testing passing go and updating player balance
		p.setPlayerBalance(200);
		//rolling 10, going from 5 to 15 - 12 to account for going round board, leaving on pos 3
		//balance should be go from 200 to 400
		p.updatePlayerPosition(10);
		assertEquals(3, p.getPlayerPosition());
		assertEquals(400, p.getPlayerBalance());
	}


}
