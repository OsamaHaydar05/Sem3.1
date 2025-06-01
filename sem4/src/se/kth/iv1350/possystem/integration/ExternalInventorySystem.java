package integration;

import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;
import java.util.HashMap;

/**
 * A external inventory system. Used to look up item information
 * based on a uniqu item id
 */
public class ExternalInventorySystem {
    private HashMap<String, ItemDTO> items = new HashMap<>();

    /**
     * Creates a new instance of the inventory system and initializes it with example items.
     */
    public ExternalInventorySystem() {
        items.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, 
            "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free"));
        items.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, 
            "YouGoGo Blueberry 240 g, low sugar yoghurt, blueberry flavour"));
    }

    /**
     * Searches for an item in the inventory using its id.
     *
     * @param itemId The unique identifier of the item.
     * @return An ItemDTO containing information about the item.
     * @throws InventoryDatabaseException If a database access error occurs.
     * @throws ItemNotFoundException If no item with the given ID is found.
     */
    public ItemDTO getItem(String itemId) {
        if ("db_error".equals(itemId)) {
            throw new InventoryDatabaseException();
        }
        ItemDTO item = items.get(itemId);
        if (item == null) {
            throw new ItemNotFoundException(itemId);
        }
        return item;
    }
}
