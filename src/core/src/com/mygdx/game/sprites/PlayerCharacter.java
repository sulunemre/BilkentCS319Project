package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.sprites.enemies.Enemy;

public class PlayerCharacter extends Character{

    public PlayerCharacter(float x, float y){
        super(100, 100, "paladin.png",100);
        damage = 25;
        speed = 10;
    }

    public int getHealth()
    {
        return maxHealth;
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
