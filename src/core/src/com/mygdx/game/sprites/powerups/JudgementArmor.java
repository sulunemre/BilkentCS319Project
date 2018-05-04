package com.mygdx.game.sprites.powerups;

/**
 * This is the first possible armor upgrade which
 * increases maxHealth by 50 and also increases the Holy Light special
 * abilities damage by 10.
 */
public class JudgementArmor extends PaladinArmor {
    private static int maxHealthIncrease = 50;
    private static int holyLightIncrese = 10;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     */
    public JudgementArmor(float x, float y) {
        super(x, y, "lesserHealthPotion.png");
    }

    public static int getMaxHealthIncrease() {
        return maxHealthIncrease;
    }

    public static int getHolyLightIncrese() {
        return holyLightIncrese;
    }
}
