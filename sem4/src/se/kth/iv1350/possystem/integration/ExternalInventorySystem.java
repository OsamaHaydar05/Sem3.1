package integration;

import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;
import java.util.HashMap;

public class ExternalInventorySystem {
    private HashMap<String, ItemDTO> items = new HashMap<>();

    public ExternalInventorySystem() {
        items.put("abc123", new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, 
            "BigWheel Oatmeal 500 g, whole grain oats, high fiber, gluten free"));
        items.put("def456", new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, 
            "YouGoGo Blueberry 240 g, low sugar youghurt, blueberry flavour"));
    }

    public ItemDTO getItem(String itemId) {
    if ("dbError".equals(itemId)) {
        throw new InventoryDatabaseException();
    }
    ItemDTO item = items.get(itemId);
    if (item == null) {
        throw new ItemNotFoundException(itemId);
    }
    return item;
}
} 
