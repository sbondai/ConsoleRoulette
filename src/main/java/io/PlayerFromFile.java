package io;

import player.Player;

import java.io.InputStream;

import java.util.List;
import java.util.Optional;

public interface PlayerFromFile {

    void loadPlayers(InputStream inputStream);
    List<Player> allPlayers();


    void save(List<Player> players);
}
