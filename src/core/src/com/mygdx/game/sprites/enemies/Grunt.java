package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

class Grunt extends Enemy {
    Grunt(float x, float y) {
        super(x, y, 100, "grunt.png", new MeleeAttack(), 100);
        speed = 1;
        leftProfile = new Texture("gruntReverse.png");
        rightProfile = new Texture("grunt.png");
        rightProfileAttack = new Texture( "gruntAttackRight.png");
        leftProfileAttack = new Texture("gruntAttackLeft.png");
    }
}
