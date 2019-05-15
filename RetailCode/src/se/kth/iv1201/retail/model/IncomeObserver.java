package se.kth.iv1201.retail.model;

/**
 * An <code>Observer</code> interface that is used to display the total
 * amount of income since the system has been turned on.
 * The object implementing this interface gets notified whenever a sale is completed.
 */
public interface IncomeObserver {

    void newIncome(double totalIncome);
}
