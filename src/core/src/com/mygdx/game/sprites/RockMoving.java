package com.mygdx.game.sprites;


public class RockMoving extends Rock {

    private int velocity;

    public RockMoving(float x) {
        super(x);
        this.velocity = 0;
    }

    public int getVelocity(){
        return velocity;

    }

    public void setVelocity(int x){
        velocity = x;

    }

    public void move(){
        setRockPosY(velocity);
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
