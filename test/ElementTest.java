package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tempart.Element;
import tempart.Systems;

class ElementTest {

	
	Systems systems;
	String elementName;
	
	int squareNum, cost, rentPrice, devPrice, elementBalance, maxDevelopment, ownedByMidValid, ownedByUpperValid, ownedByLowerValid, ownedByUpperInvalid, ownedByLowerInvalid;
	
	boolean dev1, dev2, dev3, majordev;
	

	@BeforeEach
	void setUp() throws Exception {
		
		systems = Systems.ORION;
		elementName= "Launch Abort System";
		
		squareNum = 9;
		cost = 75;
		rentPrice = cost/2;
		devPrice = cost/2;
		elementBalance = 27;
		maxDevelopment = cost*4;
		
		ownedByMidValid = 3;
		ownedByLowerValid = 1;
		ownedByUpperValid = 4;
		
		ownedByUpperInvalid = 5;
		ownedByLowerInvalid = 0;
		
		dev1 = true;
		dev2 = true;
		dev3 = true;
		majordev = true;
		
		
		
	}

	@Test
	void testDefaultConstructorElement() {
		Element e = new Element();
		assertNotNull(e);
	}

	@Test
	void testConstructorWithArgsElement() {
		Element e = new Element(squareNum, systems, elementName, cost);
		
		assertEquals(squareNum, e.getSquareNum());
		assertEquals(systems, e.getSystem());
		assertEquals(elementName, e.getElementName());
		assertEquals(cost, e.getCost());
	}

	@Test
	void testGetSystem() {
		Element e = new Element();
		e.setSystem(systems);
		assertEquals(systems, e.getSystem());
	}

	@Test
	void testGetElementName() {
		Element e = new Element();
		e.setElementName(elementName);
		assertEquals(elementName, e.getElementName());
	}

	@Test
	void testGetCost() {
		Element e = new Element();
		e.setCost(cost);
		assertEquals(cost, e.getCost());
	}

	@Test
	void testGetRentPrice() {
		Element e = new Element();
		e.setRentPrice(rentPrice);
		assertEquals(rentPrice, e.getRentPrice());
	}

	@Test
	void testGetdevPrice() {
		Element e = new Element();
		e.setdevPrice(devPrice);
		assertEquals(devPrice, e.getdevPrice());
	}

	

	@Test
	void testGetMaxDevelopment() {
		Element e = new Element();
		e.setMaxDevelopment(maxDevelopment);
		assertEquals(maxDevelopment, e.getMaxDevelopment());
	}

	@Test
	void testIsDev1() {
		Element e = new Element();
		e.setDev1(dev1);
		assertEquals(dev1, e.isDev1());
	}

	@Test
	void testIsDev2() {
		Element e = new Element();
		e.setDev2(dev2);
		assertEquals(dev2, e.isDev2());
	}

	@Test
	void testIsDev3() {
		Element e = new Element();
		e.setDev3(dev3);
		assertEquals(dev3, e.isDev3());
	}

	@Test
	void testIsMajorDev() {
		Element e = new Element();
		e.setMajorDev(majordev);
		assertEquals(majordev, e.isMajorDev());
	}

	@Test
	void testValidGetOwnedBy() {
		Element e = new Element();
		
		e.setOwnedBy(ownedByLowerValid);
		assertEquals(ownedByLowerValid, e.getOwnedBy());
		
		e.setOwnedBy(ownedByMidValid);
		assertEquals(ownedByMidValid, e.getOwnedBy());
		
		e.setOwnedBy(ownedByUpperValid);
		assertEquals(ownedByUpperValid, e.getOwnedBy());
	}
	
	@Test
	void testInvalidGetOwnedBy() {
		Element e = new Element();
		
		IllegalArgumentException e1 = assertThrows(IllegalArgumentException.class, () -> {
			e.setOwnedBy(ownedByUpperInvalid);
		});
		
		assertEquals("Invalid Number", e1.getMessage());
		System.out.println(e1.getMessage());
		
		e1 = assertThrows(IllegalArgumentException.class, () -> {
			e.setOwnedBy(ownedByLowerInvalid);
		});
	}


}
