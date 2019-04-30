package se.kth.iv1201.retail.model;

import se.kth.iv1201.retail.integration.*;

public class SaleDTO {
    private int saleID;
    private boolean completed;
    private double runningTotal;
    private ItemDTO[] registeredItems;
    private int quantityOfItems;
    private int itemCounter;

    public SaleDTO(int saleID){
        this.saleID = saleID;
        this.completed = false;
        this.runningTotal = 0;
        this.registeredItems = new ItemDTO[30];
        this.quantityOfItems = 0;
        this.itemCounter = 0;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("saleID: " + saleID + ", ");
        builder.append("completed: " + completed + ", ");
        builder.append("runningTotal: " + runningTotal + ", ");
        builder.append("registeredItems: ");
        for(ItemDTO item:registeredItems){
            if(item != null) {
                builder.append(item.getItemName() + ", ");
            }
        }
        builder.append("quantityOfItems: " + quantityOfItems);
        return builder.toString();
    }

    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final SaleDTO other = (SaleDTO) otherObj;
        if (saleID != other.saleID) {
            return false;
        }
        return true;
    }

    public boolean isEmpty(){
        boolean isEmpty = true;
        if(getRegisteredItems()[0] != null){
            isEmpty = false;
        }
        return isEmpty;
    }

    public int getSaleID(){
        return saleID;
    }

    public boolean getCompleted(){
        return completed;
    }

    public double getRunningTotal(){
        return runningTotal;
    }

    public ItemDTO[] getRegisteredItems(){
        return registeredItems;
    }

    public int getQuantity(){
        return quantityOfItems;
    }

    public void addItem(ItemDTO item){
        registeredItems[itemCounter] = item;
        itemCounter++;
    }

    public void setCompleted(boolean newCompleted){
        this.completed = newCompleted;
    }

    public void setRunningTotal(double newRunningTotal){
        this.runningTotal = newRunningTotal;
    }

    public void setQuantity(int newQuantity){
        this.quantityOfItems = newQuantity;
    }
}
