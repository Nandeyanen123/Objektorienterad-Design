package se.kth.iv1201.retail.integration;

public class ItemDTO {
    private int itemID;
    private String itemName;
    private double price;
    private double VAT;
    private int quantity;
    private boolean sold;
    private boolean returned;

    public ItemDTO(int itemID, String itemName, double price, double VAT, int quantity){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.VAT = VAT;
        this.quantity = quantity;
        this.sold = false;
        this.returned = false;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("itemID: " + itemID + ", ");
        builder.append("itemName: " + itemName + ", ");
        builder.append("price: " + price + ", ");
        builder.append("VAT: " + VAT + ", ");
        builder.append("quantity: " + quantity);
        return builder.toString();
    }

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

    public int getItemID(){
        return itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public double getPrice(){
        return price;
    }

    public double getVAT(){
        return VAT;
    }

    public int getQuantity(){
        return quantity;
    }

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public boolean getSold(){
        return sold;
    }

    public boolean getReturned(){
        return returned;
    }

    public void setSold(boolean sold){
        this.sold = sold;
    }

    public void setReturned(boolean returned){
        this.returned = returned;
    }
}
