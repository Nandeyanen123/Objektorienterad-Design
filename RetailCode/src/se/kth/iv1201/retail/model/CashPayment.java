package se.kth.iv1201.retail.model;

/**
 * Represents a cash payment.
 */
public class CashPayment {
    private Amount paidAmount;
    private double change;

    /**
     * Creates a <code>CashPayment</code> object based on how much the customer has payed and
     * the total price of the sale. The amount of change to give is calculated on construction.
     *
     * @param paidAmount The <code>Amount</code> paid by the customer.
     * @param paidSale The sale the customer paid for.
     */
    public CashPayment(Amount paidAmount, SaleDTO paidSale){
        this.paidAmount = paidAmount;
        this.change = Math.round(calculateTotalAndChange(paidSale)*10d/10d);
    }

    private double calculateTotalAndChange(SaleDTO paidSale){
        return (paidAmount.getAmount() - paidSale.getRunningTotal());
    }

    /**
     * Returns the object as a <code>String</code>.
     *
     * @return The object as a <code>String</code>.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Amount paid: " + paidAmount.toString());
        builder.append("Change: " + change + "\n");
        return builder.toString();
    }

    /**
     * Gets the amount paid as a <code>double</code>.
     *
     * @return The amount as a <code>double</code>.
     */
    public double getAmount(){
        return paidAmount.getAmount();
    }

    /**
     * Gets the change paid as a <code>double</code>.
     *
     * @return The change as a <code>double</code>.
     */
    public double getChange(){
        return change;
    }
}
