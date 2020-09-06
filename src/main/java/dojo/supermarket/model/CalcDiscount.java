package dojo.supermarket.model;

public class CalcDiscount {

    private final double quantity;
    private final double unitPrice;
    private double argument;
    private int x = 1;
    private int quantityAsint;

    public CalcDiscount(double quantity, double unitPrice, double argument) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.argument = argument;
    }

    public CalcDiscount(double quantity, double unitPrice, double argument,int x) {
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.argument = argument;
        this.x = x;
        this.quantityAsint = (int)quantity;
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

    public int getX() {
        return x;
    }

    public int getQuantityAsint() {
        return quantityAsint;
    }

    public int calcNumberOfXs(){
        return getQuantityAsint() / getX();
    }

}
