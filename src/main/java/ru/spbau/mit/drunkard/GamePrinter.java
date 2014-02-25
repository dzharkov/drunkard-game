package ru.spbau.mit.drunkard;

import ru.spbau.mit.drunkard.game.*;

/**
 * @author Denis Zharkov
 */
public class GamePrinter implements GameObserver {
    private final DrunkardGame game;

    public GamePrinter(DrunkardGame game) {
        this.game = game;
    }

    @Override
    public void onAfterStep(GameField field) {
        System.out.println();

        int tavernColumn = game.getTavernColumn();

        for (int column = 0; column < field.getWidth(); column++) {
            System.out.print(column == tavernColumn ? 'T' : ' ');
        }

        System.out.println();

        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                System.out.print(getCharByActor(field.at(column, row)));
            }

            System.out.println();
        }
    }

    private char getCharByActor(GameActor actor) {
        if (actor == null) {
            return '.';
        }

        if (actor instanceof BottleActor) {
            return 'B';
        }

        if (actor instanceof DrunkardActor) {
            DrunkardActor drunkardActor = (DrunkardActor)actor;

            if (drunkardActor.isFallen()) {
                return '&';
            }

            return drunkardActor.isSleeping() ? 'Z' : 'D';
        }

        if (actor instanceof PillarActor) {
            return 'C';
        }

        return '#';
    }
}
