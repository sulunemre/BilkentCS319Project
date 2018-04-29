package com.mygdx.game.sprites.enemies;

public class Abomination extends Enemy {
    public Abomination(float x, float y){
        super(x, y, 1, 5, "paladin.png", new MixedAttack());
        speed = 3;
    }
}
