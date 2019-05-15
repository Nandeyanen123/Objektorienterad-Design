package se.kth.iv1201.retail.view;

import se.kth.iv1201.retail.controller.Controller;
import se.kth.iv1201.retail.controller.OperationFailedException;
import se.kth.iv1201.retail.model.Amount;
import se.kth.iv1201.retail.util.LogHandler;

import java.io.IOException;

/**
 * This is a placeholder class for the entire view.
 */
public class View {
    private Controller controller;
    private ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private LogHandler logger;

    /**
     * Creates a new instance of the View.
     *
     * @param controller The controller that is used for all operations.
     */
    public View(Controller controller) throws IOException {
        this.controller = controller;
        controller.addIncomeObserver(new TotalRevenueView());
        this.logger = new LogHandler();
    }

    /**
     * Simulates user input to generate calls to illustrate program flow.
     */
    public void sampleExecution(){
        controller.startNewSale();
        try {
            System.out.println(controller.addItem(1));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(543));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(2));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(1));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(3, 4));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(24, 56));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(2, 3));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        }

        System.out.println(controller.registrationFinished());

        Amount paidAmount = new Amount(30000);
        System.out.println("Change for customer: " + controller.pay(paidAmount).getChange());

        controller.startNewSale();
        try {
            System.out.println(controller.addItem(3,6));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(4,10));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(3,20));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        } try{
            System.out.println(controller.addItem(22,15));
        } catch (OperationFailedException exc){
            handleException("There is no item with this identifier in the inventory.", exc);
        }

        System.out.println(controller.registrationFinished());

        Amount paidAmount2 = new Amount(10000);
        System.out.println("Change for customer: " + controller.pay(paidAmount2).getChange());
    }

    private void handleException(String msgForUI, Exception exc){
        errorMsgHandler.showErrorMsg(msgForUI);
        logger.logException(exc);
    }
}
