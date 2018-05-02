package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.sprites.HolyLight;

public class RangeAttack implements AttackStrategy {
    @Override
    public void attack(Vector2 initialPosition, Vector2 positionToBeAttacked, int damage) {
        // Calculate the direction
        Vector2 targetPosition = new Vector2(positionToBeAttacked);
        HolyLight holyLight = new HolyLight(initialPosition.x, initialPosition.y, damage);
        Vector2 holyLightDirection = targetPosition.mulAdd(initialPosition, -1).nor();
        holyLight.setDirection(holyLightDirection);

        // Add holy light to the game world
        GameWorld gameWorld = GameWorld.getInstance();
        gameWorld.addGameElements(holyLight);
        gameWorld.addEnemyProjectile(holyLight);
    }
}
