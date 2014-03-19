package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class BeggarActor extends GameActor {
    private final int sleepAfterTripTime = 30;
    private final GamePoint shopAt;
    private int sleepTime = 0;
    private boolean withBottle = false;
    private BottleActor target = null;

    public BeggarActor(GamePoint shopAt) {
        this.shopAt = shopAt;
    }

    @Override
    boolean isActing() {
        return true;
    }

    @Override
    void performStep(GameField field) {
        if (sleepTime-- > 0) {
            return;
        }

        if (!hasSomethingToDo()) {
            findBottle(field);
            return;
        }

        //near bottle
        if (!withBottle && getPoint().distance(target.getPoint()) == 1) {
            field.deleteActor(target);

            target = null;
            withBottle = true;

            return;
        }

        //came back to shop
        if (withBottle && getPoint().equals(shopAt)) {
            takeBreak(field);
            return;
        }

        GamePoint targetPoint = withBottle ? shopAt : target.getPoint();

        //make next step or turn to Office
        GamePoint next = PathUtils.findNextPointInPath(getPoint(), targetPoint, field);
        if (next != null) {
            field.moveActor(this, next);
        } else {
            findBottle(field);
        }
    }

    private void takeBreak(GameField field) {
        field.moveActorInBackground(this);
        withBottle = false;
        sleepTime = sleepAfterTripTime;
    }

    private boolean hasSomethingToDo() {
        return withBottle || target != null;
    }

    private void findBottle(GameField field) {
        if (getPoint() == null && !field.isFree(shopAt)) {
            return;
        }

        GamePoint currentPoint = getPoint() == null ? shopAt : getPoint();

        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                GameActor actor;
                if (
                    (actor = field.at(column, row)) != null &&
                        actor instanceof BottleActor &&
                        PathUtils.findNextPointInPath(currentPoint, actor.getPoint(), field) != null
                    ) {
                    target = (BottleActor) actor;

                    if (getPoint() == null) {
                        field.putActor(this, shopAt);
                    }

                }
            }
        }
    }
}
