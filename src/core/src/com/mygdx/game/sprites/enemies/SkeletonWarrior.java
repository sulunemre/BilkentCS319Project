package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;

public class SkeletonWarrior extends Enemy {
    public SkeletonWarrior(float x, float y) {
        super(x, y, 160, "skeletonWarriorRight.png",  new MeleeAttack(), 80);
        speed = 1.5;
        damage = 10;
        leftProfile = new Texture("skeletonWarriorLeft.png");
        rightProfile = new Texture("skeletonWarriorRight.png");
        leftProfileAttack = new Texture("skeletonWarriorAttackLeft.png");
        rightProfileAttack = new Texture("skeletonWarriorAttackRight.png");
    }
}
