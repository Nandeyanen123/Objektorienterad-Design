package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.Receipt;
import se.kth.iv1201.retail.model.StoreDTO;
import se.kth.iv1201.retail.model.AddressDTO;

/**
 * Represents a physical printer as an object.
 * Prints to the terminal instead of on paper.
 */
public class Printer {

    public Printer(){
    }

    /**
     * Prints the <code>Receipt</code> object sent as argument.
     *
     * @param receipt The object to be printed.
     */
    public void printReceipt(Receipt receipt){
        System.out.println(receipt.toString());
    }
}
