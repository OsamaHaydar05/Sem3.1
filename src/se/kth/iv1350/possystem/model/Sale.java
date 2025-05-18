package model;

import integration.ItemDTO;
import java.util.HashMap;
import java.util.Map;
import java.util.Collection;

public class Sale {
    private HashMap<String, SoldItem> soldItems = new HashMap<>();

    
    public void addItem(ItemDTO item) {  // Metod för 1 artikel åt gången
        SoldItem sold = soldItems.get(item.id);
        if (sold == null) {
            soldItems.put(item.id, new SoldItem(item, 1));
        } else {
            sold.increaseQuantity();
        }
    }

    
    public void addItem(ItemDTO item, int quantity) {     // Metod för flera artiklar direkt
        SoldItem sold = soldItems.get(item.id);
        if (sold == null) {
            soldItems.put(item.id, new SoldItem(item, quantity));
        } else {
            for (int i = 0; i < quantity; i++) {
                sold.increaseQuantity();
            }
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


    public Collection<SoldItem> getSoldItems() {    // Kvitto från View
        return soldItems.values();
    }
}
