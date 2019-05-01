package se.kth.iv1201.retail.model;

/**
 * Represents an amount.
 */
public final class Amount {
    private final double amount;

    /**
     * Creates an <code>Amount</code> object with the value 0.
     */
    public Amount(){
        this.amount = 0.0;
    }

    /**
     * Overload method that creates an <code>Amount</code> object with a
     * certain specified value.
     *
     * @param amount The specified value.
     */
    public Amount(double amount){
        this.amount = amount;
    }

    /**
     * Gets the amount as a <code>double</code>.
     *
     * @return The amount as a <code>double</code>.
     */
    public double getAmount(){
        return amount;
    }

    /**
     * Returns the <code>Amount</code> object as a <code>String</code>.
     *
     * @return The object as a <code>String</code>.
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(getAmount() + "\n");
        return builder.toString();
    }
}
