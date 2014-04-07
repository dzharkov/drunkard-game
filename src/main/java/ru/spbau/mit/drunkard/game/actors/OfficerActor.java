package ru.spbau.mit.drunkard.game.actors;

import ru.spbau.mit.drunkard.game.GameField;
import ru.spbau.mit.drunkard.game.GamePoint;
import ru.spbau.mit.drunkard.game.PathUtils;

/**
 * @author Denis Zharkov
 */
public class OfficerActor extends BasedGameActor {
    private final LampActor lamp;

    public OfficerActor(LampActor lamp, GamePoint baseAt) {
        super(baseAt);
        this.lamp = lamp;
    }

    @Override
    protected GameActor findTarget(GameField field) {
        if (!field.isFree(getBaseAt())) {
            return null;
        }

        for (GamePoint lightenedPoint : lamp.getLightenedPoints()) {
            GameActor actor;

            if (field.isValidPoint(lightenedPoint) &&
                (actor = field.at(lightenedPoint)) != null &&
                actor instanceof DrunkardActor &&
                !actor.isActing() &&
                PathUtils.findNextPointInPath(getBaseAt(), lightenedPoint, field) != null
            ) {
                return actor;
            }
        }

        return null;
    }

    @Override
    protected void processFollowing(GameField field) {
        field.deleteActor(getFollowingActor());
        field.moveActor(this, getTargetPoint());
    }

    @Override
    protected void cameToBase(GameField field) {

    }

    @Override
    protected void onFailedMission(GameField field) {
        if (!isGoingToBase()) {
            turnToBase();
        }
    }
}
