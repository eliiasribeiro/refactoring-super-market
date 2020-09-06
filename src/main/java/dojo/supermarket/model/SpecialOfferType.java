package dojo.supermarket.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum SpecialOfferType  {

    ThreeForTwo(3,"3 for 2", new ThreeForTwoDiscountCalculator()),
    TenPercentDiscount(10,"% off", new TenPercentDiscountCalculator()),
    TwoForAmount(2, "2 for ",new TwoForAmountCalculator()),
    FiveForAmount(5," for ", new FiveForAmountCalculator());

    private final int offerNumber;
    private final SpecialOfferCalculator specialOfferCalculator;
    private final String description;

    SpecialOfferType(int offerNumber, String description,SpecialOfferCalculator specialOfferCalculator) {
        this.offerNumber = offerNumber;
        this.description = description;
        this.specialOfferCalculator = specialOfferCalculator;
    }

    public int getOfferNumber() {
        return offerNumber;
    }

    public static Optional<SpecialOfferType> findByCommand(SpecialOfferType type){
        return Stream.of(SpecialOfferType.values()).filter(type::equals).findFirst();
    }

    public static boolean hasOfferNumber(int quantityAsInt, SpecialOfferType specialOfferType){
        Optional<SpecialOfferType> possibleSpecialOfferType = Stream.of(SpecialOfferType.values())
                .filter(specialOfferType::equals)
                .filter(quantity -> quantity.getOfferNumber() == quantityAsInt)
                .findFirst();
        return possibleSpecialOfferType.isPresent();
    }

    public double execute(CalcDiscount calcDiscount){
        return specialOfferCalculator.calculatorDiscount(calcDiscount);
    }

    public String getDescription() {
        return description;
    }

    public String getDescriptionPersonalize(SpecialOfferType offerType, double argument) {
        if(offerType == SpecialOfferType.FiveForAmount){
            return offerType.getDescription() + argument;
        }
        if(offerType == SpecialOfferType.TenPercentDiscount){
            return argument + offerType.getDescription();
        }
        else {
            return offerType.getDescription();
        }
    }
}
