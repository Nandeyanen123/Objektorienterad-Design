package se.kth.iv1201.retail.integration;

/**
 * This class keeps database records of how many of each items are sold.
 */
public class ItemRecord {
    private ItemRecordDTO[] itemRecords;

    /**
     * The constructor for the records. Automatically fills the database in
     * a similar way to <code>ItemInventory</code>.
     */
    public ItemRecord() {
        this.itemRecords = new ItemRecordDTO[30];
        for (int i = 0; i < itemRecords.length; i++) {
            itemRecords[i] = new ItemRecordDTO(new ItemDTO(i, "item" + (i + 1), ((Math.pow((double)i+1,2)/2)), (1.0 + (double)i/10), 10000 + i));
        }
    }

    /**
     * Increases the <code>amountSold</code> attribute of the item record based
     * on the quantity in the <code>ItemDTO</code> sent as argument.
     *
     * @param item The item to be recorded as sold.
     */
    public void recordSoldItem(ItemDTO item) {
        for (ItemRecordDTO items : itemRecords) {
            if (items.equals(item)) {
                items.setAmountSold(items.getAmountSold() + item.getQuantity());
            }
        }
    }
}
