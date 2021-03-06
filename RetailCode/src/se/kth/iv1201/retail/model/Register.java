package se.kth.iv1201.retail.model;

import java.util.*;

/**
 * Represents the register that the cashier uses.
 */
public class Register {
    private IncomeObserver incomeObserver;
    private int registerID;
    private double amountInRegister;

    /**
     * Creates a <code>Register</code> object with a random identifier and a
     * random amount of cash in it.
     */
    public Register(){
        Random random = new Random();
        this.registerID = random.nextInt(100);
        this.amountInRegister = Math.round(10 + (10000 - 10) * random.nextDouble()*10d/10d);
    }

    /**
     * Adds a <code>CashPayment</code> payment to the register if the
     * register has enough money for the change.
     * Subtracts the change from the register automatically while it adds the payment.
     *
     * @param payment The payment paid by the customer.
     */
    public void addPaymentAndUpdate(CashPayment payment){
        if(payment.getChange()<=(amountInRegister + payment.getAmount())){
            double income = payment.getAmount() - payment.getChange();
            notifyIncomeObservers(income);
            amountInRegister += income;
        }
    }

    /**
     * Returns the amount of money in the register as a <code>double</code>.
     *
     * @return The amount of money in the register as a <code>double</code>.
     */
    public double getAmountInRegister(){
        return amountInRegister;
    }

    public void addIncomeObserver(IncomeObserver observer){
        incomeObserver = observer;
    }

    private void notifyIncomeObservers(double income){
        this.incomeObserver.newIncome(income);
    }
}
