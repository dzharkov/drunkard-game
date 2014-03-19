package ru.spbau.mit.drunkard.game;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author Denis Zharkov
 */
public class PathUtils {
    private final static int[] dx = new int[]{-1, 1, 0, 0};
    private final static int[] dy = new int[]{0, 0, 1, -1};

    public static GamePoint findNextPointInPath(GamePoint from, GamePoint targetPoint, GameField gameField) {
        Queue<GamePoint> queue = new ArrayDeque<>();

        boolean[][] accessible = new boolean[gameField.getHeight()][gameField.getWidth()];
        GamePoint[][] prev = new GamePoint[gameField.getHeight()][gameField.getWidth()];

        accessible[from.row][from.column] = true;

        queue.add(from);

        while (!queue.isEmpty() && !queue.peek().equals(targetPoint)) {
            GamePoint current = queue.poll();

            for (int i = 0; i < 4; i++) {
                GamePoint next = new GamePoint(current.column + dx[i], current.row + dy[i]);

                if (
                    gameField.isValidPoint(next) &&
                        !accessible[next.row][next.column] &&
                        (gameField.isFree(next) || next.equals(targetPoint))
                    ) {
                    accessible[next.row][next.column] = true;
                    prev[next.row][next.column] = current;

                    queue.add(next);
                }
            }
        }

        if (queue.isEmpty()) {
            return null;
        }

        GamePoint prevPoint = targetPoint;
        while (!prev[prevPoint.row][prevPoint.column].equals(from)) {
            prevPoint = prev[prevPoint.row][prevPoint.column];
        }

        return prevPoint;
    }
}
