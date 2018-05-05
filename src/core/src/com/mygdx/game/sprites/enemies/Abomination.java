package com.mygdx.game.sprites.enemies;

class Abomination extends Enemy {
    Abomination(float x, float y){
        super(x, y, 300, "abomination.png", new MixedAttack(), 175);
        speed = 1;
    }
}
