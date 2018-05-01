package com.mygdx.game.sprites.powerups;

import com.mygdx.game.sprites.GameElement;

public abstract class Powerups extends GameElement{
    protected int spawnRate;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     * @param texturePath Path of the texture image in the assets folder
     */
    public Powerups(float x, float y, String texturePath) {
        super(x, y, texturePath);
    }
}
