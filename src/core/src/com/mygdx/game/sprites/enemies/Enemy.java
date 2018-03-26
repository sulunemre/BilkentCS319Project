package com.mygdx.game.sprites.enemies;

public class Enemy {
    private int spawnRate;
    private int coolDown;

    public Enemy(int spawnRate, int coolDown) {
        this.spawnRate = spawnRate;
        this.coolDown = coolDown;
    }

    public int getSpawnRate() {
        return spawnRate;
    }

    public void setSpawnRate(int spawnRate) {
        this.spawnRate = spawnRate;
    }

    public int getCoolDown() {
        return coolDown;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
}
