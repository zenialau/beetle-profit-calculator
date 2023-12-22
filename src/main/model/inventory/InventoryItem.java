package src.main.model.inventory;

import java.util.Objects;

// represents an inventory item that is bought or sold
public class InventoryItem {
    private String name;
    private String description; // 4 options: unmounted, mounted, framed, customized
    private double size; // in mm
    private String quality; // 3 options: A1, A-, B
    private String comment;
    private double price; // in CAD

    // EFFECTS: constructs an inventory item with inputted values
    public InventoryItem(String name, String description, double size, String quality,
                         String comment, double price)
    {
        this.name = name;
        this.description = description;
        this.size = size;
        this.quality = quality;
        this.comment = comment;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getSize() {
        return size;
    }

    public String getQuality() {
        return quality;
    }

    public String getComment() {
        return comment;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InventoryItem that = (InventoryItem) o;
        return Double.compare(size, that.size) == 0 && Double.compare(price, that.price) == 0 && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(quality, that.quality) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, size, quality, comment, price);
    }
}
