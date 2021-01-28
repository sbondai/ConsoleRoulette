package bet;

import bet.betTypes.NumericBetTypeImpl;
import bet.betTypes.OddEvenBetTypeImpl;
import bet.utils.TypeOfBet;

public class BetMaker {


    public BetType createFrom(final String string) {
        if (isNumberOfValidRange(string)) {
            return new NumericBetTypeImpl(Integer.parseInt(string));
        }

        if (isEvenOddValidLabel(string)) {
            return new OddEvenBetTypeImpl(TypeOfBet.byName(string));
        }

        return null;
    }

    private boolean isEvenOddValidLabel(final String string) {
        final TypeOfBet tryTarget = TypeOfBet.byName(string);
        return tryTarget != null;
    }

    private boolean isNumberOfValidRange(final String string) {
        try {

            final Integer tryNumber = Integer.valueOf(string);
            return (tryNumber > 0) && (tryNumber <= 36);

        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
