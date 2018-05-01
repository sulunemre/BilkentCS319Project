package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.GameManager;

public class OptionsStateController extends AbstractStateController {
    private GameManager gameManager;

    public OptionsStateController() {
        gameManager = GameManager.getInstance();
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
        if (gameManager.getCount() % 2 == 1) {
            gameManager.setFirstClicked(true);
            //gameManager.setSecondClicked(false);
            gameManager.getCurrentMusic().pause();
        } else {
            gameManager.setSecondClicked(true);
            //gameManager.setFirstClicked(false);
            gameManager.getCurrentMusic().play();
        }
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClearColor(0, 0, 1, 1);
    }
}