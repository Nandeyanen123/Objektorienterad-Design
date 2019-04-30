package se.kth.iv1201.retail.integration;

public class ItemRecord {
    private ItemRecordDTO[] itemRecords;

    public ItemRecord() {
        this.itemRecords = new ItemRecordDTO[30];
        for (int i = 0; i < itemRecords.length; i++) {
            itemRecords[i] = new ItemRecordDTO(new ItemDTO(i, "item" + (i + 1), ((Math.pow((double)i+1,2)/2)), (1.0 + (double)i/10), 10 + i));
        }
    }

    public void recordSoldItem(ItemDTO item) {
        for (ItemRecordDTO items : itemRecords) {
            if (items.getItemID() == item.getItemID()) {
                items.setAmountSold(items.getAmountSold() + item.getQuantity());
            }
        }
    }
}
