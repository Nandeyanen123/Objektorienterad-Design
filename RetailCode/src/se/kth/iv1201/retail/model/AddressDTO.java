package se.kth.iv1201.retail.model;

public class AddressDTO {
    private String streetAddress;
    private String zipCode;

    public AddressDTO(String streetAddress, String zipCode){
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Street address: " + streetAddress + "\n");
        builder.append("Area code: " + zipCode + "\n");
        return builder.toString();
    }
}
