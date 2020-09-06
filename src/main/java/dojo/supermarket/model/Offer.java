package dojo.supermarket.model;

public class Offer {
    private final SpecialOfferType offerType;
    private final Product product;
    private final double argument;

    public Offer(SpecialOfferType offerType, Product product, double argument) {
        this.offerType = offerType;
        this.argument = argument;
        this.product = product;
    }

    public SpecialOfferType getOfferType() {
        return offerType;
    }

    public double getArgument() {
        return argument;
    }

    public Product getProduct() {
        return product;
    }
}
