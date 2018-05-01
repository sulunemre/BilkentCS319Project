package com.mygdx.game.sprites.powerups;

public abstract class PaladinArmor extends Powerups {
    private int holyLightIncrease;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     * @param texturePath Path of the texture image in the assets folder
     */
    public PaladinArmor(float x, float y, String texturePath) {
        super(x, y, texturePath);
    }
}
