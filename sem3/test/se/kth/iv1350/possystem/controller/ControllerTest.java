package controller;

import model.Sale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ControllerTest {

    @Test
    public void testStartSaleCreatesSaleObject() {
        Controller controller = new Controller();
        controller.startNewSale();

        Sale sale = controller.getCurrentSale();
        assertNotNull(sale);
    }

    @Test
    public void testRegisterItemIncreasesTotal() {
        Controller controller = new Controller();
        controller.startNewSale();

        controller.registerItem("abc123");
        assertTrue(controller.getRunningTotal() > 0);
    }

    @Test
    public void testRegisterItemWithQuantityIncreasesCorrectly() {
        Controller controller = new Controller();
        controller.startNewSale();

        controller.registerItem("def456", 3);
        assertTrue(controller.getRunningTotal() >= 3 * 14.90);
    }
}
