package dojo.supermarket.model;

import java.util.*;

public class ShoppingCart {

    private final List<ProductQuantity> items = new ArrayList<>();
    Map<Product, Double> productQuantities = new HashMap<>();
    SupermarketCatalog catalog;

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

    void handleOffers(Receipt receipt, Map<Product, Offer> offers,SupermarketCatalog supermarketCatalog) {
        for (Product p: productQuantities().keySet()) {
            if (offers.containsKey(p)) {
                Offer offer = offers.get(p);
                Optional<SpecialOfferType> possibleSpecialOfferType = SpecialOfferType.findByCommand(offer.getOfferType());
                if(possibleSpecialOfferType.isPresent()) {
                    SpecialOfferType specialOfferType = possibleSpecialOfferType.get();
                    Discount discount = createDiscount(p, specialOfferType, offer);
                    if (discount != null)
                        receipt.addDiscount(discount);
                }
            }
        }
    }


    public Discount createDiscount(Product product, SpecialOfferType specialOfferType, Offer offer){
        CalcDiscount calcDiscount = createCalcDiscount(specialOfferType,product,productQuantities.get(product), offer.getArgument());
        String description = specialOfferType.getDescriptionPersonalize(offer.getOfferType(), offer.getArgument());

        if(SpecialOfferType.hasOfferNumber(calcDiscount.getQuantityAsint(),offer.getOfferType())){
         return new Discount(product,description,specialOfferType.execute(calcDiscount));
        }
        return null;
    }

    public CalcDiscount createCalcDiscount(SpecialOfferType specialOfferType,Product product,double quantity, double argument){
        return new CalcDiscount(quantity, catalog.getUnitPrice(product), argument, specialOfferType.getOfferNumber());
    }
}
