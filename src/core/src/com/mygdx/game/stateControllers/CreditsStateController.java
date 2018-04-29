package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.states.GameStateManager;
import com.mygdx.game.states.MenuState;

public class CreditsStateController extends AbstractStateController {

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            gameStateManager.set(new MenuState());
        }

    }
    @Override
    public void update(float dt) {
        handleInput();
    }
}
