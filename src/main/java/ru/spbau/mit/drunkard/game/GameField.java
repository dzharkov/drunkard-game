package ru.spbau.mit.drunkard.game;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Denis Zharkov
 */
public class GameField {
    private final int height;
    private final int width;
    private final GameActor[][] map;

    private final Set<GameActor> movableActors = new HashSet<>();

    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
        map = new GameActor[height + 2][width + 2];
    }

    public ArrayList<GameActor> getMovableActors() {
        return new ArrayList<>(movableActors);
    }

    public boolean isFree(int x, int y) {
        return at(x, y) == null;
    }

    public boolean isFree(GamePoint point) {
        return isFree(point.column, point.row);
    }

    private void setValue(GamePoint point, GameActor value) {
        map[point.row + 1][point.column + 1] = value;
    }

    public void putActorInBackground(GameActor gameActor) {
        if (gameActor.isActing()) {
            movableActors.add(gameActor);
        }
    }

    public void putActor(GameActor actor, GamePoint point) {
        setValue(point, actor);
        actor.setPoint(point);

        if (actor.isActing()) {
            movableActors.add(actor);
        } else {
            movableActors.remove(actor);
        }
    }

    public void moveActorInBackground(GameActor actor) {
        setValue(actor.getPoint(), null);
        actor.setPoint(null);
    }

    public void deleteActor(GameActor actor) {
        moveActorInBackground(actor);
        movableActors.remove(actor);
    }

    public void moveActor(GameActor actor, GamePoint point) {
        setValue(actor.getPoint(), null);
        putActor(actor, point);
    }

    public boolean isValidPoint(GamePoint point) {
        return isValidPoint(point.column, point.row);
    }

    public boolean isValidPoint(int x, int y) {
        return x >= 0 && y >= 0
            && y < height
            && x < width;
    }


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameActor at(int x, int y) {
        return map[y + 1][x + 1];
    }

    public GameActor at(GamePoint point) {
        return map[point.row + 1][point.column + 1];
    }
}
