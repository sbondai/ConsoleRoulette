package player;

import java.util.Optional;

public class PlayerFactory {
    enum LineAttributesPlayer {
        NAME(0), TOTAL_WIN(1), TOTAL_BET(2);

        private final int index;

        LineAttributesPlayer(final int index) {
            this.index = index;
        }

        int index() {
            return index;
        }
    }


    public Optional<Player> createPlayer(String line) throws Exception {
        String[] lineElements = line.split(",");
        if (lineElements.length == 0) {
            return null;
        }
        if (lineElements.length == LineAttributesPlayer.values().length) {
            final String name = lineElements[LineAttributesPlayer.NAME.index()];
            final String totalWin = lineElements[LineAttributesPlayer.TOTAL_WIN.index()];
            final String totalBet = lineElements[LineAttributesPlayer.TOTAL_BET.index()];
            return Optional.of(new Player(name, convertValidateInput(totalWin), convertValidateInput(totalBet)));


        } else {
            return Optional.of(new Player(lineElements[LineAttributesPlayer.NAME.index()]));
        }


    }
    private double convertValidateInput(final String string) {
        try {
            return Double.parseDouble(string);
        } catch (NumberFormatException ex) {
            return 0.0;
        }
    }

}
