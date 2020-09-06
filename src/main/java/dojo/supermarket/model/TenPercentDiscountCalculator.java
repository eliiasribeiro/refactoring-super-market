package dojo.supermarket.model;

public class TenPercentDiscountCalculator implements SpecialOfferCalculator {
    @Override
    public double calculatorDiscount(CalcDiscount calcDiscount) {
        return -calcDiscount.getQuantity() * calcDiscount.getUnitPrice() * calcDiscount.getArgument() / 100.0;
    }
}
