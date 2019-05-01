package se.kth.iv1201.retail.model;

/**
 * Represents a store with a name and address.
 */
public class StoreDTO {
    private String storeName;
    private AddressDTO storeAddress;

    /**
     * Constructs a <code>StoreDTO</code> object based on arguments.
     *
     * @param storeName The name of the store.
     * @param storeAddress The address of the store as an <code>AdressDTO</code>
     *                     object.
     */
    public StoreDTO(String storeName, AddressDTO storeAddress){
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    /**
     * Returns a <code>String</code> of the entire object.
     *
     * @return The <code>StoreDTO</code> object as a <code>String</code>.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Store: " + storeName + "\n");
        builder.append(storeAddress.toString());
        return builder.toString();
    }
}
