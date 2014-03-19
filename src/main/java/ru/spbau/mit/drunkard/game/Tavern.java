package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class Tavern extends GameActor {
    private final int sleepTime = 20;
    private final GamePoint releasePoint;

    private int wait = 0;

    public Tavern(GamePoint point, GamePoint releasePoint) {
        setPoint(point);
        this.releasePoint = releasePoint;
    }

    @Override
    boolean isActing() {
        return true;
    }

    @Override
    void performStep(GameField field) {
        if (wait > 0) {
            wait--;
            return;
        }

        if (field.isFree(releasePoint)) {
            field.putActor(new DrunkardActor(), releasePoint);
            wait = sleepTime;
        }
    }
}
