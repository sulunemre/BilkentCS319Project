package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.states.State;

public class HelpStateController extends AbstractStateController {
    public HelpStateController(State state) {
        super(state);
        if (gameManager.getCount() % 2 == 1) {
            gameManager.getCurrentMusic().pause();
        }
        else {
            gameManager.getCurrentMusic().play();
        }

    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 0);
        Gdx.gl.glClearColor(0,0,1,1);
        if (gameManager.getCount() % 2 == 1) {
            gameManager.getCurrentMusic().pause();
        }
        else {
            gameManager.getCurrentMusic().play();
        }
    }
}
