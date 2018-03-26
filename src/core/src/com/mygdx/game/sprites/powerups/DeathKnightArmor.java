package com.mygdx.game.sprites.powerups;

public class DeathKnightArmor extends Powerups {
    private int deathCoilIncrease;
    private int meleeDamage;

    public DeathKnightArmor(int spawnRate, int deathCoilIncrease, int meleeDamage) {
        super(spawnRate);
        this.deathCoilIncrease = deathCoilIncrease;
        this.meleeDamage = meleeDamage;
    }

    public int getDeathCoilIncrease() {
        return deathCoilIncrease;
    }

    public void setDeathCoilIncrease(int deathCoilIncrease) {
        this.deathCoilIncrease = deathCoilIncrease;
    }

    public int getMeleeDamage() {
        return meleeDamage;
    }

    public void setMeleeDamage(int meleeDamage) {
        this.meleeDamage = meleeDamage;
    }
}
