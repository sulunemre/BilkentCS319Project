package com.mygdx.game.sprites.powerups;

public class Ashbringer extends PaladinArmor {
    private int meleeDamageIncrease;

    public Ashbringer(int spawnRate, int holyLightIncrease, int meleeDamageIncrease) {
        super(spawnRate, holyLightIncrease);
        this.meleeDamageIncrease = meleeDamageIncrease;
    }

    public int getMeleeDamageIncrease() {
        return meleeDamageIncrease;
    }

    public void setMeleeDamageIncrease(int meleeDamageIncrease) {
        this.meleeDamageIncrease = meleeDamageIncrease;
    }
}
