package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
public class Rock extends GameElement{
    public static final int TUBE_WIDTH = 52;


    private Random rand;
    private static final int FLUCTUATION = 580;
    private static final int LOWEST_OPENING = 10;
  //  private Rectangle rock1Bounds;

    public Rock(float x, float y) {
        super(x, y, "rock.png");

        rand = new Random();
        position = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        bounds = new Rectangle(position.x, position.y, elementTexture.getWidth(), elementTexture.getHeight());
        direction = new Vector2(0,0);
        speed = 0;
    }

    public Rock(float x, float y, int speed) {
        super(x, y, "rock.png");

        rand = new Random();
        position = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        bounds = new Rectangle(position.x, position.y, elementTexture.getWidth(), elementTexture.getHeight());
        this.speed = speed;
        direction = new Vector2(0,1);
    }

    public void update(float dt){
        Vector2 velocity = direction.scl((float) speed); // Transform unit vector to velocity
        position.mulAdd(velocity, dt*10); // Add velocity to position

        bounds.setPosition(position); // Update frame bounds accordingly
        direction.nor(); // Transform direction vector to unit vector

        // Check bounds
        if (position.y <= 0){
            direction.y = 1;
            position.y = 0;
        }
        if (position.y >= 500) { //TODO: sayılar düzenlenecek
            position.y = 500;
            direction.y = -1;
        }
    }



    public void reposition(float x) {
        position.set(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        bounds.setPosition(position.x, position.y);
    }



    public void setRockBound(){
        bounds = new Rectangle(position.x, position.y, elementTexture.getWidth(), elementTexture.getHeight());
    }

    public boolean collision(Rectangle player){
        return player.overlaps(bounds);

    }
}
