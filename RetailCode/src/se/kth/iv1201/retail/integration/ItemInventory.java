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
    /*public void updateInventory(ItemDTO[] items){
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
    }*/

    /**
     * Gets an item from the inventory database by searching through the object array.
     * If an item is found it returns the Item with its status set as <code>sold</code> and its
     * <code>quantity</code> to one.
     *
     * @param itemID The item identifier used for searching.
     * @return The created <code>ItemDTO</code> object.
     */
    public ItemDTO getItemFromInventory (int itemID) throws ItemInventoryException {
        ItemDTO foundItem = searchInventory(itemID);
        return foundItem;
    }

    /**
     * Gets an item from the inventory database by searching through the object array in a similar
     * way to the overriden method with one argument.
     * This method also enables it to specify the quantity of items to retrieve and has an
     * algorithm in the private method to see if there are enough items in stock to give all
     * and if not give a somewhat fair distribution.
     * If an item is found it returns the Item with its status set as <code>sold</code> and its
     * <code>quantity</code> to one.
     *
     * @param itemID The item identifier used for searching.
     * @return The created <code>ItemDTO</code> object.
     * @throws ItemInventoryException if the specified <code>itemID</code>
     * does not correspond to an item in the inventory.
     */
    public ItemDTO getItemFromInventory(int itemID, int quantity){
        ItemDTO foundItem = searchInventory(itemID,quantity);
        return foundItem;
    }

    private ItemDTO searchInventory(int itemID){
        if(itemID<=30){
            for (int i = 0; i < itemsInInventory.length; i++) {
                if (itemID == itemsInInventory[i].getItemID()) {
                    if (itemsInInventory[i].getQuantity() > 0) {
                        ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), 1);
                        itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - 1);
                        foundItem.setSold(true);
                        return foundItem;
                    }
                    throw new ItemInventoryException(("The stock is empty of item with ID: " + itemID));
                }
            }
        }
        throw new ItemInventoryException(("There is no item in inventory with the ID: " + itemID));
    }

    private ItemDTO searchInventory(int itemID, int quantity){
        if(itemID<=30) {
            if(quantity>0) {
                for (int i = 0; i < itemsInInventory.length; i++) {
                    if (itemID == itemsInInventory[i].getItemID()) {
                        if ((itemsInInventory[i].getQuantity() - quantity) > 0) {
                            ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                    itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), quantity);
                            itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - quantity);
                            foundItem.setSold(true);
                            return foundItem;
                        } else if (itemsInInventory[i].getQuantity() > 0) {
                            int possibleQuantity = Math.abs(itemsInInventory[i].getQuantity() - quantity);
                            if (itemsInInventory[i].getQuantity() - possibleQuantity > 0) {
                                ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                        itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), possibleQuantity);
                                itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - possibleQuantity);
                                foundItem.setSold(true);
                                return foundItem;
                            } else {
                                ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                        itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), 1);
                                itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - 1);
                                foundItem.setSold(true);
                                return foundItem;
                            }
                        }
                        throw new ItemInventoryException("The stock is empty of item with ID: " + itemID);
                    }
                }
            }
            throw new ItemInventoryException("Not allowed to add less than 1 piece of item. You tried to add " + quantity + " pieces.");
        }
        throw new ItemInventoryException(("There is no item in inventory with the ID: " + itemID));
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
