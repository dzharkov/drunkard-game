package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public interface GameObserver {
    void onAfterStep(GameField field);
}
