package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tempart.Square;

class SquareTest {

	int squareNumber;

	@BeforeEach
	void setUp() throws Exception {
		
		squareNumber = 6;
		
	}

	@Test
	void testDefaultConstructorSquare() {
		Square sq = new Square();
		assertNotNull(sq);	
	}

	@Test
	void testConstructorWithArgsSquareInt() {
		Square sq = new Square(squareNumber);
		assertEquals(squareNumber, sq.getSquareNum());
	}

	@Test
	void testGetSetSquareNum() {
		Square sq = new Square();
		sq.setSquareNum(squareNumber);
		assertEquals(squareNumber, sq.getSquareNum());
	}

}
