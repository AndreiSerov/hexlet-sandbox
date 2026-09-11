package org.example.factory_example.factory;

import org.example.factory_example.games.Game;
import org.example.factory_example.util.ReflectionUtil;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author andreiserov
 */
public class FullReflectionGameFactory implements Factory {

    private final Map<String, Game> games;

    public FullReflectionGameFactory() {
        try {
            final Class<Game>[] classes = ReflectionUtil.getClasses("org.example.factory_example.games");

            games = Arrays.stream(classes)
                .filter(it -> Arrays.asList(it.getInterfaces()).contains(Game.class))
//                .filter(it -> it.isAnnotationPresent(GameComponent.class))
                .collect(
                    Collectors.toMap(
                        Class::getSimpleName,
                        game -> {
                            try {
                                return game
                                    .getConstructor()
                                    .newInstance();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }
                    )
                );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, Game> getGames() {
        return games;
    }
}
