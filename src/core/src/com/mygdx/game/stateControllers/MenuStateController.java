package com.mygdx.game.stateControllers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.states.State;

public class MenuStateController extends AbstractStateController {

    public MenuStateController(State state) {
        super(state);
        if(!gameManager.isFirstClicked())
        {
            gameManager.setMusic("stormwind.mp3");
            gameManager.getCurrentMusic().setLooping(true);
            gameManager.getCurrentMusic().play();
            gameManager.setSecondClicked(true);
        }

    }

    @Override
    public void handleInput() {}

    @Override
    public void update(float dt) {
        handleInput();
        Gdx.gl.glClearColor(1, 0, 0, 1);
        if(!gameManager.isFirstClicked() )
        {
            gameManager.getCurrentMusic().setLooping(true);
            gameManager.getCurrentMusic().play();
        }



        Gdx.gl.glClearColor(1,0,0,1);
    }
}
