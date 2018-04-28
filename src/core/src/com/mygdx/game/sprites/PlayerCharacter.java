package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class PlayerCharacter extends Character{

    private static final int MOVEMENT = 20;
    private int acceleration;

    public PlayerCharacter(float x, float y){
        super(100, 100, "paladin.png");
        acceleration = 0;
        maxHealth = 100;
        damage = 25;
        speed = 2;
    }


    public void moveUp(){
        velocity.y = 5;
    }

    public void moveDown(){
        velocity.y = -5;
    }

    public void moveRight(){
        velocity.x = 5;
        //position.add(velocity.x, velocity.y);
    }

    public void moveLeft(){
        velocity.x = -5;
      //  position.add(velocity.x, velocity.y);

    }


    public void update(float dt){
        System.out.println("dt: "  + 1/dt);

            velocity.add(0, 0);
            acceleration++;
            position.add(MOVEMENT * dt + (acceleration / 100), velocity.y);
       // position.add(0, velocity.y);
            if (position.y < 0)
                position.y = 0;
            if (position.y > 260)
                position.y = 260;

            velocity.scl(1 / (dt * 100000));
            bounds.setPosition(position.x, position.y);

//        if(fightOrFlight == false) {
            velocity.add(0, 0);
            position.add(velocity.x, velocity.y);
            if (position.y < 0)
                position.y = 0;
            if (position.y > 260)
                position.y = 260;

            velocity.scl(1 / (dt * 100000));
            bounds.setPosition(position.x, position.y);
//        }
    }

    public int getAcceleration(){
        return acceleration;
    }
    public boolean collides(Rectangle enemy){
        return enemy.overlaps(bounds);

    }
}
