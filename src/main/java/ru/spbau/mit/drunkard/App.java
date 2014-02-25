package ru.spbau.mit.drunkard;

import ru.spbau.mit.drunkard.game.DrunkardGame;
import ru.spbau.mit.drunkard.game.DrunkardGameBuilder;

public class App {
    private static final DrunkardGameBuilder builder = new DrunkardGameBuilder();
    
    public static void main(String[] args) {
        DrunkardGame game = builder.buildGame();
        game.setObserver(new GamePrinter(game));

        game.run();
    }
}
