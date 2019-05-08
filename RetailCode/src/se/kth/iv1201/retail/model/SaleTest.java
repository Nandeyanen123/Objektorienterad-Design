package se.kth.iv1201.retail.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1201.retail.integration.ItemDTO;

import static org.junit.Assert.*;

public class SaleTest {
    private Sale testSale;

    @Before
    public void setUp() throws Exception {
        testSale = new Sale();
    }

    @After
    public void tearDown() throws Exception {
        testSale = null;
    }

    @Test
    public void testAddOneItemAndUpdate() {
        int expQuantityResult = 2;
        double expTotalResult = 2*(20.0*1.25);
        ItemDTO item = new ItemDTO(1,"Potato",20.0,1.25,expQuantityResult);
        testSale.addItemAndUpdate(item);
        SaleDTO sale = testSale.getSaleDTO();
        int quantityResult = sale.getQuantity();
        double totalResult = sale.getRunningTotal();
        assertEquals("Quantities are not the same.",quantityResult,expQuantityResult);
        assertEquals("Total price is not the same.",totalResult,expTotalResult,0.01);
    }

    @Test
    public void testAddOMultipleOfSameItemAndUpdate() {
        int expQuantityResult = 4;
        double expTotalResult = 4*(20.0*1.25);
        ItemDTO item1 = new ItemDTO(1,"Potato",20.0,1.25,2);
        ItemDTO item2 = new ItemDTO(1,"Potato",20.0,1.25,2);
        testSale.addItemAndUpdate(item1);
        testSale.addItemAndUpdate(item2);
        SaleDTO sale = testSale.getSaleDTO();
        int quantityResult = sale.getQuantity();
        double totalResult = sale.getRunningTotal();
        assertEquals("Quantities are not the same.",quantityResult,expQuantityResult);
        assertEquals("Total price is not the same.",totalResult,expTotalResult,0.01);
    }

    @Test
    public void testAddSeveralDifferentItemsAndUpdate() {
        int expQuantityResult = 10;
        double expTotalResult = (2*20.0*1.25+3*10.0*1.25+5*15.0*1.25);
        ItemDTO item1 = new ItemDTO(1,"Potato",20.0,1.25,expQuantityResult-8);
        ItemDTO item2 = new ItemDTO(2,"Tomato",10.0,1.25,expQuantityResult-7);
        ItemDTO item3 = new ItemDTO(3,"Apple",15.0,1.25,expQuantityResult-5);
        testSale.addItemAndUpdate(item1);
        testSale.addItemAndUpdate(item2);
        testSale.addItemAndUpdate(item3);
        SaleDTO sale = testSale.getSaleDTO();
        int quantityResult = sale.getQuantity();
        double totalResult = sale.getRunningTotal();
        assertEquals("Quantities are not the same.",quantityResult,expQuantityResult);
        assertEquals("Total price is not the same.",totalResult,expTotalResult,0.01);
    }

    @Test
    public void pay() {
    }
}