package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Grunt extends Enemy {
    public Grunt(float x, float y) {
        super(x, y, 100, "grunt.png", new MeleeAttack(), 100);
        speed = 1;
    }
}
