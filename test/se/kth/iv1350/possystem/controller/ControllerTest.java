package controller;

import integration.ItemDTO;
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
    public void testRegisterItemIncreasesTotal() {
        Controller controller = new Controller();
        controller.startNewSale();

        controller.registerItem("abc123");
        assertTrue(controller.getRunningTotal() > 0, "Total increase after adding an item");
    }

    @Test
    public void testRegisterItemWithQuantity() {
        Controller controller = new Controller();
        controller.startNewSale();

        controller.registerItem("def456", 3);
        assertTrue(controller.getRunningTotal() >= 3 * 14.90, "Total should increase according to quantity");
    }
}
