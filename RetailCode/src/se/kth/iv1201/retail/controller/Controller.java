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
    private IncomeObserver incomeObserver;

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
     * sale if the object exists. Throws an exception if the identifier is invalid.
     *
     * @param itemID The identifier for the <code>Item</code> object.
     * @return A <code>String</code> with either the updated sale or
     * an error message if no item is found.
     * @throws OperationFailedException if unable to add the item with identifier
     * <code>itemID</code>.
     */
    public SaleDTO addItem(int itemID) throws OperationFailedException{
        try {
            ItemDTO item = itemInventory.getItemFromInventory(itemID);
            itemRecord.recordSoldItem(item);
            sale.addItemAndUpdate(item);
            return sale.getSaleDTO();
        } catch (ItemInventoryException itemInvExc){
            throw new OperationFailedException("Could not add item.",itemInvExc);
        }
    }

    /**
     * Overloads <code>addItem()</code> if a quantity parameter is present.
     * Fetches an item from the inventory with the specified quantity and adds
     * it to the sale. Throws an exception if the quantity is less than
     * 1 or the <code>itemID</code> is invalid.
     *
     * @param itemID Object identifier.
     * @param quantity Specifies the quantity of the <code>Item</code>
     *                 to be added.
     * @return Updated <code>Sale</code> or error messages.
     * @throws OperationFailedException if unable to add the item with identifier
     *      * <code>itemID</code> or if <code>quantity</code> is less than 1.
     */
    public SaleDTO addItem(int itemID, int quantity) throws OperationFailedException{
        try {
            ItemDTO item = itemInventory.getItemFromInventory(itemID, quantity);
            itemRecord.recordSoldItem(item);
            sale.addItemAndUpdate(item);
            return sale.getSaleDTO();
        } catch (ItemInventoryException itemInvExc){
            throw new OperationFailedException("Could not add item.",itemInvExc);
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
    public CashPayment pay(Amount paidAmt) {
        if(paidAmt.getAmount()>=sale.getSaleDTO().getRunningTotal()) {
            sale.completedSale();
            SaleDTO currentSale = sale.getSaleDTO();
            CashPayment payment = new CashPayment(paidAmt, currentSale);
            Receipt printerReceipt = sale.pay(payment);
            salesLog.logCompletedSale(currentSale);
            externalAccounting.sendCompletedSale(currentSale);
            register.addPaymentAndUpdate(payment);
            printer.printReceipt(printerReceipt);
            return payment;
        }
        return null;
    }

    /**
     * Adds an object implementing the <code>IncomeObserver</code> interface
     * to the controller.
     *
     * @param obs The object to be added.
     */
    public void addIncomeObserver(IncomeObserver obs){
        this.incomeObserver = obs;
        register.addIncomeObserver(incomeObserver);
    }
}
