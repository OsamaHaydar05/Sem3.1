package view;

import controller.Controller;
import integration.ItemDTO;
import model.Receipt;
import model.SoldItem;


public class View {
    private Controller controller;

    public View(Controller controller) {
        this.controller = controller;
    }

    public void run() {
        controller.startNewSale();

        
        System.out.println("Add 1 item with item id abc123:"); // En vara skannas två gånger
        ItemDTO item1 = controller.registerItem("abc123");
        printItemDetails(item1);
        printRunningTotal();

        System.out.println("\nAdd 1 more item with item id abc123:");
        ItemDTO item2 = controller.registerItem("abc123");
        printItemDetails(item2);
        printRunningTotal();

        
        System.out.println("\nAdd 3 items with item id def456:");   // Registrera 3 st av samma vara direkt
        ItemDTO item3 = controller.registerItem("def456", 3);
        printItemDetails(item3);
        printRunningTotal();

        System.out.println("\nEnd sale:");
        Receipt receipt = controller.endSale(200.0); // Tex betalar 200kr

        
        System.out.println("\n🧾 Receipt:");
        for (SoldItem soldItem : receipt.getSoldItems()) {  //kriv ut varje kvitto rad med korrekt format
            System.out.println(
                soldItem.getItem().name + "  " +
                soldItem.getQuantity() + " x " + String.format("%.2f", soldItem.getItem().price) + " SEK" +
                " = " + String.format("%.2f", soldItem.getTotalPrice()) + " SEK"
            );
        }

    
        System.out.println("Total: " + String.format("%.2f", receipt.getTotalPrice()) + " SEK"); // Totalsumma
        System.out.println("Total VAT: " + String.format("%.2f", receipt.getTotalVAT()) + " SEK");
        System.out.println("Change: " + String.format("%.2f", receipt.getChange()) + " SEK");
    }

    private void printItemDetails(ItemDTO item) {
        System.out.println("Item ID: " + item.id);
        System.out.println("Item name: " + item.name);
        System.out.println("Item cost: " + String.format("%.2f", item.price) + " SEK");
        System.out.println("VAT: " + (int)(item.vatRate * 100) + "%");
        System.out.println("Item description: " + item.description);
    }

    private void printRunningTotal() {
        System.out.println("\nTotal cost (incl VAT): " + String.format("%.2f", controller.getRunningTotal()) + " SEK");
        System.out.println("Total VAT: " + String.format("%.2f", controller.getTotalVAT()) + " SEK");
    }
}
