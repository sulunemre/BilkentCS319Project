package com.mygdx.game.sprites;

import com.badlogic.gdx.utils.Array;

import java.io.Serializable;

public class GameWorld implements Serializable {
    private static GameWorld gameWorld = new GameWorld();

    private Array<GameElement> gameElementsArray  = new Array<GameElement>();

    public static GameWorld getInstance() {
        return gameWorld;
    }

    private GameWorld() {
    }

    /**
     * Adds one or more GameElements to the array
     * @param ge
     */
    public void addGameElements(GameElement... ge){
        gameElementsArray.addAll(ge);
    }

    /**
     * Removes a GameElement from the array
     * @param ge
     */
    public void removeGameElements(GameElement ge){
        gameElementsArray.removeValue(ge, false);
    }

    public Array<GameElement> getGameElementsArray() {
        return gameElementsArray;
    }
}
