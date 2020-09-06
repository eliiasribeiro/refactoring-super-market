package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();
    private Discount discount;
    private int x = 1;
    private int quantityAsInt;



    List<ProductQuantity> getItems() {
        return new ArrayList<>(items);
    }

    void addItem(Product product) {
        this.addItemQuantity(product, 1.0);
    }

    Map<Product, Double> productQuantities() {
        return productQuantities;
    }


    public void addItemQuantity(Product product, double quantity) {
        items.add(new ProductQuantity(product, quantity));
        if (productQuantities.containsKey(product)) {
            productQuantities.put(product, productQuantities.get(product) + quantity);
        } else {
            productQuantities.put(product, quantity);
        }
    }

    void handleOffers(Receipt receipt, Map<Product, Offer> offers, SupermarketCatalog catalog) {
        for (Product p: productQuantities().keySet()) {
            double quantity = productQuantities.get(p);
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                double unitPrice = catalog.getUnitPrice(p);
               this.quantityAsInt = (int) quantity;

                Optional<SpecialOfferType> possibleSpecialOfferType = SpecialOfferType.findByCommand(offer.getOfferType());

                if(possibleSpecialOfferType.isPresent()){
                    this.x = possibleSpecialOfferType.get().getOfferNumber();
                    if(quantityAsInt >= 2){
                        discount = new Discount(p, "2 for " + offer.getArgument(), -calcDiscountN(quantity,unitPrice,offer.getArgument()));
                    }
                }
                if (offer.getOfferType() == SpecialOfferType.ThreeForTwo && quantityAsInt > 2) {
                    discount = new Discount(p, "3 for 2", -calcDiscountAmount(quantity,unitPrice));
                }
                if (offer.getOfferType() == SpecialOfferType.TenPercentDiscount) {
                    discount = new Discount(p, offer.getArgument() + "% off", calcDiscountBla(quantity,unitPrice,offer.getArgument()));
                }
                if (offer.getOfferType() == SpecialOfferType.FiveForAmount && quantityAsInt >= 5) {
                    double discountTotal = calcDiscountTotal(quantity,unitPrice,offer.getArgument());
                    discount = new Discount(p, x + " for " + offer.getArgument(), -discountTotal);
                }
                if (discount != null)
                    receipt.addDiscount(discount);
            }

        }
    }

    private double calcDiscountN(double quantity, double unitPrice, double argument){
        double total = argument * calcNumberOfXs() + quantityAsInt % 2 * unitPrice;
        return unitPrice * quantity - total;
    }

    private double calcDiscountBla(double quantity, double unitPrice, double argument){
        return -quantity * unitPrice * argument / 100.0;

    }

    private double calcDiscountTotal(double quantity, double unitPrice, double argument){
        int calcNumberOfXs = calcNumberOfXs();
        return unitPrice * quantity - (argument * calcNumberOfXs + quantityAsInt % 5 * unitPrice);
    }

    private double calcDiscountAmount(double quantity, double unitPrice){
        int calcNumberOfXs = calcNumberOfXs();
        return quantity * unitPrice - ((calcNumberOfXs * 2 * unitPrice) + quantityAsInt % 3 * unitPrice);
    }

    public int calcNumberOfXs(){
        return this.quantityAsInt / x;
    }

}
