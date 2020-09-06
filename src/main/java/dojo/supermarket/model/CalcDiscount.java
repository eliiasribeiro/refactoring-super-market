package dojo.supermarket.model;

public class CalcDiscount {
    private final double quantity;
    private final double unitPrice;
    private double argument;

    public CalcDiscount(double quantity, double unitPrice, double argument) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.argument = argument;
    }

    public CalcDiscount(double quantity, double unitPrice) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getArgument() {
        return argument;
    }
}
