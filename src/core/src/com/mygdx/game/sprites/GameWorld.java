package com.mygdx.game.sprites;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;

public class GameWorld implements Serializable {
    private static GameWorld gameWorld = new GameWorld();

    private Array<GameElement> gameElementsArray;

    public static GameWorld getInstance() {
        return gameWorld;
    }

    private GameWorld() {
    }
}
