package se.kth.iv1201.retail.integration;

import se.kth.iv1201.retail.model.SaleDTO;

/**
 * Logs completed sales. Is supposed to be a database but uses an array instead.
 */
public class SalesLog {
    private SaleDTO[] salesInfo;
    private int salesCounter;

    /**
     * The constructor for the log. Creates an empty array of <code>SaleDTO</code>s to use for the
     * logs. <code>salesCounter</code> keeps track of where in the array the latest entry was inserted
     * and rolls over to 0 if the array is filled (overwriting old records).
     */
    public SalesLog(){
        this.salesInfo = new SaleDTO[20];
        this.salesCounter = 0;
    }

    /**
     * Logs the completed <code>Sale</code> in the logger.
     *
     * @param paidSale the <code>SaleDTO</code> to be logged.
     */
    public void logCompletedSale(SaleDTO paidSale){
        logger(paidSale);
    }

    private void logger(SaleDTO paidSale) {
        salesInfo[salesCounter] = paidSale;
        System.out.println("Sale: " + paidSale.getSaleID() + " has been logged.");
        salesCounter++;
        if (salesCounter >= 20) {
            salesCounter = 0;
        }
    }
}
