package ru.spbau.mit.drunkard.game;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Denis Zharkov
 */
public class GameField {
    private final int height;
    private final int width;
    private final GameActor[][] map;

    public GameField(int height, int width) {
        this.height = height;
        this.width = width;
        map = new GameActor[height][width];
    }

    public List<GameActor> getMovableActors() {
        ArrayList<GameActor> result = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (!isFree(j,i) && map[i][j].isActing()) {
                    result.add(map[i][j]);
                }
            }
        }

        return result;
    }

    public boolean isFree(int x, int y) {
        return map[y][x] == null;
    }

    public boolean isFree(GamePoint point) {
        return isFree(point.column, point.row);
    }

    public void putActor(GameActor actor, GamePoint point) {
        map[point.row][point.column] = actor;
        actor.setPoint(point);
    }


    public void moveActor(GameActor actor, GamePoint point) {
        map[actor.getPoint().row][actor.getPoint().column] = null;
        putActor(actor, point);
    }

    public boolean isValidPoint(GamePoint point) {
        return point.row >= 0 && point.column >= 0
                                && point.row < height
                                && point.column < width;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public GameActor at(int x, int y) {
        return map[y][x];
    }

    public GameActor at(GamePoint point) {
        return map[point.row][point.column];
    }
}
