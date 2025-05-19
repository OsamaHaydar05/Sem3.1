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

    public Controller() {
        inventory = new ExternalInventorySystem();
    }

    public void startNewSale() {
        currentSale = new Sale();
    }

    public ItemDTO registerItem(String itemId)
        throws ItemNotFoundException, InventoryDatabaseException {
    ItemDTO item = inventory.getItem(itemId);
    currentSale.addItem(item);
    return item;
}

    public Receipt endSale(double amountPaid) {
        return currentSale.finalizeSale(amountPaid);
    }

    public double getRunningTotal() {
        return currentSale.getRunningTotal();
    }

    public double getTotalVAT() {
        return currentSale.getTotalVAT();
    }

    public Sale getCurrentSale() {
    return this.currentSale;
}


} 
