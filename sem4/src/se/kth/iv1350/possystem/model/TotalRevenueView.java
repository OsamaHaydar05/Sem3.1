package model;

public class TotalRevenueView implements RevenueObserver {
    private double totalRevenue = 0;

    @Override
    public void newRevenue(double amount) {
        totalRevenue += amount;
        System.out.println("🧾 Total revenue is now: " + String.format("%.2f", totalRevenue) + " SEK");
    }
}
