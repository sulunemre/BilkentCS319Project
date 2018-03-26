package com.mygdx.game.sprites.powerups;

public class LightswornArmor extends PaladinArmor {
    private int maxHealthIncrease;
    private int meleeDamage;

    public LightswornArmor(int spawnRate, int holyLightIncrease, int maxHealthIncrease, int meleeDamage) {
        super(spawnRate, holyLightIncrease);
        this.maxHealthIncrease = maxHealthIncrease;
        this.meleeDamage = meleeDamage;
    }

    public int getMaxHealthIncrease() {
        return maxHealthIncrease;
    }

    public void setMaxHealthIncrease(int maxHealthIncrease) {
        this.maxHealthIncrease = maxHealthIncrease;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }
}
