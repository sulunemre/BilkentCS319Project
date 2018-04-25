package com.mygdx.game.sprites;

import com.badlogic.gdx.utils.Array;

public class GameWorld {
    private static GameWorld gameWorld = new GameWorld();

    private Array<GameElement> gameElementsArray;

    public static GameWorld getInstance() {
        return gameWorld;
    }

    private GameWorld() {
    }
}
