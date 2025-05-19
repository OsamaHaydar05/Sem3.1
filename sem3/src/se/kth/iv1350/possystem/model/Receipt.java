package model;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashMap;

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

 
    public Collection<SoldItem> getSoldItems() {   //View använder denna
        return soldItems.values();
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getChange() {
        return change;
    }

    public LocalDateTime getTimeOfSale() {
        return timeOfSale;
    }

}
