package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class GamePoint {
    public int row, column;

    public GamePoint(int x, int y) {
        this.row = y;
        this.column = x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (!(o instanceof GamePoint)) { return false; }

        GamePoint point = (GamePoint) o;

        if (column != point.column) { return false; }
        if (row != point.row) { return false; }

        return true;
    }

    public int distance(GamePoint other) {
        return Math.abs(other.row - row) + Math.abs(other.column - column);
    }
}
