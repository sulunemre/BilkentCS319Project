package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
public class Rock extends GameElement{
    public static final int TUBE_WIDTH = 52;


    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120;
  //  private Rectangle rock1Bounds;

    public Rock(float x, float y) {
        super(x, y, "rock.png");

        rand = new Random();
        position = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        bounds = new Rectangle(position.x, position.y, elementTexture.getWidth(), elementTexture.getHeight());

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
