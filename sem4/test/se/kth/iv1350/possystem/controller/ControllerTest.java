package controller;

import integration.ItemDTO;
import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;
import model.Sale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Controller class. Ensures correct behavior during sale initialization,
 * item registration, and error handling for invalid item scenarios.
 */
public class ControllerTest {

    /**
     * Tests that a new Sale object is created when startNewSale() is called.
     */
    @Test
    public void testStartSaleCreatesSaleObject() {
        Controller controller = new Controller();
        controller.startNewSale();

        Sale sale = controller.getCurrentSale();
        assertNotNull(sale, "Controller should create a Sale object when startNewSale is called.");
    }

    /**
     * Tests that an ItemNotFoundException is thrown when an invalid item ID is used.
     */
    @Test
    public void testItemNotFoundThrowsException() {
        Controller controller = new Controller();
        controller.startNewSale();

        assertThrows(ItemNotFoundException.class, () -> {
            controller.registerItem("nonexistentId");
        });
    }

    /**
     * Tests that an InventoryDatabaseException is thrown when the item ID "dbError" is used,
     * simulating a database access failure.
     */
    @Test
    public void testInventoryDatabaseExceptionIsThrown() {
        Controller controller = new Controller();
        controller.startNewSale();

        assertThrows(InventoryDatabaseException.class, () -> {
            controller.registerItem("dbError"); // Simulates database failure
        });
    }

    /**
     * Tests that an item can be registered with a specified quantity.
     * Also checks that the returned item is valid and matches the input ID.
     *
     * @throws ItemNotFoundException if the item ID is invalid (not expected here)
     * @throws InventoryDatabaseException if inventory access fails (not expected here)
     */
    @Test
    public void testRegisterItemWithQuantity() throws ItemNotFoundException, InventoryDatabaseException {
        Controller controller = new Controller();
        controller.startNewSale();

        ItemDTO item = controller.registerItem("abc123", 3);

        assertNotNull(item, "Item should be found and not null.");
        assertEquals("abc123", item.id, "Item ID should match.");
    }
}
