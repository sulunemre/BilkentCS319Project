package com.mygdx.game.sprites.enemies;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.sprites.PlayerCharacter;

public class Abomination extends Enemy {
    Abomination(float x, float y){
        super(x, y, 300, "abomination.png", new MixedAttack(), 175);
        speed = 1;
        leftProfile = new Texture("abominationReverse.png");
        rightProfile = new Texture("abomination.png");
    }

    public void hook(PlayerCharacter playerCharacter){
        Vector2 circleCenterCoordinates = new Vector2(position);
        Circle hookBounds = new Circle(circleCenterCoordinates, 500);
        if(Intersector.overlaps(hookBounds,playerCharacter.getBounds())) {
            float xDifference = position.x - playerCharacter.getPosition().x;
            float yDifference = position.y - playerCharacter.getPosition().y;
            Vector2 distance = new Vector2(xDifference, yDifference);
            playerCharacter.setDirection(xDifference / distance.len(), yDifference / distance.len());
        }
    }
}
