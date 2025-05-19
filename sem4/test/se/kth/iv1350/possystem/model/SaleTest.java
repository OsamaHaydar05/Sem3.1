package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testAddItemIncreasesTotal() {
        Sale sale = new Sale();
        Item item = new Item("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");
        sale.addItem(item);

        double expectedTotal = 29.90;
        double actualTotal = sale.getRunningTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "The total should be equal to the item price.");
    }
}
