package se.kth.iv1201.retail.integration;

import java.math.*;

public class ItemInventory {
    private ItemDTO[] itemsInInventory;

    public ItemInventory(){
        this.itemsInInventory = new ItemDTO[30];
        for(int i = 0; i<itemsInInventory.length; i++){
            itemsInInventory[i] = new ItemDTO(i,"item" + (i+1),((Math.pow((double)i+1,2)/2)),(1.0 + (double)i/10),10+i);
        }
    }
    public ItemInventory(ItemDTO[] items){
        this.itemsInInventory = items;
    }

    public void updateInventory(ItemDTO[] items){
        calculateItems(items);
    }

    private void calculateItems(ItemDTO[] items){
        //for(int i = 0; i<items.length; i++){
        int i = 0;
        int j = 0;
        while(items[i] != null && i<items.length){
            while(itemsInInventory[j].getItemID() != items[i].getItemID()){
                j++;
            } if(items[i].getSold()){
                int newQuantity = itemsInInventory[j].getQuantity() - items[i].getQuantity();
                itemsInInventory[j].setQuantity(newQuantity);
            } else if(items[i].getReturned()){
                int newQuantity = itemsInInventory[j].getQuantity() + items[i].getQuantity();
                itemsInInventory[j].setQuantity(newQuantity);
            }
            i++;
        }
    }

    public ItemDTO getItemFromInventory(int itemID){
        for(int i = 0; i<itemsInInventory.length; i++){
            if(itemID == itemsInInventory[i].getItemID()) {
                ItemDTO foundItem = new ItemDTO(itemsInInventory[i].getItemID(),itemsInInventory[i].getItemName(),
                        itemsInInventory[i].getPrice(),itemsInInventory[i].getVAT(),1);
                foundItem.setSold(true);
                return foundItem;
            }
        }
        //System.out.println("There is no Item with itemID: " + itemID);
        return null;
    }

    public ItemDTO[] getItemsInInventory(){
        return itemsInInventory;
    }
}
