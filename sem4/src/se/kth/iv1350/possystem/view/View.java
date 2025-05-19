package view;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;
import integration.ItemNotFoundException;
import integration.InventoryDatabaseException;

public class View {
    private Controller controller;
    
    public View(Controller controller) {
        this.controller = controller;
        controller.startNewSale();
        controller.addRevenueObserver(new model.TotalRevenueView()); 
        controller.addRevenueObserver(new model.TotalRevenueFileOutput());


        System.out.println("Add 1 item with item id abc123:");
        try {
        ItemDTO item1 = controller.registerItem("abc123");
        printItemDetails(item1);
        printRunningTotal(controller);
} catch (ItemNotFoundException | InventoryDatabaseException e) {
    System.out.println("ERROR: " + e.getMessage());
}
        
        System.out.println("\nAdd 1 item with item id abc123:");
        try {
        ItemDTO item2 = controller.registerItem("abc123");
        printItemDetails(item2);
        printRunningTotal(controller);
} catch (ItemNotFoundException | InventoryDatabaseException e) {
    System.out.println("ERROR: " + e.getMessage());
}
        
        System.out.println("\nAdd 1 item with item id def456:");
        try {
        ItemDTO item3 = controller.registerItem("def456", 3);
        printItemDetails(item3);
        printRunningTotal(controller);
} catch (ItemNotFoundException | InventoryDatabaseException e) {
    System.out.println("ERROR: " + e.getMessage());
}
        
        System.out.println("\nEnd sale:");
        Receipt receipt = controller.endSale(200.0); // Det kunden betalar
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
