package com.mygdx.game.sprites;


import com.badlogic.gdx.math.Vector2;

public class RockMoving extends Rock {



    public RockMoving(float x) {
        super(x);
        velocity = new Vector2(0,0);
    }





    public void move(){
        setPosition(new Vector2(getPosition().x,getPosition().y + getVelocity().y));
    }

    /*public void update(float dt){
        setRockPosY(velocity);
        if(getRock1Pos().y < 0)
            setRockPosY(0);
        if(getRock1Pos().y > 250)
            setRockPosY(250);

        velocity.scl(1/(dt*100000));
        bounds.setPosition(position.x, position.y);
    }*/
}
