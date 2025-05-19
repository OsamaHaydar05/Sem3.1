package model;

import java.io.FileWriter;
import java.io.IOException;

public class TotalRevenueFileOutput implements RevenueObserver {
    private static final String FILE_NAME = "total-revenue.txt";
    private double totalRevenue = 0;

    @Override
    public void newRevenue(double amount) {
        totalRevenue += amount;
        try (FileWriter writer = new FileWriter(FILE_NAME, true)) { // true = append-läge
            writer.write("Total revenue is now: " + String.format("%.2f", totalRevenue) + " SEK\n");
        } catch (IOException e) {
            System.out.println("ERROR writing to file: " + e.getMessage());
        }
    }
}
