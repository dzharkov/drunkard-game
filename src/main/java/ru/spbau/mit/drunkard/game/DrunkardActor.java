package ru.spbau.mit.drunkard.game;

import java.util.Random;

/**
 * @author Denis Zharkov
 */
public class DrunkardActor extends GameActor {
    private boolean isSleeping = false;
    private boolean isFallen = false;
    private boolean hasBottle = true;

    private static final Random rand = new Random();
    private static final int[][] directionVectors = new int[][]{
        new int[]{1, 0},
        new int[]{0, 1},
        new int[]{-1, 0},
        new int[]{0, -1}
    };

    @Override
    boolean isActing() {
        return !isSleeping && !isFallen;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public boolean isFallen() {
        return isFallen;
    }

    private GamePoint getRandomDirectionStepPoint() {
        int direction = rand.nextInt(directionVectors.length);

        return new GamePoint(
            getPoint().column + directionVectors[direction][0],
            getPoint().row + directionVectors[direction][1]
        );
    }

    private boolean eventHappened(double probability) {
        return rand.nextDouble() < probability;
    }

    @Override
    void performStep(GameField field) {
        GamePoint nextPoint = getRandomDirectionStepPoint();

        if (field.isValidPoint(nextPoint)) {
            if (field.isFree(nextPoint)) {
                GamePoint prevPoint = getPoint();

                field.moveActor(this, nextPoint);

                if (hasBottle && eventHappened(0.03)) {
                    hasBottle = false;

                    field.putActor(new BottleActor(), prevPoint);
                }

            } else {
                GameActor obstacle = field.at(nextPoint);

                if (obstacle instanceof PillarActor || (obstacle instanceof DrunkardActor && ((DrunkardActor) obstacle).isSleeping)) {
                    isSleeping = true;
                    field.moveActor(this, getPoint());
                } else if (obstacle instanceof BottleActor) {
                    isFallen = true;
                    field.moveActor(this, getPoint());
                }
            }
        }
    }
}
