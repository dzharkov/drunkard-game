package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.GamePoint;

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

    abstract public boolean isActing();

    abstract public void performStep(GameField field);

    public GamePoint getPoint() {
        return point;
    }

    public void setPoint(GamePoint point) {
        this.point = point;
    }
}
