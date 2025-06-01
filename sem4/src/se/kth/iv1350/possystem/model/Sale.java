package model;

import integration.ItemDTO;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents a single sale, containing sold items and logic for calculating total price and VAT.
 * Also supports observers for revenue updates.
 */
public class Sale {
    private HashMap<String, SoldItem> soldItems = new HashMap<>();
    private List<RevenueObserver> observers = new ArrayList<>();

    /**
     * Registers a single item in the sale. If the item was already added, its quantity is increased.
     *
     * @param item The item to be registered in the sale.
     */
    public void addItem(ItemDTO item) {
        SoldItem sold = soldItems.get(item.id);
        if (sold == null) {
            soldItems.put(item.id, new SoldItem(item, 1));
        } else {
            sold.increaseQuantity();
        }
    }

    /**
     * Registers a specified quantity of an item in the sale. If the item was already added, its quantity is increased accordingly.
     *
     * @param item The item to be registered.
     * @param quantity The number of units of the item to register.
     */
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

    /**
     * Finalizes the sale, notifies observers, and returns a receipt.
     *
     * @param amountPaid The amount of money paid by the customer.
     * @return A Receipt object containing details of the completed sale.
     */
    public Receipt finalizeSale(double amountPaid) {
        notifyObservers();
        return new Receipt(soldItems, amountPaid);
    }

    /**
     * Calculates the running total price of all sold items including VAT.
     *
     * @return The total price of the sale including VAT.
     */
    public double getRunningTotal() {
        return soldItems.values().stream()
            .mapToDouble(SoldItem::getTotalPrice)
            .sum();
    }

    /**
     * Calculates the total VAT of the current sale.
     *
     * @return The total VAT for all sold items.
     */
    public double getTotalVAT() {
        return soldItems.values().stream()
            .mapToDouble(SoldItem::getTotalVAT)
            .sum();
    }

    /**
     * Adds a revenue observer that will be notified when the sale is finalized.
     *
     * @param observer The observer to be notified.
     */
    public void addRevenueObserver(RevenueObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies all registered revenue observers about the final revenue.
     */
    private void notifyObservers() {
        for (RevenueObserver observer : observers) {
            observer.newRevenue(getRunningTotal());
        }
    }
}
