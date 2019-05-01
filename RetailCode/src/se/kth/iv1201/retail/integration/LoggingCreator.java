package se.kth.iv1201.retail.integration;

/**
 * Creates objects of systems from the integration layer and is then used for
 * being passed to the construction of the <code>Controller</code>.
 */
public class LoggingCreator {
    private ExternalAccounting externalAccounting = new ExternalAccounting();
    private ItemInventory itemInventory = new ItemInventory();
    private ItemRecord itemRecord = new ItemRecord();
    private SalesLog salesLog = new SalesLog();

    /**
     * Gets the object representing the external accounting system.
     *
     * @return The <code>ExternalAccounting</code> object.
     */
    public ExternalAccounting getExternalAccounting(){
        return externalAccounting;
    }

    /**
     * Gets the object representing the inventory database.
     *
     * @return The <code>ItemInventory</code> object.
     */
    public ItemInventory getItemInventory(){
        return itemInventory;
    }

    /**
     * Gets the object representing the item records.
     *
     * @return The <code>ItemRecord</code> object.
     */
    public ItemRecord getItemRecord(){
        return itemRecord;
    }

    /**
     * Gets the object representing the sales logger.
     *
     * @return The <code>SalesLog</code> object.
     */
    public SalesLog getSalesLog(){
        return salesLog;
    }
}
