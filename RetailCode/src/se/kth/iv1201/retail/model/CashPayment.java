package se.kth.iv1201.retail.model;

public class CashPayment {
    private Amount paidAmount;
    private double change;

    public CashPayment(Amount paidAmount, SaleDTO paidSale){
        this.paidAmount = paidAmount;
        this.change = Math.round(calculateTotalAndChange(paidSale)*10d/10d);
    }

    private double calculateTotalAndChange(SaleDTO paidSale){
        return (paidAmount.getAmount() - paidSale.getRunningTotal());
    }

    private void setChange(double change){
        this.change = change;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Amount paid: " + paidAmount.toString());
        builder.append("Change: " + change + "\n");
        return builder.toString();
    }

    public double getAmount(){
        return paidAmount.getAmount();
    }

    public double getChange(){
        return change;
    }
}
