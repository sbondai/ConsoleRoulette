package game.roulette;

import bet.BetType;
import bet.betTypes.NumericBetTypeImpl;
import bet.betTypes.OddEvenBetTypeImpl;
import bet.utils.TypeOfBet;
import game.GameResult;

import static bet.utils.TypeOfBet.EVEN;
import static bet.utils.TypeOfBet.ODD;


public class RouletteResult implements GameResult {

    private final NumericBetTypeImpl numericBetType;
    private final OddEvenBetTypeImpl oddEvenBetType;

    RouletteResult(final int number) {
        this.numericBetType = new NumericBetTypeImpl(number);
        this.oddEvenBetType = evenOddBy(number);
    }

    private OddEvenBetTypeImpl evenOddBy(final int number) {
        if (number == 0) {
            return new None(null);
        }
        if (number % 2 == 0) {
            return new OddEvenBetTypeImpl(EVEN);
        }
        return new OddEvenBetTypeImpl(ODD);
    }

    @Override
    public BetType[] allWinningTargets() {
        return new BetType[]{numericBetType, oddEvenBetType};
    }


    class None extends OddEvenBetTypeImpl {

        private None(TypeOfBet type) {
            super(type);
        }

        @Override
        public double amountDueToPlayer() {
            return 0.0;
        }

        @Override
        public String betTypeAsString() {
            return "";
        }

    }
}
