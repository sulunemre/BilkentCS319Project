package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class PlayerCharacter extends Character{

//    private Texture playerCharacter;
    private static final int MOVEMENT = 20;
    private int acceleration;
    public boolean fightOrFlight;

    public PlayerCharacter(float x, float y){
        super(100, 100, 5,25);

        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        elementTexture = new Texture("paladin.png");
        acceleration = 0;
        bounds = new Rectangle(x,y,elementTexture.getWidth(), elementTexture.getHeight());
        fightOrFlight = false;
    }
    public PlayerCharacter(float x, float y, boolean wtf){
        super(100, 100, 5,25);

        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        elementTexture = new Texture("paladin.png");
        acceleration = 0;
        bounds = new Rectangle(x,y,elementTexture.getWidth(), elementTexture.getHeight());
        fightOrFlight = wtf;
    }
    @Override
    public void move(){} //TODO: implement
    @Override
    public void attack(int damage){}; //TODO: implement


    public void moveup(){
        velocity.y = 5;


    }
    public void movedown(){
        velocity.y = -5;

    }


    public void moveright(){
        velocity.x = 5;
        //position.add(velocity.x, velocity.y);

    }

    public void moveleft(){
        velocity.x = -5;
      //  position.add(velocity.x, velocity.y);

    }

    public void update(float dt){
        if(fightOrFlight == false) {
            velocity.add(0, 0);
            acceleration++;
            System.out.println("acceleration: " + acceleration);
            position.add(MOVEMENT * dt + (acceleration / 100), velocity.y);
            if (position.y < 0)
                position.y = 0;
            if (position.y > 260)
                position.y = 260;

            velocity.scl(1 / (dt * 100000));
            bounds.setPosition(position.x, position.y);
        }
        if(fightOrFlight == true) {
            velocity.add(0, 0);
            position.add(velocity.x, velocity.y);
            if (position.y < 0)
                position.y = 0;
            if (position.y > 260)
                position.y = 260;
            if (position.x < 0)
                position.x = 0;
            if (position.x > 500)
                position.x = 500;

            velocity.scl(1 / (dt * 100000));
            bounds.setPosition(position.x, position.y);
        }

    }

    public int getAcceleration(){
        return acceleration;
    }
}
