package se.kth.iv1201.retail.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1201.retail.integration.ItemDTO;
import se.kth.iv1201.retail.integration.ItemInventory;
import se.kth.iv1201.retail.integration.LoggingCreator;
import se.kth.iv1201.retail.integration.Printer;
import se.kth.iv1201.retail.model.Amount;
import se.kth.iv1201.retail.model.Sale;
import se.kth.iv1201.retail.model.SaleDTO;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller instance;
    private LoggingCreator logCreator;
    ByteArrayOutputStream outContent;
    PrintStream originalSysOut;


    @Before
    public void setUP(){
        originalSysOut = System.out;
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Printer printer = new Printer();
        logCreator = new LoggingCreator();
        instance = new Controller(logCreator,printer);
        instance.startNewSale();
    }

    @After
    public void tearDown(){
        outContent = null;
        System.setOut(originalSysOut);
        instance = null;
        logCreator = null;
    }

    @Test
    public void testAddItemWithValidID() {
        ItemInventory testInventory = logCreator.getItemInventory();
        ItemDTO expectedItem = testInventory.getItemFromInventory(1);
        SaleDTO saleTest = instance.addItem(1);
        ItemDTO[] itemTest = saleTest.getRegisteredItems();
        ItemDTO result = itemTest[0];
        assertEquals("Items are not the same.",expectedItem,result);
    }

    @Test
    public void testAddItemWithInvalidID() {
        ItemDTO expectedItem = null;
        SaleDTO nullResult = instance.addItem(Integer.MAX_VALUE);
        assertEquals("Item that should be null is not.",expectedItem,nullResult);
    }

    @Test
    public void testAddItemWithQuantity() {
        ItemInventory testInventory = logCreator.getItemInventory();
        ItemDTO expectedItem = testInventory.getItemFromInventory(1,20);
        SaleDTO saleTest = instance.addItem(1,20);
        ItemDTO[] itemTest = saleTest.getRegisteredItems();
        ItemDTO result = itemTest[0];
        assertEquals("Items with quantities are not the same.",expectedItem,result);
    }

    @Test
    public void testAddItemWithHugeQuantity() {
        ItemInventory testInventory = logCreator.getItemInventory();
        ItemDTO expectedItem = testInventory.getItemFromInventory(1, Integer.MAX_VALUE);
        SaleDTO saleTest = instance.addItem(1, Integer.MAX_VALUE);
        ItemDTO[] itemTest = saleTest.getRegisteredItems();
        ItemDTO result = itemTest[0];
        assertEquals("Items with quantities are not the same.",expectedItem,result);
    }

    @Test
    public void testAddItemWithNegativeQuantity() {
        ItemInventory testInventory = logCreator.getItemInventory();
        ItemDTO expectedItem = testInventory.getItemFromInventory(1, -1);
        SaleDTO nullResult = instance.addItem(1, -1);
        assertEquals("Items with quantities are not the same.",expectedItem,nullResult);
    }

    @Test
    public void testAddItemWithZeroQuantity() {
        ItemInventory testInventory = logCreator.getItemInventory();
        ItemDTO expectedItem = testInventory.getItemFromInventory(1, 0);
        SaleDTO nullResult = instance.addItem(1, 0);
        assertEquals("Items with quantities are not the same.",expectedItem,nullResult);
    }

    @Test
    public void testPay() {
        Amount paidAmt = new Amount(2000);
        instance.addItem(1,20);
        instance.addItem(20,4);
        instance.pay(paidAmt);
        LocalDateTime saleTime = LocalDateTime.now();
        String result = outContent.toString();
        assertTrue("Wrong sale year.",result.contains(Integer.toString(saleTime.getYear())));
        assertTrue("Wrong sale month.",result.contains(Integer.toString(saleTime.getMonthValue())));
        assertTrue("Wrong sale day.",result.contains(Integer.toString(saleTime.getDayOfMonth())));
        assertTrue("Wrong sale hour.",result.contains(Integer.toString(saleTime.getHour())));
        assertTrue("Wrong sale minute.",result.contains(Integer.toString(saleTime.getMinute())));
    }
}