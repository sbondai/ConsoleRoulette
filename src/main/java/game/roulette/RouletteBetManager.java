package game.roulette;

import bet.Bet;
import bet.BetType;
import player.Player;

public class RouletteBetManager {


    public static final String SEPARATOR = "\\s+";
    private static final int DEFAULT_INPUT_LENGTH = 3;
    private static final int PLAYER_NAME_INDEX = 0;
    private static final int BET_TARGET_INDEX = 1;
    private static final int AMOUNT_INDEX = 2;
    private static final double DEFAULT_INVALID_AMOUNT = -1.0;
    private final RouletteTableSpin roulette;
    private final RouletteTypeMaker betTargetFactory = new RouletteTypeMaker();

    RouletteBetManager(final RouletteTableSpin roulette) {
        this.roulette = roulette;
    }

    public Bet fromInput(final String input) {

        String[] inputElements = input.trim().split(SEPARATOR);

        if (inputElements.length == DEFAULT_INPUT_LENGTH) {
            return tryToCreateFrom(inputElements);
        }

        return null;
    }

    private Bet tryToCreateFrom(final String[] inputElements) {

        final Player player = roulette.playerByName(inputElements[PLAYER_NAME_INDEX].trim());

        if (player == null) {
            return null;
        }

        final BetType betType = betTargetFactory.createFrom(inputElements[BET_TARGET_INDEX].trim());
        if (betType == null) {
            return null;
        }

        final double amount = validateDouble(inputElements[AMOUNT_INDEX].trim());
        if (amount < 0) {
            return null;
        }

        return new Bet(player, betType, amount);
    }

    private double validateDouble(final String doubleString) {
        try {
            return Double.parseDouble(doubleString);
        } catch (NumberFormatException ex) {
            return DEFAULT_INVALID_AMOUNT;
        }
    }
}
