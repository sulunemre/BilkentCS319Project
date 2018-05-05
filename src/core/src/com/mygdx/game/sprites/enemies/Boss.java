package com.mygdx.game.sprites.enemies;

class Boss extends Enemy {
    Boss(float x, float y) {
        super(x, y, 200, "paladin.png", new MixedAttack(), 250);
        speed = 5;
    }
}
