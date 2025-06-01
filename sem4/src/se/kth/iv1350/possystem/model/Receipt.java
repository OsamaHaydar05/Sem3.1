package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;

/**
 * Represents a finalized receipt containing all details of a completed sale,
 * including sold items, total price, VAT, paid amount, change, and time.
 */
public class Receipt {
    private final HashMap<String, SoldItem> soldItems;
    private final double amountPaid;
    private final double totalPrice;
    private final double totalVAT;
    private final double change;
    private final LocalDateTime timeOfSale;

    /**
     * Creates a new receipt with all items and payment details for a completed sale.
     *
     * @param soldItems A collection of items sold during the sale.
     * @param amountPaid The amount of money paid by the customer.
     */
    public Receipt(HashMap<String, SoldItem> soldItems, double amountPaid) {
        this.soldItems = soldItems;
        this.amountPaid = amountPaid;
        this.totalPrice = soldItems.values().stream().mapToDouble(SoldItem::getTotalPrice).sum();
        this.totalVAT = soldItems.values().stream().mapToDouble(SoldItem::getTotalVAT).sum();
        this.change = amountPaid - totalPrice;
        this.timeOfSale = LocalDateTime.now();
    }

    /**
     * Returns the calculated change that should be given to the customer.
     *
     * @return The amount of change.
     */
    public double getChange() {
        return change;
    }

    /**
     * Returns a string representation of the entire receipt, including time of sale,
     * list of sold items, total price, VAT, paid amount, and change.
     *
     * @return A formatted string representing the printed receipt.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("- - - - - - - - - - - - - - - - - - Begin receipt - - - - - - - - - - - - - - - - - - -\n");
        sb.append("Time of Sale: ").append(timeOfSale.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))).append("\n\n");
        for (SoldItem item : soldItems.values()) {
            sb.append(item.toString()).append("\n");
        }
        sb.append(String.format("\nTotal: %.2f SEK\nVAT: %.2f SEK\n", totalPrice, totalVAT));
        sb.append(String.format("Cash: %.2f SEK\nChange: %.2f SEK\n", amountPaid, change));
        sb.append("- - - - - - - - - - - - - - - - - - End receipt - - - - - - - - - - - - - - - - - - - -\n");
        return sb.toString();
    }
}
