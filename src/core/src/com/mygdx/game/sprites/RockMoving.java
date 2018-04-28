package com.mygdx.game.sprites;


import com.badlogic.gdx.math.Vector2;

public class RockMoving extends Rock {



    public RockMoving(float x, float y) {
        super(x,y);
        direction = new Vector2(0,0);
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
