package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;
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

    public void chase(int xLocation, int yLocation)
    {
        float xDifference = xLocation - getPosition().x;
        float yDifference = yLocation - getPosition().y;
        Vector2 distance = new Vector2(xDifference, yDifference);
        Vector2 unitVector = new Vector2(xDifference / distance.len(), yDifference / distance.len());
        Vector2 newVelocity = unitVector.scl(speed);

        setVelocity(newVelocity);
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }
}
