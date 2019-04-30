package se.kth.iv1201.retail.model;

import java.util.*;
import se.kth.iv1201.retail.integration.*;

public class Sale {
    private SaleDTO sale;

    public Sale(){
        Random random = new Random();
        this.sale = new SaleDTO(random.nextInt(1000000));
    }

    public void addItemAndUpdate(ItemDTO item){
        boolean existed = false;
        int i = 0;
        if(!sale.isEmpty()) {
            //for (int i = 0; i < sale.getRegisteredItems().length; i++) {
            while(sale.getRegisteredItems()[i] != null && i<sale.getRegisteredItems().length){
                if (sale.getRegisteredItems()[i].getItemID() == item.getItemID()) {
                    sale.getRegisteredItems()[i].setQuantity(sale.getRegisteredItems()[i].getQuantity() + item.getQuantity());
                    existed = true;
                }
                i++;
            }
        }if(!existed){
            this.sale.addItem(item);
        }
        this.sale.setQuantity(this.sale.getQuantity()+item.getQuantity());
        this.sale.setRunningTotal(calculatePriceWithVAT());
    }

    public void registrationFinished(){
        this.sale.setCompleted(true);
    }

    public Receipt pay(CashPayment payment){
        Receipt saleReceipt = new Receipt(sale, payment);
        return saleReceipt;
    }

    /*public boolean itemExistsInSale(ItemDTO item){
        boolean exists = false;
        for(int i = 0; i<sale.getRegisteredItems().length; i++){
            if(sale.getRegisteredItems()[i].getItemID() == item.getItemID()){
                exists = true;
            }
        }
        return exists;
    }*/

    private double calculatePriceWithVAT(){
        double calculatedPrice = 0;
        for(ItemDTO registeredItem : sale.getRegisteredItems()){
            if(registeredItem != null) {
                calculatedPrice += (registeredItem.getQuantity() * registeredItem.getPrice() * registeredItem.getVAT());
            }
        }
        return calculatedPrice;
    }

    public SaleDTO getSaleDTO(){
        return sale;
    }
}
