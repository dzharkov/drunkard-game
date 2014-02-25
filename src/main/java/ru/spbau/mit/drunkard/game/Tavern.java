package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class Tavern extends GameActor {
    private final int sleepTime = 20;

    private int wait = 0;

    public Tavern(GamePoint point) {
        setPoint(point);
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

        if (field.isFree(getPoint())) {
            field.putActor(new DrunkardActor(), getPoint());
            wait = sleepTime;
        }
    }
}
