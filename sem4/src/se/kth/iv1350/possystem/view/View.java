package view;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;
import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;
import integration.TotalRevenueView;
import integration.TotalRevenueFileOutput;

/**
 * Handles user interaction by simulating the view in a point-of-sale system.
 * It communicates with the controller to do actions and display results.
 */
public class View {
    private Controller controller;

    /**
     * Creates a new instance of the view and initializes the sale process.
     * Also registers revenue observers for logging and displaying total revenue.
     *
     * @param controller The controller that coordinates system operations.
     */
    public View(Controller controller) {
        this.controller = controller;
        controller.startNewSale();
        controller.addRevenueObserver(new TotalRevenueView());
        controller.addRevenueObserver(new TotalRevenueFileOutput());

        System.out.println("Add 1 item with item id abc123:");
        try {
            ItemDTO item1 = controller.registerItem("abc123");
            printItemDetails(item1);
            printRunningTotal(controller);
        } catch (ItemNotFoundException e) {
            System.out.println("ERROR: The item ID you entered was not found.");
        } catch (InventoryDatabaseException e) {
            System.out.println("ERROR: Could not access the inventory system. Please try again later.");
        }

        System.out.println("\nAdd 1 item with item id abc123:");
        try {
            ItemDTO item2 = controller.registerItem("abc123");
            printItemDetails(item2);
            printRunningTotal(controller);
        } catch (ItemNotFoundException e) {
            System.out.println("ERROR: The item ID you entered was not found.");
        } catch (InventoryDatabaseException e) {
            System.out.println("ERROR: Could not access the inventory system. Please try again later.");
        }

        System.out.println("\nAdd 1 item with item id def456:");
        try {
            ItemDTO item3 = controller.registerItem("def456", 3);
            printItemDetails(item3);
            printRunningTotal(controller);
        } catch (ItemNotFoundException e) {
            System.out.println("ERROR: The item ID you entered was not found.");
        } catch (InventoryDatabaseException e) {
            System.out.println("ERROR: Could not access the inventory system. Please try again later.");
        }

        System.out.println("\nEnd sale:");
        Receipt receipt = controller.endSale(200.0); // Amount paid by customer
        System.out.println(receipt);
        System.out.println("Change to give the customer: " + String.format("%.2f", receipt.getChange()) + " SEK");
    }

    /**
     * Prints the details of a registered item to the console.
     *
     * @param item The item whose details are to be displayed.
     */
    private void printItemDetails(ItemDTO item) {
        System.out.println("Item ID: " + item.id);
        System.out.println("Item name: " + item.name);
        System.out.println("Item cost: " + String.format("%.2f", item.price) + " SEK");
        System.out.println("VAT: " + (int)(item.vatRate * 100) + "%");
        System.out.println("Item description: " + item.description);
    }

    /**
     * Prints the current running total and total VAT to the console.
     *
     * @param controller The controller used to fetch running total values.
     */
    private void printRunningTotal(Controller controller) {
        System.out.println("\nTotal cost (incl VAT): " + String.format("%.2f", controller.getRunningTotal()) + " SEK");
        System.out.println("Total VAT: " + String.format("%.2f", controller.getTotalVAT()) + " SEK");
    }
}
