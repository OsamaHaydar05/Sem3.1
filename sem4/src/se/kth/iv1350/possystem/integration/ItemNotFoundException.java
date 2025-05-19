package integration;

public class ItemNotFoundException extends RuntimeException {
    public ItemNotFoundException(String itemId) {
        super("Item with ID '" + itemId + "' was not found.");
    }
}
