package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.SaleDTO;

public class SalesLog {
    private SaleDTO[] salesInfo;
    private int salesCounter;

    public SalesLog(){
        this.salesInfo = new SaleDTO[20];
        this.salesCounter = 0;
    }

    public void logCompletedSale(SaleDTO paidSale){
        if(salesCounter<20){
            salesInfo[salesCounter] = paidSale;
            System.out.println("Sale: " + paidSale.getSaleID() + " has been logged.");
            salesCounter++;
        }
        if(salesCounter>=20){
            salesCounter = 0;
        }
    }
}
