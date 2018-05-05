package com.mygdx.game.sprites.enemies;

class Gargoyle extends Enemy {
    Gargoyle(float x, float y) {
        super(x, y, 200, "gargoyle.png",  new MeleeAttack(), 75);
        speed = 1;
        damage = 10;
    }
}
