package se.kth.iv1201.retail.model;

import se.kth.iv1201.retail.integration.*;

/**
 * Represents the data of a sale.
 */
public class SaleDTO {
    private int saleID;
    private boolean registrationFinished;
    private boolean completed;
    private double runningTotal;
    private ItemDTO[] registeredItems;
    private int quantityOfItems;
    private int itemCounter;

    /**
     * Constructs a <code>SaleDTO</code> object with an identifier passed as an
     * argument.
     *
     * @param saleID The identifier of the <code>SaleDTO</code>.
     */
    public SaleDTO(int saleID){
        this.saleID = saleID;
        this.registrationFinished = false;
        this.completed = false;
        this.runningTotal = 0;
        this.registeredItems = new ItemDTO[30];
        this.quantityOfItems = 0;
        this.itemCounter = 0;
    }

    /**
     * Returns the <code>SaleDTO</code> object as a <code>String</code>.
     *
     * @return The <code>SaleDTO</code> as a <code>String</code>.
     */
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

    /**
     * A comparator method to see if this object equals another.
     *
     * @param otherObj The other object.
     * @return <code>true</code> or <code>false</code> depending on
     * if the conditions are fulfilled or not.
     */
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

    /**
     * Checks if there are no items in the sale.
     *
     * @return <code>true</code> or <code>false</code> depending on
     * if the conditions are fulfilled or not.
     */
    public boolean isEmpty(){
        boolean isEmpty = true;
        if(getRegisteredItems()[0] != null){
            isEmpty = false;
        }
        return isEmpty;
    }

    /**
     * Returns the sale identifier as an <code>int</code>.
     *
     * @return The sale identifier as an <code>int</code>.
     */
    public int getSaleID(){
        return saleID;
    }

    /**
     * Returns the status of if the item registration is finished or not.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean getRegistrationFinished(){
        return registrationFinished;
    }

    /**
     * Returns the status of if the sale is completed or not.
     *
     * @return <code>true</code> or <code>false</code>.
     */
    public boolean getCompleted(){
        return completed;
    }

    /**
     * Returns the running total as a <code>double</code>.
     *
     * @return Yhe running total as a <code>double</code>.
     */
    public double getRunningTotal(){
        return runningTotal;
    }

    /**
     * Return the items registered in the sale as an <code>ItemDTO</code>
     * array.
     *
     * @return The items registered in the sale.
     */
    public ItemDTO[] getRegisteredItems(){
        return registeredItems;
    }

    /**
     * Returns the quantity of items registered as an <code>int</code>.
     *
     * @return The quantity of items registered as an <code>int</code>.
     */
    public int getQuantity(){
        return quantityOfItems;
    }

    /**
     * Adds an item to the <code>registeredItems</code> array.
     *
     * @param item The <code>ItemDTO</code> object to be added.
     */
    public void addItem(ItemDTO item){
        if(itemCounter<30) {
            registeredItems[itemCounter] = item;
            itemCounter++;
            if(itemCounter>= 30){
                itemCounter = 0;
            }
        }
    }

    /**
     * Sets the status of if the registration of items is finished or not to
     * whatever value is provided in the argument.
     *
     * @param newRegistrationFinished <code>true</code> or <code>false</code>
     */
    public void setRegistrationFinished(boolean newRegistrationFinished){
        this.completed = newRegistrationFinished;
    }

    /**
     * Sets the status of if the sale is completed or not to
     * whatever value is provided in the argument.
     *
     * @param newCompleted <code>true</code> or <code>false</code>
     */
    public void setCompleted(boolean newCompleted){
        this.completed = newCompleted;
    }

    /**
     * Sets the running total to a now value provided as an
     * argument.
     *
     * @param newRunningTotal The value of the new running total.
     */
    public void setRunningTotal(double newRunningTotal){
        this.runningTotal = newRunningTotal;
    }

    /**
     * Sets the quantity of the items registered to the value
     * provided as argument.
     *
     * @param newQuantity The value of the new quantity of items.
     */
    public void setQuantity(int newQuantity){
        this.quantityOfItems = newQuantity;
    }
}
