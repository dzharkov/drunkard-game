package ru.spbau.mit.drunkard.game;

import ru.spbau.mit.drunkard.game.actors.*;
import ru.spbau.mit.drunkard.game.geometry.GamePoint;
import ru.spbau.mit.drunkard.game.geometry.GeometryStrategy;
import ru.spbau.mit.drunkard.game.geometry.HexagonalGeometryStrategy;
import ru.spbau.mit.drunkard.game.geometry.RectangularGeometryStrategy;

import java.util.Arrays;

/**
 * @author Denis Zharkov
 */
public class DrunkardGameBuilder {

    private DrunkardGame buildGame(GeometryStrategy geometryStrategy) {
        Tavern tavern = new Tavern(new GamePoint(9, -1), new GamePoint(9, 0));
        PillarActor pillar = new PillarActor(new GamePoint(7, 7));

        LampActor lamp = new LampActor(new GamePoint(10, 3));
        PoliceOfficeActor policeOffice = new PoliceOfficeActor(new GamePoint(15, 3));
        OfficerActor officerActor = new OfficerActor(lamp, new GamePoint(14, 3));

        BottleShopActor bottleShopActor = new BottleShopActor(new GamePoint(-1, 4));
        BeggarActor beggarActor = new BeggarActor(new GamePoint(0, 4));

        GameField field = new GameField(15, 15, geometryStrategy);

        DrunkardGame game = new DrunkardGame(
                field,
                Arrays.asList(tavern, pillar, lamp, policeOffice, officerActor, bottleShopActor, beggarActor),
                500
        );

        return game;
    }

    public DrunkardGame buildRectangularGame() {
        return buildGame(new RectangularGeometryStrategy());
    }

    public DrunkardGame buildHexagonalGame() {
        return buildGame(new HexagonalGeometryStrategy());
    }
}
