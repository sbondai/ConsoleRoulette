package bet;

import player.Player;

public class Bet {

    private final Player player;
    private final double amountToBet;
    private final BetType betType;

    public Bet(Player player,  BetType betType,double amountToBet) {
        this.player = player;
        this.amountToBet = amountToBet;
        this.betType = betType;
    }

    public Player getPlayer() {
        return player;
    }

    public double getAmountToBet() {
        return amountToBet;
    }

    public BetType getBetType() {
        return betType;
    }
}
