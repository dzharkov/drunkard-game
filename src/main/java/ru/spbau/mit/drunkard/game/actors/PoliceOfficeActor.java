package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.geometry.GamePoint;

/**
 * @author Denis Zharkov
 */
public class PoliceOfficeActor extends GameActor {

    public PoliceOfficeActor(GamePoint point) {
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
