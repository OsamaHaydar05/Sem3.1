package model;

import integration.ItemDTO;

/**
 * Represents an item that has been sold, including its quantity and price calculations.
 */
public class SoldItem {
    /**
     * The item data for the sold item.
     */
    public final ItemDTO item;

    private int quantity;

    /**
     * Creates a new SoldItem with the given item and quantity.
     *
     * @param item The item that was sold.
     * @param quantity The number of units sold.
     */
    public SoldItem(ItemDTO item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of this sold item by one.
     */
    public void increaseQuantity() {
        quantity++;
    }

    /**
     * Returns the quantity of this sold item.
     *
     * @return The number of units sold.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Calculates the total price for this sold item (excluding VAT).
     *
     * @return The total price.
     */
    public double getTotalPrice() {
        return item.price * quantity;
    }

    /**
     * Calculates the total VAT for this sold item.
     *
     * @return The total VAT amount.
     */
    public double getTotalVAT() {
        return item.price * item.vatRate * quantity;
    }

    /**
     * Returns a string representation of this sold item for use in a receipt.
     *
     * @return A formatted string describing the item, quantity, price per unit, and total price.
     */
    @Override
    public String toString() {
        return String.format("%s %d x %.2f %.2f SEK", item.name, quantity, item.price, getTotalPrice());
    }
}
