package com.mygdx.game.sprites.enemies;

public class Gargoyle extends Enemy {
    public Gargoyle(float x, float y) {
        super(x, y, 1, 3, "paladinReverse.png",  new RangeAttack(), 75);
        speed = 15;
    }
}
