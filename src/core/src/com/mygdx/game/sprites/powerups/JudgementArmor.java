package com.mygdx.game.sprites.powerups;

public class JudgementArmor extends PaladinArmor {
    private int maxHealthIncrease;

    public JudgementArmor(int spawnRate, int holyLightIncrease, int maxHealthIncrease) {
        super(spawnRate, holyLightIncrease);
        this.maxHealthIncrease = maxHealthIncrease;
    }

    public int getMaxHealthIncrease() {
        return maxHealthIncrease;
    }

    public void setMaxHealthIncrease(int maxHealthIncrease) {
        this.maxHealthIncrease = maxHealthIncrease;
    }
}
