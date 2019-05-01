package se.kth.iv1201.retail.integration;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemInventoryTest {
    private ItemInventory inventory;

    @Before
    public void setUp(){
        ItemDTO[] items = new ItemDTO[3];
        items[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 3);
        items[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 4);
        items[2] = new ItemDTO(3,"Pomade", 17.5, 1.06, 5);
        inventory = new ItemInventory(items);
    }

    @After
    public void tearDown(){
        inventory = null;
    }

    @Test
    public void testUpdateSoldInventory() {
        ItemDTO[] updatedItems = new ItemDTO[2];
        updatedItems[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 2);
        updatedItems[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 3);
        updatedItems[0].setSold(true);
        updatedItems[1].setSold(true);

        ItemDTO[] expectedResults = new ItemDTO[3];
        expectedResults[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 5);
        expectedResults[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 6);
        expectedResults[2] = new ItemDTO(3,"Pomade", 17.5, 1.06, 5);

        inventory.updateInventory(updatedItems);
        ItemDTO[] result = inventory.getItemsInInventory();
        assertEquals("the inventory list is not updated as expected", expectedResults, result);
    }

    @Test
    public void testUpdateReturnedInventory() {
        ItemDTO[] updatedItems = new ItemDTO[2];
        updatedItems[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 2);
        updatedItems[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 3);
        updatedItems[0].setSold(true);
        updatedItems[1].setSold(true);

        ItemDTO[] expectedResults = new ItemDTO[3];
        expectedResults[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 1);
        expectedResults[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 1);
        expectedResults[2] = new ItemDTO(3,"Pomade", 17.5, 1.06, 5);

        inventory.updateInventory(updatedItems);
        ItemDTO[] result = inventory.getItemsInInventory();
        assertEquals("the inventory list is not updated as expected", expectedResults, result);
    }

    @Test
    public void testGetItemFromInventorySuccessful() {
        int itemID = 1;
        ItemDTO expectedItem = new ItemDTO(1,"Tomato", 10.0, 1.12, 3);
        ItemDTO result = inventory.getItemFromInventory(itemID);
        assertEquals("items are not the same.",expectedItem,result);
    }

    @Test
    public void testGetItemFromInventoryUnsuccessful() {
        int itemID = 5;
        ItemDTO expectedItem = null;
        ItemDTO result = inventory.getItemFromInventory(itemID);
        assertEquals("items that should not exist did.",expectedItem,result);
    }

    @Test
    public void testGetItemsInInventory() {
        ItemDTO[] expectedItems = new ItemDTO[3];
        expectedItems[0] = new ItemDTO(1,"Tomato", 10.0, 1.12, 3);
        expectedItems[1] = new ItemDTO(2,"Potato", 15.0, 1.25, 4);
        expectedItems[2] = new ItemDTO(3,"Pomade", 17.5, 1.06, 5);

        ItemDTO[] result = inventory.getItemsInInventory();
        assertEquals("the items in the inventory are not the same",expectedItems,result);
    }
}