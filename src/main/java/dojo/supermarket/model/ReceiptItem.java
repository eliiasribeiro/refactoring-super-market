package dojo.supermarket.model;

import java.util.Objects;

public class ReceiptItem {
    private final Product product;
    private final Item item;

    ReceiptItem(Product p, Item item) {
        this.product = p;
        this.item = item;
    }

    public double getPrice() {
        return this.item.getPrice();
    }

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return item.getQuantity();
    }

    public double getTotalPrice() {
        return item.getTotalPrice();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItem that = (ReceiptItem) o;
        return Objects.equals(product, that.product) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, item);
    }
}
