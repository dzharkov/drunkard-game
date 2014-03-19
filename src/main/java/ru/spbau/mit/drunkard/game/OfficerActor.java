package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class OfficerActor extends GameActor {
    private final LampActor lamp;
    private final GamePoint officeAt;

    private DrunkardActor followingDrunkard = null;
    private boolean goingToOffice = false;
    private GamePoint targetPoint = null;

    public OfficerActor(LampActor lamp, GamePoint officeAt) {
        this.lamp = lamp;
        this.officeAt = officeAt;
    }

    @Override
    boolean isActing() {
        return true;
    }

    @Override
    void performStep(GameField field) {
        if (!hasSomethingToDo()) {
            findVictim(field);
            return;
        }

        //near drunkard
        if (!goingToOffice && getPoint().distance(followingDrunkard.getPoint()) == 1) {
            field.deleteActor(followingDrunkard);
            field.moveActor(this, targetPoint);

            turnToOffice();

            return;
        }

        //came back to office
        if (goingToOffice && getPoint().equals(officeAt)) {
            hideInOffice(field);
            return;
        }

        //make next step or turn to Office
        GamePoint next = PathUtils.findNextPointInPath(getPoint(), targetPoint, field);
        if (next != null) {
            field.moveActor(this, next);
        } else {
            if (!goingToOffice) {
                turnToOffice();
            }
        }
    }

    private void turnToOffice() {
        goingToOffice = true;
        followingDrunkard = null;
        targetPoint = officeAt;
    }

    private void hideInOffice(GameField field) {
        targetPoint = null;
        goingToOffice = false;
        field.moveActorInBackground(this);
    }

    private boolean hasSomethingToDo() {
        return hasVictim() || goingToOffice;
    }

    private boolean hasVictim() {
        return followingDrunkard != null;
    }

    private boolean findVictim(GameField field) {
        if (!field.isFree(officeAt)) {
            return false;
        }

        for (GamePoint lightenedPoint : lamp.getLightenedPoints()) {
            GameActor actor;

            if (
                field.isValidPoint(lightenedPoint) &&
                    (actor = field.at(lightenedPoint)) != null &&
                    actor instanceof DrunkardActor &&
                    !actor.isActing() &&
                    PathUtils.findNextPointInPath(officeAt, lightenedPoint, field) != null
                ) {
                field.putActor(this, officeAt);
                goingToOffice = false;
                followingDrunkard = (DrunkardActor) actor;
                targetPoint = followingDrunkard.getPoint();

                return true;
            }
        }

        return false;
    }
}
