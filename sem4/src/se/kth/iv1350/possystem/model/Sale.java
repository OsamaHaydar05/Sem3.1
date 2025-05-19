package model;

import integration.ItemDTO;
import java.util.HashMap;

public class Sale {
    private HashMap<String, SoldItem> soldItems = new HashMap<>();

    public void addItem(ItemDTO item) {
        SoldItem sold = soldItems.get(item.id);
        if (sold == null) {
            soldItems.put(item.id, new SoldItem(item, 1));
        } else {
            sold.increaseQuantity();
        }
    }

    public Receipt finalizeSale(double amountPaid) {
        return new Receipt(soldItems, amountPaid);
    }

    public double getRunningTotal() {
        return soldItems.values().stream()
            .mapToDouble(SoldItem::getTotalPrice)
            .sum();
    }

    public double getTotalVAT() {
        return soldItems.values().stream()
            .mapToDouble(SoldItem::getTotalVAT)
            .sum();
    }
} 