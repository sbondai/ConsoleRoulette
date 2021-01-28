import game.Game;
import game.roulette.RouletteTableSpin;
import io.FileLoader;
import io.PlayerFromFile;
import io.PlayerFromFileImpl;

import java.io.IOException;


public class ConsoleRoulette {

//    private final Game game;
    public static final String PLAYERS_DEFAULT_INPUT_FILE = "gamedata.txt";


    public static void main(String[] args) throws Exception {
        PlayerFromFile playersFromFile = new PlayerFromFileImpl(new FileLoader().readFile(PLAYERS_DEFAULT_INPUT_FILE));
        RouletteTableSpin tableSpin = new RouletteTableSpin(playersFromFile);
        tableSpin.start();


    }




}
