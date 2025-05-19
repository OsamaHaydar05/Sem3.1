package model;

import integration.ItemDTO;

public class SoldItem {
    public final ItemDTO item;
    private int quantity;

    public SoldItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public void increaseQuantity() {
        quantity++;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return item.price * quantity;
    }

    public double getTotalVAT() {
        return item.price * item.vatRate * quantity;
    }

    public String toString() {
        return String.format("%s %d x %.2f %.2f SEK", item.name, quantity, item.price, getTotalPrice());
    }
} 
