package dojo.supermarket.model;

public class ThreeForTwoDiscountCalculator implements SpecialOfferCalculator {


    public ThreeForTwoDiscountCalculator(){}

    @Override
    public double calculatorDiscount(CalcDiscount calcDiscount) {
        int x = calcDiscount.calcNumberOfXs();
        return calcDiscount.getQuantity() * calcDiscount.getUnitPrice() - ((x * 2 * calcDiscount.getUnitPrice()) + calcDiscount.getQuantityAsint() % 3 * calcDiscount.getUnitPrice());
    }
}
