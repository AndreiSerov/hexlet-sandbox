package org.example.factory_example.games;

/**
 * @author andreiserov
 */
@GameComponent
public class Calc implements Game {

    @Override
    public void play() {
        System.out.println("Calc");
    }
}
