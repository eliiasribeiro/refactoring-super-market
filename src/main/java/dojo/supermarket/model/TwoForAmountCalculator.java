package dojo.supermarket.model;

public class TwoForAmountCalculator implements SpecialOfferCalculator {
    @Override
    public double calculatorDiscount(CalcDiscount calcDiscount) {
        double total = calcDiscount.getArgument() * calcDiscount.calcNumberOfXs() + calcDiscount.getQuantityAsint() % 2 * calcDiscount.getUnitPrice();
        return calcDiscount.getUnitPrice() * calcDiscount.getQuantity() - total;
    }
}
