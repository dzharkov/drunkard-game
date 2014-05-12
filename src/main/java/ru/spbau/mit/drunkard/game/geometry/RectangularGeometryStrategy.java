package ru.spbau.mit.drunkard.game.geometry;

public class RectangularGeometryStrategy extends GeometryStrategy {
    private static final int[][] directionVectors = new int[][]{
            new int[]{1, 0},
            new int[]{0, 1},
            new int[]{-1, 0},
            new int[]{0, -1}
    };

    @Override
    public int[][] getDirectionVectors(GamePoint point) {
        return directionVectors;
    }

    @Override
    public boolean isValidPoint(int x, int y, int width, int height) {
        return x >= 0 && y >= 0
                && y < height
                && x < width;
    }

    @Override
    public boolean isAdjacentPoints(GamePoint a, GamePoint b) {
        return (Math.abs(a.row - b.row) + Math.abs(a.column - b.column)) == 1;
    }
}
