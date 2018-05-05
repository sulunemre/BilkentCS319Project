package com.mygdx.game.sprites.enemies;

class Grunt extends Enemy {
    Grunt(float x, float y) {
        super(x, y, 100, "grunt.png", new MeleeAttack(), 100);
        speed = 1;
    }
}
