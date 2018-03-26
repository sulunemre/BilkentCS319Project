package com.mygdx.game.sprites.enemies;

import com.mygdx.game.sprites.Character;

public abstract class Enemy extends Character{
    private int spawnRate;
    private int coolDown;

    public Enemy(int health, int maxHealth, int speed, int damage, int spawnRate, int coolDown){
        super(health, maxHealth, speed, damage);
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

    @Override
    public void move(){

    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
}
