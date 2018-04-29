package com.mygdx.game.sprites.enemies;

public class SkeletonWarrior extends Enemy {
    public SkeletonWarrior(float x, float y) {
        super(x, y, 1, 3, "paladin.png",  new RangeAttack());
        speed = 7;
    }
}
