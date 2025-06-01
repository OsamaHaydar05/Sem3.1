package model;

import integration.ItemDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the Sale class. Verifies that items can be added,
 * quantities are handled correctly, and observers are notified properly.
 */
public class SaleTest {

    /**
     * Tests that adding one item updates the running total to match the item's price.
     */
    @Test
    public void testAddItemIncreasesTotal() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");
        sale.addItem(item);

        double expectedTotal = 29.90;
        double actualTotal = sale.getRunningTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "Total should equal item price.");
    }

    /**
     * Tests that adding the same item twice increases the total to match the correct quantity.
     */
    @Test
    public void testAddSameItemMultipleTimes() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "BigWheel Oatmeal", 29.90, 0.06, "500g");

        sale.addItem(item);
        sale.addItem(item); // Should increase quantity to 2

        double expectedTotal = 2 * 29.90;
        double actualTotal = sale.getRunningTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "Total should reflect multiple of same item.");
    }

    /**
     * Tests that adding an item with a specified quantity results in the correct total cost.
     */
    @Test
    public void testAddItemWithQuantity() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("def456", "Yoghurt", 14.90, 0.06, "240g blueberry");

        sale.addItem(item, 3); // 3 × 14.90

        double expectedTotal = 3 * 14.90;
        double actualTotal = sale.getRunningTotal();

        assertEquals(expectedTotal, actualTotal, 0.01, "Total should reflect quantity added.");
    }

    /**
     * A fake observer used to test the observer notification functionality.
     */
    class FakeObserver implements RevenueObserver {
        boolean wasCalled = false;
        double receivedAmount = 0;

        /**
         * Simulates receiving a revenue update.
         * @param amount The revenue amount passed by the Sale.
         */
        public void newRevenue(double amount) {
            wasCalled = true;
            receivedAmount = amount;
        }
    }

    /**
     * Tests that the revenue observer is correctly notified with the running total.
     */
    @Test
    public void testRevenueObserverIsNotified() {
        Sale sale = new Sale();
        ItemDTO item = new ItemDTO("abc123", "TestItem", 50.0, 0.06, "test");
        sale.addItem(item);

        FakeObserver observer = new FakeObserver();
        sale.addRevenueObserver(observer);

        sale.finalizeSale(200.0);

        assertTrue(observer.wasCalled, "Observer should be notified.");
        assertEquals(50.0, observer.receivedAmount, 0.01, "Observer should get the correct revenue amount.");
    }
}
