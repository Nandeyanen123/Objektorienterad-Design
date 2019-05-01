package se.kth.iv1201.retail.integration;

import java.util.Random;
/**
 * The inventory system for the items in the store.
 */
public class ItemInventory {
    private ItemDTO[] itemsInInventory;

    /**
     * Creates an inventory (database) of item objects store in an <code>ItemDTO</code> array.
     * This process is pre-determined and automatized for now. VAT rates are randomly generated.
     */
    public ItemInventory(){
        Random random = new Random();
        this.itemsInInventory = new ItemDTO[30];
        for(int i = 0; i<itemsInInventory.length; i++){
            double randomVAT = (0.01 + (random.nextDouble()*(0.5-0.01)));
            if(randomVAT<0.1) {
                itemsInInventory[i] = new ItemDTO(i, "item" + (i + 1), ((Math.pow((double) i + 1, 2) / 2)), (1.06), 10000 + i);
            } else if(randomVAT<0.2){
                itemsInInventory[i] = new ItemDTO(i, "item" + (i + 1), ((Math.pow((double) i + 1, 2) / 2)), (1.12), 10000 + i);
            }else{
                itemsInInventory[i] = new ItemDTO(i, "item" + (i + 1), ((Math.pow((double) i + 1, 2) / 2)), (1.25), 10000 + i);
            }
        }
    }

    /**
     * Overloaded constructor that creates an inventory from a pre-constructed array of objects.
     *
     * @param items The <code>ItemDTO</code> array.
     */
    public ItemInventory(ItemDTO[] items){
        this.itemsInInventory = items;
    }

    /**
     * Updates the inventory based on the array of objects sent in.
     * It uses the private method <code>calculateItems()</code> to decrease
     * or increase the items in stock based on their status as <code>sold</code> or <code>returned</code>.
     *
     * @param items The array of objects.
     */
    public void updateInventory(ItemDTO[] items){
        calculateItems(items);
    }

    private void calculateItems(ItemDTO[] items){
        //for(int i = 0; i<items.length; i++){
        int i = 0;
        int j = 0;
        while(i<items.length){
            if(items[i] != null) {
                while (!itemsInInventory[j].equals(items[i]) && j < itemsInInventory.length) {
                    j++;
                }
                if (items[i].getSold()) {
                    int newQuantity = itemsInInventory[j].getQuantity() - items[i].getQuantity();
                    itemsInInventory[j].setQuantity(newQuantity);
                } else if (items[i].getReturned()) {
                    int newQuantity = itemsInInventory[j].getQuantity() + items[i].getQuantity();
                    itemsInInventory[j].setQuantity(newQuantity);
                }
                j = 0;
            }
            i++;
        }
    }

    /**
     * Gets an item from the inventory database by searching through the object array.
     * If an item is found it returns the Item with its status set as <code>sold</code> and its
     * <code>quantity</code> to one.
     *
     * @param itemID The item identifier used for searching.
     * @return The created <code>ItemDTO</code> object.
     */
    public ItemDTO getItemFromInventory(int itemID){
        for(int i = 0; i<itemsInInventory.length; i++){
            if(itemID == itemsInInventory[i].getItemID()) {
                ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(),itemsInInventory[i].getItemName(),
                        itemsInInventory[i].getPrice(),itemsInInventory[i].getVAT(),1);
                foundItem.setSold(true);
                return foundItem;
            }
        }
        return null;
    }

    /**
     * Gets the <code>ItemDTO</code> array from the inventory.
     *
     * @return The <code>ItemDTO</code> array.
     */
    public ItemDTO[] getItemsInInventory(){
        return itemsInInventory;
    }
}
