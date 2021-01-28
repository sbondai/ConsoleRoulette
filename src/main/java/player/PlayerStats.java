package player;

import bet.Bet;
import bet.BetType;
import utils.ResultFormatter;

import static utils.GameMessages.LOSE;
import static utils.GameMessages.WIN;
import static utils.GameMessages.getString;


public class PlayerStats {
    public static final double NO_PAY = 0.0;
    private final Bet bet;
    private final BetType[] winningTargets;

    public PlayerStats(final Bet bet, final BetType[] winningTargets) {
        this.bet = bet;
        this.winningTargets = winningTargets;
    }

    Bet bet() {
        return bet;
    }

    public String toString() {
        if (win()) {
            return ResultFormatter.gameResultRow(bet.getPlayer().getName(),
                    bet.getBetType().betTypeAsString(),
                    getString(WIN.name()),
                    payoff());
        } else {
            return ResultFormatter.gameResultRow(bet.getPlayer().getName(),
                    bet.getBetType().betTypeAsString(),
                    getString(LOSE.name()),
                    String.valueOf(NO_PAY));
        }
    }

    boolean win() {
        for(BetType betTarget : winningTargets){
            if(betTarget.equals(bet.getBetType())) {
                return true;
            }
        }
        return false;
    }

    String payoff() {
        return String.valueOf(bet.getAmountToBet() * bet.getBetType().amountDueToPlayer());
    }
}
