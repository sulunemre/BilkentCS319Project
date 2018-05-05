package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.Character;
import com.mygdx.game.sprites.PlayerCharacter;

public abstract class Enemy extends Character{
    int coolDown;
    private int maxCoolDown;
    private AttackStrategy attackStrategy;
    Texture leftProfile, rightProfile, leftProfileAttack, rightProfileAttack;

    /**
     * @param x x coordinate
     * @param y y coordinate
     * @param maxCoolDown the time interval between attacks. The longer cooldown, the less frequent attack
     * @param texturePath filename of the default texture in the assets folder
     * @param attackStrategy the type of the attack
     * @param maxHealth initial health
     */
    public Enemy(float x, float y, int maxCoolDown, String texturePath, AttackStrategy attackStrategy, int maxHealth){
        super(x, y, texturePath, maxHealth);
        this.maxCoolDown = maxCoolDown;
        coolDown = maxCoolDown;
        this.attackStrategy = attackStrategy;
    }

    public int getCoolDown() {
        return coolDown;
    }

    /**
     * Sets the direction of the enemy
     * according to target location
     * @param xLocation target x coordinates
     * @param yLocation target y coordinates
     */
    public void chase(float xLocation, float yLocation) {
        float xDifference = xLocation - getPosition().x;
        float yDifference = yLocation - getPosition().y;
        Vector2 distance = new Vector2(xDifference, yDifference);
        direction.set(xDifference / distance.len(), yDifference / distance.len());

        if(direction.x  < 0)
            elementTexture = leftProfile;
        else
            elementTexture = rightProfile;
    }

    public void setCoolDown(int coolDown) {
        this.coolDown = coolDown;
    }

    /**
     * An enemy attacks only if cooldown is zero
     * @param victim player object that enemy attacks on
     */
    public void attack(PlayerCharacter victim){
        if(coolDown == 0)
            attackStrategy.attack(this, victim, damage);
    }

    public void decrementCooldown(){
        coolDown = coolDown - 1;
        if(coolDown < 0)
            coolDown = maxCoolDown;
    }

    public void resetCooldown(){
        coolDown = maxCoolDown;
    }

    public int getKillingReward(){
        return damage * maxHealth;
    }
    public void hook(PlayerCharacter playerCharacter){
        Vector2 circleCenterCoordinates = new Vector2(position);
        Circle hookBounds = new Circle(circleCenterCoordinates, 25);
        if(Intersector.overlaps(hookBounds,playerCharacter.getBounds())) {
            float xDifference = position.x - playerCharacter.getPosition().x;
            float yDifference = position.y - playerCharacter.getPosition().y;
            Vector2 distance = new Vector2(xDifference, yDifference);
            playerCharacter.setDirection(xDifference / distance.len(), yDifference / distance.len());
        }
    }
}
