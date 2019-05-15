package se.kth.iv1201.retail.model;

import java.util.*;
import se.kth.iv1201.retail.integration.*;

/**
 * Represents a sale that is ongoing.
 */
public class Sale {
    private SaleDTO sale;

    /**
     * Constructs a <code>Sale</code> object with a random identifier in the DTO.
     */
    public Sale(){
        Random random = new Random();
        this.sale = new SaleDTO(random.nextInt(1000000));
    }

    /**
     * Adds an item to the sale. Uses method <code>itemExists()</code> to
     * check if the item to be added already exists in the sale or not.
     * If it does exist it adds the quantity of the item to be added to its
     * previous representation in the <code>SaleDTO</code> and returns <code>true</code>.
     * If not the item just gets added. Calculates the new running total by using
     * method <code>calculatePriceWithVAT()</code>.
     *
     * @param item The item to be added.
     */
    public void addItemAndUpdate(ItemDTO item){
        if(!sale.getRegistrationFinished()) {
            boolean existed = itemExists(item);
            if (!existed) {
                this.sale.addItem(item);
            }
            this.sale.setQuantity(this.sale.getQuantity() + item.getQuantity());
            this.sale.setRunningTotal(calculatePriceWithVAT());
        }
    }

    private boolean itemExists(ItemDTO item){
        boolean existed = false;
        int i = 0;
        if(!sale.isEmpty()) {
            while (sale.getRegisteredItems()[i] != null && i < sale.getRegisteredItems().length) {
                if (sale.getRegisteredItems()[i].getItemID() == item.getItemID()) {
                    sale.getRegisteredItems()[i].setQuantity(sale.getRegisteredItems()[i].getQuantity() + item.getQuantity());
                    existed = true;
                }
                i++;
            }
        } return existed;
    }

    private double calculatePriceWithVAT(){
        double calculatedPrice = 0;
        for(ItemDTO registeredItem : sale.getRegisteredItems()){
            if(registeredItem != null) {
                calculatedPrice += (registeredItem.getQuantity() * registeredItem.getPrice() * registeredItem.getVAT());
            }
        }
        return calculatedPrice;
    }

    /**
     * Signals that the registration of items is finished.
     */
    public void registrationFinished(){
        this.sale.setRegistrationFinished(true);
    }

    /**
     * Signals that the sale has been successfully completed.
     */
    public void completedSale(){
        this.sale.setCompleted(true);
    }

    /**
     * Creates and return a <code>Receipt</code> object based on the current sale.
     *
     * @param payment The payment to be added to the receipt.
     * @return The <code>Receipt</code> object.
     */
    public Receipt pay(CashPayment payment){
        if(sale.getCompleted()) {
            Receipt saleReceipt = new Receipt(sale, payment);
            return saleReceipt;
        }
        return null;
    }

    /**
     * Returns the <code>SaleDTO</code> object of the current sale.
     *
     * @return The <code>SaleDTO</code> object.
     */
    public SaleDTO getSaleDTO(){
        return sale;
    }
}
