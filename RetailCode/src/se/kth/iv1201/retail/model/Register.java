package se.kth.iv1201.retail.model;

import java.util.*;

public class Register {
    private int registerID;
    private double amountInRegister;

    public Register(){
        Random random = new Random();
        this.registerID = random.nextInt(100);
        this.amountInRegister = Math.round(10 + (10000 - 10) * random.nextDouble()*10d/10d);
    }

    public void addPaymentAndUpdate(CashPayment payment){
        amountInRegister += payment.getAmount() - payment.getChange();
    }

    public double getAmountInRegister(){
        return amountInRegister;
    }
}
