package se.kth.iv1201.retail.view;

import se.kth.iv1201.retail.model.IncomeObserver;

/**
 * A class that implements the <code>IncomeObserver</code> interface
 * to observe and notify the user about how much revenue has been generated
 * since the system was turned on.
 */
class TotalRevenueView implements IncomeObserver {
    private double revenue;

    /**
     * Constructs the observing object and sets the starting revenue to 0.
     */
    public TotalRevenueView(){
        this.revenue = 0.0;
    }

    /**
     * Sets the observed revenue to the value sent as argument plus the
     * previous value stored in the object, then prints it to the user.
     *
     * @param income The income from the processed sale.
     */
    @Override
    public void newIncome(double income){
        addNewIncome(income);
        printCurrentState();
    }

    private void addNewIncome(double income){
        this.revenue += income;
    }

    private void printCurrentState(){
        System.out.println("Revenue so far: ");
        System.out.println("###########################");
        System.out.println(revenue + "SEK.");
        System.out.println("###########################\n");
    }
}
