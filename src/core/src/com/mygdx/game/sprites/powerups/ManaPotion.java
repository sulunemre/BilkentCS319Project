package com.mygdx.game.sprites.powerups;

public class ManaPotion extends Powerups {
    private int manaIncrease;
    private enum type{};

    public ManaPotion(int spawnRate, int manaIncrease) {
        super(spawnRate);
        this.manaIncrease = manaIncrease;
    }

    public int getManaIncrease() {
        return manaIncrease;
    }

    public void setManaIncrease(int manaIncrease) {
        this.manaIncrease = manaIncrease;
    }
}
