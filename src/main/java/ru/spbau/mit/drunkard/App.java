package ru.spbau.mit.drunkard;

import ru.spbau.mit.drunkard.game.DrunkardGame;
import ru.spbau.mit.drunkard.game.DrunkardGameBuilder;

public class App {
    private static final DrunkardGameBuilder builder = new DrunkardGameBuilder();

    public static void main(String[] args) {

        boolean isHexagonal = args.length > 0 && args[0].startsWith("h");

        DrunkardGame game = isHexagonal ? builder.buildHexagonalGame() : builder.buildRectangularGame();
        game.setObserver(new GamePrinter(game));

        game.run();
    }
}
