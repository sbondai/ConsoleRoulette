package bet.betTypes;

import bet.BetType;
import bet.utils.TypeOfBet;

public class OddEvenBetTypeImpl implements BetType {

    static final double AMOUNTTOPAY = 2.0;
    private final TypeOfBet type;

    public OddEvenBetTypeImpl(final TypeOfBet type) {
        this.type = type;
    }

    public String typeOfBet() {
        return type.name();
    }

    public boolean betMatch(BetType other) {
        return this.typeOfBet().equals(other.typeOfBet());
    }

    public double amountDueToPlayer() {
        return AMOUNTTOPAY;
    }

    @Override
    public String betTypeAsString() {
        return type.name();
    }
}
