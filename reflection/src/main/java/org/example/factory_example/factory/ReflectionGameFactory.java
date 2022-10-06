package org.example.factory_example.factory;

import org.example.factory_example.games.Calc;
import org.example.factory_example.games.Even;
import org.example.factory_example.games.Game;
import org.example.factory_example.games.Greet;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author andreiserov
 */
public class ReflectionGameFactory implements Factory {

    private final Map<String, Game> games;

    public ReflectionGameFactory() {
        games = Stream.of(
            new Calc(),
            new Even(),
            new Greet()
        ).collect(
            Collectors.toMap(
                game -> game.getClass().getSimpleName(),
                game -> game
            )
        );
    }

    public Map<String, Game> getGames() {
        return games;
    }
}
