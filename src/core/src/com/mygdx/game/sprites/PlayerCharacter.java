package com.mygdx.game.sprites;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.graphics.Texture;
public class PlayerCharacter {

    private Vector2 position;
    private Vector2 velocity;
    private Texture playerCharacter;

    public PlayerCharacter(int x, int y){

        position = new Vector2(x, y);
        velocity = new Vector2(0,0);
        playerCharacter = new Texture("paladin.jpg");
    }

    public void moveup(){
        velocity.y = 5;

    }
    public void movedown(){
        velocity.y = -5;

    }

    public void update(float dt){
        velocity.add(0,0);
        position.add(0, velocity.y);

        velocity.scl(1/(dt*100000));
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getPlayerCharacter() {
        return playerCharacter;
    }
}
