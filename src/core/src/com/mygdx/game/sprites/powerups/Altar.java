package com.mygdx.game.sprites.powerups;

/**
 * Altar makes health = maxHealth
 */
public class Altar extends Powerups {
    /**
     * type 0 is for paladins type 1 is for death knights
     */
    private int type;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     */
    public Altar(float x, float y) {
        super(x, y, "altarOfKings.png");
    }
}
