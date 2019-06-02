package se.kth.iv1201.retail.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import se.kth.iv1201.retail.integration.*;
import se.kth.iv1201.retail.model.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller instance;
    private LoggingCreator logCreator;
    private Register register;
    private IncomeObserver incomeObserver;
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
        instance.addIncomeObserver(new TotalRevenueView());
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
        ItemDTO expectedItem = ItemInventory.getItemInventory().getItemFromInventory(1);
        try {
            SaleDTO saleTest = instance.addItem(1);
            ItemDTO[] itemTest = saleTest.getRegisteredItems();
            ItemDTO result = itemTest[0];
            assertEquals("Items are not the same.",expectedItem,result);
        } catch (Exception ex){
            fail("Exception occurred.");
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddItemWithInvalidID() {
        try {
            SaleDTO result = instance.addItem(51234);
            fail("Method did not throw exception");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddItemWithQuantity() {
        ItemDTO expectedItem = ItemInventory.getItemInventory().getItemFromInventory(1,20);
        try {
            SaleDTO saleTest = instance.addItem(1, 20);
            ItemDTO[] itemTest = saleTest.getRegisteredItems();
            ItemDTO result = itemTest[0];
            assertEquals("Items with quantities are not the same.", expectedItem, result);
        } catch (Exception ex){
            fail("Exception occurred.");
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddItemWithHugeQuantity() {
        ItemDTO expectedItem = ItemInventory.getItemInventory().getItemFromInventory(1, Integer.MAX_VALUE);
        try {
            SaleDTO saleTest = instance.addItem(1, Integer.MAX_VALUE);
            ItemDTO[] itemTest = saleTest.getRegisteredItems();
            ItemDTO result = itemTest[0];
            assertEquals("Items with quantities are not the same.", expectedItem, result);
        } catch(Exception ex){
            fail("Exception occurred.");
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddItemWithNegativeQuantity() {
        try {
            SaleDTO nullResult = instance.addItem(1, -1);
            fail("Method did not throw exception.");
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testAddItemWithZeroQuantity() {
        try {
            SaleDTO nullResult = instance.addItem(1, 0);
            fail("Exception did not occur.");
        }  catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testPay() {
        Amount paidAmt = new Amount(2000);
        try {
            instance.addItem(1, 20);
            instance.addItem(20, 4);
        } catch(Exception ex){
            fail("Exception occurred.");
            ex.printStackTrace();
        }
        instance.pay(paidAmt);
        LocalDateTime saleTime = LocalDateTime.now();
        String result = outContent.toString();
        assertTrue("Wrong sale year.",result.contains(Integer.toString(saleTime.getYear())));
        assertTrue("Wrong sale month.",result.contains(Integer.toString(saleTime.getMonthValue())));
        assertTrue("Wrong sale day.",result.contains(Integer.toString(saleTime.getDayOfMonth())));
        assertTrue("Wrong sale hour.",result.contains(Integer.toString(saleTime.getHour())));
        assertTrue("Wrong sale minute.",result.contains(Integer.toString(saleTime.getMinute())));
    }


    class TotalRevenueView implements IncomeObserver {
        private double revenue;

        public TotalRevenueView(){
            this.revenue = 0.0;
        }

        @Override
        public void newIncome(double income){
            addNewIncome(income);
            printCurrentState();
        }

        private void addNewIncome(double income){
            this.revenue += income;
        }

        private void printCurrentState(){
            System.out.println("Revenue so far: ");
            System.out.println("###########################");
            System.out.println(revenue + "SEK.");
            System.out.println("###########################\n");
        }
    }

}