package com.mygdx.game.sprites.powerups;

/**
 * Increases melee damage by 25
 */
public class Ashbringer extends PaladinArmor {
    private static int meleeDamageIncrease = 25;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     */
    Ashbringer(float x, float y) {
        super(x, y, "ashbringer.png");
    }

    public static int getMeleeDamageIncrease() {
        return meleeDamageIncrease;
    }
}
