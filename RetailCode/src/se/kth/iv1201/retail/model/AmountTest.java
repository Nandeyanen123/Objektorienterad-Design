package se.kth.iv1201.retail.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AmountTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void toStringPosAmt() {
        double representedAmt = 10.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt) + "\n";
        String result = amount.toString();
        assertEquals("Wrong String returned by toString()", expResult,result);
    }

    @Test
    public void toStringNegAmt() {
        double representedAmt = -10.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt)+ "\n";
        String result = amount.toString();
        assertEquals("Wrong String returned by toString()", expResult,result);
    }

    @Test
    public void toStringZeroAmt() {
        double representedAmt = 0.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt)+ "\n";
        String result = amount.toString();
        assertEquals("Wrong String returned by toString()", expResult,result);
    }
}