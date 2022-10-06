package org.example.factory_example.factory;

import org.example.factory_example.games.Calc;
import org.example.factory_example.games.Even;
import org.example.factory_example.games.Game;
import org.example.factory_example.games.Greet;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author andreiserov
 */
public class GameFactory implements Factory {

    private final Map<String, Game> games;

    public GameFactory() {
        games = new LinkedHashMap<>();
        games.put("Greet", new Greet());
        games.put("Calc", new Calc());
        games.put("Even", new Even());
    }

    public Map<String, Game> getGames() {
        return games;
    }
}
