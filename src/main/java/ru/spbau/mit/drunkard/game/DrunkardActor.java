package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class DrunkardActor extends GameActor {
    private boolean isSleeping = false;
    private boolean isFallen = false;
    private boolean hasBottle = true;

    @Override
    boolean isActing() {
        return !isSleeping && !isFallen;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public boolean isFallen() {
        return isFallen;
    }

    @Override
    void performStep(GameField field) {
        GamePoint nextPoint = null;

        do {
            nextPoint = getRandomDirectionStepPoint();
        } while (!field.isValidPoint(nextPoint));

        if (field.isValidPoint(nextPoint)) {
            if (field.isFree(nextPoint)) {
                GamePoint prevPoint = getPoint();

                field.moveActor(this, nextPoint);

                if (hasBottle && eventHappened(0.03)) {
                    hasBottle = false;

                    field.putActor(new BottleActor(), prevPoint);
                }

            } else {
                GameActor obstacle = field.at(nextPoint);

                if (obstacle instanceof PillarActor || (obstacle instanceof DrunkardActor && ((DrunkardActor)obstacle).isSleeping)) {
                    isSleeping = true;
                    field.moveActor(this, getPoint());
                } else if (obstacle instanceof BottleActor) {
                    isFallen = true;
                    field.moveActor(this, getPoint());
                }
            }
        }
    }
}
