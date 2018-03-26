package com.mygdx.game.sprites.powerups;


public class HealthPotion extends Powerups {
    private int healthIncrease;
    private enum type{};

    public HealthPotion(int spawnRate, int healthIncrease) {
        super(spawnRate);
        this.healthIncrease = healthIncrease;
    }

    public int getHelathIncrease() {
        return healthIncrease;
    }

    public void setHelathIncrease(int helathIncrease) {
        this.healthIncrease = helathIncrease;
    }
}
