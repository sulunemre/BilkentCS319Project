package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Character;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.PlayerCharacter;
import com.mygdx.game.sprites.Rock;

public abstract class Enemy extends Character{
    protected int spawnRate;
    protected int coolDown;
    protected AttackStrategy attackStrategy;

    public Enemy(float x, float y, int spawnRate, int coolDown, String texturePath, AttackStrategy attackStrategy){
        super(x, y, texturePath);
        this.spawnRate = spawnRate;
        this.coolDown = coolDown;
        this.attackStrategy = attackStrategy;
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


    public void chase(float xLocation, float yLocation) {
            float xDifference = xLocation - getPosition().x;
            float yDifference = yLocation - getPosition().y;
            Vector2 distance = new Vector2(xDifference, yDifference);
            Vector2 unitVector = new Vector2(xDifference / distance.len(), yDifference / distance.len());
            Vector2 newVelocity = unitVector.scl((float) speed);
            setDirection(newVelocity);
    }

//    public void update(Rectangle enemy)
//    {
//        //position.add(direction);
//
//        Vector2 positionTemp = new Vector2(position);
//        Rectangle boundsTemp = new Rectangle(bounds);
//
//        positionTemp.add(direction);
//
//        if ( !enemy.overlaps( boundsTemp.setPosition(positionTemp))) {
//            position.add(direction);
//            bounds.setPosition(position);
//        }
//        if (position.y < 0)
//            position.y = 0;
//        if (position.y > 260)
//            position.y = 260;
//        if (position.x < 0)
//            position.x = 0;
//        if (position.x > 500)
//            position.x = 500;
//    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    public boolean collides(Rectangle enemy){
        //TODO: collision detection, controller classa taşınacak
        return enemy.overlaps(bounds);

    }

    public void attack(Vector2 positionToBeAttacked){
        attackStrategy.attack(positionToBeAttacked);
    }
}
