package se.kth.iv1201.retail.view;

import se.kth.iv1201.retail.controller.Controller;
import se.kth.iv1201.retail.model.Amount;

/**
 * This is a placeholder class for the entire view.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new instance of the View.
     *
     * @param controller The controller that is used for all operations.
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * Simulates user input to generate calls to illustrate program flow.
     */
    public void sampleExecution(){
        controller.startNewSale();
        int itemID = 1;
        System.out.println(controller.addItem(itemID));
        itemID = 543;
        Object nullView = controller.addItem(itemID);
        if(nullView != null){
            System.out.println(nullView);
        } else{
            System.out.println("The identifier " + itemID + "is not valid.");
        }
        itemID = 2;
        System.out.println(controller.addItem(itemID));
        itemID = 1;
        System.out.println(controller.addItem(itemID));
        itemID = 3;
        System.out.println(controller.addItem(itemID,4));
        itemID = 24;
        System.out.println(controller.addItem(itemID,56));
        itemID = 2;
        System.out.println(controller.addItem(itemID,3));

        controller.registrationFinished();

        Amount paidAmount = new Amount(30000);
        System.out.println("Change for customer: " + controller.pay(paidAmount).getChange());
    }
}
