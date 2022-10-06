package org.example.factory_example;

import org.example.factory_example.factory.Factory;
import org.example.factory_example.factory.FullReflectionGameFactory;
import org.example.factory_example.factory.GameFactory;
import org.example.factory_example.factory.ReflectionGameFactory;

import java.util.List;
import java.util.Random;

/**
 * @author andreiserov
 */
public class Main {

    public static void main(String[] args) {
        List.of(
            new GameFactory(),
            new ReflectionGameFactory(),
            new FullReflectionGameFactory()
        )
            .forEach(Main::printGamesAndChoose);
    }

    private static void printGamesAndChoose(Factory factory) {
        final List<String> gameNames = factory.getGames().keySet().stream().toList();

        for (int i = 0; i < gameNames.size(); i++) {
            System.out.printf("%d - %s\n", i, gameNames.get(i));
        }

        int playerChoice = new Random().nextInt(3);


        System.out.print("Choosen game is: ");

        factory
            .getGame(gameNames.get(playerChoice))
            .play();
    }
}
