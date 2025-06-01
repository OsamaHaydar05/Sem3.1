package integration;

/**
 * Represents a data transfer object (DTO) containing information about an item.
 * Used to transfer item data between the integration layer and other parts of the system.
 */
public class ItemDTO {
    /**
     * The unique identifier for the item.
     */
    public final String id;

    /**
     * The name of the item.
     */
    public final String name;

    /**
     * The price of the item excluding VAT.
     */
    public final double price;

    /**
     * The VAT rate for the item.
     */
    public final double vatRate;

    /**
     * A description of the item.
     */
    public final String description;

    /**
     * Creates a new instance of ItemDTO with specified item details.
     *
     * @param id The unique identifier for the item.
     * @param name The name of the item.
     * @param price The price of the item excluding VAT.
     * @param vatRate The VAT rate applied to the item.
     * @param description A description of the item.
     */
    public ItemDTO(String id, String name, double price, double vatRate, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.vatRate = vatRate;
        this.description = description;
    }
}
