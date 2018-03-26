package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Grunt extends Enemy {
    public Grunt(int health, int maxHealth, double speed, int damage, int spawnRate, int coolDown){
        super(health, maxHealth, speed, damage, spawnRate, coolDown);
        elementTexture = new Texture("paladin.png");
    }

    public void attack(int damage){}; //TODO: implement
}
