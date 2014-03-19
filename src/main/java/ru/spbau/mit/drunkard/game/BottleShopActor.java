package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class BottleShopActor extends GameActor {
    public BottleShopActor(GamePoint point) {
        super(point);
    }

    @Override
    boolean isActing() {
        return false;
    }

    @Override
    void performStep(GameField field) {

    }
}
