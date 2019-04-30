package se.kth.iv1201.retail.view;

import se.kth.iv1201.retail.controller.Controller;
import se.kth.iv1201.retail.model.Amount;

public class View {
    private Controller controller;

    public View(Controller controller){
        this.controller = controller;
    }

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

        Amount paidAmount = new Amount(60000);
        System.out.println(controller.pay(paidAmount));
    }
}
