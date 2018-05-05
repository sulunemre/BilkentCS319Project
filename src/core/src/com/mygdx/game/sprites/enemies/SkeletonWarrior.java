package com.mygdx.game.sprites.enemies;

class SkeletonWarrior extends Enemy {
    SkeletonWarrior(float x, float y) {
        super(x, y, 160, "skeletonWarrior.png",  new MeleeAttack(), 80);
        speed = 1.5;
        damage = 10;
    }
}
