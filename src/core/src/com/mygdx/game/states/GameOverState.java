package com.mygdx.game.states;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.stateControllers.GameOverStateController;

import java.io.IOException;

public class GameOverState extends State {
    public GameOverState() {
        controller = new GameOverStateController(this);
        try {
            gameManager.saveHighScore();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void render(SpriteBatch ab) {
        //ab.setProjectionMatrix(controller.getCam().combined);
        menuBatch.begin();
        menuBatch.draw(background, 0, 0);
        BitmapFont scoreText = new BitmapFont();
        controller.getCam().position.set(new Vector2(MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2), 0);
        scoreText.draw(menuBatch, "Game over "
                + gameManager.getPlayerName()
                + "\nYour score: " + gameManager.getScore()
                + "\n\nPress ENTER to continue", 350, 300);
        menuBatch.end();
    }

    @Override
    public void dispose() {

    }
}
