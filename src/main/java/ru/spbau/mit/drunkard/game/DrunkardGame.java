package ru.spbau.mit.drunkard.game;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Denis Zharkov
 */
public class DrunkardGame {
    private final GameField field;
    private Tavern tavern;
    private GameObserver observer = null;

    public DrunkardGame(GameField field, Tavern tavern, PillarActor lamp) {
        this.field = field;
        this.tavern = tavern;
        field.putActor(lamp, lamp.getPoint());
    }

    private boolean ended() {
        return false;
    }

    private void performStep() {
        tavern.performStep(field);

        ArrayList<GameActor> movableActors = field.getMovableActors();
        Collections.shuffle(movableActors);

        for (GameActor actor : movableActors) {
            actor.performStep(field);
        }
    }

    public void run() {
        while (!ended()) {
            performStep();
            notifyStep();
        }
    }

    public GameField getField() {
        return field;
    }

    public int getTavernColumn() {
        return tavern.getPoint().column;
    }

    public void notifyStep() {
        if (observer != null) {
            observer.onAfterStep(this.field);
        }
    }

    public void setObserver(GameObserver observer) {
        this.observer = observer;
    }
}
