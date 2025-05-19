package controller;

import integration.ItemDTO;
import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;
import model.Sale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testStartSaleCreatesSaleObject() {
        Controller controller = new Controller();
        controller.startNewSale();

        Sale sale = controller.getCurrentSale();
        assertNotNull(sale, "Controller should create a Sale object when startNewSale is called.");
    }

    @Test
    public void testItemNotFoundThrowsException() {
        Controller controller = new Controller();
        controller.startNewSale();

        assertThrows(ItemNotFoundException.class, () -> {
            controller.registerItem("nonexistentId");
        });
    }

    @Test
    public void testInventoryDatabaseExceptionIsThrown() {
        Controller controller = new Controller();
        controller.startNewSale();

        assertThrows(InventoryDatabaseException.class, () -> {
            controller.registerItem("dbError"); // ska simulera fel
        });
    }

    @Test
    public void testRegisterItemWithQuantity() throws ItemNotFoundException, InventoryDatabaseException {
        Controller controller = new Controller();
        controller.startNewSale();

        ItemDTO item = controller.registerItem("abc123", 3);

        assertNotNull(item, "Item should be found and not null.");
        assertEquals("abc123", item.id, "Item ID should match.");
    }
}
