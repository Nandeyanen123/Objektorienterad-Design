package se.kth.iv1201.retail.model;

public class StoreDTO {
    private String storeName;
    private AddressDTO storeAddress;

    public StoreDTO(String storeName, AddressDTO storeAddress){
        this.storeName = storeName;
        this.storeAddress = storeAddress;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Store: " + storeName + "\n");
        builder.append(storeAddress.toString());
        return builder.toString();
    }
}
