package com.mygdx.game.sprites.enemies;

public class Abonimation extends Enemy {
    public Abonimation(int health, int maxHealth, int speed, int damage, int spawnRate, int coolDown){
        super(health, maxHealth, speed, damage, spawnRate, coolDown);
    }
    public void hook()
    {
    }

    public void attack(int damage){}; //TODO: implement
    public void update(){}; //TODO: implement
}
