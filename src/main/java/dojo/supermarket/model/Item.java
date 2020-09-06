package dojo.supermarket.model;

public class Item {

    private final double quantity;
    private final double price;

    public Item(double quantity, double price){
        this.quantity = quantity;
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    public double getTotalPrice() {
        return price * quantity;
    }
}
