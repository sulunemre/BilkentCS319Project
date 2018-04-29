package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Grunt extends Enemy {
    public Grunt(float x, float y) {
        super(x, y, 1, 5, "paladin.png", new MeleeAttack());
        speed = 5;
    }
}
