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
     * Gets an item from the inventory database by searching through the object array.
     * If an item is found it returns the Item with its status set as <code>sold</code> and its
     * <code>quantity</code> to one.
     *
     * @param itemID The item identifier used for searching.
     * @return The created <code>ItemDTO</code> object.
     */
    public ItemDTO getItemFromInventory(int itemID){
        ItemDTO foundItem = searchInventory(itemID);
        return foundItem;
    }

    /**
     * Gets an item from the inventory database by searching through the object array in a similar
     * way to the overridden method with one argument.
     * This method also enables it to specify the quantity of items to retrieve and checks
     * if there are enough items in stock to give the requested amount and if not gives a somewhat fair distribution.
     * If an item is found it returns the Item with its status set as <code>sold</code> and its
     * <code>quantity</code> to one.
     *
     * @param itemID The item identifier used for searching.
     * @return The created <code>ItemDTO</code> object.
     */
    public ItemDTO getItemFromInventory(int itemID, int quantity){
        ItemDTO foundItem = searchInventory(itemID,quantity);
        return foundItem;
    }

    private ItemDTO searchInventory(int itemID){
        for(int i = 0; i<itemsInInventory.length; i++){
            if(itemID == itemsInInventory[i].getItemID() && itemsInInventory[i].getQuantity()>0) {
                ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                        itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), 1);
                itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - 1);
                foundItem.setSold(true);
                return foundItem;
            }
        }
        return null;
    }

    private ItemDTO searchInventory(int itemID, int quantity){
        for(int i = 0; i<itemsInInventory.length; i++){
            if(itemID == itemsInInventory[i].getItemID() && quantity>0) {
                if ((itemsInInventory[i].getQuantity()-quantity) > 0) {
                    ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                            itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), quantity);
                    itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - quantity);
                    foundItem.setSold(true);
                    return foundItem;
                } else if (itemsInInventory[i].getQuantity()>0) {
                    int possibleQuantity = Math.abs(itemsInInventory[i].getQuantity() - quantity);
                    if(itemsInInventory[i].getQuantity()-possibleQuantity>0) {
                        ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), possibleQuantity);
                        itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - possibleQuantity);
                        foundItem.setSold(true);
                        return foundItem;
                    }else{
                        ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(), itemsInInventory[i].getItemName(),
                                itemsInInventory[i].getPrice(), itemsInInventory[i].getVAT(), 1);
                        itemsInInventory[i].setQuantity(itemsInInventory[i].getQuantity() - 1);
                        foundItem.setSold(true);
                        return foundItem;
                    }
                }
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
