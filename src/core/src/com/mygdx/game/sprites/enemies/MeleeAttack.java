package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.PlayerCharacter;

public class MeleeAttack implements AttackStrategy {
    @Override
    public void attack(Enemy attacker, PlayerCharacter victim, int damage) {
        Vector2 circleCenterCoordinates;

        circleCenterCoordinates = new Vector2(attacker.getPosition());

        int meleeRange = attacker.getWidth() * 2;
        Circle meleeRangeBounds = new Circle(circleCenterCoordinates, meleeRange);

        if (Intersector.overlaps(meleeRangeBounds,victim.getBounds())) {
            victim.reduceHealth(damage);
            attacker.resetCooldown();

            if(attacker.getDirection().x < 0)
                attacker.setElementTexture(attacker.leftProfileAttack);
            else
                attacker.setElementTexture(attacker.rightProfileAttack);
        }
    }
}
