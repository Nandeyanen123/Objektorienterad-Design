package se.kth.iv1201.retail.integration;

public class LoggingCreator {
    private ExternalAccounting externalAccounting = new ExternalAccounting();
    private ItemInventory itemInventory = new ItemInventory();
    private ItemRecord itemRecord = new ItemRecord();
    private SalesLog salesLog = new SalesLog();

    public ExternalAccounting getExternalAccounting(){
        return externalAccounting;
    }

    public ItemInventory getItemInventory(){
        return itemInventory;
    }

    public ItemRecord getItemRecord(){
        return itemRecord;
    }

    public SalesLog getSalesLog(){
        return salesLog;
    }
}
