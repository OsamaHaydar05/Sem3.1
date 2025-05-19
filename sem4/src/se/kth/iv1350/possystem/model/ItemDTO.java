package integration;

public class ItemDTO {
    public final String id;
    public final String name;
    public final double price;
    public final double vatRate;
    public final String description;

    public ItemDTO(String id, String name, double price, double vatRate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
    }
} 