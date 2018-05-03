package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.GameWorld;
import com.mygdx.game.stateControllers.GameOverStateController;

public class GameOverState extends State {
    public GameOverState() {
        controller = new GameOverStateController(this);
    }

    @Override
    public void render(SpriteBatch ab) {
        //ab.setProjectionMatrix(controller.getCam().combined);
        ab.begin();
        ab.flush();
        BitmapFont scoreText = new BitmapFont();
        controller.getCam().position.set(new Vector2(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2), 0);
        scoreText.draw(ab, "Dear " + gameManager.getPlayerName() + "\nYour score: " + gameManager.getScore(), controller.getCam().position.x, controller.getCam().position.y);
        ab.end();

    }

    @Override
    public void dispose() {

    }
}
