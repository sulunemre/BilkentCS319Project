package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;

public interface AttackStrategy {
    /**
     * Enemy attacks to the given position.
     * @param positionToBeAttacked location of the target
     */
    void attack(Vector2 positionToBeAttacked);
}
