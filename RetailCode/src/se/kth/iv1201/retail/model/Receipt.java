package se.kth.iv1201.retail.model;

import se.kth.iv1201.retail.integration.ItemDTO;

import java.time.LocalDateTime;

public class Receipt {
    private final SaleDTO sale;
    private final CashPayment cashPayment;

    Receipt(SaleDTO sale, CashPayment cashPayment){
        this.sale = sale;
        this.cashPayment = cashPayment;
    }

    public String toString(StoreDTO store) {
        StringBuilder builder = new StringBuilder();
        builder.append("\nReceipt:\n\n");

        LocalDateTime saleTime = LocalDateTime.now();
        builder.append("Date/Time: " + saleTime.toString() + "\n");
        builder.append(store.toString() + "\n");
        builder.append("Items:\tQuantity:\tPrice:\n");
        for(ItemDTO item : sale.getRegisteredItems()){
            if(item != null) {
                builder.append(item.getItemName() + "\t\t" + item.getQuantity() + "\t\t" +
                        (item.getPrice() * item.getQuantity()) + "\n");
            }
        }
        builder.append("\nTotal price: " + sale.getRunningTotal() + "\n");
        builder.append("Average VAT: " + averageVAT() + "\n\n");
        builder.append(cashPayment.toString() + "\n");
        return builder.toString();
    }

    public double averageVAT(){
        double averageVAT = 0;
        ItemDTO[] items = sale.getRegisteredItems();
        double divisor = 0;
        //for(int i = 0; i<items.length; i++){
        for(ItemDTO item : items){
            if(item != null){
                divisor = divisor + item.getQuantity();
                averageVAT = (averageVAT + (item.getVAT())*(double)item.getQuantity());
            }
        }
        averageVAT = averageVAT/divisor;
        return averageVAT;
    }

}
