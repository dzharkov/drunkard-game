package ru.spbau.mit.drunkard.game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Zharkov
 */
public class LampActor extends GameActor {
    private static final int RADIUS = 100;

    public LampActor(GamePoint point) {
        setPoint(point);
    }

    @Override
    boolean isActing() {
        return false;
    }

    @Override
    void performStep(GameField field) {

    }

    public List<GamePoint> getLightenedPoints() {
        ArrayList<GamePoint> result = new ArrayList<>();

        for (int i = getPoint().row - RADIUS; i <= getPoint().row + RADIUS; i++) {
            for (int j = getPoint().column - RADIUS; j <= getPoint().column + RADIUS; j++) {
                result.add(new GamePoint(j, i));
            }
        }

        return result;
    }
}
