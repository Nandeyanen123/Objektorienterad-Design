package se.kth.iv1201.retail.model;

import se.kth.iv1201.retail.integration.ItemDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a receipt of a completed sale.
 */
public class Receipt {
    private final SaleDTO sale;
    private final CashPayment cashPayment;

    /**
     * Constructs a <code>Receipt</code> object based on the sale that has been completed and
     * the cash payment the customer has paid.
     *
     * @param completedSale The completed sale object.
     * @param cashPayment The payment paid by the customer.
     */
    Receipt(SaleDTO completedSale, CashPayment cashPayment){
        this.sale = completedSale;
        this.cashPayment = cashPayment;
    }

    /**
     * Returns a <code>String</code> of the entire <code>Receipt</code> object.
     * Creates an <code>AddressDTO</code> and a <code>StoreDTO</code> object for
     * the current store to add in the <code>String</code>.
     * Uses a private method <code>averageVAT()</code> to compute the average VAT of
     * the entire sale and adds it to the <code>String</code> as a percentage (in the sale
     * it has been represented as a scalar for easier multiplication.
     *
     * @return The receipt as a <code>String</code>.
     */
    public String toString() {
        AddressDTO address = new AddressDTO("Isafjordsgatan 31", "754 31");
        StoreDTO store = new StoreDTO("ICA Maxi", address);
        StringBuilder builder = new StringBuilder();
        builder.append("\nReceipt:\n*****************************************\n");

        LocalDateTime saleTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        builder.append("Date/Time: " + saleTime.format(formatter) + "\n");
        builder.append(store.toString() + "\n");
        builder.append("Items:\tQuantity:\tPrice:\n");
        for(ItemDTO item : sale.getRegisteredItems()){
            if(item != null) {
                builder.append(item.getItemName() + "\t\t" + item.getQuantity() + "\t\t" +
                        (item.getPrice() * item.getQuantity()) + "\n");
            }
        }
        builder.append("\nTotal price: " + sale.getRunningTotal() + "\n");
        builder.append("Average VAT: " + averageVAT() + "%\n\n");
        builder.append(cashPayment.toString() + "\n*****************************************");
        return builder.toString();
    }

    private double averageVAT(){
        double averageVAT = 0.0;
        ItemDTO[] items = sale.getRegisteredItems();
        double divisor = 0.0;
        for(ItemDTO item : items){
            if(item != null){
                divisor = divisor + (double)item.getQuantity();
                averageVAT = (averageVAT + (item.getVAT())*(double)item.getQuantity());
            }
        }
        averageVAT = Math.round((((averageVAT/divisor)-1.0)*100.0*100.0)/100.0);
        return averageVAT;
    }

}
