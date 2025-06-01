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

        assertEquals(29.90, sale.getRunningTotal(), 0.01);
    }

    @Test
    public void testAddSameItemTwiceIncreasesQuantity() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");
        sale.addItem(item);
        sale.addItem(item);

        assertEquals(59.80, sale.getRunningTotal(), 0.01);
    }

    @Test
    public void testAddItemWithQuantity() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("def456", "YouGoGo Blueberry", 14.90, 0.06, "240g");
        sale.addItem(item, 3);

        assertEquals(3 * 14.90, sale.getRunningTotal(), 0.01);
    }
}
