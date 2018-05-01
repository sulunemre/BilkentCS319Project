package com.mygdx.game.sprites.enemies;

public class Abomination extends Enemy {
    public Abomination(float x, float y){
        super(x, y, 1, 5, "abomination.png", new MixedAttack(), 175);
        speed = 3;
    }
}
