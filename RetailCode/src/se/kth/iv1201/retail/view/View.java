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
     * @param controller The controler that is used for all operations.
     */
    public View(Controller controller){
        this.controller = controller;
    }

    /**
     * Simulates user input to generate calls to illustrate program flow.
     */
    public void sampleExecution(){
        controller.startNewSale();
        System.out.println(controller.addItem(1));
        System.out.println(controller.addItem(543));
        System.out.println(controller.addItem(2));
        System.out.println(controller.addItem(1));
        System.out.println(controller.addItem(3,4));
        System.out.println(controller.addItem(24,56));
        System.out.println(controller.addItem(2,3));

        System.out.println(controller.registrationFinished());

        Amount paidAmount = new Amount(30000);
        System.out.println(controller.pay(paidAmount));
    }
}
