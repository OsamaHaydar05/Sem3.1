package model;

import integration.ItemDTO;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class Sale {
    private HashMap<String, SoldItem> soldItems = new HashMap<>();
    private List<RevenueObserver> observers = new ArrayList<>();

    public void addItem(ItemDTO item) {
        SoldItem sold = soldItems.get(item.id);
        if (sold == null) {
            soldItems.put(item.id, new SoldItem(item, 1));
        } else {
            sold.increaseQuantity();
        }
    }

    public void addItem(ItemDTO item, int quantity) {
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
        notifyObservers();
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

public void addRevenueObserver(RevenueObserver observer) {
    observers.add(observer);
}

private void notifyObservers() {
    for (RevenueObserver observer : observers) {
        observer.newRevenue(getRunningTotal());
    }
}

} 
