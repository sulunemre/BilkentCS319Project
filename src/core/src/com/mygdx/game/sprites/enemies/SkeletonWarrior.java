package com.mygdx.game.sprites.enemies;

public class SkeletonWarrior extends Enemy {
    public SkeletonWarrior(float x, float y) {
        super(x, y, 1, 3, "skeletonWarrior.png",  new RangeAttack(), 80);
        speed = 1.5;
    }
}
