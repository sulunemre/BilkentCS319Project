package com.mygdx.game.sprites;


import com.badlogic.gdx.math.Vector2;

public class RockMoving extends Rock {



    public RockMoving(float x, float y) {
        super(x,y);
        direction = new Vector2(0,1);
    }

    public void update(float dt){
        Vector2 velocity = direction.scl((float) speed); // Transform unit vector to velocity
        position.mulAdd(velocity, dt*10); // Add velocity to position

        bounds.setPosition(position); // Update frame bounds accordingly
        direction.nor(); // Transform direction vector to unit vector

        // Check bounds
        if (position.y <= 0){

        }
            position.y = 0;
        if (position.y >= 400) //TODO: sayılar düzenlenecek
            position.y = 400;
    }



    public void move(){
        setPosition(new Vector2(getPosition().x,getPosition().y + getDirection().y));
    }

    /*public void update(float dt){
        setRockPosY(direction);
        if(getRock1Pos().y < 0)
            setRockPosY(0);
        if(getRock1Pos().y > 250)
            setRockPosY(250);

        direction.scl(1/(dt*100000));
        bounds.setPosition(position.x, position.y);
    }*/
}
