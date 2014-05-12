package ru.spbau.mit.drunkard.game.geometry;

public abstract class GeometryStrategy {
    public abstract int[][] getDirectionVectors(GamePoint point);

    public abstract boolean isValidPoint(int column, int row, int width, int height);

    public abstract boolean isAdjacentPoints(GamePoint a, GamePoint b);
}
