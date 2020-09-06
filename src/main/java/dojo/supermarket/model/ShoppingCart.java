package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();
    private int x = 1;
    private int quantityAsInt;
    private double quantity;
    private double unitPrice;



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
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                Optional<SpecialOfferType> possibleSpecialOfferType = SpecialOfferType.findByCommand(offer.getOfferType());
                if(possibleSpecialOfferType.isPresent()) {
                    SpecialOfferType specialOfferType = possibleSpecialOfferType.get();
                    setQuantities(p,catalog);
                    setX(specialOfferType);
                    Discount discount = createDiscount(p, specialOfferType, offer);
                    if (discount != null)
                        receipt.addDiscount(discount);
                }
            }
        }
    }

    public void setQuantities(Product product, SupermarketCatalog catalog){
        this.unitPrice = catalog.getUnitPrice(product);
        this.quantity = productQuantities.get(product);
        this.quantityAsInt = (int) this.quantity;
    }

    public void setX(SpecialOfferType specialOfferType){
        this.x = specialOfferType.getOfferNumber();
    }

    public Discount createDiscount(Product product, SpecialOfferType specialOfferType, Offer offer){
        CalcDiscount calcDiscount = createCalcDiscount(quantity, offer.getArgument());
        String description = specialOfferType.getDescriptionPersonalize(offer.getOfferType(), offer.getArgument());

        if(SpecialOfferType.hasOfferNumber(quantityAsInt,offer.getOfferType())){
         return new Discount(product,description,specialOfferType.execute(calcDiscount));
        }
        return null;
    }

    public CalcDiscount createCalcDiscount(double quantity, double argument){
        return new CalcDiscount(quantity, unitPrice, argument, this.x, quantityAsInt);
    }
}
