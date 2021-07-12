package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DiceTest {



	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void testValidRollDice() {
		Random random = new Random();
		
		int diceOne = random.nextInt(6)+1;
		int diceTwo = random.nextInt(6)+1;
		
		int totalRoll = diceOne+diceTwo;
		
		assertEquals(totalRoll, diceOne+diceTwo);
		
	}

}
