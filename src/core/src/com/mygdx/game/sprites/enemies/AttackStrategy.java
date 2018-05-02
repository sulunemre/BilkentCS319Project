package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;

public interface AttackStrategy {
    /**
     * Enemy attacks to the given position.
     * @param initialPosition
     * @param positionToBeAttacked location of the target
     * @param damage
     */
    void attack(Vector2 initialPosition, Vector2 positionToBeAttacked, int damage);
}
