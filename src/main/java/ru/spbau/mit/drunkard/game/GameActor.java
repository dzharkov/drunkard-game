package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
abstract public class GameActor {
    private GamePoint point;

    protected GameActor(GamePoint point) {
        this.point = point;
    }

    protected GameActor() {
    }

    abstract boolean isActing();

    abstract void performStep(GameField field);

    public GamePoint getPoint() {
        return point;
    }

    public void setPoint(GamePoint point) {
        this.point = point;
    }
}
