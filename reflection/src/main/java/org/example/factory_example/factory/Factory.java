package org.example.factory_example.factory;

import org.example.factory_example.games.Game;

import java.util.Map;
import java.util.Optional;

/**
 * @author andreiserov
 */
public interface Factory {

    Map<String, Game> getGames();

    default Game getGame(String game) {
        return Optional.of(
                getGames().get(game)
            )
            .orElseThrow(RuntimeException::new);
    }
}
