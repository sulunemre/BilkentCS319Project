package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends GameElement{
    protected int health;
    protected int maxHealth;
    protected int damage;

    public Character(float x, float y, String texturePath, int maxHealth) {
        super(x,y, texturePath);
        this.maxHealth = maxHealth;
        health = maxHealth;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void reduceHealth(int damage) {
        health = health - damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public boolean isAlive(){
        return health > 0;
    }

    public void increaseHealth(int increaseAmount){
        health = health + increaseAmount;
        if(health > maxHealth)
            health = maxHealth;

    }

    /*public void move(float x, float y, GameWorld world){
        if (moveCheck(x,y,world)){
            position.x = position.x + x;
            position.y = position.y + y;
        }
    };*/

    //abstract boolean moveCheck(float x, float y, GameWorld world);
}
