package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class PillarActor extends GameActor {
    public PillarActor(GamePoint point) {
        setPoint(point);
    }

    @Override
    boolean isActing() {
        return false;
    }

    @Override
    void performStep(GameField field) {

    }
}
