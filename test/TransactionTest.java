package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tempart.Element;
import tempart.Player;
import tempart.Systems;
import tempart.Transactions;

class TransactionTest {

	Element e;
    Player p;
    Player payer;
    Player payee;
    
    @BeforeEach
    void setUp() throws Exception {
        //New element whose prices are based off a cost of 100
        e = new Element(2, Systems.EXPLORATION_GROUND_SYSTEM, "Element", 100);
        //New player starting with 200
        p = new Player("Player", 1);
        payer = new Player("Payer", 1);
        payee = new Player("Payee", 2);
    }
    
    @Test
    void testBuyElementValid() {
        //player buys element
        Transactions.buyElement(p, e);
        //check balance changed
        assertEquals(p.getPlayerBalance(), 100);
        //check element assigned to player
        assertEquals(p.getPlayerID(), e.getOwnedBy());
    }
   
    @Test
    void testPayRentValid() {
    	System.out.println("Testing valid rent");
        //rent price is 50, should result in payer balance being 150 and payee 250
        Transactions.payRent(payer, payee, e);
        assertEquals(150, payer.getPlayerBalance());
        assertEquals(250, payee.getPlayerBalance());
    }
    @Test
    void testPayRentInvalid() {
    	System.out.println("Testing INVALID rent");
    	//payer balance reduced to 25
        payer.decreaseBalance(175);
        //rent price is 50, should result in payer being bankrupt and payee balance staying at 200
        Transactions.payRent(payer, payee, e);
        assertTrue(payer.isBankrupt());
        assertEquals(200, payee.getPlayerBalance());
    }
    @Test
    void testDevelopmentChargeValid() {
        //payer balance now 500
        payer.increaseBalance(300);
        //buy Element, balance now 400
        Transactions.buyElement(payer, e);
        assertEquals(payer.getPlayerID(), e.getOwnedBy());
        assertEquals(400, payer.getPlayerBalance());
        //dev element, cost 50, balance now 350
        Transactions.developElement(payer, e);
        assertEquals(350, payer.getPlayerBalance());
        assertTrue(e.isDev1());
        assertFalse(e.isDev2());
        assertFalse(e.isDev3());
        assertFalse(e.isMajorDev());
        //dev 2, new balance 300
        Transactions.developElement(payer, e);
        assertEquals(300, payer.getPlayerBalance());
        assertTrue(e.isDev1());
        assertTrue(e.isDev2());
        assertFalse(e.isDev3());
        assertFalse(e.isMajorDev());
        //dev 3, new balance 250
        Transactions.developElement(payer, e);
        assertEquals(250, payer.getPlayerBalance());
        assertTrue(e.isDev1());
        assertTrue(e.isDev2());
        assertTrue(e.isDev3());
        assertFalse(e.isMajorDev());
        //major dev, new balance 50
        Transactions.developElement(payer, e);
        assertEquals(50, payer.getPlayerBalance());
        assertTrue(e.isDev1());
        assertTrue(e.isDev2());
        assertTrue(e.isDev3());
        assertTrue(e.isMajorDev());
    }
    
    @Test
    void testBuyElementInvalid() {
        //reduce player balance to 50
        p.decreaseBalance(150);
        //player buys element
        Transactions.buyElement(p, e);
        //check bankrupt
        assertTrue(p.isBankrupt());
        //check element has not been given to player
        assertNotEquals(p.getPlayerID(), e.getOwnedBy());
    }
    
}

