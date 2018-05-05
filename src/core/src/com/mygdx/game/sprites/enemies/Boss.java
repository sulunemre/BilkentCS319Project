package com.mygdx.game.sprites.enemies;

public class Boss extends Enemy {
    public Boss(float x, float y) {
        super(x, y, 300, "boss.png", new MixedAttack(), 250);
        speed = 1.5;
        damage = 20;
        coolDown = 0;
    }
}
