package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.PlayerCharacter;

public interface AttackStrategy {
    /**
     * Enemy attacks to the given position.
     * @param initialPosition
     * @param positionToBeAttacked location of the target
     * @param damage
     */
    void attack(Enemy attacker, PlayerCharacter Victim, int damage);
}
