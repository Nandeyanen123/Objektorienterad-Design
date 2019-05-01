package se.kth.iv1201.retail.integration;

/**
 * The DTO for <code>ItemRecord</code>.
 * Each object contains an item and how many of that item have been sold.
 */
public class ItemRecordDTO {
    private ItemDTO item;
    private int amountSold;

    /**
     * Constructs an <code>ItemRecordDTO</code> and initializes <code>amountSold</code> to 0.
     *
     * @param item The item to create a record for.
     */
    public ItemRecordDTO(ItemDTO item){
        this.item = item;
        this.amountSold = 0;
    }

    /**
     * Gets how many quantities of the item have been sold.
     *
     * @return The amount sold.
     */
    public int getAmountSold(){
        return amountSold;
    }

    /**
     * Sets the amount sold to the value specified in the argument.
     *
     * @param amount How many items have been sold.
     */
    public void setAmountSold(int amount){
        this.amountSold = amount;
    }
}
