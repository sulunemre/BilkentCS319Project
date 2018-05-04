package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.game.states.MenuState;
import com.mygdx.game.states.State;

public class GameOverStateController extends AbstractStateController {
    public GameOverStateController(State state) {
        super(state);
    }

    @Override
    public void handleInput() {
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
            controlledState.dispose();
            gameStateManager.set(new MenuState());

        }
    }

    @Override
    public void update(float dt) {
        handleInput();

    }
}
