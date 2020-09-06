package dojo.supermarket.model;

import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private final List<ReceiptItem> items = new ArrayList<>();
    private final List<Discount> discounts = new ArrayList<>();
    private double total;

    public Double getTotalPrice() {
        incrementTotalPrice();
        incrementDiscont();
        return total;
    }

    public void incrementDiscont(){
        discounts.forEach(discount -> total += discount.getDiscountAmount());
    }

    public void incrementTotalPrice(){
        items.forEach(item -> total += item.getTotalPrice());
    }

    public void addProduct(Product p, Item item) {
        this.items.add(new ReceiptItem(p, item));
    }

    public List<ReceiptItem> getItems() {
        return new ArrayList<>(this.items);
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }
}
