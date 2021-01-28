package game;



import bet.Bet;
import bet.BetMaker;
import bet.BetManager;

import game.roulette.RouletteBetManager;
import game.roulette.RouletteTypeMaker;
import io.PlayerFromFile;
import player.Player;
import player.PlayerStats;
import utils.ResultFormatter;

import java.util.*;


public abstract class Game {

    protected final PlayerFromFile playersFromFile;

    protected final RouletteTypeMaker rouletteTypeMaker;

    protected final List<GameResultsListener> listeners = new ArrayList<GameResultsListener>();
    protected final List<Player> players;
    protected final BetManager betManager= new BetManager();
    protected final List<PlayerStats> lastPlayerResults = new ArrayList<PlayerStats>();
    protected GameResult lastResult;

    protected Game(final PlayerFromFile playersFromFile, final RouletteTypeMaker rouletteTypeMaker) throws Exception {
        this.playersFromFile = playersFromFile;
        this.rouletteTypeMaker = rouletteTypeMaker;
        players = playersFromFile.allPlayers();
    }

    public List<Player> players() {
        return players;
    }

    public boolean placeBet(final Bet bet) {
        return betManager.placeBet(bet);
    }


    public List<Bet> currentBets() {
        return betManager.currentBets();
    }

    public Player playerByName(final String name) {
        for (Player player : players) {
            if(player.getName().equals(name)){
                return player;
            }
        }
        return null;
    }


    public static RouletteBetManager aBetFor(final Game game) {
        return game.rouletteBetManager();
    }

    protected void acceptBets(final boolean accept) {
        betManager.acceptBets(accept);
    }

    public void addListener(final GameResultsListener listener) {
        listeners.add(listener);
    }


    public GameResult lastResult() {
        return lastResult;
    }

    public String playersTotalsToString() {
        final StringBuilder totals = new StringBuilder();
        totals.append(ResultFormatter.defaultGameTotalsHeader());

        for (Player player : players) {
            totals.append(player.toString());
        }

        return "\n" + totals.toString() + "\n";
    }

    protected abstract RouletteBetManager rouletteBetManager();
    public abstract void start();
    public abstract void stop();
    public abstract String gameResultToString(GameResult result);
}
