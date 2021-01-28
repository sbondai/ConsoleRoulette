package io;

import player.Player;
import player.PlayerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


public class PlayerFromFileImpl implements PlayerFromFile {


    InputStream inputStream;
    private List<Player> playerFromFile = new ArrayList<>();

    public PlayerFromFileImpl(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public void loadPlayers(InputStream inputStream) {

        PlayerFactory playerFactory = new PlayerFactory();


        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String input;
            while ((input = reader.readLine()) != null) {
                Optional<Player> player = playerFactory.createPlayer(input);
                player.ifPresent(playerFromFile::add);

            }
        } catch (Exception e) {
            throw new RuntimeException("Error no players found in file " + e.getMessage(), e);
        }
    }

    @Override
    public List<Player> allPlayers() {
        return playerFromFile;
    }

    @Override
    public void save(List<Player> players) {
        new Thread(new StorageWorker(players)).start();
    }

    private class StorageWorker implements Runnable {


        private final Collection<Player> players;

        public StorageWorker(final Collection<Player> players) {
            this.players = players;
        }

        @Override
        public void run() {


            try {
                ClassLoader classLoader = this.getClass().getClassLoader();

                File configFile=new File(classLoader.getResource("gamedata.txt").getFile());
                String coma = ",";
                final StringBuilder content = new StringBuilder();
                for(Player player : players) {
                    content.append(player.getName()).append(coma)
                            .append(player.getTotalWin()).append(coma)
                            .append(player.getTotalBet())
                            .append("\n");
                }
                final BufferedWriter writer = new BufferedWriter(new FileWriter(configFile));
                writer.write(content.toString());
                writer.close();
            } catch (IOException e) {

            }

        }
    }


}
