package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.HolyLight;
import com.mygdx.game.sprites.PlayerCharacter;

public class RangeAttack implements AttackStrategy {
    /**
     * Finds the direction of the projectile(holy light) by some
     * vector calculation and creates a projectile that starts
     * moving from attacker and towards to victim.
     * @param attacker enemy that attacks
     * @param victim   player to be attacked
     * @param damage   hit power of the attack
     */
    @Override
    public void attack(Enemy attacker, PlayerCharacter victim, int damage) {
        // Calculate the direction
        Vector2 targetPosition = new Vector2(victim.getPosition());
        HolyLight holyLight = new HolyLight(attacker.getPositionx(), attacker.getPositiony(), damage);
        Vector2 holyLightDirection = targetPosition.mulAdd(attacker.getPosition(), -1).nor();
        holyLight.setDirection(holyLightDirection);

        // Add holy light to the game world
        GameWorld gameWorld = GameWorld.getInstance();
        gameWorld.addGameElements(holyLight);
        gameWorld.addEnemyProjectile(holyLight);
        attacker.resetCooldown();
    }
}
