package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.geometry.GamePoint;
import ru.spbau.mit.drunkard.game.geometry.GeometryStrategy;

import java.util.Random;

/**
 * @author Denis Zharkov
 */
public class DrunkardActor extends GameActor {
    private boolean isSleeping = false;
    private boolean isFallen = false;
    private boolean hasBottle = true;

    private final GeometryStrategy geometryStrategy;

    private static final Random rand = new Random();

    public DrunkardActor(GeometryStrategy geometryStrategy) {
        this.geometryStrategy = geometryStrategy;
    }

    @Override
    public boolean isActing() {
        return !isSleeping && !isFallen;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public boolean isFallen() {
        return isFallen;
    }

    private GamePoint getRandomDirectionStepPoint() {
        int[][] directionVectors = geometryStrategy.getDirectionVectors(getPoint());
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
    public void performStep(GameField field) {
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
