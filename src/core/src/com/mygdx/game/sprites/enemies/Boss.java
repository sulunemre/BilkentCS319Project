package com.mygdx.game.sprites.enemies;

public class Boss extends Enemy {
    public Boss(float x, float y) {
        super(x, y, 1, 5, "paladin.png", new MixedAttack(), 250);
        speed = 5;
    }
}
