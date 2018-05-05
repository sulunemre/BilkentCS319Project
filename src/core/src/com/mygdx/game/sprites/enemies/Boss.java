package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

class Boss extends Enemy {
    Boss(float x, float y) {
        super(x, y, 300, "boss.png", new MixedAttack(), 250);
        speed = 1.5;
        damage = 20;
        coolDown = 0;
        speed = 1.5;
        damage = 20;
        leftProfile = new Texture("bossLeft.png");
        rightProfile = new Texture("bossRight.png");
        leftProfileAttack = new Texture("bossLeftAttack.png");
        rightProfileAttack = new Texture("bossRightAttack.png");
    }
}
