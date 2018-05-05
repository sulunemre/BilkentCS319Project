package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

class Gargoyle extends Enemy {
    Gargoyle(float x, float y) {
        super(x, y, 200, "gargoyle.png",  new RangeAttack(), 75);
        speed = 1;
        damage = 10;
        leftProfile = new Texture("gargoyle.png");
        rightProfile = new Texture("gargoyle.png");
        leftProfileAttack = new Texture("gargoyle.png");
        rightProfileAttack = new Texture("gargoyle.png");
    }
}
