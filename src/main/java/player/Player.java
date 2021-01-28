package player;


import bet.Bet;

public class Player {

    private final String name;
    private double totalWin;
    private double totalBet;

    public Player(String name, double totalWin, double totalBet) {
        this.name = name;
        this.totalWin = totalWin;
        this.totalBet = totalBet;
    }
    public Player(final String name) {
        this(name, 0.0, 0.0);
    }


    public String getName() {
        return name;
    }

    public double getTotalWin() {
        return totalWin;
    }

    public double getTotalBet() {
        return totalBet;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (! (obj instanceof Player)) return false;
        Player player = (Player) obj;
        return name.equals(player.name);
    }


    public void apply(final PlayerStats playerResult) {
        final Bet bet = playerResult.bet();
        if(playerResult.win()) {
            totalWin += (bet.getBetType().amountDueToPlayer() * bet.getAmountToBet());
        }
        totalBet += bet.getAmountToBet();
    }
}
