package ru.spbau.mit.drunkard;

import ru.spbau.mit.drunkard.game.DrunkardGame;
import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.GameObserver;
import ru.spbau.mit.drunkard.game.actors.*;
import ru.spbau.mit.drunkard.game.geometry.HexagonalGeometryStrategy;

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

        boolean isHexagonalGeometry = field.getGeometryStrategy() instanceof HexagonalGeometryStrategy;

        for (int row = -1; row <= field.getHeight(); row++) {

            //shift -1-st row of hexagonal field
            if (row == -1 && isHexagonalGeometry) {
                System.out.print(" ");
            }

            //shift even rows of hexagonal field
            if (isHexagonalGeometry && row % 2 == 0) {
                System.out.print(" ");
            }

            for (int column = -1; column <= field.getWidth(); column++) {
                System.out.print(
                        getCharByActor(
                                field.at(column, row),
                                field.isValidPoint(column, row)
                        )
                );

                if (isHexagonalGeometry && (column + 1) != field.getWidth()) {
                    System.out.print(" ");
                }
            }

            System.out.println();
        }
    }

    private char getCharByActor(GameActor actor, boolean withinField) {
        if (actor == null) {
            return withinField ? '.' : ' ';
        }

        if (actor instanceof BottleActor) {
            return 'B';
        }

        if (actor instanceof Tavern) {
            return 'T';
        }

        if (actor instanceof PoliceOfficeActor) {
            return 'S';
        }

        if (actor instanceof OfficerActor) {
            return 'P';
        }

        if (actor instanceof LampActor) {
            return 'L';
        }

        if (actor instanceof BottleShopActor) {
            return 'R';
        }

        if (actor instanceof BeggarActor) {
            return 'z';
        }

        if (actor instanceof DrunkardActor) {
            DrunkardActor drunkardActor = (DrunkardActor) actor;

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
