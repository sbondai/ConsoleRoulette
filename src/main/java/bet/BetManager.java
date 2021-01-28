package bet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BetManager {

    private final List<Bet> betList = new ArrayList<Bet>();
    private final BlockingQueue<Bet> betQueue = new LinkedBlockingQueue<Bet>();
    private boolean accepting = false;

    public BetManager() {
        Registrar registrar = new Registrar(betQueue, betList);
        new Thread(registrar).start();
    }

    public void acceptBets(final boolean accept) {
        accepting = accept;
    }

    public List<Bet> currentBets() {
        final ArrayList<Bet> copy = new ArrayList<Bet>();
        copy.addAll(betList);
        return copy;
    }

    public boolean placeBet(final Bet bet) {
        if (accepting) {
            return betQueue.offer(bet);
        }
        return false;
    }


    public void reset() {
        betList.clear();
    }

    private class Registrar  implements Runnable {

        private final BlockingQueue<Bet> queue;
        private final List<Bet> list;

        public Registrar(final BlockingQueue<Bet> betQueue, final List<Bet> betList) {
            this.queue = betQueue;
            this.list = betList;
        }

        @Override
        public void run() {
            while(true) {
                if(accepting) {
                    final Bet bet;
                    try {
                        bet = queue.take();
                        list.add(bet);
                    } catch (InterruptedException e) {

                    }
                }
            }
        }
    }
}
