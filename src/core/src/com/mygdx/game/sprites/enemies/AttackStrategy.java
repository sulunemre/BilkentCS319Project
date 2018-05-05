package com.mygdx.game.sprites.enemies;

import com.mygdx.game.sprites.PlayerCharacter;

public interface AttackStrategy {
    /**
     * Enemy attacks to the given position.
     * @param attacker enemy that attacks
     * @param victim player to be attacked
     * @param damage hit power of the attack
     */
    void attack(Enemy attacker, PlayerCharacter victim, int damage);
}
