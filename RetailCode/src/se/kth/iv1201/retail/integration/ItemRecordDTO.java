package se.kth.iv1201.retail.integration;

public class ItemRecordDTO {
    private ItemDTO item;
    private int amountSold;

    public ItemRecordDTO(ItemDTO item){
        this.item = item;
        this.amountSold = 0;
    }

    public int getAmountSold(){
        return amountSold;
    }

    public void setAmountSold(int amount){
        this.amountSold = amount;
    }

    public int getItemID(){
        return item.getItemID();
    }
}
