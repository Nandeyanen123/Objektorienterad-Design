package se.kth.iv1201.retail.model;

/**
 * Represents an adress with a street address and zip code.
 */
public class AddressDTO {
    private String streetAddress;
    private String zipCode;

    /**
     * Creates an <code>AddressDTO</code>.
     *
     * @param streetAddress The street address.
     * @param zipCode The zip code.
     */
    public AddressDTO(String streetAddress, String zipCode){
        this.streetAddress = streetAddress;
        this.zipCode = zipCode;
    }

    /**
     * Returns the object as a <code>String</code>.
     *
     * @return The object as a <code>String</code>.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Street address: " + streetAddress + "\n");
        builder.append("Area code: " + zipCode + "\n");
        return builder.toString();
    }
}
