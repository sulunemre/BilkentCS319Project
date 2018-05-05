package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

class SkeletonWarrior extends Enemy {
    SkeletonWarrior(float x, float y) {
        super(x, y, 160, "skeletonWarriorRight.png",  new MeleeAttack(), 80);
        speed = 7;
        damage = 10;
        leftProfile = new Texture("skeletonWarriorLeft.png");
        rightProfile = new Texture("skeletonWarriorRight.png");
        leftProfileAttack = new Texture("skeletonWarriorAttackLeft.png");
        rightProfileAttack = new Texture("skeletonWarriorAttackRight.png");
    }
}
