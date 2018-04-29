package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Rectangle;
public class PlayerCharacter extends Character{

    public PlayerCharacter(float x, float y){
        super(100, 100, "paladin.png");
        maxHealth = 100;
        damage = 25;
        speed = 10;
    }


    public void moveUp(){
        position.y += (speed / 10);
    }

    public void moveDown(){
        position.y -= (speed / 10);
    }

    public void moveRight(){
        position.x += speed;
    }

    public void moveLeft(){
        position.x -= speed;
    }
    public boolean collides(Rectangle enemy){
        return enemy.overlaps(bounds);

    }
}
