package com.mygdx.game.sprites.powerups;

/**
 * This is the second possible armor upgrade which
 * increases max health by 75. Melee deals 25 additional damage. Holy Light
 * deals 25 additional damage. It can only be worn by Arathras the Pure.
 */
public class LightswornArmor extends PaladinArmor {
    private static int maxHealthIncrease = 75;
    private static int meleeDamage = 25;
    private static int holyLightDamage = 25;

    /**
     * @param x           Spawn location x coordinate
     * @param y           Spawn location y coordinate
     * @param texturePath Path of the texture image in the assets folder
     */
    public LightswornArmor(float x, float y, String texturePath) {
        super(x, y, "lightsworn.png");
    }

    public static int getMaxHealthIncrease() {
        return maxHealthIncrease;
    }

    public static int getMeleeDamage() {
        return meleeDamage;
    }

    public static int getHolyLightDamage() {
        return holyLightDamage;
    }
}
