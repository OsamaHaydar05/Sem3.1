package view;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;

public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        controller.startNewSale();

        System.out.println("Add 1 item with item id abc123:");
        ItemDTO item1 = controller.registerItem("abc123");
        printItemDetails(item1);
        printRunningTotal(controller);

        System.out.println("\nAdd 1 item with item id abc123:");
        ItemDTO item2 = controller.registerItem("abc123");
        printItemDetails(item2);
        printRunningTotal(controller);

        System.out.println("\nAdd 1 item with item id def456:");
        ItemDTO item3 = controller.registerItem("def456");
        printItemDetails(item3);
        printRunningTotal(controller);

        System.out.println("\nEnd sale:");
        Receipt receipt = controller.endSale(100.0);
        System.out.println(receipt);
        System.out.println("Change to give the customer: " + String.format("%.2f", receipt.getChange()) + " SEK");
    }

    private void printItemDetails(ItemDTO item) {
        System.out.println("Item ID: " + item.id);
        System.out.println("Item name: " + item.name);
        System.out.println("Item cost: " + String.format("%.2f", item.price) + " SEK");
        System.out.println("VAT: " + (int)(item.vatRate * 100) + "%");
        System.out.println("Item description: " + item.description);
    }

    private void printRunningTotal(Controller controller) {
        System.out.println("\nTotal cost (incl VAT): " + String.format("%.2f", controller.getRunningTotal()) + " SEK");
        System.out.println("Total VAT: " + String.format("%.2f", controller.getTotalVAT()) + " SEK");
    }
}
