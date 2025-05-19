package controller;

import integration.InventorySystem;
import integration.AccountingSystem;
import integration.Printer;
import integration.ItemRegistry;
import model.Sale;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ControllerTest {

    @Test
    public void testStartSaleCreatesSaleObject() {
        Controller controller = new Controller(
            new InventorySystem(),
            new AccountingSystem(),
            new Printer(),
            new ItemRegistry()
        );

        controller.startSale();

        
        Sale sale = controller.getCurrentSale();  

        assertNotNull(sale, "Controller should create a Sale object when startSale is called.");
    }
}
