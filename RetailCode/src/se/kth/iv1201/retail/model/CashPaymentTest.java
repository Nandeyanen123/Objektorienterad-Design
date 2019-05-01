package se.kth.iv1201.retail.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CashPaymentTest {


    @Test
    public void testGetAmount() {
        Amount paidAmount = new Amount(100.0);
        SaleDTO sale = new SaleDTO(1);
        sale.setRunningTotal(80.5);
        CashPayment instance = new CashPayment(paidAmount,sale);
        Amount expResult = paidAmount;
        Amount result = new Amount(instance.getAmount());
        assertEquals("Wrong paid amount", expResult.getAmount(),result.getAmount(),0.01);
    }

    @Test
    public void testGetChange() {
        Amount paidAmount = new Amount(100.0);
        SaleDTO sale = new SaleDTO(1);
        sale.setRunningTotal(80.5);
        CashPayment instance = new CashPayment(paidAmount,sale);
        Amount expResult = new Amount(Math.round(paidAmount.getAmount()-sale.getRunningTotal())*10d/10d);
        Amount result = new Amount(instance.getChange());
        assertEquals("Wrong change", expResult.getAmount(),result.getAmount(),0.01);
    }
}