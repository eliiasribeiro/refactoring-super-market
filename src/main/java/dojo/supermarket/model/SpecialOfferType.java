package dojo.supermarket.model;

import java.util.Optional;
import java.util.stream.Stream;

public enum SpecialOfferType  {


    ThreeForTwo(3),
    TenPercentDiscount(10),
    TwoForAmount(2),
    FiveForAmount(5);


    private final int offerNumber;


    SpecialOfferType(int offerNumber) {
        this.offerNumber = offerNumber;
    }

    public int getOfferNumber() {
        return offerNumber;
    }


    public static Optional<SpecialOfferType> findByCommand(SpecialOfferType type){
        return Stream.of(SpecialOfferType.values()).filter(type::equals).findFirst();
    }


}
