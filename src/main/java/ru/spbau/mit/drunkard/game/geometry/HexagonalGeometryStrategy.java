package ru.spbau.mit.drunkard.game.geometry;

public class HexagonalGeometryStrategy extends GeometryStrategy {
    private static final int[][] directionVectorsTemplate = new int[][]{
            //in the same row
            new int[]{1, 0},
            new int[]{-1, 0},

            //upper and lower row with same column
            new int[]{0, 1},
            new int[]{0, -1},

            //upper and lower row with different column depending on row oddness
            new int[]{0, 1},
            new int[]{0, -1},
    };

    @Override
    public int[][] getDirectionVectors(GamePoint point) {
        int[][] result = new int[directionVectorsTemplate.length][];
        for (int i = 0; i < directionVectorsTemplate.length; i++) {
            result[i] = directionVectorsTemplate[i].clone();
        }

        int neighbourRowsDelta = point.column % 2 == 0 ? 1 : -1;
        result[4][0] = neighbourRowsDelta;
        result[5][0] = neighbourRowsDelta;

        return result;
    }

    @Override
    public boolean isValidPoint(int column, int row, int width, int height) {
        int rowWidth = row % 2 == 0 ? width - 1 : width;
        return column >= 0 && row >= 0
                && row < height
                && column < rowWidth;
    }

    @Override
    public boolean isAdjacentPoints(GamePoint a, GamePoint b) {
        for (int[] v : getDirectionVectors(a)) {
            if (new GamePoint(a.column + v[0], a.row + v[1]).equals(b)) {
                return true;
            }
        }

        return false;
    }
}
