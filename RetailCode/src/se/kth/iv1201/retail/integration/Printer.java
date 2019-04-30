package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.Receipt;
import se.kth.iv1201.retail.model.StoreDTO;
import se.kth.iv1201.retail.model.AddressDTO;

public class Printer {

    public Printer(){
    }

    public void printReceipt(Receipt receipt){
        AddressDTO address = new AddressDTO("Isafjordsgatan 31", "754 31");
        StoreDTO store = new StoreDTO("ICA Maxi", address);
        System.out.println(receipt.toString(store));
    }
}
