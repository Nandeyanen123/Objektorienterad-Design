package se.kth.iv1201.retail.model;

public final class Amount {
    private final double amount;

    public Amount(){
        this.amount = 0;
    }

    public Amount(double amount){
        this.amount = amount;
    }

    public double getAmount(){
        return amount;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getAmount() + "\n");
        return builder.toString();
    }
}
