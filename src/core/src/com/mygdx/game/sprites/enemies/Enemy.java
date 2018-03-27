package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Character;

public abstract class Enemy extends Character{
    private int spawnRate;
    private int coolDown;

    public Enemy(int health, int maxHealth, double speed, int damage, int spawnRate, int coolDown){
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

    public void chase(float xLocation, float yLocation)
    {
            float xDifference = xLocation - getPosition().x;
            float yDifference = yLocation - getPosition().y;
            Vector2 distance = new Vector2(xDifference, yDifference);
            Vector2 unitVector = new Vector2(xDifference / distance.len(), yDifference / distance.len());
            Vector2 newVelocity = unitVector.scl((float) speed);
            setVelocity(newVelocity);



    }

    public void update(Rectangle enemy)
    {
        //position.add(velocity);

        Vector2 positionTemp = new Vector2(position);
        Rectangle boundsTemp = new Rectangle(bounds);

        positionTemp.add(velocity);

        if ( !enemy.overlaps( boundsTemp.setPosition(positionTemp))) {
            position.add(velocity);
            bounds.setPosition(position);
        }
        if (position.y < 0)
            position.y = 0;
        if (position.y > 260)
            position.y = 260;
        if (position.x < 0)
            position.x = 0;
        if (position.x > 500)
            position.x = 500;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean collides(Rectangle enemy){
        return enemy.overlaps(bounds);

    }
}
