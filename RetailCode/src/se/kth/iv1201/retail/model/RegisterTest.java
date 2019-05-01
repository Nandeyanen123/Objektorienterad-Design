package se.kth.iv1201.retail.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegisterTest {

    @Test
    public void testAddPaymentAndUpdateSuccessful() {
        Amount paidAmount = new Amount(100.0);
        SaleDTO sale = new SaleDTO(1);
        sale.setRunningTotal(80.5);
        CashPayment payment = new CashPayment(paidAmount,sale);
        Register setupRegister = new Register();
        Register instance = setupRegister;
        double expResult = setupRegister.getAmountInRegister();
        expResult = expResult + payment.getAmount() - payment.getChange();
        instance.addPaymentAndUpdate(payment);
        assertEquals("Wrong amount in register",expResult,instance.getAmountInRegister(),0.01);
    }
}