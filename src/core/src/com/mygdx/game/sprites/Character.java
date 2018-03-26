package com.mygdx.game.sprites;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Character extends GameElement{
    protected int health;
    protected int maxHealth;
    protected double speed;
    protected int damage;

    public Character(int health, int maxHealth, double speed, int damage) {
        super();
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.damage = damage;
    }

    public abstract void attack(int damage);
    public abstract void move();
    public void update()
    {
        position.add(velocity);
        bounds.setPosition(position);
        if (position.y < 0)
            position.y = 0;
        if (position.y > 260)
            position.y = 260;
        if (position.x < 0)
            position.x = 0;
        if (position.x > 500)
            position.x = 500;
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

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
