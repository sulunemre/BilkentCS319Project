package com.mygdx.game.sprites;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import java.util.Random;
public class Rock {
    public static final int TUBE_WIDTH = 52;
    private Texture rock1, rock2;
    private Vector2 rock1Pos;
    private Random rand;
    private static final int FLUCTUATION = 130;
    private static final int LOWEST_OPENING = 120;

    public Rock(float x) {
        rock1 = new Texture("paladin.jpg");
        rock2 = new Texture("paladin.jpg");
        rand = new Random();
        rock1Pos = new Vector2(x, rand.nextInt(FLUCTUATION) + LOWEST_OPENING);

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


    }
}
