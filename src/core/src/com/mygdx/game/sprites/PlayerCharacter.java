package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Rectangle;
public class PlayerCharacter extends Character{

    private static final int MOVEMENT = 20;
    private int acceleration;

    public PlayerCharacter(float x, float y){
        super(100, 100, "paladin.png");
        acceleration = 0;
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

  //  @Override
   // public void update(float dt){
//
//        acceleration++; //TODO: controller'a taşınmalı
//        position.add(MOVEMENT * dt + (acceleration / 100), direction.y);
//
//        if (position.y < 0)
//            position.y = 0;
//        if (position.y > 260)
//            position.y = 260;
//
//        direction.scl(1 / (dt * 100000));
//
//        bounds.setPosition(position);
  //  }

    public int getAcceleration(){
        return acceleration;
    }
    public boolean collides(Rectangle enemy){
        return enemy.overlaps(bounds);

    }
}
