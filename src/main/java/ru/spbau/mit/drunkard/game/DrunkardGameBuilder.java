package ru.spbau.mit.drunkard.game;

/**
 * @author Denis Zharkov
 */
public class DrunkardGameBuilder {

    public DrunkardGame buildGame() {
        Tavern tavern = new Tavern(new GamePoint(9, 0));
        PillarActor lamp = new PillarActor(new GamePoint(7, 7));
        GameField field = new GameField(15, 15);

        DrunkardGame game = new DrunkardGame(field, tavern, lamp);

        return game;
    }
}
