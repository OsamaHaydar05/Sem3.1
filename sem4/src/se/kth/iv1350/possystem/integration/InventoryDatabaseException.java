package integration;

public class InventoryDatabaseException extends RuntimeException {
    public InventoryDatabaseException() {
        super("Inventory system is currently unavailable.");
    }
}
