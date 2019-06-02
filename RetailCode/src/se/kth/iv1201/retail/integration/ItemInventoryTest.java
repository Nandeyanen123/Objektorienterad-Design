package se.kth.iv1201.retail.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemInventoryTest {

    @Before
    public void setUp(){

    }

    @After
    public void tearDown(){

    }

    @Test
    public void testItemIDExceptionThrown(){
        try{
            ItemDTO exceptionItem = ItemInventory.getItemInventory().getItemFromInventory(95);
            fail("Exception was not thrown");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Test
    public void testItemQuantityExceptionThrown(){
        try{
            ItemDTO exceptionItem = ItemInventory.getItemInventory().getItemFromInventory(5,-100);
            fail("Exception was not thrown");
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }
}