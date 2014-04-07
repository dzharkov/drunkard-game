package ru.spbau.mit.drunkard.game;


/**
 * Actor with the base on the field's edge
 * e.g. Officer with OfficeStation or Beggar with BottleShop
 */
abstract public class BasedGameActor extends GameActor {
    private final GamePoint baseAt;
    private GameActor followingActor = null;
    private boolean goingToBase = false;
    private GamePoint targetPoint = null;

    public BasedGameActor(GamePoint baseAt) {
        this.baseAt = baseAt;
    }

    @Override
    boolean isActing() {
        return true;
    }

    @Override
    void performStep(GameField field) {
        if (!hasSomethingToDo()) {
            findSomethingTodo(field);
            return;
        }

        if (nearTheFollowing()) {
            processFollowing(field);
            turnToBase();
            return;
        }

        //came back to base
        if (nearTheBase()) {
            hideInBase(field);
            return;
        }

        makeNextStep(field);
    }

    protected boolean hasSomethingToDo() {
        return followingActor != null || goingToBase;
    }

    protected void findSomethingTodo(GameField field) {
        followingActor = findTarget(field);

        if (followingActor != null) {

            if (getPoint() == null) {
                field.putActor(this, baseAt);
            }

            goingToBase = false;
            targetPoint = followingActor.getPoint();
        }
    }

    protected void makeNextStep(GameField field) {
        GamePoint next = PathUtils.findNextPointInPath(getPoint(), targetPoint, field);
        if (next != null) {
            field.moveActor(this, next);
        } else {
            onFailedMission(field);
        }
    }

    protected boolean nearTheFollowing() {
        return !goingToBase && getPoint().distance(followingActor.getPoint()) == 1;
    }

    protected boolean nearTheBase() {
        return goingToBase && getPoint().equals(baseAt);
    }

    protected void turnToBase() {
        goingToBase = true;
        followingActor = null;
        targetPoint = baseAt;
    }

    private void hideInBase(GameField field) {
        targetPoint = null;
        goingToBase = false;
        field.moveActorInBackground(this);

        cameToBase(field);
    }

    protected GamePoint getBaseAt() {
        return baseAt;
    }

    protected GameActor getFollowingActor() {
        return followingActor;
    }

    public boolean isGoingToBase() {
        return goingToBase;
    }

    public GamePoint getTargetPoint() {
        return targetPoint;
    }

    abstract protected GameActor findTarget(GameField field);
    abstract protected void processFollowing(GameField field);
    abstract protected void cameToBase(GameField field);
    abstract protected void onFailedMission(GameField field);
}
