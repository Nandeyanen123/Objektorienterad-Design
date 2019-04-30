package se.kth.iv1201.retail.controller;

import se.kth.iv1201.retail.integration.*;
import se.kth.iv1201.retail.model.*;

public class Controller {
    private Printer printer;
    private ExternalAccounting externalAccounting;
    private ItemInventory itemInventory;
    private ItemRecord itemRecord;
    private SalesLog salesLog;
    private Sale sale;
    private Register register;

    public Controller(LoggingCreator creator, Printer printer){
        this.printer = printer;
        this.externalAccounting = creator.getExternalAccounting();
        this.itemInventory = creator.getItemInventory();
        this.itemRecord = creator.getItemRecord();
        this.salesLog = creator.getSalesLog();
        this.register = new Register();
    }

    public void startNewSale(){
        sale = new Sale();
    }

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

    public String addItem(int itemID, int quantity){
        if(quantity<1){
            return "No items will be added if \"quantity\" is less than 1.";
        }
        ItemDTO item = itemInventory.getItemFromInventory(itemID);
        if(item != null){
            item.setQuantity(quantity);
            itemRecord.recordSoldItem(item);
            sale.addItemAndUpdate(item);
            return sale.getSaleDTO().toString();
        } else{
            return ("There is no Item with the identifier: " + itemID);
        }
    }

    public String registrationFinished(){
        //sale.registrationFinished();
        return ("Total price: " + sale.getSaleDTO().getRunningTotal());
    }

    public String pay(Amount paidAmt){
        if(paidAmt.getAmount()>=sale.getSaleDTO().getRunningTotal()) {
            sale.getSaleDTO().setCompleted(true);
            SaleDTO currentSale = sale.getSaleDTO();
            CashPayment payment = new CashPayment(paidAmt, currentSale);
            Receipt printerReceipt = sale.pay(payment);
            salesLog.logCompletedSale(currentSale);
            externalAccounting.sendCompletedSale(currentSale);
            itemInventory.updateInventory(currentSale.getRegisteredItems());
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
