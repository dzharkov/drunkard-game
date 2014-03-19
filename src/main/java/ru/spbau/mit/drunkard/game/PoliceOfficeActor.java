package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class PoliceOfficeActor extends GameActor {

    public PoliceOfficeActor(GamePoint point) {
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
