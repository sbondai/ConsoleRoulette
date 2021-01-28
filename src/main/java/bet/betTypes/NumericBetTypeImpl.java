package bet.betTypes;

import bet.BetType;
import bet.utils.TypeOfBet;

public class NumericBetTypeImpl implements BetType {

    static final double AMOUNTTOPAY = 36.0;
    private final int choosenNumber;

    public NumericBetTypeImpl(int choosenNumber) {
        this.choosenNumber = choosenNumber;
    }

    public String typeOfBet() {
        return String.valueOf(choosenNumber);
    }

    public boolean betMatch(BetType other) {
        return this.choosenNumber == Integer.parseInt(other.typeOfBet());
    }

    public double amountDueToPlayer() {
        return AMOUNTTOPAY;
    }

    @Override
    public String betTypeAsString() {
        return String.valueOf(choosenNumber);
    }
}
