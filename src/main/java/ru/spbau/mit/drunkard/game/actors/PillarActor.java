package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.GamePoint;

/**
 * @author Denis Zharkov
 */
public class PillarActor extends GameActor {
    public PillarActor(GamePoint point) {
        setPoint(point);
    }

    @Override
    public boolean isActing() {
        return false;
    }

    @Override
    public void performStep(GameField field) {

    }
}
