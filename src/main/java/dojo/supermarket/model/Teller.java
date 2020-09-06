package dojo.supermarket.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Teller {

    private final SupermarketCatalog catalog;
    private final Map<Product, Offer> offers = new HashMap<>();
    private final Receipt receipt = new Receipt();

    public Teller(SupermarketCatalog catalog) {
        this.catalog = catalog;
    }

    public void addSpecialOffer(SpecialOfferType offerType, Product product, double argument) {
        this.offers.put(product, new Offer(offerType, product, argument));
    }

    public Receipt checksOutArticlesFrom(ShoppingCart theCart) {
        addItemsInReceipt(theCart);
        theCart.handleOffers(receipt, this.offers, this.catalog);
        return receipt;
    }

    public void addItemsInReceipt(ShoppingCart theCart){
        List<ProductQuantity> productQuantities = theCart.getItems();
        for (ProductQuantity productQuantity: productQuantities) {
            Product product = productQuantity.getProduct();
            receipt.addProduct(product, new Item(productQuantity.getQuantity(),this.catalog.getUnitPrice(product)));
        }
    }

}
