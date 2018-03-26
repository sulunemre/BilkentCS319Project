package com.mygdx.game.sprites;

public abstract class Character {
    protected int health;
    protected int maxHealth;
    protected int speed;
    protected int damage;

    public Character(int health, int maxHealth, int speed, int damage) {
        this.health = health;
        this.maxHealth = maxHealth;
        this.speed = speed;
        this.damage = damage;
    }

    public abstract void attack(int damage);
    public abstract void move();
    public abstract void update();

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

    public int getSpeed() {
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
