package model;

import integration.ItemDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SaleTest {

    @Test
    public void testAddItemIncreasesTotal() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");
        sale.addItem(item);

        double expectedTotal = 29.90;
        double actualTotal = sale.getRunningTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "The total should be equal to the item price");
    }

    @Test
    public void testAddSameItemTwice() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");
        sale.addItem(item);
        sale.addItem(item);

        double expected = 2 * 29.90;
        assertEquals(expected, sale.getRunningTotal(), 0.01, "Total should double when same item is added twice");
    }

    @Test
    public void testAddItemWithQuantity() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, "240g");
        sale.addItem(item, 3);

        double expected = 3 * 14.90;
        assertEquals(expected, sale.getRunningTotal(), 0.01, "Total should match the quantity");
    }
}
