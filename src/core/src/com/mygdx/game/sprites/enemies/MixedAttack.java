package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.HolyLight;
import com.mygdx.game.sprites.PlayerCharacter;

public class MixedAttack implements AttackStrategy {
    //TODO: javadoc
    @Override
    public void attack(Enemy attacker, PlayerCharacter victim, int damage) {
        Vector2 circleCenterCoordinates;

        circleCenterCoordinates = new Vector2(attacker.getPosition());

        int meleeRange = attacker.getWidth() * 2;
        Circle meleeRangeBounds = new Circle(circleCenterCoordinates, meleeRange);

        if (Intersector.overlaps(meleeRangeBounds,victim.getBounds())) {
            victim.reduceHealth(damage);
            attacker.resetCooldown();
        }
        else{
            Vector2 targetPosition = new Vector2(victim.getPosition());
            HolyLight holyLight = new HolyLight(attacker.getPositionx(), attacker.getPositiony(), damage);
            Vector2 holyLightDirection = targetPosition.mulAdd(attacker.getPosition(), -1).nor();
            holyLight.setDirection(holyLightDirection);

            // Add holy light to the game world
            GameWorld gameWorld = GameWorld.getInstance();
            gameWorld.addGameElements(holyLight);
            gameWorld.addEnemyProjectile(holyLight);
            attacker.setCoolDown(20);
        }
    }
}
