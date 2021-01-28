package game.roulette;

import bet.Bet;
import bet.BetType;
import game.Game;
import game.GameResult;
import game.GameResultsListener;
import io.PlayerFromFile;

import player.PlayerStats;
import utils.ResultFormatter;

import java.util.Random;

import static utils.GameMessages.WIN_NUMBER;
import static utils.GameMessages.getString;

public class RouletteTableSpin extends Game {

    public static final int ROULETTE_MIN = 0;
    public static final int ROULETTE_MAX = 36;

    private final static int DEFAULT_INTERVAL_SEC = 30;

    private final Thread spinnerThread;


    private int spinInterval = DEFAULT_INTERVAL_SEC;
    private boolean playing;


    public RouletteTableSpin(final PlayerFromFile playerFromFile) throws Exception {
        super(playerFromFile, new RouletteTypeMaker());
        spinnerThread = new Thread(new Spinner());
    }

    @Override
    public void start() {
        if (!playing) {
            playing = true;
            spinnerThread.start();
        }
    }

    @Override
    public void stop() {
        acceptBets(false);
    }

    @Override
    public String gameResultToString(final GameResult result) {
        final StringBuilder gameResult = new StringBuilder();
        gameResult.append(winningNumberHeader(result))
                .append(ResultFormatter.defaultGameResultHeader());

        for (PlayerStats playerResult : lastPlayerResults) {
            gameResult.append(playerResult.toString());
        }

        return "\n" + gameResult.toString() + "\n";
    }

    private String winningNumberHeader(GameResult result) {
        final StringBuilder winningTargets = new StringBuilder();
        for(BetType betTarget : result.allWinningTargets()) {
            winningTargets.append(betTarget.betTypeAsString()).append(" ");
        }

        return getString(WIN_NUMBER.name(), winningTargets.toString());
    }

    @Override
    protected RouletteBetManager rouletteBetManager() {
        return new RouletteBetManager(this);
    }

    public void spinInterval(final int spinInterval) {
        this.spinInterval = spinInterval;
    }


    private class Spinner implements Runnable {
        final Random random = new Random();

        @Override
        public void run() {
            while (playing) {
                try {
                    initSpin();
                    spinning();
                    haltBets();
                    spinEnd();
                } catch (InterruptedException e) {
                    // TODO log
                }
            }
        }


        private void initSpin() {
            lastPlayerResults.clear();
            betManager.acceptBets(true);
        }


        private void spinning() throws InterruptedException {
            Thread.sleep(spinInterval * 1000);
        }


        private void haltBets() {
            betManager.acceptBets(false);
        }


        private void spinEnd() {
            lastResult = new RouletteResult(winningNumber());
            storeResult(lastResult);
            reportResult(lastResult);
            betManager.reset();
        }

        private int winningNumber() {
            return random.nextInt(ROULETTE_MAX + 1);
        }

        private void storeResult(final GameResult newResult) {
            for (Bet bet : betManager.currentBets()) {
                PlayerStats playerResult = new PlayerStats(bet, newResult.allWinningTargets());
                lastPlayerResults.add(playerResult);
                bet.getPlayer().apply(playerResult);
            }
            playersFromFile.save(players);
        }

        protected void reportResult(final GameResult result) {
            notifyGameResultsListeners(gameResultToString(result));
            notifyGameResultsListeners(playersTotalsToString());
        }

        private void notifyGameResultsListeners(final String totals) {
            for (GameResultsListener listener : listeners) {
                listener.update(totals);
            }
        }
    }
}
