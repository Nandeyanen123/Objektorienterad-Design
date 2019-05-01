package se.kth.iv1201.retail.integration;

import org.junit.Test;

import static org.junit.Assert.*;

public class ItemDTOTest {

    @Test
    public void testToString() {
        int itemID = 1;
        String itemName = "Tomato";
        double price = 10.5;
        double VAT = 1.12;
        int quantity = 10;
        ItemDTO instance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        String expResult = "itemID: " + itemID + ", " +
                "itemName: " + itemName + ", " +
                "price: " + price + ", " +
                "VAT: " + VAT + ", " +
                "quantity: " + quantity;
        String result = instance.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }

    @Test
    public void testToStringNullParam() {
        int itemID = 0;
        String itemName = null;
        double price = 0;
        double VAT = 0;
        int quantity = 0;
        ItemDTO instance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        String expResult = "itemID: " + itemID + ", " +
                "itemName: " + itemName + ", " +
                "price: " + price + ", " +
                "VAT: " + VAT + ", " +
                "quantity: " + quantity;
        String result = instance.toString();
        assertEquals("Wrong string returned by toString", expResult, result);
    }

    @Test
    public void testEquals() {
        int itemID = 1;
        String itemName = "Tomato";
        double price = 10.5;
        double VAT = 1.12;
        int quantity = 10;
        ItemDTO instance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        ItemDTO equalInstance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        boolean expResult = true;
        boolean result = instance.equals(equalInstance);
        assertEquals("ItemDTO instances with the same state are not equal.",
                expResult,result);
    }

    @Test
    public void testNotEqualsItemID() {
        int itemID = 1;
        String itemName = "Tomato";
        double price = 10.5;
        double VAT = 1.12;
        int quantity = 10;
        ItemDTO instance = new ItemDTO(0, itemName, price, VAT, quantity);
        ItemDTO equalInstance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        boolean expResult = false;
        boolean result = instance.equals(equalInstance);
        assertEquals("ItemDTO instances with the same state are not equal.",
                expResult,result);
    }

    @Test
    public void testNotEqualsItemName() {
        int itemID = 1;
        String itemName = "Tomato";
        double price = 10.5;
        double VAT = 1.12;
        int quantity = 10;
        ItemDTO instance = new ItemDTO(itemID, "Potato", price, VAT, quantity);
        ItemDTO notEqualInstance = new ItemDTO(itemID, itemName, price, VAT, quantity);
        boolean expResult = false;
        boolean result = instance.equals(notEqualInstance);
        assertEquals("ItemDTO instances with the same state are not equal.",
                expResult,result);
    }
}