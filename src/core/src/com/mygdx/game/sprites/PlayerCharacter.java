package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
public class PlayerCharacter {

    private Vector2 position;
    private Vector2 velocity;
    private Texture playerCharacter;
    private static final int MOVEMENT = 20;
    private int acc;
    private Rectangle bounds;
    public boolean fightOrFlight;
    public PlayerCharacter(float x, float y){

        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        playerCharacter = new Texture("paladin.png");
        acc= 0;
        bounds = new Rectangle(x,y,playerCharacter.getWidth(), playerCharacter.getHeight());
        fightOrFlight = false;
    }
    public PlayerCharacter(float x, float y, boolean wtf){

        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        playerCharacter = new Texture("paladin.png");
        acc= 0;
        bounds = new Rectangle(x,y,playerCharacter.getWidth(), playerCharacter.getHeight());
        fightOrFlight = wtf;
    }


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
            acc++;
            System.out.println("acc: " + acc);
            position.add(MOVEMENT * dt + (acc / 100), velocity.y);
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
    public Rectangle getBounds(){
        return bounds;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getPlayerCharacter() {
        return playerCharacter;
    }
    public void changeMode(){
        if(fightOrFlight == false){
            fightOrFlight = true;
        }
        else{
            fightOrFlight = false;
        }
    }
    public int getAcc(){
        return acc;
    }
}
