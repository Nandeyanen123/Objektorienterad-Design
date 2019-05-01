package se.kth.iv1201.retail.integration;

/**
 * Contains information about an item.
 */
public class ItemDTO {
    private int itemID;
    private String itemName;
    private double price;
    private double VAT;
    private int quantity;
    private boolean sold;
    private boolean returned;

    /**
     * The constructor that creates an item.
     *
     * @param itemID The unique identifier for the specific item.
     * @param itemName The name of the item.
     * @param price The price of the item.
     * @param VAT The tax-percentage for the item.
     * @param quantity How many of these items are in this object.
     * @param sold A state indicator on if the item is sold or not.
     * @param returned A state indicator on if the item is returned or not.
     */
    public ItemDTO(int itemID, String itemName, double price, double VAT, int quantity){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.VAT = VAT;
        this.quantity = quantity;
        this.sold = false;
        this.returned = false;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("itemID: " + itemID + ", ");
        builder.append("itemName: " + itemName + ", ");
        builder.append("price: " + price + ", ");
        builder.append("VAT: " + VAT + ", ");
        builder.append("quantity: " + quantity);
        return builder.toString();
    }

    /**
     * Two <code>ItemDTO</code> objects are equal if all conditions
     * are satisfied.
     *
     * @param otherObj The object used for comparison.
     * @return true if conditions are satisfied.
     */
    @Override
    public boolean equals(Object otherObj) {
        if (otherObj == null) {
            return false;
        }
        if (getClass() != otherObj.getClass()) {
            return false;
        }
        final ItemDTO other = (ItemDTO) otherObj;
        if (itemID != other.itemID) {
            return false;
        }
        if (itemName != other.itemName) {
            return false;
        }
        return true;
    }

    /**
     * Gets the identifier of the object.
     *
     * @return The identifier.
     */
    public int getItemID(){
        return itemID;
    }

    /**
     * Gets the name of the object.
     *
     * @return The name.
     */
    public String getItemName(){
        return itemName;
    }

    /**
     * Gets the price of the object.
     *
     * @return The price.
     */
    public double getPrice(){
        return price;
    }

    /**
     * Gets the VAT of the object.
     *
     * @return The VAT.
     */
    public double getVAT(){
        return VAT;
    }

    /**
     * Gets the quantity of the object.
     *
     * @return The quantity.
     */
    public int getQuantity(){
        return quantity;
    }

    /**
     * Gets the sold status of the object.
     *
     * @return The sold status.
     */
    public boolean getSold(){
        return sold;
    }

    /**
     * Gets the returned status of the object.
     *
     * @return The returned status.
     */
    public boolean getReturned(){
        return returned;
    }

    /**
     * Sets the quantity of the object to the one specified in
     * the argument.
     *
     * @param quantity The quantity.
     */
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    /**
     * Sets the sold status of the object.
     *
     * @param sold True or false.
     */
    public void setSold(boolean sold){
        this.sold = sold;
    }

    /**
     * Sets the returned status of the object.
     *
     * @param returned True or false.
     */
    public void setReturned(boolean returned){
        this.returned = returned;
    }
}
