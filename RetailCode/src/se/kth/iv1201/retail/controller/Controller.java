/**
 * Written by Erik Mickols for Seminar 3 in Objektorienterad
 * Design.
 */
package se.kth.iv1201.retail.controller;

import se.kth.iv1201.retail.integration.*;
import se.kth.iv1201.retail.model.*;

/**
 * This is the controller class which passes all call to the model and
 * integration layer.
 */
public class Controller {
    private Printer printer;
    private ExternalAccounting externalAccounting;
    private ItemInventory itemInventory;
    private ItemRecord itemRecord;
    private SalesLog salesLog;
    private Sale sale;
    private Register register;

    /**
     * The controller constructor.
     *
     * @param creator Used to get all classes that handle external systems
     *                and databases.
     * @param printer Printer interface
     */
    public Controller(LoggingCreator creator, Printer printer){
        this.printer = printer;
        this.externalAccounting = creator.getExternalAccounting();
        this.itemInventory = creator.getItemInventory();
        this.itemRecord = creator.getItemRecord();
        this.salesLog = creator.getSalesLog();
        this.register = new Register();
    }

    /**
     * Starts a new sale by constructing a <code>Sale</code> object.
     */
    public void startNewSale(){
        sale = new Sale();
    }

    /**
     * Adds an item to the current sale. It first gets the <code>Item</code>
     * object from the inventory db, records it as sold and adds it to the current
     * sale if the object exists.
     *
     * @param itemID The identifier for the <code>Item</code> object.
     * @return A <code>String</code> with either the updated sale or
     * an error message if no item is found.
     */
    public String addItem(int itemID){
        ItemDTO item = itemInventory.getItemFromInventory(itemID);
        if(item != null){
            itemRecord.recordSoldItem(item);
            sale.addItemAndUpdate(item);
            return sale.getSaleDTO().toString();
        } else{
            return ("There is no Item with the identifier: " + itemID);
        }
    }

    /**
     * Overloads <code>addItem()</code> if a quantity parameter is present
     * it then sets the quantity attribute of the object fetched to what is
     * inputted. Returns an additional error message if the quantity is less than
     * 1.
     *
     * @param itemID Object identifier.
     * @param quantity Specifies the quantity of the <code>Item</code>
     *                 to be added.
     * @return Updated <code>Sale</code> or error messages.
     */
    public String addItem(int itemID, int quantity){
        if(quantity<1){
            return "No items will be added if \"quantity\" is less than 1.";
        }
        ItemDTO item = itemInventory.getItemFromInventory(itemID, quantity);
        if(item != null){
            //item.setQuantity(quantity);
            itemRecord.recordSoldItem(item);
            sale.addItemAndUpdate(item);
            return sale.getSaleDTO().toString();
        } else{
            return ("There is no Item with the identifier: " + itemID);
        }
    }

    /**
     *Shows the user the total price for the <code>Sale</code>
     * after registration is finished.
     *
     * @return The total price for the sale.
     */
    public String registrationFinished(){
        sale.registrationFinished();
        return ("Total price: " + sale.getSaleDTO().getRunningTotal());
    }

    /**
     * Sets the <code>Sale</code> as completed if the amount that is paid
     * is equal to or higher than the total price. It then calculates the change
     * to give to the customer, creates a receipt of the sale, sends the sale
     * to logging and accounting, updates the inventory and cash register and
     * tells the cashier how much change to give to the customer.
     * Finally it prints the receipt.
     *
     * @param paidAmt The amount paid by the customer.
     * @return A confirmation that the sale is completed or an error message
     * stating that the customer hasn't paid enough.
     */
    public String pay(Amount paidAmt){
        if(paidAmt.getAmount()>=sale.getSaleDTO().getRunningTotal()) {
            sale.completedSale();
            SaleDTO currentSale = sale.getSaleDTO();
            CashPayment payment = new CashPayment(paidAmt, currentSale);
            Receipt printerReceipt = sale.pay(payment);
            salesLog.logCompletedSale(currentSale);
            externalAccounting.sendCompletedSale(currentSale);
            register.addPaymentAndUpdate(payment);
            System.out.println("Change for customer: " + payment.getChange());
            System.out.println("Current amount in register: " + register.getAmountInRegister() + "\n");
            printer.printReceipt(printerReceipt);
            return "\n\nSale completed";
        }
        else{
            return "It's time to put in more coins, man.";
        }
    }
}
