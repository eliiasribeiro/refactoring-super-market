package dojo.supermarket.model;

public class FiveForAmountCalculator implements SpecialOfferCalculator {
    @Override
    public double calculatorDiscount(CalcDiscount calcDiscount) {
       int calcNumberOfX = calcDiscount.calcNumberOfXs();
       return calcDiscount.getUnitPrice() * calcDiscount.getQuantity() -(calcDiscount.getArgument() * calcNumberOfX + calcDiscount.getQuantity() % 5 * calcDiscount.getUnitPrice());
    }
}
