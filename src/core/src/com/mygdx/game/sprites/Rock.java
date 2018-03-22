package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;
public class Rock {
    public static final int TUBE_WIDTH = 52;
    private Texture rock1, rock2;
    private Vector2 rock1Pos;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120;
    private Rectangle rock1Bounds;

    public Rock(float x) {
        rock1 = new Texture("rock.png");
        rock2 = new Texture("rock.png");
        rand = new Random();
        rock1Pos = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        rock1Bounds = new Rectangle(rock1Pos.x, rock1Pos.y, rock1.getWidth(), rock1.getHeight());

    }

    public Texture getRock1() {
        return rock1;
    }

    public Texture getRock2() {
        return rock2;
    }

    public Vector2 getRock1Pos() {
        return rock1Pos;
    }

    public void reposition(float x) {
        rock1Pos.set(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);
        rock1Bounds.setPosition(rock1Pos.x, rock1Pos.y);

    }
    public boolean collision(Rectangle player){
        return player.overlaps(rock1Bounds);

    }
}
