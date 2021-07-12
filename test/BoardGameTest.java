package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tempart.BoardGame;
import tempart.Element;

class BoardGameTest {
	
	
	@BeforeEach
	void setUp() throws Exception {
		
	
		
	}

	@Test
	void testSetUp() {
		
		ArrayList<Element> bg = new ArrayList<Element>();
		bg = BoardGame.setUp();
		assertNotNull(bg);
		
	}


}
