package ru.spbau.mit.drunkard.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Denis Zharkov
 */
public class DrunkardGame {
    private final GameField field;
    private final int maxStep;
    private GameObserver observer = null;

    public DrunkardGame(GameField field, List<GameActor> initialActors, int maxStep) {
        this.field = field;
        this.maxStep = maxStep;

        for (GameActor actor : initialActors) {
            if (actor.getPoint() != null) {
                field.putActor(actor, actor.getPoint());
            } else {
                field.putActorInBackground(actor);
            }
        }
    }

    private boolean ended() {
        return false;
    }

    private void performStep() {
        ArrayList<GameActor> movableActors = field.getMovableActors();
        Collections.shuffle(movableActors);

        for (GameActor actor : movableActors) {
            actor.performStep(field);
        }
    }

    public void run() {
        for (int step = 0; !ended() && step < maxStep; step++) {
            performStep();
            notifyStep();
        }
    }

    public GameField getField() {
        return field;
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
