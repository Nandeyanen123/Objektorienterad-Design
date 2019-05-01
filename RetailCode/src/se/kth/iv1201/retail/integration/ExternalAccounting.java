package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.SaleDTO;

/**
 * A representation of the system meant to send sales information
 * to external accounting services.
 */
public class ExternalAccounting {

    public ExternalAccounting(){

    }

    /**
     * Sends the completed sales information to accounting.
     *
     * @param completedSale The completed <code>SaleDTO</code> object.
     */
    public void sendCompletedSale(SaleDTO completedSale){
        System.out.println("Sale: " + completedSale.toString() + " has been sent to accounting.\n");
    }
}
