package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.PathUtils;
import ru.spbau.mit.drunkard.game.geometry.GamePoint;

/**
 * @author Denis Zharkov
 */
public class BeggarActor extends BasedGameActor {
    private final int sleepAfterTripTime = 30;
    private int sleepTime = 0;

    public BeggarActor(GamePoint shopAt) {
        super(shopAt);
    }

    @Override
    public void performStep(GameField field) {
        if (sleepTime-- > 0) {
            return;
        }

        super.performStep(field);
    }

    @Override
    protected GameActor findTarget(GameField field) {
        if (getPoint() == null && !field.isFree(getBaseAt())) {
            return null;
        }

        GamePoint currentPoint = getPoint() == null ? getBaseAt() : getPoint();

        for (int row = 0; row < field.getHeight(); row++) {
            for (int column = 0; column < field.getWidth(); column++) {
                GameActor actor;
                if ((actor = field.at(column, row)) != null &&
                        actor instanceof BottleActor &&
                        PathUtils.findNextPointInPath(currentPoint, actor.getPoint(), field) != null
                        ) {
                    return actor;
                }
            }
        }

        return null;
    }

    @Override
    protected void processFollowing(GameField field) {
        field.deleteActor(getFollowingActor());
    }

    @Override
    protected void cameToBase(GameField field) {
        sleepTime = sleepAfterTripTime;
    }

    @Override
    protected void onFailedMission(GameField field) {
        findSomethingTodo(field);
    }
}
