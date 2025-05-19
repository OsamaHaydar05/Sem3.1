package model;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.time.format.DateTimeFormatter;

public class Receipt {
    private final HashMap<String, SoldItem> soldItems;
    private final double amountPaid;
    private final double totalPrice;
    private final double totalVAT;
    private final double change;
    private final LocalDateTime timeOfSale;

    public Receipt(HashMap<String, SoldItem> soldItems, double amountPaid) {
        this.soldItems = soldItems;
        this.amountPaid = amountPaid;
        this.totalPrice = soldItems.values().stream().mapToDouble(SoldItem::getTotalPrice).sum();
        this.totalVAT = soldItems.values().stream().mapToDouble(SoldItem::getTotalVAT).sum();
        this.change = amountPaid - totalPrice;
        this.timeOfSale = LocalDateTime.now();
    }

    public double getChange() {
        return change;
    }

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