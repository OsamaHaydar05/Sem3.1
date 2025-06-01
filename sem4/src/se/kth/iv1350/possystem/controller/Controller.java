package controller;

import integration.ExternalInventorySystem;
import integration.ItemDTO;
import model.Sale;
import model.Receipt;
import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;

public class Controller {
    private Sale currentSale;
    private ExternalInventorySystem inventory;

    /**
     * Creates a new instance of the controller and initializes the external inventory system.
     */
    public Controller() {
        inventory = new ExternalInventorySystem();
    }

    /**
     * Starts a new sale by creating a new Sale object.
     */
    public void startNewSale() {
        currentSale = new Sale();
    }

    /**
     * Registers a single item by its identifier.
     *
     * @param itemId The identifier of the item to register.
     * @return An ItemDTO containing information about the registered item.
     * @throws ItemNotFoundException If the item ID does not exist in the inventory.
     * @throws InventoryDatabaseException If the inventory system fails to respond.
     */
    public ItemDTO registerItem(String itemId)
            throws ItemNotFoundException, InventoryDatabaseException {
        ItemDTO item = inventory.getItem(itemId);
        currentSale.addItem(item);
        return item;
    }

    /**
     * Registers an item and a specific quantity.
     *
     * @param itemId The identifier of the item to register.
     * @param quantity The quantity of the item to register.
     * @return An ItemDTO containing information about the registered item.
     * @throws ItemNotFoundException If the item ID does not exist in the inventory.
     * @throws InventoryDatabaseException If the inventory system fails to respond.
     */
    public ItemDTO registerItem(String itemId, int quantity)
            throws ItemNotFoundException, InventoryDatabaseException {
        ItemDTO item = inventory.getItem(itemId);
        currentSale.addItem(item, quantity);
        return item;
    }

    /**
     * Finalizes the current sale, creates a receipt, and returns it.
     *
     * @param amountPaid The amount of money the customer paid.
     * @return A Receipt object containing details of the completed sale.
     */
    public Receipt endSale(double amountPaid) {
        return currentSale.finalizeSale(amountPaid);
    }

    /**
     * Returns the running total of the current sale, including VAT.
     *
     * @return The total cost including VAT.
     */
    public double getRunningTotal() {
        return currentSale.getRunningTotal();
    }

    /**
     * Returns the total VAT for the current sale.
     *
     * @return The total VAT.
     */
    public double getTotalVAT() {
        return currentSale.getTotalVAT();
    }

    /**
     * Returns the current Sale object.
     *
     * @return The ongoing Sale.
     */
    public Sale getCurrentSale() {
        return this.currentSale;
    }

    /**
     * Adds a revenue observer to be notified when a sale is completed.
     *
     * @param observer The observer to be added.
     */
    public void addRevenueObserver(RevenueObserver observer) {
        if (currentSale != null) {
            currentSale.addRevenueObserver(observer);
        }
    }
}
