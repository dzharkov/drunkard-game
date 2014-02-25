package ru.spbau.mit.drunkard.game;

import java.util.Random;

/**
 * @author Denis Zharkov
 */
abstract public class GameActor {
    private GamePoint point;

    private static final Random rand = new Random();
    private static final int[][] directionVectors = new int[][] {
        new int[]{1,0},
        new int[]{0,1},
        new int[]{-1,0},
        new int[]{0,-1}
    };

    abstract boolean isActing();
    abstract void performStep(GameField field);

    public GamePoint getPoint() {
        return point;
    }

    public void setPoint(GamePoint point) {
        this.point = point;
    }

    protected GamePoint getRandomDirectionStepPoint() {
        int direction = rand.nextInt(directionVectors.length);

        return new GamePoint(
            point.column + directionVectors[direction][0],
            point.row + directionVectors[direction][1]
        );
    }

    protected boolean eventHappened(double probability) {
        return rand.nextDouble() < probability;
    }
}
