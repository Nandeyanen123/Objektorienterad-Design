package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.SaleDTO;

public class ExternalAccounting {

    public ExternalAccounting(){

    }

    public void sendCompletedSale(SaleDTO completedSale){
        System.out.println("Sale: " + completedSale.toString() + " has been sent to accounting.\n");
    }
}
