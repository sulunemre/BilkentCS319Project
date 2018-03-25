package com.mygdx.game.sprites.powerups;

public class PaladinArmor extends Powerups {
    private int holyLightIncrease;

    public PaladinArmor(int spawnRate, int holyLightIncrease) {
        super(spawnRate);
        this.holyLightIncrease = holyLightIncrease;
    }

    public int getHolyLightIncrease() {
        return holyLightIncrease;
    }

    public void setHolyLightIncrease(int holyLightIncrease) {
        this.holyLightIncrease = holyLightIncrease;
    }
}
